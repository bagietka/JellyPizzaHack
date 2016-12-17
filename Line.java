import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Line {

	private int from;
	private int to;
	private int thickness;
	List<PeakG> listOfPeaks = new ArrayList<PeakG>();
	
	Line(int from, int to, List<PeakG> listOfPeaks, int thickness)
	{
		this.from = from;
		this.to = to;
		this.listOfPeaks = listOfPeaks;
		this.thickness = thickness;
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(thickness));
		
		for (int i = 0; i < 10; i++)
		{
			System.out.println(i);
			System.out.println(listOfPeaks.get(i).getXfromN() + " " + listOfPeaks.get(i).getYfromN());
			
		}
		
		System.out.println(from + " " + to);		
		//g2d.drawLine(listOfPeaks.get(from).getXfromN(from), listOfPeaks.get(from).getYfromN(from), listOfPeaks.get(to).getXfromN(to), listOfPeaks.get(to).getYfromN(to));
		System.out.println(listOfPeaks.get(from).getXfromN() + " " + listOfPeaks.get(from).getYfromN() + " " + listOfPeaks.get(to).getXfromN() + " " + listOfPeaks.get(to).getYfromN());
		g2d.drawLine(listOfPeaks.get(from).getXfromN(), listOfPeaks.get(from).getYfromN(), listOfPeaks.get(to).getXfromN(), listOfPeaks.get(to).getYfromN());
		g2d.setStroke(new BasicStroke(1));
		//g.drawLine(x1, y1, x2, y2);
	}
}