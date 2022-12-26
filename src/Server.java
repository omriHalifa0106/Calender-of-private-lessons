import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    private ServerSocket serverSocket;

    private CalendarLessons calendar; //instance of calendar that s

    public Server() {
        try {
            serverSocket = new ServerSocket(8000);
            calendar = CalendarLessons.getInstance(); // build the instance of lessons.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket Accept() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
