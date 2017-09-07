import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jason on 2017/9/6.
 */
public class ChatServer {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        DataInputStream dis = null;
        boolean started = false;
        try {
            ss = new ServerSocket(8888);

            started = true;
            while (started) {
                boolean bConnected = false;
                s = ss.accept();
                System.out.println("A Client Connected!");
                bConnected = true;
                dis = new DataInputStream(s.getInputStream());
                while (bConnected) {
                    String str = dis.readUTF();
                    System.out.println(str);
                }
                //dis.close();
            }
        } catch (IOException e) {

            //e.printStackTrace();
            System.out.println("Client Closed!");
        }finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
