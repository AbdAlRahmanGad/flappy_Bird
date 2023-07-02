import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class  Bird {
    private final int birdWidth = 34;
    private final int birdHeight = 24;
    int birdY = 155;
    private int birdX = 57;

//    private Image bird = new ImageIcon("src/sprites/yellowbird-downflap.png").getImage();
    boolean jumpNow = false;

    protected    BufferedImage birdNow ;
    protected  BufferedImage birdDown ;
    protected  BufferedImage birdMid ;
    protected   BufferedImage  birdUp ;
    public void changeBird(int brd){
        switch (brd){
            case 0:
                birdNow = birdDown;
                break;
            case 1:
                birdNow = birdMid;

                break;
            case 2:
                birdNow = birdUp;

                break;
        }
    }

    Bird(){
        birdColor();
    }
    public void addY(int num){
        birdY += num;
    }

    public BufferedImage getBirdNow() {
        return birdNow;
    }

    public void setBirdNow(BufferedImage birdNow) {
        this.birdNow = birdNow;
    }

    protected abstract void birdColor();

    public int getBirdWidth() {
        return birdWidth;
    }

    public int getBirdHeight() {
        return birdHeight;
    }

    public int getBirdY() {
        return birdY;
    }

    public void setBirdY(int birdY) {
        this.birdY = birdY;
    }

    public int getBirdX() {
        return birdX;
    }

    public void setBirdX(int birdX) {
        this.birdX = birdX;
    }

    public boolean isJumpNow() {
        return jumpNow;
    }

    public void setJumpNow(boolean jumpNow) {
        this.jumpNow = jumpNow;
    }

    public void birdControls(ActionMap am , InputMap im){
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jump");
//        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "jumpReleased");
        am.put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 76
                jumpNow = true;
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
    public boolean birdDeath(int floorY ,  ArrayList<Pipe> pipes){
        int endGame = 0;
        if(birdY <=0 || birdY+birdHeight >= floorY){
            endGame = 1;

        }
        for (int i = 0; i < 600; i++) {
            Pipe p = pipes.get(i);

            if(  (birdX + 12 >=  p.getObstaclesX() &&  birdX + 12 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +24 >=  p.getObstaclesX() && birdX +24 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){


                if(birdY <= p.getObstacleHeightUp()||birdY >= p.getObstacleHeightDown()){
                    endGame = 1;

                }

            }

            if(  (birdX + 8 >=  p.getObstaclesX() &&  birdX + 8 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +26 >=  p.getObstaclesX() && birdX +26 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+2 <= p.getObstacleHeightUp()||birdY+2 >= p.getObstacleHeightDown()){
                    endGame = 1;

                }
            }
            if(  (birdX + 6 >=  p.getObstaclesX() &&  birdX + 6 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +28 >=  p.getObstaclesX() && birdX +28 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+4 <= p.getObstacleHeightUp()||birdY+4 >= p.getObstacleHeightDown()){
                    endGame = 1;

                }
            }
            if(  (birdX + 2 >=  p.getObstaclesX() &&  birdX + 2 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +30 >=  p.getObstaclesX() && birdX +30 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+6 <= p.getObstacleHeightUp()||birdY+6 >= p.getObstacleHeightDown()){
                    endGame = 1;

                }
            }
            if(  (birdX + 0 >=  p.getObstaclesX() &&  birdX + 0 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +30 >=  p.getObstaclesX() && birdX +30 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+8 <= p.getObstacleHeightUp()||birdY+8 >= p.getObstacleHeightDown()){
                    endGame = 1;

                }
            }
            if(  (birdX + 0 >=  p.getObstaclesX() &&  birdX + 0 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +32 >=  p.getObstaclesX() && birdX +32 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+12 <= p.getObstacleHeightUp()||birdY+12 >= p.getObstacleHeightDown()){
                    endGame = 1;

                }
            }
            if(  (birdX + 2 >=  p.getObstaclesX() &&  birdX + 2 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +34 >=  p.getObstaclesX() && birdX +34 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+14 <= p.getObstacleHeightUp()||birdY+14 >= p.getObstacleHeightDown()){
                    endGame = 1;
                }
            }
            if(  (birdX + 4 >=  p.getObstaclesX() &&  birdX + 4 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +32 >=  p.getObstaclesX() && birdX +32 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+16 <= p.getObstacleHeightUp()||birdY+16 >= p.getObstacleHeightDown()){
                    endGame = 1;
                }
            }

            if(  (birdX + 6 >=  p.getObstaclesX() &&  birdX + 6 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +30 >=  p.getObstaclesX() && birdX +30 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){
                if(birdY+20 <= p.getObstacleHeightUp()||birdY+20 >= p.getObstacleHeightDown()){
                    endGame = 1;
                }
            }
            if(  (birdX + 10 >=  p.getObstaclesX() &&  birdX + 10 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null))
                    ||
                    ( birdX +20 >=  p.getObstaclesX() && birdX +20 <= p.getObstaclesX()+Pipe.getPipeDown().getWidth(null) )){

                if(birdY+22 <= p.getObstacleHeightUp()||birdY+22 >= p.getObstacleHeightDown()){
                    endGame = 1;
                }
            }
        }
        if(endGame == 1)
        return true;
        else
            return false;
    }

}
