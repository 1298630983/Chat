import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Jason on 2017/9/6.
 */
public class ChatClient extends Frame {
    DataOutputStream dos = null;
    DataInputStream dis = null;
    Socket s = null;
    private boolean bConnected = false;
    TextField tfTxt = new TextField();
    TextArea taContent = new TextArea();
    Thread tRecv = new Thread(new RecvThread());

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    public void launchFrame() {
        setBounds(100, 100, 400, 400);
        add(tfTxt, BorderLayout.SOUTH);
        add(taContent, BorderLayout.NORTH);
        tfTxt.addActionListener(new TfListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnect();
                System.exit(0);
            }
        });
        pack();
        setVisible(true);
        connect();

        tRecv.start();
    }

    public void connect() {
        try {
            s = new Socket("192.168.2.162", 8888);
            System.out.println("Connected!");
            bConnected = true;
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {

        try {
            dos.close();
            dis.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try {
            bConnected = false;
            tRecv.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                dos.close();
                dis.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
    }

    private class TfListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = tfTxt.getText().trim();
            //taContent.setText(str);
            tfTxt.setText("");
            try {
                dos.writeUTF(str);
                dos.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class RecvThread implements Runnable {

        @Override
        public void run() {

            try {
                while (bConnected) {
                    String str = dis.readUTF();
                    //System.out.println(str);
                    taContent.setText(taContent.getText() + str + '\n');
                }

            } catch (SocketException e) {
                System.out.println("byebye!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


