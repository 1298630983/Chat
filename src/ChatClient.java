import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Jason on 2017/9/6.
 */
public class ChatClient extends Frame{
    DataOutputStream dos = null;
    Socket s = null;
    TextField tfTxt = new TextField();
    TextArea taContent = new TextArea();

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    public void launchFrame() {
        setBounds(100,100,400,400);
        add(tfTxt,BorderLayout.SOUTH);
        add(taContent,BorderLayout.NORTH);
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
    }

    public void connect() {
        try {
            s = new Socket("192.168.2.162",8888);
            System.out.println("Connected!");
            dos = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            dos.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class TfListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = tfTxt.getText().trim();
            taContent.setText(str);
            tfTxt.setText("");
            try {
                //DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(str);
                dos.flush();
                //dos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
