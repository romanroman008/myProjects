package month;

import device.Device;

import java.math.BigDecimal;
import java.util.List;

public class Month {

    private Integer id;
    private MonthEnum name;
    private BigDecimal powerCost;
    private List<Device> listOfDevices;
    private BigDecimal totalPowerUsage;
    private BigDecimal totalPowerCost;



    public Month(Integer id, MonthEnum name, BigDecimal powerCost, BigDecimal totalPowerUsage, BigDecimal totalPowerCost, List<Device> list) {
        this.id = id;
        this.name = name;
        this.powerCost = powerCost;
        this.listOfDevices=list;
        this.totalPowerUsage = totalPowerUsage;
        this.totalPowerCost = totalPowerCost;

    }
    public Month(MonthEnum name, BigDecimal powerCost) {
        this.name = name;
        this.powerCost = powerCost;
    }

    public Month(MonthEnum name, BigDecimal powerCost, BigDecimal totalPowerUsage, BigDecimal totalPowerCost,List<Device> list) {
        this.name = name;
        this.powerCost = powerCost;
        this.totalPowerUsage = totalPowerUsage;
        this.totalPowerCost = totalPowerCost;
        this.listOfDevices=list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MonthEnum getName() {
        return name;
    }

    public void setName(MonthEnum name) {
        this.name = name;
    }

    public BigDecimal getPowerCost() {
        return powerCost;
    }

    public void setPowerCost(BigDecimal powerCost) {
        this.powerCost = powerCost;
    }

    public BigDecimal getTotalPowerUsage() {
        return totalPowerUsage;
    }

    public void setTotalPowerUsage(BigDecimal totalPowerUsage) {
        this.totalPowerUsage = totalPowerUsage;
    }

    public BigDecimal getTotalPowerCost() {
        return totalPowerCost;
    }

    public void setTotalPowerCost(BigDecimal totalPowerCost) {
        this.totalPowerCost = totalPowerCost;
    }

    public List<Device> getListOfDevices() {
        return listOfDevices;
    }

    public void setListOfDevices(List<Device> listOfDevices) {
        this.listOfDevices = listOfDevices;
    }

    @Override
    public String toString() {
        return name.getDesc()+ ": Koszt energii elektrycznej: "+ powerCost + " zł .Łączny koszt: "+ totalPowerCost +" zł. Łączne zużycie: "+ totalPowerUsage+ " kWh";
    }
}