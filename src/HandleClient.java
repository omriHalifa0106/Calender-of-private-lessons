import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

class HandleClient implements Runnable {
    private Socket socket; // A connected socket

    private ObjectInputStream inputFromClient;
    private ObjectOutputStream outputToClient;

    private Lesson lessonFromClient;

    public HandleClient(Socket socket) {
        this.socket = socket;
        lessonFromClient = new Lesson();
    }



    /*
    A function that is locked for each student,
    creates an instance of a thread to check the correctness of the entered class,
    and adds it if correct to the arraylist of private classes.
     */
    public synchronized void AddLessonToCalendar(Lesson lessonFromClient) throws InterruptedException {
       ValidationThread validationThread = new ValidationThread(lessonFromClient,false);
       validationThread.run(); // check if the lesson is correct.
       //wait();
       if (validationThread.isFlag() == true) //if the thread return true, add to arraylist of lessons.
           CalendarLessons.getInstance().addLesson(lessonFromClient);
    }


    public void run() {
        try {
            // Create data input and output streams
            outputToClient = new ObjectOutputStream(socket.getOutputStream());
            inputFromClient = new ObjectInputStream(socket.getInputStream());

            // Continuously serve the client
            while (true) {
                // Receive lesson from the client
                try {
                    lessonFromClient = (Lesson) inputFromClient.readObject();
                    System.out.println("the lesson from client that the server got is: " + lessonFromClient);
                    AddLessonToCalendar(lessonFromClient);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
