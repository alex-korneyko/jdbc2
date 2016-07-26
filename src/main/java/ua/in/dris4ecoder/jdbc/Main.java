package ua.in.dris4ecoder.jdbc;

import ua.in.dris4ecoder.jdbc.model.jdbc.JdbcEmployeeDao;
import ua.in.dris4ecoder.jdbc.model.EmployeeDao;

/**
 * Created by Alex Korneyko on 26.07.2016 15:11.
 */
public class Main {

    public static void main(String[] args) {

        EmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();
        System.out.println("All employees:");
        jdbcEmployeeDao.getAll().forEach(System.out::println);

        System.out.println("Employee with id = 4");
        System.out.println(jdbcEmployeeDao.load(4));
    }

}
