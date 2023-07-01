import javax.swing.*;

public class Frame extends JFrame {
    Frame(){
        this.setVisible(true);
        this.setResizable(false);
    }
    public void createPanel(){
        GamePanel myPanel ;
        myPanel = new GamePanel();
        this.add(myPanel);
        this.pack();
    }
}