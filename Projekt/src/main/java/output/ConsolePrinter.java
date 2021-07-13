package output;

import device.Device;

import java.util.List;

public class ConsolePrinter {

    public void printLine(String text){
        System.out.println(text);
    }


    public void printOptions(){
        printLine("Wybierz opcje: ");
        printLine("0 - Wyjdź z programu");
        printLine("1 - Policz wydatki w miesiącu");

    }

    public void printDeviceOptions(){
        printLine("Wybierz opcje: ");
        printLine("0 - Dodaj kolejne urządzenie");
        printLine("1 - Edytuj urządzenie");
        printLine("2 - Usuń urządzenie");
        printLine("3 - Przejdź dalej");
    }

    public void printAllDevices(List<Device> list){
        int i=0;
        for (Device device : list) {
            System.out.println(i+" - "+ device);
            i++;
        }
    }

}