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

package de.samply.auth.client.jwt;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.samply.auth.client.jwt.JWTVocabulary.TokenType;
import de.samply.auth.rest.LocationDTO;
import de.samply.auth.rest.RoleDTO;
import de.samply.auth.rest.Usertype;
import de.samply.common.config.OAuth2Client;
import net.minidev.json.JSONObject;

/**
 * The client side JWT OpenID Token. Checks the signature and validity of a serialized OpenID ID Token JWT.
 * Contains for example the users real name, his email address, his address, and other fields.
 *
 */
public class JWTIDToken extends AbstractJWT {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * The client ID used to get this ID token. It is used to verify the audience.
     */
    private final String clientId;

    /**
     * The list of locations that the user belongs to.
     */
    private List<LocationDTO> locations = new ArrayList<>();

    /**
     * The list of roles that the user is a member of
     */
    private List<RoleDTO> roles = new ArrayList<>();

    /**
     * The map for clientIDs and their permissions
     */
    private Map<String, List<String>> permissions = new HashMap<>();

    /**
     * {@link de.samply.auth.client.jwt.AbstractJWT#AbstractJWT(OAuth2Client, String)}
     *
     * @param config the OAuth2 client side configuration. The public key is needed to check the signature.
     * @param serialized the serialized JWT
     * @throws de.samply.auth.client.jwt.JWTException if any error occurs during deserialization or signature verification
     */
    public JWTIDToken(OAuth2Client config, String serialized) throws JWTException {
        this(config.getClientId(), KeyLoader.loadKey(config.getHostPublicKey()), serialized);
    }

    /**
     * {@link de.samply.auth.client.jwt.AbstractJWT#AbstractJWT(PublicKey, String)}
     *
     * @param clientId Your client ID
     * @param publicKey The IdP's public key
     * @param serialized the serialized ID token
     * @throws JWTException
     */
    public JWTIDToken(String clientId, PublicKey publicKey, String serialized) throws JWTException {
        super(publicKey, serialized);
        this.clientId = clientId;

        Object locationsClaim = getClaimsSet().getClaim(JWTVocabulary.LOCATIONS);

        if(locationsClaim instanceof List<?>) {
            List<?> list = (List<?>) locationsClaim;

            for(Object o : list) {
                if(o instanceof JSONObject) {
                    JSONObject json = (JSONObject) o;
                    LocationDTO location = new LocationDTO();
                    location.setContact((String) json.get(JWTVocabulary.LOCATION_CONTACT));
                    location.setDescription((String) json.get(JWTVocabulary.LOCATION_DESCRIPTION));
                    location.setId((String) json.get(JWTVocabulary.LOCATION_IDENTIFIER));
                    locations.add(location);
                }
            }
        }

        Object rolesClaim = getClaimsSet().getClaim(JWTVocabulary.ROLES);

        if(rolesClaim instanceof List<?>) {
            List<?> list = (List<?>) rolesClaim;

            for(Object o : list) {
                if(o instanceof JSONObject) {
                    JSONObject json = (JSONObject) o;
                    RoleDTO role = new RoleDTO();
                    role.setIdentifier((String) json.get(JWTVocabulary.ROLE_IDENTIFIER));
                    role.setDescription((String) json.get(JWTVocabulary.ROLE_DESCRIPTION));
                    role.setName((String) json.get(JWTVocabulary.ROLE_NAME));
                    roles.add(role);
                }
            }
        }

        Object permissionsClaim = getClaimsSet().getClaim(JWTVocabulary.PERMISSIONS);
        if(permissionsClaim instanceof Map<?, ?>) {
            Map<String , List<String>> map = (Map<String, List<String>>) permissionsClaim;
            for(String key: map.keySet()) {
                permissions.put(key, map.get(key));
            }
        }
    }

    /**
     * Returns the users real email address
     *
     * @return the users real email address
     */
    public String getEmail() {
        return (String) getClaimsSet().getClaim(JWTVocabulary.EMAIL);
    }

    /**
     * Returns the real name, or an empty string if this information is not available.
     *
     * @return the users real name
     */
    public String getName() {
        String name = (String) getClaimsSet().getClaim(JWTVocabulary.NAME);
        if (name != null && !name.equals("")) {
            return name;
        } else {
            return (String) getClaimsSet().getClaim("preferred_username");
        }
    }

    /**
     * Returns the language the user selected in Samply.Auth. Always "en" at this point.
     *
     * @return the language the user selected in the identity provider
     */
    public String getLanguage() {
        return (String) getClaimsSet().getClaim(JWTVocabulary.LANG);
    }

    /**
     * Returns the usertype, one of "NORMAL", "REGISTRY", "BRIDGEHEAD".
     * @return
     */
    public Usertype getUsertype() {
        try {
            return Usertype.valueOf((String) getClaimsSet().getClaim(JWTVocabulary.USERTYPE));
        } catch(Exception e) {
            return Usertype.NORMAL;
        }
    }

    /**
     * Returns the label of the external identity provider, might be null.
     * @return
     */
    public String getExternalLabel() {
        return (String) getClaimsSet().getClaim(JWTVocabulary.EXTERNAL_LABEL);
    }

    /**
     * Returns a list of roles
     * @return
     */
    public List<RoleDTO> getRoles() {
        return roles;
    }

    /**
     * Returns the locations that this user belongs to.
     * @return
     */
    public List<LocationDTO> getLocations() {
        return locations;
    }

    @Override
    protected String getTokenType() {
        return TokenType.ID_TOKEN;
    }

    /**
     * Returns the map of clientIDs and their permissions
     * @return
     */

    public Map<String, List<String>> getPermissions() {
        return permissions;
    }
}
