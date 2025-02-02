package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
	}
	
	public void setDefaultValues() 
	{
		x = 100;
		y = 100;
		speed = 4;
//		x = gp.screenWidth/3;
//		y = gp.screenHeight/3;
	}
	public void update()
	{
		if(keyH.upPressed == true && y>gp.screenHeight-gp.screenHeight)
		{
			y -= speed;
		}
		else if(keyH.downPressed == true && y<gp.screenHeight-gp.tileSize)
		{
			y += speed;
		}
		else if(keyH.leftPressed == true && x>gp.screenWidth-gp.screenWidth)
		{
			x -= speed;
		}
		else if(keyH.rightPressed == true && x<gp.screenWidth-gp.tileSize)
		{
			x += speed;
		}
	}
	public void draw(Graphics2D b2)
	{
		b2.setColor(Color.white);
		
		b2.fillRect(x, y, gp.tileSize, gp.tileSize);	
	}
}
