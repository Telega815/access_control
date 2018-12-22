package ru.access_control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.access_control.dao.interfaces.AttendanceDAO;
import ru.access_control.dao.interfaces.EmployeesDAO;
import ru.access_control.entities.Attendance;
import ru.access_control.entities.Employee;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

@Service
public class CheckPoint {

    private Logger logger = LoggerFactory.getLogger(CheckPoint.class);
    private Scanner scanner;

    private final EmployeesDAO employeesDAO;
    private final AttendanceDAO attendanceDAO;

    @Autowired
    public CheckPoint(EmployeesDAO employeesDAO, AttendanceDAO attendanceDAO) {
        scanner = new Scanner(System.in);
        this.employeesDAO = employeesDAO;
        this.attendanceDAO = attendanceDAO;
    }

    public void run(){
        while (true){
            String input = scanner.next();

            int key;
            try {
                key = Integer.valueOf(input);
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                logger.info("Unexpected input: " + input + " Time: " + new Time(System.currentTimeMillis()).toLocalTime().toString());
                continue;
            }

            Employee employee = employeesDAO.getEmployeeByKey(key);

            if (employee == null){
                logger.info("Unknown key: " + input + " Time: " + new Time(System.currentTimeMillis()).toLocalTime().toString());
                continue;
            }

            Attendance attendance = new Attendance();
            attendance.seteId(employee.getId());
            Calendar calendar = new GregorianCalendar();
            attendance.setTime(new Time(calendar.getTimeInMillis()));

            attendance.setaYear(calendar.get(Calendar.YEAR));
            attendance.setMonth(calendar.get(Calendar.MONTH));
            attendance.setDay(calendar.get(Calendar.DAY_OF_MONTH));

            logger.info("Written: " + employee.getSurname()+ " " + employee.getName() + " from dep:" + employee.getDepartment().getDepName() + " Time:" + attendance.getTime().toLocalTime().toString());
            attendanceDAO.saveAttendance(attendance);
        }
    }
}
