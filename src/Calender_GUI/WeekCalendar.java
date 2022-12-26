package Calender_GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class WeekCalendar extends Calendar {

    private Week week;

    public WeekCalendar(ArrayList<CalendarEvent> events) {
        super(events);
        week = new Week(LocalDate.now());
    }

    @Override
    protected boolean dateInRange(LocalDate date) {
        return Week.getStartOfWeek(date).equals(week.getDay(DayOfWeek.SUNDAY));
    }

    @Override
    protected LocalDate getDateFromDay(DayOfWeek day) {
        return week.getDay(day);
    }

    protected int numDaysToShow() {
        return 5;
    }

    @Override
    protected DayOfWeek getStartDay() {
        return DayOfWeek.SUNDAY;
    }

    @Override
    protected DayOfWeek getEndDay() {
        return DayOfWeek.SATURDAY;
    }

    @Override
    protected void setRangeToToday() {
        week = new Week(LocalDate.now());
    }

    @Override
    protected double dayToPixel(DayOfWeek dayOfWeek) {
        return TIME_COL_WIDTH + getDayWidth() * (dayOfWeek.getValue() % 7 );
    }



    public void nextWeek() {
        week = week.nextWeek();
        repaint();
    }

    public void prevWeek() {
        week = week.prevWeek();
        repaint();
    }

    public void nextMonth() {
        week = new Week(week.getDay(DayOfWeek.SUNDAY).plusWeeks(4));
        repaint();
    }

    public void prevMonth() {
        week = new Week(week.getDay(DayOfWeek.SUNDAY).minusWeeks(4));
        repaint();
    }

}