import java.io.*;
import java.net.*;

public class Session {
    Socket clientSocket = null;
    ServerSocket serverSocket = null;
    BufferedReader is = null;
    PrintWriter os = null;
    String message = new String();

    public Session() {
    }

    public void receive(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintWriter(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void connect(int port) {
        try {
            clientSocket = new Socket("127.0.0.1", port);
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintWriter(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Cannot connect to " + port + ".");
        }
    }

    public void close() {
        try {
            os.close();
            is.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Failed to close connection.");
        }
    }

    public void send(String name, String message) {
        try {
            message = name + ": " + message;
            os.println(message);
            os.flush();
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("Failed to send message.");
        }
    }

    public String read() {
        try {
            message = is.readLine();
        } catch (Exception e) {
            message = "Cannot read input.";
        }
        return message;
    }
}
