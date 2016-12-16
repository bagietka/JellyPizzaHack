import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class Backbone {
	
	List<PeakG> listOfPeaks = new ArrayList<PeakG>();
	static final int width = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()-200;
	static final int height = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int n; //liczba kolumn = 2*n
	
	Backbone(int n){
		this.n = n;
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		int distanceX = width/(2*n+3);
		int distanceY = height/4;
		int number = 0;
		int x = 50;
		int y = height/2-50;
		listOfPeaks.add(new PeakG(x,y,number));
		listOfPeaks.get(number++).drawMe(g);
		for (int i = 0; i < 2*n; i++){
			x += distanceX;
			y = 50;
			for(int j = 0; j < 4; j++){
				listOfPeaks.add(new PeakG(x,y,number));
				listOfPeaks.get(number++).drawMe(g);
				y += distanceY;
			}
		}
		x += distanceX;
		y = 50;
		for(int j = 0; j < 4; j++){
			listOfPeaks.add(new PeakG(x,y,number));
			listOfPeaks.get(number++).drawMe(g);
			y += distanceY;
		}
		x += distanceX;
		y = height/2-50;
		listOfPeaks.add(new PeakG(x,y,number));
		listOfPeaks.get(number++).drawMe(g);
		
	}
	

}
