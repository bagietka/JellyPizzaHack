<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics;

public class Fireworks {

	int r;
	int x;
	int y;
	int color;
	
	Fireworks()
	{
		//System.out.println("###########################");
		//System.out.println(randomInt());
		
	}
	
	
	Fireworks(int x, int y, int r, int color)
	{
		this.color = color;
		this.x = x;
		this.y = y;
		this.r = r;
		
	}
	
	
	public static final int randomInt()
	{
		Double rand = Math.random()*2000;
		int rand_int = rand.intValue();
		System.out.println("dabl " + rand + " ynt " + rand_int);
		return rand_int;
	}
	
	public void checkColor(int input, Graphics g)
	{
		switch(input)
		{
		case 0:
			g.setColor(Color.BLUE);
			break;
		case 1:
			g.setColor(Color.CYAN);
			break;
		case 2:
			g.setColor(Color.GREEN);
			break;
		case 3:
			g.setColor(Color.MAGENTA);
			break;
		case 4:
			g.setColor(Color.ORANGE);
			break;
		case 5:
			g.setColor(Color.PINK);
			break;
		case 6:
			g.setColor(Color.RED);
			break;
		case 7:
			g.setColor(Color.WHITE);
			break;
		case 8:
			g.setColor(Color.YELLOW);
			break;
		default:
			g.setColor(Color.BLACK);
			break;
		
		}
	}
	
	public void drawMe(Graphics g)
	{
		checkColor(color, g);
		//System.out.println(x + " " + y + " " + r);
		g.fillOval(x, y, r, r);
		//g.drawOval(x, y, r, r);
		//g.drawLine(1, 2, 800, 900);
		
	}
	
	public void setFirework(int x, int y, int r, int color)
	{
		this.r = r;
		this.x = x;
		this.y = y;
		this.color = color;
		System.out.println("Dane " + x + " " + y + " " + r + " " + color);
	}
	
	
}
=======
import java.awt.Color;
import java.awt.Graphics;

public class Fireworks {

	int r;
	int x;
	int y;
	int color;
	
	Fireworks()
	{
		System.out.println("###########################");
		System.out.println(randomInt());
		
	}
	
	
	Fireworks(int x, int y, int r, int color)
	{
		this.color = color;
		this.x = x;
		this.y = y;
		this.r = r;
		
	}
	
	
	public static final int randomInt()
	{
		Double rand = Math.random()*2000;
		int rand_int = rand.intValue();
		System.out.println("dabl " + rand + " ynt " + rand_int);
		return rand_int;
	}
	
	public void checkColor(int input, Graphics g)
	{
		switch(input)
		{
		case 0:
			g.setColor(Color.BLUE);
			break;
		case 1:
			g.setColor(Color.CYAN);
			break;
		case 2:
			g.setColor(Color.GREEN);
			break;
		case 3:
			g.setColor(Color.MAGENTA);
			break;
		case 4:
			g.setColor(Color.ORANGE);
			break;
		case 5:
			g.setColor(Color.PINK);
			break;
		case 6:
			g.setColor(Color.RED);
			break;
		case 7:
			g.setColor(Color.WHITE);
			break;
		case 8:
			g.setColor(Color.YELLOW);
			break;
		default:
			g.setColor(Color.BLACK);
			break;
		
		}
	}
	
	public void drawMe(Graphics g)
	{
		checkColor(color, g);
		//System.out.println(x + " " + y + " " + r);
		g.fillOval(x, y, r, r);
		//g.drawOval(x, y, r, r);
		//g.drawLine(1, 2, 800, 900);
		
	}
	
	public void setFirework(int x, int y, int r, int color)
	{
		this.r = r;
		this.x = x;
		this.y = y;
		this.color = color;
		System.out.println("Dane " + x + " " + y + " " + r + " " + color);
	}
	
	
}
>>>>>>> origin/master
