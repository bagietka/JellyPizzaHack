import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Backbone {
	
	List<PeakG> listOfPeaks;
	static final int width = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()-200;
	static final int height = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int n; //liczba kolumn = 2*n; n-kÂ¹t
	int version;
	double alpha;
	List<List<Path>> graph;
	List<Path> bestway;
	List<Ant> ants;
	
	Backbone(int n, int v, List<PeakG> listOfPeaks){
		this.n = n;
		this.version = v;
		this.listOfPeaks = listOfPeaks;
	}
	
	public void drawMe(Graphics g, int n, List<List<Path>> graph, List<Ant> ants) {
		// TODO Auto-generated method stub
		this.n = n;
		this.version = 1;
		int number = 0;
		int distanceX;
		int distanceY;
		int x;
		int y;
				listOfPeaks.clear();
				distanceX = width/(2*n+3);
				distanceY = height/4;
				x = 50;
				y = height/2-50;
				listOfPeaks.add(new PeakG(x,y,0,number));
				listOfPeaks.get(number++).drawMe(g);
				for (int i = 0; i < 2*n; i++){
					x += distanceX;
					y = 50;
					for(int j = 0; j < 4; j++){
						listOfPeaks.add(new PeakG(x,y,0,number));
						listOfPeaks.get(number++).drawMe(g);
						y += distanceY;
					}
				}
				x += distanceX;
				y = 50;
				for(int j = 0; j < 4; j++){
					listOfPeaks.add(new PeakG(x,y,0,number));
					listOfPeaks.get(number++).drawMe(g);
					y += distanceY;
				}
				x += distanceX;
				y = height/2-50;
				listOfPeaks.add(new PeakG(x,y,0,number));
				listOfPeaks.get(number++).drawMe(g);
		
		for (List<Path> element : graph){
			for (Path p: element){
				Line l = new Line(p.from,p.to, listOfPeaks, p.pheromone, p.Length.toString());
				l.drawMe(g);
			}
		}
	}
	
	public void drawMe(Graphics g, int n, List<Path> bestway, List<List<Path>> graph, List<Ant> ants) {
		// TODO Auto-generated method stub
		this.n = n;
		int number = 0;
		this.version = 2;
		this.bestway = bestway;
		int x;
		int y;
		listOfPeaks.clear();
		x = 300; y = height/2;
		alpha = 0;
		for (int i = 0; i < n; i++){ //TODO n+1, bo Verticles +1 i inaczej nie dzia³a :p Rudy!!!
			listOfPeaks.add(new PeakG(x,y,alpha,number));
			listOfPeaks.get(number++).drawMe(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.rotate(2*Math.PI/n, width/2, height/2);
			alpha += 2*Math.PI/n;
		}
		
		for (List<Path> element : graph){
			for (Path p: element){
				Line l = new Line(p.from,p.to, listOfPeaks, p.pheromone, p.Length.toString());
				l.drawMe(g);
			}
		}
		
	}
	
	public void drawBestWay(Graphics g, List<Path> bestway){
		
		for (Path p: bestway){
			g.setColor(Color.GREEN);
			Line l = new Line(p.from, p.to, listOfPeaks, 1, "");
			l.drawMe(g);
		}
		g.setColor(Color.BLACK);
	}
	
	public List<PeakG> getList(Graphics g)
	{
		if (this.version == 1) this.drawMe(g, n, graph, ants);
		if (this.version == 2) this.drawMe(g, n, bestway, graph, ants);
		return this.listOfPeaks;
	}
	

}