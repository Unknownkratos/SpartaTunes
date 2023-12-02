import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.*;

public class SpartaTunes {
    private static Clip[] clips;

    public static void main(String[] args) {
        loadSoundtracks();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input a number: ");
        String inputNumber = scan.next();
        playMusic(inputNumber);
    }

    public static void loadSoundtracks() {
        try {
            clips = new Clip[10];
            for (int i = 0; i < 10; i++) {
                String fileName = i + ".wav";
                File file = new File(fileName);
                if (file.exists()) {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                    clips[i] = AudioSystem.getClip();
                    clips[i].open(audioStream);
                } else {
                    System.out.println("Soundtrack for digit " + i + " not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(String input) {
        try {
            long totalDuration = 0;
            for (char digit : input.toCharArray()) {
                int soundtrackIndex = Character.getNumericValue(digit);
                if (clips[soundtrackIndex] != null) {
                    totalDuration += clips[soundtrackIndex].getMicrosecondLength() / 1000;
                    clips[soundtrackIndex].setFramePosition(0);
                    clips[soundtrackIndex].start();
                }
            }

            Thread.sleep(totalDuration + 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
