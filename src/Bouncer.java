import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//Matt Lawson and Dimitrios Vlahos
public class Bouncer extends JFrame {

	private JFrame controlFrame;
	private JButton addBallButton, startButton, stopButton, speedUpButton, slowDownButton;
	private Timer timer;
	private Balls balls;
	
	public Bouncer() {
		setupThisFrame();		
		setupControlFrame();
		setupButtons();
		setupTimer();
		setupBalls();
		
		showFrames();
	}
	
	private void setupThisFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bouncing Balls");
		setLocation(0,0);
		setSize(800,450);
	}

	private void setupControlFrame() {
		controlFrame = new JFrame();
		controlFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		controlFrame.setTitle("Controls");
		controlFrame.setLocation(801,0);
		controlFrame.setSize(100,450);
	}
	
	private void setupButtons(){
		addBallButton = new JButton("Add Ball");
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		speedUpButton = new JButton("Speed Up");
		slowDownButton = new JButton("Slow Down");
		
		ActionListener addBallListener = new AddBallListener();
		addBallButton.addActionListener(addBallListener);
		
		ActionListener startListener = new StartTimerListener();
		startButton.addActionListener(startListener);
		
		ActionListener stopListener = new StopTimerListener();
		stopButton.addActionListener(stopListener);
		
		ActionListener speedUpList = new SpeedUpListener();
		speedUpButton.addActionListener(speedUpList);
		
		ActionListener slowDownList = new SlowDownListener();
		slowDownButton.addActionListener(slowDownList);
		
		JPanel panel = new JPanel();
		panel.add(addBallButton);
		panel.add(startButton);
		panel.add(stopButton);
		panel.add(speedUpButton);
		panel.add(slowDownButton);
		
		controlFrame.add(panel);
	}

	// place button inner classes here
	class AddBallListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			balls.addBall();
			repaint();
			System.out.println("Ball Added");
		}
	}
	
	class StartTimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			timer.start();
			System.out.println("Time Started");
		}	
	}
	
	class StopTimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			System.out.println("Time Stopped");
		}
	}
	
	class SpeedUpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int delay = timer.getDelay();
			timer.setDelay(Math.max(1, delay / 2));
			
			System.out.println("Sped Up");
		}
	}
	
	class SlowDownListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int delay = timer.getDelay();
			timer.setDelay(delay * 2);
			System.out.println("Slowed Down");
		}
	}
	
	private void setupTimer() {
		timer = new Timer(64, new TimerListener());
	}
	
	// place timer inner class here
	
	class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("tick");
			balls.move();
			repaint();
		}
		
	}
	
	private void setupBalls() {
		balls = new Balls(800,450);
		add(balls);
	}	
	
	private void showFrames() {
		// your code here
		setVisible(true);
		controlFrame.setVisible(true);
		// DO NOT EDIT BELOW THIS LINE

		// insets of frame not known until frame is shown
		if (isVisible()) {
			Insets i = getInsets();
			balls.updateDimensions(getWidth() - i.left - i.right, getHeight() - i.top - i.bottom);
		}
	}
}
