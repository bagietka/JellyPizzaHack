import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
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
	acofortsp acofortsp_obj;
	Fireworks fireworks;
	
	int tab_x[] = new int[10];
	int tab_y[] = new int[10];
	
	

	/**
	 * This is the default constructor
	 */
	public Panel() {
		super();
		fillTabs();
		peak = new PeakG(0,0,0,0);
		ant = new AntG(0,0,'b');
		backbone = new Backbone(30,2, listOfPeaks);
		fireworks = new Fireworks(200, 200, 50, 1);
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
			paintFireworks(g);
			break;
		case 3:
			paintFireworks(g);
			break;
		default:
			break;
		}
	
	}
	
	public void paintFireworks(Graphics g)
	{
		
		for (int i  = 0; i < 10000; i++)
		{
		fireworks.setFirework(Fireworks.randomInt()%1000, Fireworks.randomInt()%1000, Fireworks.randomInt()%100, Fireworks.randomInt()%9);
		fireworks.drawMe(g);
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
			
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		String text = "Hcemy Tylko Tê Pizzê (HTTP)";
		Font f = new Font("Courier New", Font.BOLD, 60);
		
	
		g2d.setFont(f);
		g2d.drawString(text, 70, 400);
	
		
		FontRenderContext frc = g2d.getFontRenderContext();
		AffineTransform transform = new AffineTransform();
		TextLayout textLayout = new TextLayout(text, f, frc);
		
		Shape stroke = textLayout.getOutline(null);
		Rectangle outlineBounds = stroke.getBounds();
		
		transform = g2d.getTransform();
		transform.translate(400, 400);
		g2d.transform(transform);
		g2d.setColor(Color.GREEN);
		g2d.setClip(stroke);
		
		
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
	
	public void paintBestWay(Graphics g, List<Path> bestway){
		backbone.drawBestWay(g, bestway);
}
	
	public void paintBackbone(Graphics g, int n, List<Path> bestway, List<List<Path>> graph, List<Ant> ants)
	{
		backbone.drawMe(g,n,bestway, graph, ants);
		//backbone.drawMe(g, 1000000, 2);
		//wyglada super, ale jest bezuzyteczne
	}
	
	public void paintBackbone(Graphics g, int n, List<List<Path>> graph, List<Ant> ants)
	{
		backbone.drawMe(g,n, graph, ants);
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