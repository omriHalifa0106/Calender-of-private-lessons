import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.*;
public class ClientGUI extends JFrame {
    static List<String> hours = Arrays.asList("15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00");
    static List<String> subjects = Arrays.asList("מדעי המחשב","פיזיקה","מתמטיקה","אנגלית","אזרחות","היסטוריה","ספרות","תנך","לשון");
    static List<String> classes = Arrays.asList("א","ב","ג","ד","ה","ו","ז","ח","ט","י","יא","יב");
    static List<Integer> numHours = Arrays.asList(1,2,3);
    private JButton buttonSubmit;

    private JLabel id_title,name_title, class_title, subject_title, fromHour_title,numberHours_title,date_title;
    private JTextField idField,nameField;

    private JComboBox<String> fromHourField,subjectField,classField;
    private JComboBox<Integer> numberHoursField;

    private JDateChooser dateChooser = null;
    private Client client = new Client();

    private Lesson lessonToServer;

    public static void main(String[] args) {
        new ClientGUI();
    }

    public ClientGUI() {
        // create Lesson to read/write from/to server
        lessonToServer = new Lesson();

        CalendarLessons.getInstance().getP().setAlignmentX(JPanel.LEFT_ALIGNMENT);
        CalendarLessons.getInstance().getP().setLayout(new GridLayout(8, 2));

        id_title = new JLabel("הכנס תעודת זהות:");
        idField = new JTextField();
        id_title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        idField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        CalendarLessons.getInstance().getP().add(idField);
        CalendarLessons.getInstance().getP().add(id_title);

        name_title = new JLabel("הכנס שם תלמיד:");
        nameField = new JTextField();
        name_title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        nameField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        CalendarLessons.getInstance().getP().add(nameField);
        CalendarLessons.getInstance().getP().add(name_title);

        class_title = new JLabel("הכנס כיתה:");
        classField = new JComboBox<>(classes.toArray(new String[0]));
        classField.setBackground(Color.WHITE);
        class_title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        classField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        CalendarLessons.getInstance().getP().add(classField);
        CalendarLessons.getInstance().getP().add(class_title);

        subject_title = new JLabel("הכנס מקצוע:");
        subjectField =  new JComboBox<>(subjects.toArray(new String[0]));
        subjectField.setBackground(Color.WHITE);
        subject_title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        subjectField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        CalendarLessons.getInstance().getP().add(subjectField);
        CalendarLessons.getInstance().getP().add(subject_title);

        fromHour_title = new JLabel("הכנס את שעת ההתחלה הרצויה של השיעור:");
        fromHourField = new JComboBox<>(hours.toArray(new String[0]));
        fromHourField.setBackground(Color.WHITE);
        fromHour_title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        fromHourField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        CalendarLessons.getInstance().getP().add(fromHourField);
        CalendarLessons.getInstance().getP().add(fromHour_title);

        numberHours_title = new JLabel("הכנס כמות שעות:");
        numberHoursField = new JComboBox<>(numHours.toArray(new Integer[0]));
        numberHoursField.setBackground(Color.WHITE);
        numberHours_title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        numberHoursField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        CalendarLessons.getInstance().getP().add(numberHoursField);
        CalendarLessons.getInstance().getP().add(numberHours_title);


        date_title = new JLabel("הכנס תאריך:");
        dateChooser = new JDateChooser();
        // Set the date format
        dateChooser.setDateFormatString("dd-MM-yyyy");

        // Set the start date to today
        dateChooser.setMinSelectableDate(new Date());
        date_title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        dateChooser.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        CalendarLessons.getInstance().getP().add(dateChooser);
        CalendarLessons.getInstance().getP().add(date_title);



        buttonSubmit = new JButton("קבע שיעור");
        buttonSubmit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        buttonSubmit.setForeground(Color.WHITE);
        buttonSubmit.setBackground(Color.BLACK);
        buttonSubmit.setHorizontalAlignment(JLabel.CENTER);
        buttonSubmit.addActionListener(new ButtonListener());
        CalendarLessons.getInstance().getP().add(buttonSubmit);

        setContentPane(CalendarLessons.getInstance().getP());
        setTitle("עומרי חליפה- שיעורים פרטיים");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id;
            if (!(idField.getText().isEmpty()) && !(nameField.getText().isEmpty()) && dateChooser.getDate() != null) {   //check if all fields complete
                try {
                    id = Integer.parseInt(idField.getText());
                } catch  (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(CalendarLessons.getInstance().getP(),"תעודת זהות חייבת להיות מספר.", "תעודת זהות לא תקינה", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Inserting the fields into an instance of class type
                lessonToServer.setClient_id(id);
                lessonToServer.setName_client(nameField.getText());
                lessonToServer.setClass_client((String) classField.getSelectedItem());
                lessonToServer.setSubject_client((String) subjectField.getSelectedItem());
                lessonToServer.setStartHour_client((String) fromHourField.getSelectedItem());
                lessonToServer.setNumberHours_client((Integer) numberHoursField.getSelectedItem());
                lessonToServer.setDate(LocalDate.ofInstant(dateChooser.getDate().toInstant(), ZoneId.systemDefault()));

                System.out.println("Lesson send:" + lessonToServer.toString());
                client.writeToServer(lessonToServer);
            }
            else
            {
                JOptionPane.showMessageDialog(CalendarLessons.getInstance().getP(), "אנא מלא את כל הפרטים.");
                return;
            }
        }
    }
}
