package ru.access_control.dao.interfaces;

import ru.access_control.entities.Attendance;
import ru.access_control.entities.Employee;

import java.util.List;

public interface AttendanceDAO {
    Attendance getAttendance(long id);

    List<Attendance> getAttendance(Employee employee);

    long saveAttendance(Attendance attendance);
    void deleteAttendance(Attendance attendance);
    void updateAttendance(Attendance attendance);
}
