import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Jason on 2017/9/6.
 */
public class ChatClient extends Frame{

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
                setVisible(false);
                System.exit(0);
            }
        });
        pack();
        setVisible(true);
        connect();
    }

    public void connect() {
        try {
            Socket s = new Socket("192.168.2.162",8888);
            System.out.println("Connected!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class TfListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = tfTxt.getText().trim();
            taContent.setText(s);
            tfTxt.setText("");
        }
    }
}
