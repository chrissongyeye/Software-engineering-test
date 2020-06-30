import javax.swing.JFrame;
import javax.swing.JPanel;


public abstract class superwindow extends JFrame{
	protected String titleInfo;
	private static int X, Y, WIDTH, HEIGHT;	
	
	protected JPanel north, center, south;		
	
	public void setlocate(int X, int Y, int WIDTH, int HEIGHT) {
		this.X = X;
		this.Y = Y;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		super.setBounds(X, Y, WIDTH, HEIGHT);	
	}
	
	abstract public void setNorthPanel(JPanel north);		
	abstract public void setCenterPanel(JPanel center);
	abstract public void setSouthPanel(JPanel south);	

}
