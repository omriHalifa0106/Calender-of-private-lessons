import Calender_GUI.CalendarEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

class Client {

    private int client_id;

    // IO streams
    private static ObjectOutputStream toServer;
    private static ObjectInputStream fromServer;

    private Socket socket;


    private ArrayList<Lesson> lessons;


    public Client() {
        try {
            // Create a socket to connect to the server
            socket = new Socket("localhost", 8000);

            // Create an output stream to send data
            // to the server
            toServer = new ObjectOutputStream(socket.getOutputStream());

            // Create an input stream to receive data
            // from the server
            fromServer = new ObjectInputStream(socket.getInputStream());

            lessons = new ArrayList<Lesson>();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToServer(Lesson lesson) {
        try {
            toServer.writeObject(lesson);
            //toServer.flush();
            toServer.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromServer() {
        try {
            return fromServer.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
