import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class RedBird extends Bird{
    protected void birdColor() {



        try {
            birdNow = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/redbird-downflap.png")));
            birdDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/redbird-downflap.png")));
            birdUp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/redbird-upflap.png")));
            birdMid = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/redbird-midflap.png")));
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
