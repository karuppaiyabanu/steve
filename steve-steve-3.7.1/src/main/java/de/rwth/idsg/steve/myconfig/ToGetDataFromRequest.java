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

import ocpp.cs._2015._10.MeterValue;
import ocpp.cs._2015._10.SampledValue;

import java.util.List;


public class ToGetDataFromRequest {


    public static Customise buildCustomiseTable(List<MeterValue> list, int connectorPk, Integer transactionId) {
        System.out.println("buildCustomiseTable method working ...");
        Customise customise = new Customise();
        customise.setConnectorPk(connectorPk);
        customise.setTransactionPk(transactionId);

        for (MeterValue meterValue : list) {
            for (SampledValue sampledValue : meterValue.getSampledValue()) {
                String measurand = sampledValue.isSetMeasurand() ? sampledValue.getMeasurand().value() : null;
                String valueStr = sampledValue.getValue();
                System.out.println("Print "+measurand);
                if (measurand != null && valueStr != null) {
                    try {
                        double value = Double.parseDouble(valueStr);

                        switch (measurand) {
                            case "Voltage":
                                customise.setVoltage(value);
                                break;
                            case "Power.Active.Import":
                                customise.setPower(value);
                                break;
                            case "Energy.Active.Import.Register":
                                customise.setEnergy(value);
                                break;
                            case "SoC":
                                customise.setSoc(value);
                                break;
                            case "Power.Offered":
                                customise.setCurrent(value);
                            default:

                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid numeric value: " + valueStr);
                    }
                }
            }
        }

        return customise;
    }

}
