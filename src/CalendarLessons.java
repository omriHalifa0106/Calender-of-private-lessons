import Calender_GUI.CalendarEvent;
import Calender_GUI.WeekCalendar;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
/*
I created this class because
I wanted to have a class that would be common for all the classes that use the lessons
and the lessonInGui and for it to be the same throughout the project
 */

// singleton Manager
public class CalendarLessons {
    private static  CalendarLessons sInstance;
    private ArrayList<Lesson> lessons;
    private ArrayList<CalendarEvent> lessonInGui;
    private WeekCalendar calenderGui;
    private int countClient;

    private JPanel p;

    // private constructor to limit new instance creation
    private CalendarLessons() {
        lessons = new ArrayList<Lesson>();
        lessonInGui = new ArrayList<CalendarEvent>();
        calenderGui = new WeekCalendar(lessonInGui);
        p = new JPanel();
        countClient = 0;
    }

    public static CalendarLessons getInstance() {
        if (sInstance == null) { //If no instance of the class exists
            sInstance = new CalendarLessons();
        }
        return sInstance;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public int getCountClient() {
        return countClient;
    }

    public ArrayList<CalendarEvent> getLessonInGui() {
        return lessonInGui;
    }

    public WeekCalendar getCalenderGui() {
        return calenderGui;
    }

    public JPanel getP() {
        return p;
    }

    public void addLesson(Lesson lessonToAdd)
    {
        this.lessons.add(lessonToAdd);
        String detailsToCalender = lessonToAdd.getName_client()  + "," + lessonToAdd.getClass_client() + ","+ lessonToAdd.getSubject_client();
        CalendarEvent private_lesson_to_gui = new CalendarEvent(lessonToAdd.getDate(), LocalTime.of(Integer.parseInt(lessonToAdd.getStartHour_client().split(":")[0]),Integer.parseInt(lessonToAdd.getStartHour_client().split(":")[1])),LocalTime.of(Integer.parseInt(lessonToAdd.getStartHour_client().split(":")[0]) + lessonToAdd.getNumberHours_client(),Integer.parseInt(lessonToAdd.getStartHour_client().split(":")[1])),detailsToCalender);
        this.calenderGui.addEvent(private_lesson_to_gui);
    }
}
