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
			//alpha = 0;
			this.x -= (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()-200/2;
			this.y -= (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
			this.x = (int) (this.x*Math.cos(alpha)-this.y*Math.sin(alpha));
			this.y = (int) (this.x*Math.sin(alpha)+this.y*Math.cos(alpha));
			this.x += (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()-200/2;
			this.y += (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
			System.out.println("drawMe " + this.x + " " + this.y);
		}
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