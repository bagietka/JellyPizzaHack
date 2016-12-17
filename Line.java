import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Line {

	private int from;
	private int to;
	private int thickness;
	String length;
	List<PeakG> listOfPeaks = new ArrayList<PeakG>();
	
	Line(int from, int to, List<PeakG> listOfPeaks, double thickness, String length)
	{
		this.from = from;
		this.to = to;
		this.listOfPeaks = listOfPeaks;
		this.thickness = (int) thickness;
		this.length = length;
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(thickness));
		
		Font f = new Font(length, 0, 20);
		
		g2d.drawLine(listOfPeaks.get(from).getX(), listOfPeaks.get(from).getY(), 
				listOfPeaks.get(to).getX(), listOfPeaks.get(to).getY());
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.RED);
		g2d.setFont(f);
		g2d.drawString(length, (Math.abs(listOfPeaks.get(from).getX()+listOfPeaks.get(to).getX()))/2, 
				(Math.abs(listOfPeaks.get(from).getY()+listOfPeaks.get(to).getY())/2));
		g2d.setColor(Color.BLACK);
		//g.drawLine(x1, y1, x2, y2);
	}
}