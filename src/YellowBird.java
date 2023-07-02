import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class YellowBird extends Bird{

    @Override
    protected void birdColor() {

        try {
            birdNow = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/yellowbird-downflap.png")));
            birdDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/yellowbird-downflap.png")));
            birdUp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/yellowbird-upflap.png")));
            birdMid = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/yellowbird-midflap.png")));
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
