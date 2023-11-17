import java.awt.EventQueue;
import javax.swing.JFrame;
	
	
// IMPORTED TO PLAY MUSIC IN BACKGROUND ! 
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;	
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

	public class HelbAquarium extends JFrame {

	    public HelbAquarium() {
	        
	        initUI();
	    }
	    
	    private void initUI() {
	        
	        add(new Board());
	               
	        setResizable(false);
	        pack();
	        
	        setTitle("HelbAquarium2022");
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    

	    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException  {

	    	File file = new File("C:\\Users\\kizil\\OneDrive - Ecole INRACI\\Desktop\\24-12-22\\KIZILIRMAK_Enes_HelbAquarium2022\\src\\backgroundmusic_V2.wav");
	    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
	    	Clip clip = AudioSystem.getClip();
	    	
	    	clip.open(audioStream);
	    	clip.start();

	    	
	        EventQueue.invokeLater(() -> {
	            JFrame ex = new HelbAquarium();
	            ex.setVisible(true);
	        });
	    }
	}

	

