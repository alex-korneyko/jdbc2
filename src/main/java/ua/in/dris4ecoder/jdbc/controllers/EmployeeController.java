package ua.in.dris4ecoder.jdbc.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import ua.in.dris4ecoder.jdbc.model.Employee;
import ua.in.dris4ecoder.jdbc.model.EmployeeDao;

import java.util.List;

/**
 * Created by Alex Korneyko on 27.07.2016 17:24.
 */
public class EmployeeController {

    private PlatformTransactionManager txManager;
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {

        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED));
        try {
            List<Employee> result = employeeDao.findAll();
            txManager.commit(status);
            return result;
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee getEmployeeById(int id) {
        return employeeDao.load(id);
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
