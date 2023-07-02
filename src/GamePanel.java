import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
    private  BufferedImage bird10 ;
    private  BufferedImage bird11 ;
    private  BufferedImage bird12 ;
    private  BufferedImage bird13 ;
    private  Image bird1 = new ImageIcon("src/sprites/yellowbird-downflap.png").getImage();
    private  Image bird2 = new ImageIcon("src/sprites/yellowbird-upflap.png").getImage();
    private  Image bird3 = new ImageIcon("src/sprites/yellowbird-midflap.png").getImage();
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
    boolean endGame = false;
//    Pipe p1 ;
    GamePanel() throws IOException {
        try {
            bird10 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/yellowbird-downflap.png")));
       bird11 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/yellowbird-downflap.png")));
         bird12 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/yellowbird-upflap.png")));
           bird13 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/yellowbird-midflap.png")));
        }catch (IOException e){
            e.printStackTrace();
        }

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
    int jump = 0;
    private void gameStart(){
        gameThread = new Thread(this);
//        endGameThread = new Thread();
//        endGameThread.start();
        gameThread.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//        imageI

//        g2.drawImage(score.get(birdScore).get(1),120,51 ,null);
        // background
        if(endGame == false) {


            g2.drawImage(backgroundDay, 0, 0, null);

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

            for (int i = 0; i < 100 * 6; i++) {
                pipes.get(i).setObstaclesX(x);
//            g2.drawImage(Pipe.getPipeUp(),x,pipes.get(i).getObstacleOneY() ,null);

                g2.drawImage(Pipe.getPipeUp(), pipes.get(i).getObstaclesX()
                        , pipes.get(i).getObstacleOneY(), null);
                g2.drawImage(Pipe.getPipeDown(), pipes.get(i).getObstaclesX(), pipes.get(i).getObstacleHeightDown(), null);
//            g2.drawImage(Pipe.getPipeDown(),x,pipes.get(i).getObstacleHeightDown(),null);
                x += Pipe.getObstacleHorizontalGap();

            }

            //score
            g2.drawImage(score.get(birdScore).get(0), scoreX, 51, null);
            int scoreWidth = score.get(birdScore).get(0).getWidth(null);
            g2.drawImage(score.get(birdScore).get(1), scoreX + scoreWidth, scoreY, null);
            // floor
            g2.drawImage(floor, 0, floorY, null);
//            g2.drawImage(bird,birdX,birdY ,null);
            if(jump == 0)
            g2.drawImage(bird10,birdX,birdY ,null);
            else{
                int drawLocationX = birdX;
                int drawLocationY = birdY;

// Rotation information

                double rotationRequired = Math.toRadians (-45);
                double locationX = bird10.getWidth() / 2;
                double locationY = bird10.getHeight() / 2;
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
                g2.drawImage(op.filter(bird10, null), drawLocationX, drawLocationY, null);
            }

        }else{
            g2.drawImage(gameOver,scoreX-gameOver.getWidth(null)/2 +20, scoreY*3,null);
//            g2.drawImage(bird10,birdX,birdY ,null);
//            if(end == 0){
                // The required drawing location
                int drawLocationX = birdX;
                int drawLocationY = birdY;

// Rotation information

                double rotationRequired = Math.toRadians (-45);
                double locationX = bird10.getWidth() / 2;
                double locationY = bird10.getHeight() / 2;
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
                g2.drawImage(op.filter(bird10, null), drawLocationX, drawLocationY, null);
//                bird11 =  rotate(bird11,90.0);
//                bird12 = rotate(bird12,90.0);
//                bird13 = rotate(bird13,90.0);
//                end++;
//            }

//            g2.drawImage(bird,birdX,birdY ,null);

        }
        // bird initial
//        g2.drawImage();
//        if(end == 1)



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

    int jum =0 ;
    private void update() {
     birdDeath();
     birdControls();
     setScore();
//        birdY += 2;
//        birdY = pipes.get(0).getObstacleHeightDown()-22;;
//        birdY += 1;
//        if(now < birdY)jump =1;else jump=0;


        for (int i = 0; i < 600; i++) {
            pipes.get(i).subX(1);
        }
//        obstaclesX -= 1;
//        bird10
        cnt++;
        if(cnt % 10 == 0){
            brd++;
            if(jumpNow)
            jum++;
            if(jum >= 25 ){
                jumpNow =false;
                jum =0;
            }
        }
        if(jumpNow)
            jum++;
        if(jum >= 10 ){
            jumpNow =false;
            jum =0;
        }
        if(jumpNow)
            birdY -=5;else birdY+=2;
//        switch (brd){
//            case 0:
//                bird = bird1;
//                break;
//            case 1:
//                bird = bird2;
//
//                break;
//            case 2:
//                bird = bird3;
//
//                break;
//        }
        switch (brd){
            case 0:
                bird10 = bird11;
                break;
            case 1:
                bird10 = bird12;

                break;
            case 2:
                bird10 = bird13;

                break;
        }
        brd %=3;
    }
    int cnt =0;
    int brd =0;
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
//        end = 1;
//        repaint();
        gameThread = null;

//        endGame = true;

    }
    int now=600;
    boolean jumpNow = false;
    private void birdControls(){
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jump");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "jumpReleased");
        am.put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /// 5*10
                // 76
//                jump =1;
//                now  = birdY-45;
                jumpNow = true;
//                for (int i = 0; i < 10; i++) {
//                    birdY-=5;
//
//                }


//                now  = birdY+5;
//                jump =0;
            }
        });
//        am.put("jumpReleased", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                /// 5*10
//                // 76
////                jump =0;
////                now  = 600;
//
////                for (int i = 0; i < 10; i++) {
////                    birdY-=5;
////
////                }
//
////                now  = birdY+5;
////                jump =0;
//            }
//        });
    }
}
