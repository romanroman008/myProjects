package output;

import device.Device;
import month.Month;

import java.math.BigDecimal;
import java.sql.*;

public class DeviceSql {
    private final Connection connection;
    public DeviceSql(){
        try{
            this.connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/devices?serverTimezone=UTC", "root", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void close(){
        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDevice(Device device, Month month, BigDecimal totalUsage, BigDecimal totalCost){
        final String sql=String.format("INSERT INTO devices_table (name,power,power_usage_kWh,power_cost_zł,months_id) VALUES(?,?,?,?,?)",
                device.getName(),device.getPower());
        try(PreparedStatement statement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, device.getName());
            statement.setBigDecimal(2,device.getPower());
            statement.setBigDecimal(3,totalUsage);
            statement.setBigDecimal(4,totalCost);
            statement.setInt(5,month.getId());
            statement.executeUpdate();
            ResultSet generatedKeys=statement.getGeneratedKeys();
            if(generatedKeys.next()){
                device.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}