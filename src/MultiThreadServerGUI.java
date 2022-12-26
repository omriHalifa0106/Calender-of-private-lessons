import Calender_GUI.CalendarEvent;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MultiThreadServerGUI extends JFrame {

    private JTextArea jta = new JTextArea();
    private Server server = new Server();

    public static void main(String[] args) {
        new MultiThreadServerGUI();
    }


    public MultiThreadServerGUI() {
        CalenderGuiFrame();

        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);
        setTitle("עומרי חליפה - שיעורים פרטיים");
        jta.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jta.append("השרת התחיל לקבל שיעורים פרטיים ב: " + new Date() + '\n');
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        int clientNo = 1;

        while (true) {
            // Listen for a new connection request
            Socket socket = server.Accept();

            // Display the client number
            jta.append("תלמיד מספר " + clientNo  + " התחבר ב: " +  new Date() + '\n');


            // Create a new task for the connection
            Thread task = new Thread(new HandleClient(socket));
            task.start();
            clientNo++;
        }
    }

    //This function creates the calendar gui.
    public void CalenderGuiFrame()
    {
        JFrame frm = new JFrame();
        JButton goToTodayBtn = new JButton("היום");
        goToTodayBtn.addActionListener(e -> CalendarLessons.getInstance(). getCalenderGui().goToToday());

        JButton nextWeekBtn = new JButton(">");
        nextWeekBtn.addActionListener(e -> CalendarLessons.getInstance(). getCalenderGui().nextWeek());

        JButton prevWeekBtn = new JButton("<");
        prevWeekBtn.addActionListener(e -> CalendarLessons.getInstance(). getCalenderGui().prevWeek());

        JButton nextMonthBtn = new JButton(">>");
        nextMonthBtn.addActionListener(e -> CalendarLessons.getInstance(). getCalenderGui().nextMonth());

        JButton prevMonthBtn = new JButton("<<");
        prevMonthBtn.addActionListener(e -> CalendarLessons.getInstance(). getCalenderGui().prevMonth());

        JPanel weekControls = new JPanel();
        weekControls.add(prevMonthBtn);
        weekControls.add(prevWeekBtn);
        weekControls.add(goToTodayBtn);
        weekControls.add(nextWeekBtn);
        weekControls.add(nextMonthBtn);

        frm.add(weekControls, BorderLayout.NORTH);

        frm.add(CalendarLessons.getInstance(). getCalenderGui(), BorderLayout.CENTER);
        frm.setSize(1000, 900);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }











}
