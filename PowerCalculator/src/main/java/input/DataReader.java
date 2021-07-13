package input;

import device.Device;
import month.Month;
import month.MonthEnum;
import output.ConsolePrinter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    private Scanner scanner =new Scanner(System.in);
    private ConsolePrinter printer=new ConsolePrinter();

    private double getDouble(){
        boolean optionOk=false;
        double positive=-1;
        while(optionOk==false) {
            try {
                positive = scanner.nextDouble();
                if (positive >= 0)
                    optionOk = true;
                else
                    printer.printLine("Wprowadzono ujemną liczbę.");
            } catch (Exception e) {
                printer.printLine("Wprowadzono nieprawidłową wartość, spróbuj ponownie.");
            }finally{
                scanner.nextLine();

            }
        }
        return positive;
    }
    private double getTime(){
        boolean optionOk=false;
        double positive=-1;
        while(optionOk==false) {
            try {
                positive = scanner.nextDouble();
                if (positive >= 0) {
                    if(positive<=24)
                        optionOk = true;
                    else
                        printer.printLine("Doba ma 24 godziny.");
                } else
                    printer.printLine("Wprowadzono ujemną liczbę.");
            } catch (Exception e) {
                printer.printLine("Wprowadzono nieprawidłową wartość, spróbuj ponownie.");
            }finally{
                scanner.nextLine();
            }
        }
        return positive;
    }
    public int getInt() {
        boolean optionOk=false;
        int option = -1;
        while(optionOk==false) {
            try {
                option = scanner.nextInt();
                optionOk = true;
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzono nieprawidłową wartość, spróbuj ponownie.");
            } finally {
                scanner.nextLine();
            }
        }
        return option;
    }
    public List<Device> createDevices(){
        int option;
        List<Device> listOfDevices=new ArrayList<>();
        printer.printLine("Dodaj urządzenia używane w tym miesiącu.");
        listOfDevices.add(readAndCreateDevice());
        do{
            printer.printDeviceOptions();
            option=getInt();
            switch(option){
                case 0:
                    listOfDevices.add(readAndCreateDevice());
                    break;
                case 1:
                    editDevice(listOfDevices);
                    break;
                case 2:
                    deleteDevice(listOfDevices);
                    break;
                case 3:
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji");
            }
        }while(option!=3);
        return listOfDevices;
    }

    private void editDevice(List<Device> listOfDevices) {
        printer.printLine("Wybierz urządzenie które chcesz edytować");
        printer.printAllDevices(listOfDevices);
        printer.printLine(listOfDevices.size() + " - Cofnij");
        boolean optionOk=false;
        int option;
        while(optionOk==false) {
            option=getInt();
            if(option<listOfDevices.size()&&option>=0) {
                listOfDevices.remove(option);
                listOfDevices.add(readAndCreateDevice());
                optionOk=true;
            }
            if(option==listOfDevices.size()){
                optionOk=true;
            }
            else
                printer.printLine("Nie ma takiej opcji");
        }
    }

    private void deleteDevice(List<Device> listOfDevices) {
        printer.printLine("Wybierz urządzenie które chcesz usunąć");
        printer.printAllDevices(listOfDevices);
        printer.printLine(listOfDevices.size()+ " - Cofnij");
        boolean optionOk=false;
        int option;
        while(optionOk==false) {
            option=getInt();
            if(option<listOfDevices.size()&&option>=0) {
                listOfDevices.remove(option);
                optionOk=true;
            }
            if(option==listOfDevices.size()){
                optionOk=true;
            }
            else
                printer.printLine("Nie ma takiej opcji");
        }
    }

    public Device readAndCreateDevice(){
        printer.printLine("Podaj nazwę urządzenia: ");
        String name= scanner.nextLine();
        printer.printLine("Podaj moc urządzenia w kWh: ");
        BigDecimal power=BigDecimal.valueOf(getDouble());
        printer.printLine("Podaj czas pracy dziennej w godzinach: ");
        BigDecimal time=BigDecimal.valueOf(getTime());

        return new Device(name,power,time);
    }
    public Month readAndCreateMonth(){
        MonthEnum name=getMonthName();
        printer.printLine("Podaj cenę prądu w tym miesiącu w złotówkach: ");
        double prize = getDouble();
        MonthEnum[] values = MonthEnum.values();

        return new Month(name,BigDecimal.valueOf(prize));
    }
    private MonthEnum getMonthName(){
        boolean trueName=false;
        while(trueName==false) {
            printer.printLine("Podaj nazwę miesiąca");
            String name= scanner.nextLine();
            for (MonthEnum value : MonthEnum.values()) {
                if(name.equalsIgnoreCase((value.getDesc()))||name.equalsIgnoreCase(value.name())){
                    trueName = true;
                    return value;
                }
            }
                printer.printLine("Nieprawidłowy miesiąc");
        }
        return null;
    }

    public void close() {
        scanner.close();
    }
}
