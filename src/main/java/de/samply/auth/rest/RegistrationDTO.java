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

package de.samply.auth.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The registration confirmation. The key ID can be used to request a code to sign in order
 * to authenticate with your private key.
 *
 */
@XmlRootElement
public class RegistrationDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Your user ID
     */
    private int userId;

    /**
     * Your key ID
     */
    private int keyId;

    /**
     * {@link #userId}
     *
     * @return a int.
     */
    @XmlElement(name = "user_id")
    public int getUserId() {
        return userId;
    }

    /**
     * {@link #userId}
     *
     * @param userId a int.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * {@link #keyId}
     *
     * @return a int.
     */
    @XmlElement(name = "key_id")
    public int getKeyId() {
        return keyId;
    }

    /**
     * {@link #keyId}
     *
     * @param keyId a int.
     */
    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

}
