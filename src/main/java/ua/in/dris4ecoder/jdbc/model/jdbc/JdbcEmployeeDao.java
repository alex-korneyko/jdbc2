package ua.in.dris4ecoder.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.in.dris4ecoder.jdbc.Main;
import ua.in.dris4ecoder.jdbc.model.Employee;
import ua.in.dris4ecoder.jdbc.model.EmployeeDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeDao implements EmployeeDao {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Override
    public Employee load(int id) {
        LOGGER.info("Connection to DB");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?")) {
            LOGGER.info("Successfully connected to DB\n");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else {
                LOGGER.error("ID: " + id + " not found");
                throw new RuntimeException("ID not found");

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();

        LOGGER.info("Connection to DB");

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            LOGGER.info("Successfully connected to DB\n");

            String sql = "SELECT * FROM EMPLOYEE";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);

                result.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB " , e);
            throw new RuntimeException(e);
        }

        return result;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setAge(resultSet.getInt("age"));
        employee.setAddress(resultSet.getString("address"));
        employee.setSalary(resultSet.getInt("salary"));
        employee.setJoin_date(resultSet.getDate("join_date"));
        return employee;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
