import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    private final int birdWidth = 34;
    private int birdHeight = 24;
        int birdY = 155;
    private    final int scoreX = 120;
//    private    final int obstacleVerticalGap = 100;
    private    final int panelWidth = 288;
    private final int panelHeight = 512;
    //    final int floorWidth = 109;
//    private  Image pipeDown = new ImageIcon("src/sprites/pipe-green-down.png").getImage();
//    private Image pipeUp = new ImageIcon("src/sprites/pipe-green-up.png").getImage();
    private Image floor = new ImageIcon("src/sprites/base.png").getImage();
    private int floorHeight = floor.getHeight(null);
    private final int floorY = panelHeight - floorHeight;
//    240 200 150 100 50
//    private  int obstacleHeight1 = 512 - obstacleVerticalGap - floorHeight - 200;
//    private  int obstacleOneY =  -(pipeUp.getHeight(null) - obstacleHeight1);
    private int obstaclesX = 200;
    // height arr equation
    //
    //

    private  Image bird = new ImageIcon("src/sprites/yellowbird-downflap.png").getImage();
    private  Image backgroundDay = new ImageIcon("src/sprites/background-day.png").getImage();
    private  Image backgroundNight = new ImageIcon("src/sprites/background-night.png").getImage();
    private  Image zero = new ImageIcon("src/sprites/0.png").getImage();
    private  Image one = new ImageIcon("src/sprites/1.png").getImage();
    private  Image two = new ImageIcon("src/sprites/2.png").getImage();
    private  Image three = new ImageIcon("src/sprites/3.png").getImage();
    private  Image four = new ImageIcon("src/sprites/4.png").getImage();
    private  Image five = new ImageIcon("src/sprites/5.png").getImage();
    private  Image six = new ImageIcon("src/sprites/6.png").getImage();
    private Image seven = new ImageIcon("src/sprites/7.png").getImage();
    private Image eight = new ImageIcon("src/sprites/8.png").getImage();
    private Image nine = new ImageIcon("src/sprites/9.png").getImage();
    private Image gameOver = new ImageIcon("src/sprites/gameover.png").getImage();

    private ArrayList<ArrayList<Image>> score = new ArrayList<>();
    private ArrayList<Pipe> pipes = new ArrayList<>();
    private int birdScore = 0;

    private int birdX = 57;
    private final int scoreY = 51;
//    private final int obstacleHorizontalGap = 96;

    private final int obstacleWidth = 52;
    private final int FPS = 60;

    private int stopMoving = 0;

    Thread gameThread;
//    Pipe p1 ;
    GamePanel(){
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));
        this.setVisible(true);
        Image[] nums = new Image[]{zero , one, two , three ,four , five ,six ,seven , eight , nine};

        for (int i = 0; i <= 9; i++) {
            ArrayList<Image> temp =new ArrayList<>();
            temp.add(nums[i]);
            for (int j = 0; j <= 9; j++) {
                temp.add(nums[j]);
                ArrayList<Image> t =new ArrayList<>();
                t.add(temp.get(0));
                t.add(temp.get(1));
                score.add(t);
                temp.remove(1);
            }
//            pipes.add();
//            final int obstacleHorizontalGap = 96;
//            final int obstacleVerticalGap = 100;
//            final int obstacleWidth = 52;
//            score.add(nums[0]);
        }
        gameStart();
//        for (int i = 0; i < ; i++) {
//
//        }

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


//        p.set
    }
    private void gameStart(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//        imageI

//        g2.drawImage(score.get(birdScore).get(1),120,51 ,null);
        // background
        g2.drawImage(backgroundDay,0,0 ,null);

//        if(obstaclesX == -obstacleWidth){
//            obstaclesX+=100;
//        }
        Pipe p1 = pipes.get(0);
//
        int pipe1X = p1.getObstaclesX();
//        if(pipe1X == -obstacleWidth ){
////            p1.setObstaclesX(pipe1X+Pipe.getObstacleHorizontalGap());
//            stopMoving++;
//            System.out.println(pipe1X);
//        }
//        System.out.println(pipe1X);
        int x = pipe1X;

        for (int i = 0; i < 100*6; i++) {
            pipes.get(i).setObstaclesX(x);
//            g2.drawImage(Pipe.getPipeUp(),x,pipes.get(i).getObstacleOneY() ,null);

            g2.drawImage(Pipe.getPipeUp(),pipes.get(i).getObstaclesX()
                    ,pipes.get(i).getObstacleOneY() ,null);
            g2.drawImage(Pipe.getPipeDown(),pipes.get(i).getObstaclesX(),pipes.get(i).getObstacleHeightDown(),null);
//            g2.drawImage(Pipe.getPipeDown(),x,pipes.get(i).getObstacleHeightDown(),null);
            x += Pipe.getObstacleHorizontalGap();

        }

        //score
        g2.drawImage(score.get(birdScore).get(0),scoreX,51 ,null);
        int scoreWidth = score.get(birdScore).get(0).getWidth(null);
        g2.drawImage(score.get(birdScore).get(1),scoreX+scoreWidth,scoreY ,null);
        // floor
        g2.drawImage(floor,0,floorY ,null);
        // bird initial
        g2.drawImage(bird,birdX,birdY ,null);
//        g2.drawImage();
        if(end == 1)
        g2.drawImage(gameOver,scoreX-gameOver.getWidth(null)/2 +20, scoreY*3,null);



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
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
     birdDeath();
     birdControls();
     setScore();
        birdY += 1;
//        birdY = pipes.get(0).getObstacleHeightDown()-22;;
//        birdY += 1;
        for (int i = 0; i < 600; i++) {
            pipes.get(i).subX(1);
        }
//        obstaclesX -= 1;

    }

    private void setScore() {
        Pipe p1 = pipes.get(stopMoving);
//
        int pipe1X = p1.getObstaclesX();
        if(pipe1X == 0 ){
//            p1.setObstaclesX(pipe1X+Pipe.getObstacleHorizontalGap());
            stopMoving++;
            System.out.println(pipe1X);
            birdScore++;
        }
    }

    private void birdDeath(){
        if(birdY <=0 || birdY+birdHeight >= floorY){
            endGame();
        }
        for (int i = 0; i < 600; i++) {
            Pipe p = pipes.get(i);

            if(  (birdX + 12 >=  p.getObstaclesX() &&  birdX + 12 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +24 >=  p.getObstaclesX() && birdX +24 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){


                if(birdY <= p.getObstacleHeightUp()||birdY >= p.getObstacleHeightDown()){
                    endGame();
                }

            }

            if(  (birdX + 8 >=  p.getObstaclesX() &&  birdX + 8 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +26 >=  p.getObstaclesX() && birdX +26 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+2 <= p.getObstacleHeightUp()||birdY+2 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }
            if(  (birdX + 6 >=  p.getObstaclesX() &&  birdX + 6 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +28 >=  p.getObstaclesX() && birdX +28 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+4 <= p.getObstacleHeightUp()||birdY+4 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }
            if(  (birdX + 2 >=  p.getObstaclesX() &&  birdX + 2 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +30 >=  p.getObstaclesX() && birdX +30 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+6 <= p.getObstacleHeightUp()||birdY+6 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }
            if(  (birdX + 0 >=  p.getObstaclesX() &&  birdX + 0 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +30 >=  p.getObstaclesX() && birdX +30 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+8 <= p.getObstacleHeightUp()||birdY+8 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }
            if(  (birdX + 0 >=  p.getObstaclesX() &&  birdX + 0 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +32 >=  p.getObstaclesX() && birdX +32 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+12 <= p.getObstacleHeightUp()||birdY+12 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }
            if(  (birdX + 2 >=  p.getObstaclesX() &&  birdX + 2 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +34 >=  p.getObstaclesX() && birdX +34 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+14 <= p.getObstacleHeightUp()||birdY+14 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }
            if(  (birdX + 4 >=  p.getObstaclesX() &&  birdX + 4 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +32 >=  p.getObstaclesX() && birdX +32 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+16 <= p.getObstacleHeightUp()||birdY+16 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }

            if(  (birdX + 6 >=  p.getObstaclesX() &&  birdX + 6 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +30 >=  p.getObstaclesX() && birdX +30 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+20 <= p.getObstacleHeightUp()||birdY+20 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }
            if(  (birdX + 10 >=  p.getObstaclesX() &&  birdX + 10 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +20 >=  p.getObstaclesX() && birdX +20 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){

                if(birdY+22 <= p.getObstacleHeightUp()||birdY+22 >= p.getObstacleHeightDown()){
                    endGame();
                }
            }
        }
    }
    int end = 0;
    private void endGame(){
        end = 1;
        repaint();
        gameThread = null;

    }
    private void birdControls(){
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jump");
        am.put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /// 5*10
                for (int i = 0; i < 4; i++) {
                    birdY-=4;
                }
            }
        });
    }
}
