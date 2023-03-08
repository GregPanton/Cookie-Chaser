package com.greginc.characters;

import com.greginc.game.Game;
import com.greginc.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;

public class Player 
{
 //Player class attributes   
 private Vector position;
 private Vector displacement;
 
 private BufferedImage sprite;
 private int spriteWidth;
 private int spriteHeight;
 private int score;
 
 public Player()
 {
  position = new Vector(100, 350);
  displacement = new Vector(0,0);
  score = 0;
  init();
 }
 
 private void init()
 {
  try
  {
   sprite = ImageIO.read(getClass().getResourceAsStream("/images/player.png"));   
  } catch(Exception ex)
  {
   System.out.println("Error loading player image");
  }
 
  spriteWidth=sprite.getWidth();
  spriteHeight=sprite.getHeight();
 }
 
 public void setPosition(Vector v)
 {
   position = v;  
 }
 
 public Vector getPosition()
 {
  return position;   
 }
 
 public int getSpriteWidth()
 {
  return spriteWidth;   
 }
 
 public int getSpriteHeight()
 {
  return spriteHeight;   
 }
 
 public void setScore(int newScore)
 {
  score = newScore;
 }
 
 public int getScore()
 {
  return score;
 }
 
 public BufferedImage getSprite()
 {
  return sprite;   
 }
 
 public void move(int direction)
 {
  switch(direction)
  {
    case 1:
      displacement.setY(-2);
    break;
    case 2:
        displacement.setY(2);
    break;
    case 3:
        displacement.setX(-2);
    break;
    case 4:
        displacement.setX(2);
    break;
  }    
 }
 
 public void doMove()
 {
  position.add(displacement);   
 }
 
 public void stop()
 {
  displacement.setX(0);
  displacement.setY(0);
 }
 
 public Rectangle getBounds()
 {
  Rectangle characterRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
  return characterRect;
 }
 
 public boolean checkCollision(Cookie  c)
 {
  if(c.getBounds().intersects(getBounds()))
  { 
   if(c.getVisible()==true) 
   {
    score += c.getScore();
    
    Random rand = new Random();
  
    Vector v = new Vector();
    v.setX(rand.nextInt(Game.WINDOW_WIDTH) - 50); //So you dont go right to the edge when the treasure is created
    v.setY(rand.nextInt(Game.WINDOW_HEIGHT) - 50);
    c.setPosition(v); 
   } 
   return true;
  } 
  return false;
 }
 
 public void draw(Graphics2D g)
 {
  g.drawImage(sprite, position.getX(), position.getY(), null);  
 }
}
