import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    /// panel
    private    final int panelWidth = 288;
    private final int panelHeight = 512;
    ////// floor
    private Image floor = new ImageIcon("src/sprites/base.png").getImage();
    private int floorHeight = floor.getHeight(null);
    private final int floorY = panelHeight - floorHeight;
//    240 200 150 100 50
    private int obstaclesX = 200;

    private  Image backgroundDay = new ImageIcon("src/sprites/background-day.png").getImage();
    private  Image background ;
    private  Image backgroundNight = new ImageIcon("src/sprites/background-night.png").getImage();
    private ArrayList<Pipe> pipes = new ArrayList<>();
    private int birdScore = 0;
    private final int FPS = 60;
    private int stopMoving = 0;
    private Image gameOver = new ImageIcon("src/sprites/gameover.png").getImage();
    private Thread gameThread;
    private boolean endGame = false;
    private Bird bird;
    private Score score;
    private Audios audios;
    GamePanel(int birdColor,int screenMode) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
       startGame(birdColor,screenMode);
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));
        this.setVisible(true);

    }
    public void startGame(int birdColor,int screenMode) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        audios = new Audios();
        score = new Score();
        if(birdColor == 0 ){
            bird = new RedBird();
        }else if (birdColor == 1){
            bird = new BlueBird();
        }else{
            bird = new YellowBird();
        }
        if(screenMode == 0){
            background = backgroundNight;
        }else{
            background = backgroundDay;

        }
        gameStart();
        initialzePipes();
    }
    private void initialzePipes(){

        for (int i = 0; i < 100; i++) {
            int z = 200;
            for (int j = 0; j < 4; j++) {
                Pipe p = new Pipe(obstaclesX, floorHeight, z);
                obstaclesX+=Pipe.getObstacleHorizontalGap();
                z -= 50;
                pipes.add(p);
            }
            z = 100;
            for (int j = 0; j < 2; j++) {
                Pipe p = new Pipe(obstaclesX, floorHeight, z);
                obstaclesX+=Pipe.getObstacleHorizontalGap();

                z += 50;
                pipes.add(p);
            }
        }
    }
    private void gameStart(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    int temp=0;
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // background
            g2.drawImage(background, 0, 0, null);

            Pipe p1 = pipes.get(0);

            int p = p1.getObstaclesX();
            int x = p;

            for (int i = 0; i < 100 * 6; i++) {
                pipes.get(i).setObstaclesX(x);

                g2.drawImage(Pipe.getPipeUp(), pipes.get(i).getObstaclesX()
                        , pipes.get(i).getObstacleOneY(), null);
                g2.drawImage(Pipe.getPipeDown(), pipes.get(i).getObstaclesX(), pipes.get(i).getObstacleHeightDown(), null);
                x += Pipe.getObstacleHorizontalGap();

            }

            ArrayList<ArrayList<Image>> scoreArray = score.getScore();
            //score
            g2.drawImage(scoreArray.get(birdScore).get(0), score.getScoreX() , 51, null);
            int scoreWidth = scoreArray.get(birdScore).get(0).getWidth(null);
            g2.drawImage(scoreArray.get(birdScore).get(1),score.getScoreX()  + scoreWidth, score.getScoreY(), null);
            // floor
            g2.drawImage(floor, 0, floorY, null);
            if(!endGame){
                g2.drawImage(bird.getBirdNow(),bird.getBirdX(),bird.getBirdY(),null);
            }
            else{
                if(temp == 0){
                    audios.playHit();
                    temp++;
                }

                bird.addY(4);
                int drawLocationX = bird.getBirdX();
                int drawLocationY = bird.getBirdY();

// Rotation

                double rotationRequired = Math.toRadians (90);
                double locationX = bird.getBirdNow().getWidth() / 2;
                double locationY = bird.getBirdNow().getHeight() / 2;
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                g2.drawImage(op.filter(bird.getBirdNow(), null), drawLocationX, drawLocationY, null);
                if(bird.getBirdY()+bird.getBirdHeight() >= floorY){
                    gameThread = null;
                   Main.startGame.makeFrame(birdScore);

                }
            }

        if(endGame){
            g2.drawImage(gameOver,score.getScoreX()-gameOver.getWidth(null)/2 +20, score.getScoreY()*3,null);
        }
    }


    @Override
    public void run() {

        double drawiInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawiInterval;

            lastTime = currentTime;
            if (delta >= 1) {
//                try {
                try {
                    update();
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                repaint();
                delta--;
            }
        }
    }
    private void update() throws LineUnavailableException, IOException {

        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        bird.birdControls(am,im);
        setScore();
                if(!endGame) {
                    for (int i = 0; i < 600; i++) {
                        pipes.get(i).subX(1);
                    }
                    pressJump();
                    bird.changeBird(brd);
                }
        if(bird.birdDeath(floorY, pipes)){
            endGame();
        }

    }
    private int cnt =0;
    private int brd =0;
    private int jumbCounter =0 ;

    private void pressJump(){
        cnt++;
        if(cnt % 10 == 0){
            brd++;
            if(bird.isJumpNow())
                jumbCounter++;
            if(jumbCounter >= 25 ){
                bird.setJumpNow(false);
                jumbCounter =0;
            }
        }
        if(bird.isJumpNow())
            jumbCounter++;
        if(jumbCounter >= 10 ){
            bird.setJumpNow(false);
            jumbCounter =0;
        }
        if(bird.isJumpNow())
            bird.addY(-5); else   bird.addY(2);
        brd %=3;
    }

    private void setScore(){
        Pipe p1 = pipes.get(stopMoving);
        int p = p1.getObstaclesX();
        if(p == 0 ){
            stopMoving++;
            birdScore++;
//            audios.playPoint();
        }
    }
    private void endGame(){
        endGame = true;
    }

}
