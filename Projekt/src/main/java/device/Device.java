package device;

import java.math.BigDecimal;

public class Device {
    private Integer id;
    private String name;
    private BigDecimal power;
    private BigDecimal usageTime;

    public Device(String name, BigDecimal power, BigDecimal usageTime) {
        this.name = name;
        this.power = power;
        this.usageTime=usageTime;
    }

    public Device(Integer id, String name, BigDecimal power,BigDecimal usageTime) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.usageTime=usageTime;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public BigDecimal getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(BigDecimal usageTime) {
        this.usageTime = usageTime;
    }

    @Override
    public String toString() {
        return "Nazwa urządzenia: "
                 + name +
                " Moc: " + power +
                " Czas użytkowania: " + usageTime;
    }
}