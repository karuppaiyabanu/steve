/*
 * SteVe - SteckdosenVerwaltung - https://github.com/steve-community/steve
 * Copyright (C) 2013-2025 SteVe Community Team
 * All Rights Reserved.
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
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.rwth.idsg.steve.myconfig;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static jooq.steve.db.tables.Customise.CUSTOMISE;

@Service
public class CustomiseTableService {

    private final DSLContext dslContext;

    @Autowired
    public CustomiseTableService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void insert(Customise customise) {
        try {


            dslContext.insertInto(CUSTOMISE)
                    .set(CUSTOMISE.TRANSACTION_PK, customise.getTransactionPk())
                    .set(CUSTOMISE.CONNECTOR_PK, customise.getConnectorPk())
                    .set(CUSTOMISE.VOLTAGE, customise.getVoltage())
                    .set(CUSTOMISE.POWER, customise.getPower())
                    .set(CUSTOMISE.ENERGY, customise.getEnergy())
                    .set(CUSTOMISE.SOC, customise.getSoc())
                    .set(CUSTOMISE.CURRENT, customise.getCurrent())
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
