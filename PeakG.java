import java.awt.Color;
import java.awt.Graphics;

public class PeakG
{

	int x;
	int y;
	int r = 10;
	double alpha;
	int n;//numer wezla
	

	PeakG(int x, int y, double alpha, int n)
	{
		
		this.x = x;
		this.y = y;
		this.alpha = alpha;
		this.n = n;
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.drawOval(x,y,r,r);
		if (alpha != 0) {
			this.x -= (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()-200)/2;
			this.y -=  (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight())/2;
			int tmpX = this.x;
			this.x = (int) (this.x*Math.cos(alpha)-this.y*Math.sin(alpha));
			this.y = (int) (tmpX*Math.sin(alpha)+this.y*Math.cos(alpha));
			this.x += (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()-200)/2;
			this.y +=  (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight())/2;
		}
		this.x += r/2;
		this.y += r/2;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	
}