import java.awt.Color;
import java.awt.Graphics;

public class AntG {
	int x;
	int y;
	char color;
	
	AntG(int x, int y, char color){
		this.x = x;
		this.y = y;
		this.color = color;
		
	}
	
	public void drawMe(Graphics g){
		switch(color){
			case 'r': 
				g.setColor(Color.RED);
				break;
			case 'g':
				g.setColor(Color.GREEN);
				break;
			case 'b':
				g.setColor(Color.BLUE);
				break;
			default:
				g.setColor(Color.BLACK);
				break;
		}
		
		int radius = 6;
		g.fillOval(x, y, radius, radius);
		g.setColor(Color.BLACK);
	}

}
