package output;

import month.Month;

import java.sql.*;

public class MonthSql {
    private final Connection connection;

    public MonthSql(){
        try{
            this.connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/devices?serverTimezone=UTC", "root", "admin");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Month month){
        final String sql=String.format("INSERT INTO months (name,power_cost_zł,total_usage_kWh,total_cost_zł) VALUES(?,?,?,?)",
                month.getName(),month.getTotalPowerUsage(),month.getTotalPowerCost());
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,month.getName().getDesc());
            statement.setBigDecimal(2,month.getPowerCost());
            statement.setBigDecimal(3,month.getTotalPowerUsage());
            statement.setBigDecimal(4,month.getTotalPowerCost());
            statement.executeUpdate();
            ResultSet generatedKeys=statement.getGeneratedKeys();
            if(generatedKeys.next()){
                month.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void close(){
        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}