package ua.in.dris4ecoder.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.in.dris4ecoder.jdbc.controllers.EmployeeController;

/**
 * Created by Alex Korneyko on 26.07.2016 15:11.
 */
public class Main {

    private EmployeeController employeeController;

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = context.getBean(Main.class);
        main.start();
    }

    private void start() {
        employeeController.getAllEmployees().forEach(System.out::println);
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }
}
