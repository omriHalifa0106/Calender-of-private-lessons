import java.io.Serializable;
import java.time.LocalDate;

public class Lesson implements Serializable {
    private int client_id;

    private String name_client;
    private String class_client;
    private String subject_client;
    private String startHour_client;
    private int numberHours_client;
    private LocalDate date;

    public Lesson(int client_id, String name_client, String class_client, String subject_client, String startHour_client, LocalDate date,int numberHours_client) {
        this.client_id = client_id;
        this.name_client = name_client;
        this.class_client = class_client;
        this.subject_client = subject_client;
        this.startHour_client = startHour_client;
        this.date = date;
        this.numberHours_client = numberHours_client;
    }

    public Lesson()
    {

    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }

    public String getClass_client() {
        return class_client;
    }

    public void setClass_client(String class_client) {
        this.class_client = class_client;
    }

    public String getSubject_client() {
        return subject_client;
    }

    public void setSubject_client(String subject_client) {
        this.subject_client = subject_client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartHour_client() {
        return startHour_client;
    }

    public void setStartHour_client(String startHour_client) {
        this.startHour_client = startHour_client;
    }

    public int getNumberHours_client() {
        return numberHours_client;
    }

    public void setNumberHours_client(int numberHours_client) {
        this.numberHours_client = numberHours_client;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "client_id=" + client_id +
                ", name_client='" + name_client + '\'' +
                ", class_client='" + class_client + '\'' +
                ", subject_client='" + subject_client + '\'' +
                ", startHour_client=" + startHour_client +
                ", numberHours_client=" + numberHours_client +
                ", date=" + date +
                '}';
    }
}
