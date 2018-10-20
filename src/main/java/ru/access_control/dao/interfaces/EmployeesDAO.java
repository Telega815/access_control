package ru.access_control.dao.interfaces;

import ru.access_control.entities.Department;
import ru.access_control.entities.Employee;

import java.util.List;

public interface EmployeesDAO {
    Employee getEmployee(int id);

    Employee getEmployeeByKey(int key);

    List<Employee> getEmployees(String surname);
    List<Employee> getEmployees(String name, String surname);
    List<Employee> getEmployees(String name, String surname, String patronymic);

    List<Employee> getEmployees(Department department);

    int saveEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    void updateEmployee(Employee employee);
}
