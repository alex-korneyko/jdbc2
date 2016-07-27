package ua.in.dris4ecoder.jdbc.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import ua.in.dris4ecoder.jdbc.model.Employee;

import java.util.List;

/**
 * Created by Alex Korneyko on 27.07.2016 17:24.
 */
public class EmployeeController {

    private PlatformTransactionManager txManager;

    public List<Employee> getAllEmployees() {

        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED));

    }

}
