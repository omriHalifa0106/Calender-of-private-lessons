package Calender_GUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class WeekCalendarTest {
    public static void main(String[] args) {
        JFrame frm = new JFrame();

        ArrayList<CalendarEvent> events = new ArrayList<>();

        events.add(new CalendarEvent(LocalDate.of(2022, 12, 20), LocalTime.of(15, 0), LocalTime.of(15, 30), "Test 11/11 14:00-14:20"));

        WeekCalendar cal = new WeekCalendar(events);
        cal.addEvent(new CalendarEvent(LocalDate.of(2022, 12, 25), LocalTime.of(15, 0), LocalTime.of(15, 30), "Test 11/11 14:00-14:20"));
        JButton goToTodayBtn = new JButton("היום");
        goToTodayBtn.addActionListener(e -> cal.goToToday());

        JButton nextWeekBtn = new JButton(">");
        nextWeekBtn.addActionListener(e -> cal.nextWeek());

        JButton prevWeekBtn = new JButton("<");
        prevWeekBtn.addActionListener(e -> cal.prevWeek());

        JButton nextMonthBtn = new JButton(">>");
        nextMonthBtn.addActionListener(e -> cal.nextMonth());

        JButton prevMonthBtn = new JButton("<<");
        prevMonthBtn.addActionListener(e -> cal.prevMonth());

        JPanel weekControls = new JPanel();
        weekControls.add(prevMonthBtn);
        weekControls.add(prevWeekBtn);
        weekControls.add(goToTodayBtn);
        weekControls.add(nextWeekBtn);
        weekControls.add(nextMonthBtn);

        frm.add(weekControls, BorderLayout.NORTH);

        frm.add(cal, BorderLayout.CENTER);
        frm.setSize(1000, 900);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}