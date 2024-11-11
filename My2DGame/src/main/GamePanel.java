package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	//screen settings 
	final int originalTileSize = 16;// 16x16 tile
	final int scale = 3; // for scaling up the screen to it does not look so small
	
	final int tileSize = originalTileSize * scale; //48x48 tile
	final int maxScreenCol = 16; //16px hoz
	final int maxScreenRow = 12; //12 vert
	final int screenWidth = tileSize * maxScreenCol; //768     scaling shit
	final int screenHeight = tileSize * maxScreenRow; //576
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//set player's def pos
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
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
		while(gameThread != null)
		{
			System.out.println("frame");
			update();
			repaint();
		}
	}
	public void update() 
	{
		if(keyH.upPressed == true)
		{
			//playerY -= playerSpeed;
			playerY = playerY - playerSpeed; //same thing as this 
		}
		else if(keyH.downPressed == true)
		{
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed == true)
		{
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true)
		{
			playerX += playerSpeed;
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D b2 = (Graphics2D)g;
		
		b2.setColor(Color.white);
		
		b2.fillRect(playerX, playerY, tileSize, tileSize);
		b2.dispose();
	}
}
