/***********************************************************************
 * This file is part of iDempiere ERP Open Source                      *
 * http://www.idempiere.org                                            *
 *                                                                     *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Carlos Ruiz - globalqss - bxservice                               *
 **********************************************************************/

/**
 *
 * @author Carlos Ruiz - globalqss - bxservice
 *
 */
package com.trekglobal.idempiere.rest.api.process;

import java.util.ArrayList;

import org.compiere.model.MProcessPara;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

import com.trekglobal.idempiere.rest.api.model.MRefreshToken;

@org.adempiere.base.annotation.Process
public class ExpireRefreshTokens extends SvrProcess {

	/* All Tokens */
	private Boolean p_REST_AllTokens = null;
	/* User/Contact */
	private int p_AD_User_ID = 0;

	@Override
	protected void prepare() {
		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			switch (name) {
			case "REST_AllTokens":
				p_REST_AllTokens = para.getParameterAsBoolean();
				break;
			case "AD_User_ID":
				p_AD_User_ID = para.getParameterAsInt();
				break;
			default:
				MProcessPara.validateUnknownParameter(getProcessInfo().getAD_Process_ID(), para);
			}
		}
	}

	@Override
	protected String doIt() throws Exception {
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuilder where = new StringBuilder("");

		if (p_REST_AllTokens && getAD_Client_ID() > 0) {
			where.append("AD_Client_ID=?");
			params.add(getAD_Client_ID());
		}

		if (p_AD_User_ID > 0) {
			if (where.length() > 0)
				where.append(" AND ");
			where.append("CreatedBy=?");
			params.add(p_AD_User_ID);
		}

		int cnt = MRefreshToken.expireTokens(where.toString(), MRefreshToken.REST_REVOKECAUSE_ManualExpire, params);

		return "@Updated@ " + cnt;
	}

}
