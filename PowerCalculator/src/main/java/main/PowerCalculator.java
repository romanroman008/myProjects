package main;

import device.Device;
import month.MonthEnum;

import java.math.BigDecimal;
import java.util.List;

public class PowerCalculator {

    public static BigDecimal getEnergyUsedOfDevice(Device d){
        return d.getUsageTime().multiply(d.getPower());

    }
    public static BigDecimal getTotalEnergy(List<Device> d, MonthEnum month){
        BigDecimal toReturn=BigDecimal.ZERO;
        for (Device device : d) {
            toReturn=toReturn.add(getEnergyUsedOfDevice(device));
        }
        toReturn=toReturn.multiply(BigDecimal.valueOf(month.getDays()));
        return toReturn;
    }
    public static BigDecimal getTotalCost(List<Device> d,MonthEnum month,BigDecimal powerCost){
        BigDecimal toReturn=getTotalEnergy(d,month);
        toReturn=toReturn.multiply(powerCost);
        return toReturn;
    }
}