import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jason on 2017/9/6.
 */
public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true) {
                Socket s = ss.accept();
                System.out.println("A Client Connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
