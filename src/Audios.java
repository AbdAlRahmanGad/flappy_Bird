import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audios {
    Clip hit;
//    Clip point;
//    int temp=0;


    Audios() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File file = new File("src/audio/hit.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        hit = clip;
//
//        File file2 = new File("src/audio/point.wav");
//        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
//        Clip clip2 = AudioSystem.getClip();
//        clip2.open(audioStream2);
//        point = clip2;
    }

    public void playHit(){
        hit.start();
    }
//    public void playPoint(){
//        if(temp==0)
//        point.start();
//        else
//         point.loop(1);
//        temp++;
//    }

}
