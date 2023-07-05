import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audios {
    Clip hit;
    Audios() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File file = new File("src/audio/hit.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        hit = clip;
    }

    public void playHit(){
        hit.start();
    }
}
