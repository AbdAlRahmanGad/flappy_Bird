import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class BlueBird extends Bird{

    protected void birdColor() {

        try {
            birdNow = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/bluebird-downflap.png")));
            birdDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/bluebird-downflap.png")));
            birdUp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/bluebird-upflap.png")));
            birdMid = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("sprites/bluebird-midflap.png")));
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
