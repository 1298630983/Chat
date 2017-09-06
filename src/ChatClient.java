import java.awt.*;

/**
 * Created by Jason on 2017/9/6.
 */
public class ChatClient extends Frame{
    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    public void launchFrame() {
        setBounds(100,100,400,400);
        setVisible(true);
    }
}
