
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	PeakG peak;
	AntG ant;
	
	int tab_x[] = new int[10];
	int tab_y[] = new int[10];
	

	/**
	 * This is the default constructor
	 */
	public Panel() {
		super();
		fillTabs();
		peak = new PeakG(0,0);
		ant = new AntG(0,0,'b');
	}


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		for (int i = 0; i < 10; i++)
		{
			modifyAnt(tab_x[i]+5, tab_y[i]+5, 'r');
			modifyPeak(tab_x[i], tab_y[i]);
			peak.drawMe(g);
			ant.drawMe(g);
		}
		
		ant.drawMe(g);
		peak.drawMe(g);	
	//	clear(g);
	}
	
	public void modifyAnt(int x, int y, char color)
	{
		ant.x = x;
		ant.y = y;
		ant.color = color;
	}
	
	public void modifyPeak(int x, int y)
	{
		peak.x = x;
		peak.y = y;
	}
	
	public void fillTabs()
	{
		for (int i = 0; i < 10; i++)
		{
			this.tab_x[i] = i*40;
			this.tab_y[i] = i*20;
		}
	}
	
	public void clear(Graphics g)
	{
		//g.clearRect(0,  0, getWidth(), getHeight());
		super.paintComponent(g);
	}
	
	
	
}