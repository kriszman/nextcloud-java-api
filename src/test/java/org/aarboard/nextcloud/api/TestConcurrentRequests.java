package org.aarboard.nextcloud.api;

import org.aarboard.nextcloud.api.provisioning.User;
import org.aarboard.nextcloud.api.provisioning.UserXMLAnswer;
import org.aarboard.nextcloud.api.utils.NextcloudResponseHelper;
import org.aarboard.nextcloud.api.utils.XMLAnswer;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConcurrentRequests extends ATestClass {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("http.maxConnections", "10");
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
        if (_nc != null) {
            _nc.useSystemHttpClient(false);
        }
    }

    @Test
    public void t01_testCreateMultipleUsersConcurrently() {
        System.out.println("createMultipleUsersConcurrently");
        if (_nc != null) {
            boolean success = testUserManagementConcurrently(user -> _nc.createUserAsync(user, "aBcDeFg123456"))
                    .stream()
                    .allMatch(NextcloudResponseHelper::isStatusCodeOkay);
            Assert.assertTrue(success);
        }
    }

    @Test
    public void t02_testGetMultipleUsersConcurrently() {
        System.out.println("getMultipleUsersConcurrently");
        if (_nc != null) {
            List<UserXMLAnswer> result = testUserManagementConcurrently(user -> _nc.getUserAsync(user));
            boolean success = result.stream().allMatch(NextcloudResponseHelper::isStatusCodeOkay);
            Assert.assertTrue(success);
            result.stream().forEach(userXMLAnswer -> {
                User user = userXMLAnswer.getUser();
                Assert.assertNotNull(user);
                Assert.assertNotNull(user.getId());
                Assert.assertTrue(user.getId().startsWith(TESTUSER));
            });
        }
    }

    @Test
    public void t03_testDeleteMultipleUsersConcurrently() {
        System.out.println("deleteMultipleUsersConcurrently");
        if (_nc != null) {
            boolean success = testUserManagementConcurrently(user -> _nc.deleteUserAsync(user))
                    .stream()
                    .allMatch(NextcloudResponseHelper::isStatusCodeOkay);
            Assert.assertTrue(success);
        }
    }

    private <R extends XMLAnswer> List<R> testUserManagementConcurrently(Function<String, CompletableFuture<R>> userFunction) {
        List<String> users = IntStream.range(0, 50).boxed()
                .map(integer -> TESTUSER + integer)
                .collect(Collectors.toList());

        List<R> result = users.stream()
                .map(user -> userFunction.apply(user))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        return result;
    }
}
