import java.awt.Graphics;

public class PeakG
{

	int x;
	int y;
	int r = 10;
	

	PeakG(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		g.drawOval(x,y,r,r);
}
}