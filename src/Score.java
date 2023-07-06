import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Score {
    private final int scoreX = 120;
    private Image zero = new ImageIcon("src/sprites/0.png").getImage();
    private Image one = new ImageIcon("src/sprites/1.png").getImage();
    private Image two = new ImageIcon("src/sprites/2.png").getImage();
    private Image three = new ImageIcon("src/sprites/3.png").getImage();
    private Image four = new ImageIcon("src/sprites/4.png").getImage();
    private Image five = new ImageIcon("src/sprites/5.png").getImage();
    private Image six = new ImageIcon("src/sprites/6.png").getImage();
    private Image seven = new ImageIcon("src/sprites/7.png").getImage();
    private Image eight = new ImageIcon("src/sprites/8.png").getImage();
    private Image nine = new ImageIcon("src/sprites/9.png").getImage();
    private ArrayList<ArrayList<Image>> score = new ArrayList<>();
    private final int scoreY = 51;
    Image[] nums = new Image[]{zero, one, two, three, four, five, six, seven, eight, nine};

    Score() {
        for (int k = 0; k <= 5; k++) {
            ArrayList<Image> temp = new ArrayList<>();
        if(k != 0 ){
            temp.add(nums[k]);
        }
        for (int i = 0; i <= 9; i++) {
            temp.add(nums[i]);
            for (int j = 0; j <= 9; j++) {
                temp.add(nums[j]);
                ArrayList<Image> t = new ArrayList<>();
                t.add(temp.get(0));
                t.add(temp.get(1));
                if(k!=0)
                t.add(temp.get(2));
                score.add(t);
                if(k != 0){
                    temp.remove(2);
                }else{
                    temp.remove(1);
                }
            }
            if(k == 0)
            temp.remove(0);
            else {
                temp.remove(1);
            }

        }

        }
    }

    public int getScoreX() {
        return scoreX;
    }

    public ArrayList<ArrayList<Image>> getScore() {
        return score;
    }

    public int getScoreY() {
        return scoreY;
    }

}
