import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartFrame extends JFrame implements ActionListener {
    private JButton myButton = new JButton();
    private int WIDTH = 288;
    private int HEIGHT = 300;

    private JRadioButton darkMode;
    private JRadioButton lightMode;

    private JRadioButton redBird;
    private JRadioButton yellowBird;
    private JRadioButton blueBird;
    private Frame game = new Frame();
    private JLabel screenLabel;
    private JLabel empty1;
    private JLabel empty2;
    private JLabel empty3;
    JLabel scoreLabel;

    StartFrame(){
        scoreLabel =  new JLabel();
        blueBird = new JRadioButton(new ImageIcon("src/sprites/bluebird-midflap.png"));
        redBird = new JRadioButton(new ImageIcon("src/sprites/redbird-midflap.png"));
        yellowBird = new JRadioButton(new ImageIcon("src/sprites/yellowbird-midflap.png"));
        yellowBird.setSelected(true);
        ButtonGroup birds =new  ButtonGroup();
        birds.add(blueBird);
        birds.add(redBird);
        birds.add(yellowBird);
        this.setTitle("Main Menu");
        this.setSize(WIDTH,HEIGHT);
        myButton.setSize(WIDTH,50 );
        darkMode = new JRadioButton("Night");
        lightMode = new JRadioButton("Day");
        screenLabel = new JLabel(" Screen Mode");
        empty1 = new JLabel("");
        empty2 = new JLabel("");
        empty3 = new JLabel("");
        ButtonGroup mode = new ButtonGroup();
        mode.add(darkMode);
        mode.add(lightMode);

        myButton.addActionListener(this);
        darkMode.setSelected(true);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public void makeFrame(int score){
        if(score != -1 )
            scoreLabel.setText("Your score is "+score);


        scoreLabel.setForeground(Color.BLACK);
        if(score == -1){
            myButton.setText("Play");
            this.setLayout(new GridLayout(3,1));

        }else{
            myButton.setText("Play again");

            this.setLayout(new GridLayout(4,1));
            this.add(empty1,0).setVisible(false);
            this.add(scoreLabel,1);
            this.add(empty2,2).setVisible(false);
        }
        this.setVisible(true);
        this.add(blueBird);
        this.add(redBird);
        this.add(yellowBird);
        this.add(screenLabel);

        this.add(darkMode);
        this.add(lightMode);

        this.add(empty3).setVisible(false);
        this.add(myButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        this.setVisible(false);
        game.setVisible(true);
        int birdColor;
        if(blueBird.isSelected()){
            birdColor = 1;
        }else if(redBird.isSelected()){
            birdColor = 0;
        }else {
            birdColor = 2;
        }
        int screenMode;
        if(darkMode.isSelected()){
            screenMode = 0;
        }else{
            screenMode = 1;
        }
        try {
            game.createPanel(birdColor,screenMode);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }

    }
}