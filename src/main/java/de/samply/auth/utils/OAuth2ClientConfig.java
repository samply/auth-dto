/*
 * Copyright (C) 2015 Working Group on Joint Research, University Medical Center Mainz
 * Copyright (C) since 2016 The Samply Community
 *
 * <p>This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU Affero General Public License along with this
 * program; if not, see http://www.gnu.org/licenses.
 *
 * <p>Additional permission under GNU GPL version 3 section 7:
 *
 * <p>If you modify this Program, or any covered work, by linking or combining it with Jersey
 * (https://jersey.java.net) (or a modified version of that library), containing parts covered by
 * the terms of the General Public License, version 2.0, the licensors of this Program grant you
 * additional permission to convey the resulting work.
 */

package de.samply.auth.utils;

import de.samply.auth.rest.Scope;
import de.samply.common.config.OAuth2Client;
import de.samply.common.config.OAuth2Client.AdditionalHostnames.Hostname;
import de.samply.string.util.StringUtil;
import de.samply.string.util.StringUtil.Builder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * A small helper used to generate URLs to the identity provider.
 *
 */
public class OAuth2ClientConfig {

    public static String getHost(OAuth2Client config, String serverName) {
        String host = config.getHost();

        /**
         * There might be an additional hostname that should be used. Check the servername for
         * equality and change the variable host accordingly.
         */
        if(config.getAdditionalHostnames() != null) {
            for(Hostname hostname : config.getAdditionalHostnames().getHostname()) {
                if(serverName.toLowerCase().equals(hostname.getIfServernameEquals())) {
                    host = hostname.getHost();
                }
            }
        }

        return host;
    }

    /**
     * Constructs the URL to the OAuth2 provider, using the provided configuration.
     * The redirect is set to "http(s)?://$serverName(:$port)?/$contextPath/$redirectUrl"
     *
     * @param config the OAuth2 configuration
     * @param scheme the scheme (http|https) from the HTTP request
     * @param serverName the server name from the HTTP request
     * @param port the port (80, 443, 8080 ...) from the HTTP request
     * @param contextPath the context path for the application ("/", "/mdr-gui") from the HTTP request
     * @param redirectUrl the redirect URL inside the context path
     * @param scopes a list of scopes separated by a whitespace
     * @return the URL
     * @throws UnsupportedEncodingException
     */
    public static String getRedirectUrl(OAuth2Client config, String scheme,
            String serverName, int port, String contextPath, String redirectUrl,
            Scope... scopes) throws UnsupportedEncodingException {
        return getRedirectUrl(config, scheme, serverName, port, contextPath, redirectUrl, null, null, scopes);
    }

    /**
     * Constructs the URL to the OAuth2 provider, using the provided configuration.
     * The redirect is set to "http(s)?://$serverName(:$port)?/$contextPath/$redirectUrl"
     *
     * @param config the OAuth2 configuration
     * @param scheme the scheme (http|https) from the HTTP request
     * @param serverName the server name from the HTTP request
     * @param port the port (80, 443, 8080 ...) from the HTTP request
     * @param contextPath the context path for the application ("/", "/mdr-gui") from the HTTP request
     * @param redirectUrl the redirect URL inside the context path
     * @param ip the identifier for the identity provider you want to use. Use null if you don't want to use a specific identity provider
     * @param scopes a list of scopes separated by a whitespace
     * @return the URL
     * @throws UnsupportedEncodingException
     */
    public static String getRedirectUrl(OAuth2Client config, String scheme,
            String serverName, int port, String contextPath, String redirectUrl,
            String ip, Scope... scopes) throws UnsupportedEncodingException {
        return getRedirectUrl(config, scheme, serverName, port, contextPath, redirectUrl, ip, null, scopes);
    }

    /**
     * Constructs the URL to the OAuth2 provider, using the provided configuration.
     * The redirect is set to "http(s)?://$serverName(:$port)?/$contextPath/$redirectUrl"
     *
     * @param config the OAuth2 configuration
     * @param scheme the scheme (http|https) from the HTTP request
     * @param serverName the server name from the HTTP request
     * @param port the port (80, 443, 8080 ...) from the HTTP request
     * @param contextPath the context path for the application ("/", "/mdr-gui") from the HTTP request
     * @param redirectUrl the redirect URL inside the context path
     * @param ip the identifier for the identity provider you want to use. Use null if you don't want to use a specific identity provider
     * @param state the state parameter used to protect against cross site requests. Use null if you don't want to use a state parameter.
     * @param scopes a list of scopes separated by a whitespace
     * @return the URL
     * @throws UnsupportedEncodingException
     */
    public static String getRedirectUrl(OAuth2Client config, String scheme,
            String serverName, int port, String contextPath, String redirectUrl,
            String ip, String state, Scope... scopes) throws UnsupportedEncodingException {
        String redirectUri = getLocalRedirectUrl(config, scheme, serverName, port, contextPath, redirectUrl);

        String host = getHost(config, serverName);

        StringBuilder builder = new StringBuilder(host);
        if (config.isUseSamplyAuth()) {
            builder.append("/grant.xhtml?");
        } else {
            // if not samply auth is used, take the endpoint prefix from the config
            builder.append(getEndpointPrefix(config.getRealm()));
            builder.append("auth");
            builder.append("?response_type=code&");
        }
        builder.append("client_id=").append(URLEncoder.encode(config.getClientId(), StandardCharsets.UTF_8.displayName()));
        builder.append("&scope=").append(URLEncoder.encode(StringUtil.join(scopes, " ", new Builder<Scope>() {
                @Override
                public String build(Scope o) {
                    return o.getIdentifier();
                }
            }), StandardCharsets.UTF_8.displayName()));
        builder.append("&redirect_uri=").append(URLEncoder.encode(redirectUri, StandardCharsets.UTF_8.displayName()));

        if(!StringUtil.isEmpty(ip)) {
            builder.append("&ip=").append(URLEncoder.encode(ip, StandardCharsets.UTF_8.displayName()));
        } else if(!StringUtil.isEmpty(config.getExternalIP())) {
            builder.append("&ip=").append(URLEncoder.encode(config.getExternalIP(), StandardCharsets.UTF_8.displayName()));
        }
        // required for samply auth, keycloak does not work with this, must be available in the post request
        if(!StringUtil.isEmpty(state)) {
            builder.append("&state=").append(URLEncoder.encode(state, StandardCharsets.UTF_8.displayName()));
        }

        return builder.toString();
    }

    /**
     * Creates a URL for the current host.
     *
     * @param config the OAuth2 Configuration
     * @param scheme the scheme (http|https)
     * @param serverName the server name
     * @param port the port (80, 443, 8080 ...)
     * @param contextPath the context path for the application ("/", "/mdr-gui")
     * @param redirectUrl the redirect URL inside the context path.
     * @return a {@link java.lang.String} object.
     */
    public static String getLocalRedirectUrl(OAuth2Client config, String scheme,
            String serverName, int port, String contextPath, String redirectUrl) {
        return getLocalRedirectUrl(scheme, serverName, port, contextPath, redirectUrl);
    }

    public static String getLocalRedirectUrl(String scheme,
            String serverName, int port, String contextPath, String redirectUrl) {
        String strPort = (port == 80 || port == 443 ? "" : ":" + port);
        StringBuilder builder = new StringBuilder(scheme);
        builder.append("://").append(serverName).append(strPort)
            .append(contextPath).append(redirectUrl);
        return builder.toString();
    }

    /**
     * Returns the logout URL for Samply Auth
     *
     * @param config the OAuth2 Configuration
     * @param scheme the scheme (http|https)
     * @param serverName the server name
     * @param port the port (80, 443, 8080 ...)
     * @param contextPath the context path for the application ("/", "/mdr-gui")
     * @param localRedirectURL the redirect URL inside the context path.
     * @return a {@link java.lang.String} object.
     * @throws UnsupportedEncodingException
     */
    public static String getLogoutUrl(OAuth2Client config, String scheme,
            String serverName, int port, String contextPath, String localRedirectURL) throws UnsupportedEncodingException {

        String redirect = getLocalRedirectUrl(config, scheme,
                serverName, port,
                contextPath, localRedirectURL);

        String host = getHost(config, serverName);

        StringBuilder builder = new StringBuilder(host);
        if (config.isUseSamplyAuth()) {
            builder.append("/logout.xhtml?redirect_uri=").append(URLEncoder.encode(redirect, StandardCharsets.UTF_8.displayName()))
                .append("&client_id=").append(URLEncoder.encode(config.getClientId(), StandardCharsets.UTF_8.displayName()));
        } else {
            builder.append(getEndpointPrefix(config.getRealm()));
            builder.append("logout?redirect_uri=");
            builder.append(URLEncoder.encode(redirect, StandardCharsets.UTF_8.displayName()));
        }
        return builder.toString();
    }

    /**
     * Returns the Endpoint Prefix with the realm of the given config.
     * Has a leading and a trailing slash included.
     * Endpoint prefix: /realms/{realm}/protocol/openid-connect/
     * @param realm  the OAuth2 realm
     * @return
     */
    public static String getEndpointPrefix(String realm) {
        return "/realms/" + realm + "/protocol/openid-connect/";
    }

}
