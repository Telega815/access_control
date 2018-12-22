package ru.access_control.dao.impl;

import ru.access_control.dao.interfaces.AttendanceDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.access_control.entities.Attendance;
import ru.access_control.entities.Employee;

import java.util.List;

@Component
public class AttendanceDAOImpl implements AttendanceDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public AttendanceDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Attendance getAttendance(long id) {
        return sessionFactory.getCurrentSession().get(Attendance.class, id);
    }

    @Transactional
    public List<Attendance> getAttendance(Employee employee) {
        return sessionFactory.getCurrentSession().createQuery("from Attendance where eId = :employee").setParameter("employee", employee.getId()).list();
    }

    @Transactional
    public long saveAttendance(Attendance attendance) {
        return (Long)sessionFactory.getCurrentSession().save(attendance);
    }

    @Transactional
    public void deleteAttendance(Attendance attendance) {
        sessionFactory.getCurrentSession().delete(attendance);
    }

    @Transactional
    public void updateAttendance(Attendance attendance) {
        sessionFactory.getCurrentSession().update(attendance);
    }
}
