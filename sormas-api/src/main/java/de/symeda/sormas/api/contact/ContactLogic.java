/*******************************************************************************
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2018 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.symeda.sormas.api.contact;

import java.util.Date;

public final class ContactLogic {

	public static final int ALLOWED_CONTACT_DATE_OFFSET = 30;

	private ContactLogic() {
		// Hide Utility Class Constructor
	}

	public static Date getStartDate(Date lastContactDate, Date reportDate) {
		return lastContactDate != null ? lastContactDate : reportDate;
	}

	public static Date getEndDate(Date lastContactDate, Date reportDate, Date followUpUntil) {
		return followUpUntil != null ? followUpUntil : lastContactDate != null ? lastContactDate : reportDate;
	}
}
