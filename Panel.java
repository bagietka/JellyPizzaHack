
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
	AntG a;

	/**
	 * This is the default constructor
	 */
	public Panel() {
		super();
		//this.setSize(110, 90);
		a = new AntG(100,100,'g');
	}


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		a.drawMe(g);
		
	}
}