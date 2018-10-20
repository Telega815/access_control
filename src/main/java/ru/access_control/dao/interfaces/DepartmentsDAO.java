package ru.access_control.dao.interfaces;

import ru.access_control.entities.Department;

public interface DepartmentsDAO {
    Department getDepartment(int id);

    Department getDepartment(String depName);

    int saveDepartment(Department department);
    void deleteDepartment(Department department);
    void updateDepartment(Department department);
}
