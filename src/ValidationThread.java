import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class ValidationThread extends Thread {
    private boolean flag;
    private Lesson lesson_to_check;

    public ValidationThread(Lesson lesson_to_check,boolean flag)
    {
         super("checkLessonThread");
         this.lesson_to_check = lesson_to_check;
         this.flag = false;
    }

    public void run () {
        JFrame frm = new JFrame();
        boolean flag_valid = true;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2023, 6, 1);

        LocalTime startTime = LocalTime.parse(lesson_to_check.getStartHour_client());
        LocalTime endTime = LocalTime.of(Integer.parseInt(lesson_to_check.getStartHour_client().split(":")[0]) + lesson_to_check.getNumberHours_client(), Integer.parseInt(lesson_to_check.getStartHour_client().split(":")[1]));

        if(this.lesson_to_check.getDate().getDayOfWeek() == DayOfWeek.FRIDAY || this.lesson_to_check.getDate().getDayOfWeek() == DayOfWeek.SATURDAY )
        {
            JOptionPane.showMessageDialog(CalendarLessons.getInstance().getP(),"לא מקבל שיעורים בשישי-שבת.", "לא ניתן לקבוע שיעור", JOptionPane.ERROR_MESSAGE);
            System.out.println("Does not accept lessons on Friday-Saturday! ");
            flag_valid = false;
        }

        if (!(this.lesson_to_check.getDate().equals(startDate) || (this.lesson_to_check.getDate().isAfter(startDate)) && this.lesson_to_check.getDate().isBefore(endDate))) {
            JOptionPane.showMessageDialog(frm,"לא מקבל שיעורים אחרי התאריך גיוס.", "לא ניתן לקבוע שיעור", JOptionPane.ERROR_MESSAGE);
            System.out.println("Does not accept lessons after 1/6/2023 ! ");
            flag_valid = false;
        }

        if ((this.lesson_to_check.getDate().equals(startDate)))
        {
            if(LocalTime.parse(lesson_to_check.getStartHour_client()).isBefore(LocalTime.now()))
            {
                JOptionPane.showMessageDialog(CalendarLessons.getInstance().getP(),"שעת קביעת השיעור עברה.", "לא ניתן לקבוע שיעור", JOptionPane.ERROR_MESSAGE);
                flag_valid = false;
            }
        }


        if ((this.lesson_to_check.getDate().equals(startDate) || (this.lesson_to_check.getDate().isAfter(startDate)) && this.lesson_to_check.getDate().isBefore(endDate))) {

            for (Lesson lesson : CalendarLessons.getInstance().getLessons()) {
                LocalTime endLessonTime = LocalTime.of(Integer.parseInt(lesson.getStartHour_client().split(":")[0]) + lesson.getNumberHours_client(), Integer.parseInt(lesson.getStartHour_client().split(":")[1]));

                if(this.lesson_to_check.getDate().equals(lesson.getDate())) {

                    if(startTime.isAfter(LocalTime.parse(lesson.getStartHour_client())))
                    {
                        if (startTime.isBefore(endLessonTime))
                        {
                            JOptionPane.showMessageDialog(CalendarLessons.getInstance().getP(),"קיים כבר שיעור בזמן שרצית לקבוע את השיעור.", "לא ניתן לקבוע שיעור", JOptionPane.ERROR_MESSAGE);
                            flag_valid = false;
                        }
                    }

                    if(endTime.isAfter(LocalTime.parse(lesson.getStartHour_client())))
                    {
                        if (endTime.isBefore(endLessonTime))
                        {
                            JOptionPane.showMessageDialog(CalendarLessons.getInstance().getP(),"קיים כבר שיעור בזמן שרצית לקבוע את השיעור.", "לא ניתן לקבוע שיעור", JOptionPane.ERROR_MESSAGE);
                            flag_valid = false;
                        }
                    }

                    if (startTime.equals(LocalTime.parse(lesson.getStartHour_client()))) {
                        JOptionPane.showMessageDialog(CalendarLessons.getInstance().getP(),"קיים כבר שיעור בזמן שרצית לקבוע את השיעור.", "לא ניתן לקבוע שיעור", JOptionPane.ERROR_MESSAGE);
                        flag_valid = false;
                        break;
                    }
                }


            }
        }

        if (flag_valid == false)
        {
            System.out.println("the lesson already exist or the details are not correct! ");
        }
        else
        {
            System.out.println("the lesson can be in lesson array list!");
            JOptionPane.showMessageDialog(CalendarLessons.getInstance().getP(),"השיעור נקבע בהצלחה!", "קביעת שיעור", JOptionPane.INFORMATION_MESSAGE);
            flag = true;
        }
    }

    public boolean isFlag() {
        return flag;
    }
}
