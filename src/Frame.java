import javax.swing.*;
import java.io.IOException;

public class Frame extends JFrame {
    Frame(){
        this.setVisible(true);
        this.setResizable(false);
    }
    public void createPanel() throws IOException {
        GamePanel myPanel ;
        myPanel = new GamePanel(2);
        this.add(myPanel);
        this.pack();
    }
}