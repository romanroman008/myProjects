package main;

import device.Device;
import output.DeviceSql;
import output.ConsolePrinter;
import input.DataReader;
import month.Month;
import output.MonthSql;

import java.math.BigDecimal;
import java.util.List;

public class MainController {

    ConsolePrinter printer = new ConsolePrinter();
    DataReader reader = new DataReader();
    MonthSql monthSql =new MonthSql();
    DeviceSql deviceSql =new DeviceSql();

    public void mainLoop(){
        int option;
        do{
            printer.printOptions();
            option= reader.getInt();

            switch(option){
                case 0:
                    exit();
                    printer.printLine("Koniec programu.");
                    break;
                case 1:
                    saveMonths();
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji.");
            }
        }while(option!=0);
    }

    private void saveMonths(){
       Month month = reader.readAndCreateMonth();
        List<Device> deviceList=reader.createDevices();
        BigDecimal totalUsage = PowerCalculator.getTotalEnergy(deviceList, month.getName());
        BigDecimal totalCost = PowerCalculator.getTotalCost(deviceList, month.getName(),month.getPowerCost());
        month.setTotalPowerCost(totalCost);
        month.setTotalPowerUsage(totalUsage);
        month.setListOfDevices(deviceList);
        monthSql.save(month);
        saveDevices(month);
        System.out.println(month);

    }
    private void saveDevices(Month month){
        List<Device> list = month.getListOfDevices();
        for (int i = 0; i < list.size(); i++) {
            BigDecimal totalUsage= PowerCalculator.getEnergyUsedOfDevice(list.get(i)).multiply(BigDecimal.valueOf(month.getName().getDays()));
            BigDecimal totalCost=totalUsage.multiply(month.getPowerCost());
            deviceSql.saveDevice(list.get(i),month,totalUsage,totalCost);
        }
    }
    private void exit(){
        reader.close();
        monthSql.close();
    }

}
