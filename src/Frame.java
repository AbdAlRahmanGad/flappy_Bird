import javax.swing.*;
import java.io.IOException;

public class Frame extends JFrame {
    Frame(){
        this.setVisible(true);
        this.setResizable(false);
    }
    public void createPanel(int bird,int screenMode) throws IOException {
        GamePanel myPanel ;
        myPanel = new GamePanel(bird,screenMode);
        this.add(myPanel);
        this.pack();
    }
}