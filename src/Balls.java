import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

//Matt Lawson and Dimitrios Vlahos
public class Balls extends JComponent {
	
	private ArrayList<MovingBall> balls;
	private int frameWidth;
	private int frameHeight;
	private int mouseX;
	private int mouseY;
	private boolean inside;
	
	public Balls(int w, int h) {
		balls = new ArrayList<MovingBall>();
		updateDimensions(w, h);
		setupMouse();
		for (int i = 0; i < 100; i++) addBall();
	}
	
	public void updateDimensions(int w, int h) {
		frameWidth = w;
		frameHeight = h;
	}
	
	public void setupMouse() {
		mouseX = Integer.MIN_VALUE;
		mouseY = Integer.MIN_VALUE;
		inside = true;
		
		MouseMotionAdapter mouseMotion = new MouseMotion();
		addMouseMotionListener(mouseMotion);
		
		MouseAdapter mAdapter = new Adapter();
		addMouseListener(mAdapter);
		
	}
	
	// mouse inner classes here
	class MouseMotion extends MouseMotionAdapter{
		
		@Override
		public void mouseMoved(MouseEvent e){
			mouseX = e.getX();
			mouseY = e.getY();
		//	System.out.println("Moved");
		}
	}

	class Adapter extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e){
			inside = !inside;
		}
		
		@Override
		public void mouseExited(MouseEvent e){
			mouseX = Integer.MIN_VALUE;
			mouseY = Integer.MIN_VALUE;
		}
	}
	
	public void addBall() {
		Random r = new Random();		
		balls.add(new MovingBall(5 + r.nextInt(11), 0, 0, frameWidth, frameHeight));		
	}
	
	public void move() {
		for (MovingBall ball : balls) {
			ball.move(0, 0, frameWidth, frameHeight);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D)(g);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		Ellipse2D.Double m = new Ellipse2D.Double(mouseX - 100, mouseY - 100, 200, 200);
		Color alt = new Color(0,0,0);
		
		
		for(int i = 0; i < balls.size(); i++){
			if(m.intersects(balls.get(i).getBounds2D())){
				if(inside){
					balls.get(i).display(g2D,alt);
				}
				else{
					balls.get(i).display(g2D,null);
				}
			}
			else{
				if(!inside){
					balls.get(i).display(g2D,alt);
				}
				else{
					balls.get(i).display(g2D,null);
				}
			}
		}
		
		g2D.setColor(alt);
		g2D.draw(m);
	}
}
