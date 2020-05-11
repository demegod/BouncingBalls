import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

//Matt Lawson and Dimitrios Vlahos
public class MovingBall {

	private Ellipse2D.Double ball;
	private Color color;
	private int dx, dy;

	public MovingBall(int radius, int leftX, int topY, int rightX, int bottomY) {
		Random r = new Random();
		radius = Math.max(2, radius);
		int diam = 2 * radius;
		int x = r.nextInt(rightX - diam);
		int y = r.nextInt(bottomY - diam);
		ball = new Ellipse2D.Double(x, y, diam, diam);
		x = r.nextInt(5) + 1;
		dx = r.nextInt(2) == 0 ? x : -x;
		y = r.nextInt(5) + 1;
		dy = r.nextInt(2) == 0 ? y : -y;
		color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}
	
	public void move(int leftX, int topY, int rightX, int bottomY) {
		double x = ball.getX() + dx;
		double y = ball.getY() + dy;
		if (x < leftX) {
			x = leftX;
			dx = -dx;
		}
		else if (x + ball.getWidth() > rightX) {
			x = rightX - ball.getWidth();
			dx = -dx;
		}
		if (y < topY) {
			y = topY;
			dy = -dy;
		}
		else if (y + ball.getHeight() > bottomY) {
			y = bottomY - ball.getHeight();
			dy = -dy;
		}
		ball.setFrame(x, y, ball.getWidth(), ball.getHeight());
	}
	
	public Rectangle2D getBounds2D() {
		return ball.getBounds2D();
	}
	
	public void display(Graphics2D g2D, Color alt) {
		if(alt == null){
			g2D.setColor(color);
		}
		else{
			g2D.setColor(alt);
		}
		
		g2D.fill(ball);
	}
}
