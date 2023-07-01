import javax.swing.*;
import java.awt.*;

public class Pipe {
    private final static  Image pipeDown = new ImageIcon("src/sprites/pipe-green-down.png").getImage();
    private final static Image pipeUp = new ImageIcon("src/sprites/pipe-green-up.png").getImage();
    private  int obstacleHeightUp ;
    private  int obstacleOneY ;
    private  int obstacleHeightDown;
    private int obstaclesX ;
    private final static int obstacleVerticalGap = 100;
//    private final static int obstacleHorizontalGap = 96;
    private final static int obstacleHorizontalGap = 144;


    public int getObstacleHeightDown() {
        return obstacleHeightDown;
    }
    public static int getObstacleVerticalGap() {
        return obstacleVerticalGap;
    }
    public static int  getObstacleHorizontalGap() {
        return obstacleHorizontalGap;
    }
    public static Image getPipeDown() {
        return pipeDown;
    }
    public static Image getPipeUp() {
        return pipeUp;
    }

    public int getObstacleHeightUp() {
        return obstacleHeightUp;
    }
    public void subX(int num) {
        obstaclesX-=num;
    }

    public Pipe(int obstaclesX ,int floorHeight, int length) {
        this.obstaclesX = obstaclesX;
        this.obstacleHeightUp = 512 - obstacleVerticalGap - floorHeight - length;
        this.obstacleHeightDown = obstacleHeightUp + obstacleVerticalGap;
        this.obstacleOneY =  -(pipeUp.getHeight(null) - obstacleHeightUp);
    }

    public void setObstaclesHeight(int floorHeight, int length) {
//        240 , 200 , 150 ,100 , 50
        this.obstacleHeightUp = 512 - obstacleVerticalGap - floorHeight - length;
        this.obstacleHeightDown = obstacleHeightUp + obstacleVerticalGap;
        this.obstacleOneY =  -(pipeUp.getHeight(null) - obstacleHeightUp);



    }


    public int getObstacleOneY() {
        return obstacleOneY;
    }

    public int getObstaclesX() {
        return obstaclesX;
    }

    public void setObstaclesX(int obstaclesX) {
        this.obstaclesX = obstaclesX;
    }


}
