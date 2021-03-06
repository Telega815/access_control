package ru.access_control.dao.impl;

import ru.access_control.dao.interfaces.DepartmentsDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.access_control.entities.Department;

@Component
public class DepartmentsDAOImpl implements DepartmentsDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public DepartmentsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public Department getDepartment(int id) {
        return sessionFactory.getCurrentSession().get(Department.class, id);
    }

    @Transactional
    public Department getDepartment(String depName) {
        return (Department) sessionFactory.getCurrentSession()
                .createQuery("from Department where depName = :depName")
                .setString("depName", depName).list().get(0);
    }

    @Transactional
    public int saveDepartment(Department department) {
        return (Integer) sessionFactory.getCurrentSession().save(department);
    }

    @Transactional
    public void deleteDepartment(Department department) {
        sessionFactory.getCurrentSession().delete(department);
    }

    @Transactional
    public void updateDepartment(Department department) {
        sessionFactory.getCurrentSession().update(department);
    }
}
