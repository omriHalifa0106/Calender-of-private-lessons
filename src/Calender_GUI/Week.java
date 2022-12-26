package Calender_GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Week {

    private ArrayList<LocalDate> days;

    // Gets week variables from any date (can be within week)
    public Week(LocalDate date) {
        days = new ArrayList<>();
        LocalDate sunday = getStartOfWeek(date);
        days.add(sunday);
        for (int i = 1; i < 7; i++) {
            days.add(sunday.plusDays(i));
        }
    }

    public static LocalDate getStartOfWeek(LocalDate date) {
        LocalDate day = date;
        while (day.getDayOfWeek() != DayOfWeek.SUNDAY) {
            day = day.minusDays(1);
        }
        return day;
    }

    public LocalDate getDay(DayOfWeek dayOfWeek) {
        // DayOfWeek enum starts with monday == 1
        return days.get(dayOfWeek.getValue() % 7 );
    }

    public Week nextWeek() {
        final LocalDate friday = getDay(DayOfWeek.SUNDAY);
        return new Week(friday.plusDays(7));
    }

    public Week prevWeek() {
        final LocalDate monday = getDay(DayOfWeek.SATURDAY);
        return new Week(monday.minusDays(7));
    }

    public String toString() {
        return "Week of the " + getDay(DayOfWeek.SUNDAY);
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        Week currentWeek = new Week(now);
        System.out.println(currentWeek);
        System.out.println(currentWeek.prevWeek());
        System.out.println(currentWeek.nextWeek());
    }

}