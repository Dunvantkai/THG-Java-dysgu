package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;

public class GamePanel extends JPanel implements Runnable
{
	//screen settings 
	final int originalTileSize = 16;// 16x16 tile
	final int scale = 3; // for scaling up the screen to it does not look so small 3
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	final int maxScreenCol = 16; //16px hoz
	final int maxScreenRow = 12; //12 vert
	public final int screenWidth = tileSize * maxScreenCol; //768     scaling shit
	public final int screenHeight = tileSize * maxScreenRow; //576
	//Player P;
	//fps
	int TPS=60;
	
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	//set player's def pos
//	int playerX = 50;
//	int playerY = 100;
//	int playerSpeed = 4; //player speed is 4
	
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() 
	{
		double drawInterval = 1000000000/TPS; //
		double nextDrawTime = System.nanoTime() + drawInterval;
		int checkTPS = TPS;
		
		while(gameThread != null)
		{

			update();
			repaint();
			if(checkTPS>=TPS) 
			{
				System.out.printf("PlayerX: %d PlayerY: %d TPS: %d%n", player.x, player.y, checkTPS);
				checkTPS = 0;
			}
			else {
				checkTPS++;
			}			
			
			try 
			{
				double remainingTime = nextDrawTime - System.nanoTime();
				//remainingTime = remainingTime /1000000;
				if(remainingTime <0)
				{
					remainingTime = 0;
				}
				double timeLeft = remainingTime/1000000;
				Thread.sleep((long) timeLeft);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update() 
	{
		player.update();
	}
	public void paintComponent(Graphics b)
	{
		super.paintComponent(b);
		Graphics2D b2 = (Graphics2D)b;
		
		player.draw(b2);
		b2.dispose();
		
	}
}
