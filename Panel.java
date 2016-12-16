
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public Panel() {
		super();
		//this.setSize(110, 90);
	}


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawLine(0,0,100,100);
		/*try {
			Image img = ImageIO.read(new File("D:/PWr/JP - lab/lab06/pld.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), new ImageObserver(){
		
		
		@Override
		public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
			// TODO Auto-generated method stub
			return false;
		}
		});*/
	}
}