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

/** A scope is a permission that will be included in the access token. */
public enum Scope {
  OPENID("openid"),
  MDR("mdr"),
  LOGIN("login"),
  FORMREPOSITORY("formrepository");

  private final String identifier;

  private Scope(String identifier) {
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return identifier;
  }
}
