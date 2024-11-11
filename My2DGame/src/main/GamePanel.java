package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	//screen settings 
	final int originalTileSize = 16;// 16x16 tile
	final int scale = 3; // for scaling up the screen to it does not look so small
	
	final int tileSize = originalTileSize * scale; //48x48 tile
	final int maxScreenCol = 16; //16px hoz
	final int maxScreenRow = 12; //12 vert
	final int screenWidth = tileSize * maxScreenCol; //768
	final int screenHeight = tileSize * maxScreenRow; //576
	
	Thread gameThread;
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
