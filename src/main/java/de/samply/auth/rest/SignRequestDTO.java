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
 * Use this class if you want to authenticate using your private key.
 * Sign the code using the algorithm {@link #algorithm}.
 *
 */
@XmlRootElement
public class SignRequestDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * The code that you should sign using your private key.
     */
    private String code;

    /**
     * The expiration date of this sign request. After this date, this sign request is invalid.
     */
    private long expirationDate;

    /**
     * The algorithm that you need to used to sign the code.
     */
    private String algorithm;

    /**
     * {@link #code}
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCode() {
        return code;
    }

    /**
     * {@link #code}
     *
     * @param code a {@link java.lang.String} object.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * {@link #expirationDate}
     *
     * @return a long.
     */
    @XmlElement(name = "exp")
    public long getExpirationDate() {
        return expirationDate;
    }

    /**
     * {@link #expirationDate}
     *
     * @param expirationDate a long.
     */
    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * {@link #algorithm}
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * {@link #algorithm}
     *
     * @param algorithm a {@link java.lang.String} object.
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

}
