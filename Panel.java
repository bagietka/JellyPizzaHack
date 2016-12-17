
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	PeakG peak;
	AntG ant;
	Backbone backbone;
	int choice = 0;
	List<PeakG> listOfPeaks = new ArrayList<PeakG>();
	
	
	int tab_x[] = new int[10];
	int tab_y[] = new int[10];
	
	

	/**
	 * This is the default constructor
	 */
	public Panel() {
		super();
		fillTabs();
		peak = new PeakG(0,0,0);
		ant = new AntG(0,0,'b');
		backbone = new Backbone(30,2, listOfPeaks);
	}


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		//Line line = new Line(1, 10, backbone.getList(g),10);
		
		//line.drawMe(g);
		switch(choice)
		{
		case 1:
			paintTest(g);
			break;
		case 2:
			//paintBackbone(g, n, v, graph, ants);
			break;
		default:
			break;
		}
	
	}
	
	public void paintTest(Graphics g)
	{
		for (int i = 0; i < 10; i++)
		{
			modifyAnt(tab_x[i]+5, tab_y[i]+5, 'r');
			modifyPeak(tab_x[i], tab_y[i]);
			peak.drawMe(g);
			ant.drawMe(g);
		}
	}
	
	public void paintBackbone(Graphics g, int n, int v, List<List<Path>> graph, List<Ant> ants)
	{
		backbone.drawMe(g,n,v, graph, ants);
		//backbone.drawMe(g, 1000000, 2);
		//wyglada super, ale jest bezuzyteczne
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
	
	public void setChoice(int choice, Graphics g)
	{
		this.choice = choice;
		paintComponent(g);
	}
	
	public Graphics getBetterGraphics(){
				//System.out.println("No elo" + this.getGraphics());
				return this.getGraphics();
			}
}