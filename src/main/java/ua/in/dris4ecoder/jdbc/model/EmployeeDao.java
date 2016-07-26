package ua.in.dris4ecoder.jdbc.model;

import java.util.List;

/**
 * Created by Alex Korneyko on 26.07.2016 21:55.
 */
public interface EmployeeDao {

    Employee load(int id);

    List<Employee> getAll();
}
