/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanhv;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author duchi
 */
public class SoundControl implements Runnable {

    public AudioInputStream audioStream = null;
    public AudioFormat format = null;
    public DataLine.Info info = null;
    public Clip playerWAV = null;
    final static String play = "src/sound/play.wav";
    final static String lose = "src/sound/lose.wav";
    final static String win = "src/sound/win.wav";

    public SoundControl() throws Exception {
        this.audioStream = AudioSystem.getAudioInputStream(new File(play));
        this.format = audioStream.getFormat();
        this.info = new DataLine.Info(Clip.class, this.format);
        this.playerWAV = (Clip) AudioSystem.getLine(this.info);
        
    }

    public void play(String input) throws Exception {

        this.audioStream = AudioSystem.getAudioInputStream(new File(input));
        this.format = audioStream.getFormat();
        this.info = new DataLine.Info(Clip.class, this.format);
        this.playerWAV = (Clip) AudioSystem.getLine(this.info);
        playerWAV.open(audioStream);
        new Thread(this).start();
    }

    @Override
    public void run() {

        playerWAV.start();

    }

}
