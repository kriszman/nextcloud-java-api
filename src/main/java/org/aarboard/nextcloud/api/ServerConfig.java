/* 
 * Copyright (C) 2017 a.schild
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.aarboard.nextcloud.api;

/**
 *
 * @author a.schild
 */
public class ServerConfig {
    
    private String userName;
    private String password;
    private String serverName;
    private String subPathPrefix;
    private boolean useHTTPS;
    private int port;
    private boolean trustAllCertificates;
    private boolean useSystemHttpClient;

    /**
     * Use this constructor if your nextcloud instance is installed in the 
     * root of the webhosting, like https://nextcloud.company.my/
     * 
     * @param serverName    ip or dns name of server
     * @param useHTTPS      Use https or http to connect
     * @param port          Port, usuallay 443 for https and 80 for http
     * @param userName      User name for authentication
     * @param password      Password for authentication
     */
    public ServerConfig(String serverName, 
            boolean useHTTPS, 
            int port, 
            String userName, 
            String password) {
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
        this.subPathPrefix = null;
        this.useHTTPS = useHTTPS;
        this.port = port;
        this.trustAllCertificates = false;
        this.useSystemHttpClient = false;
    }
    
    /**
     * Is this constructor if your nextcloud is installed in a subfolder of the server
     * like https://nextcloud.company.my/<b>nextcloud/</b>
     * 
     * @param serverName    ip or dns name of server
     * @param useHTTPS      Use https or http to connect
     * @param port          Port, usuallay 443 for https and 80 for http
     * @param subPathPrefix Path to your nextcloud instance, without starting and trailing /
     *                      can be null if installed in root
     * @param userName      User name for authentication
     * @param password      Password for authentication
     */
    public ServerConfig(
            String serverName, 
            boolean useHTTPS, 
            int port, 
            String subPathPrefix,
            String userName, 
            String password) {
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
        this.subPathPrefix = subPathPrefix;
        this.useHTTPS = useHTTPS;
        this.port = port;
        this.trustAllCertificates = false;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

	/**
	 * @param serverName
	 *            the serverName to set, defaults to <code>null</code>
	 */
	public void setServerName(String serverName){
		this.serverName = serverName;
	}
	
	/**
         * @deprecated Use getSubPathPrefix() instead
	 * @return the configured subpath prefix
	 */
	public String getSubpathPrefix(){
		return getSubPathPrefix();
	}

	/**
	 * @return the configured sub path prefix
	 */
	public String getSubPathPrefix(){
		return subPathPrefix;
	}
        
	/**
         * @deprecated Use setSubPathPrefix() instead
	 * @param subpathPrefix to apply
	 */
	public void setSubpathPrefix(String subpathPrefix){
            setSubPathPrefix(subpathPrefix);
	}

	/**
	 * @param subPathPrefix to apply
	 */
	public void setSubPathPrefix(String subPathPrefix){
		this.subPathPrefix = subPathPrefix;
	}
        
    /**
     * @return the useHTTPS
     */
    public boolean isUseHTTPS() {
        return useHTTPS;
    }

    /**
     * @param useHTTPS the useHTTPS to set
     */
    public void setUseHTTPS(boolean useHTTPS) {
        this.useHTTPS = useHTTPS;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }
    
	/**
	 * @param trustAllCertificates if the client should accept any 
         *          HTTPScertificate (e.g. to work against a self-signed
         * 	    certificate)
	 */
	public void setTrustAllCertificates(boolean trustAllCertificates){
		this.trustAllCertificates = trustAllCertificates;
	}
	
	/**
	 * @return if the client should accept any HTTPS certificate (e.g. to work against a self-signed
	 *         certificate)
	 */
	public boolean isTrustAllCertificates(){
		return trustAllCertificates;
	}

    /**
     *
     * @param useSystemHttpClient meaning the underlying http client shall be configured to use system properties
     *      * in order to be able to setup certain aspects of the client (e.g http.maxConnections) by means of startup parameters
     */
    public void setUseSystemHttpClient(boolean useSystemHttpClient) {
        this.useSystemHttpClient = useSystemHttpClient;
    }

    /**
     *
     * @return if the underlying http client should be configured to use system properties
     * in order to be able to setup certain aspects of the client (e.g http.maxConnections) by means of startup parameters
     */
    public boolean isUseSystemHttpClient() {
        return useSystemHttpClient;
    }
}
