package com.greginc.levels;

import com.greginc.characters.Player;
import com.greginc.characters.Cookie;
import com.greginc.game.Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Level1 extends JPanel implements ActionListener
{
 private Game g;
 private Timer timer;
 private BufferedImage background;
 private Player player;
 private Cookie cookies;
 int counter;
 int secondscounter;
 public int[] cookietimer = new int[6];
 int currentcookie=1;
 
 //Level Constructor
 public Level1(Game g)
 {
  this.g=g;
  player = new Player();
  
  init();
  resetLevel();
 }
 
 public void resetLevel()
 {
  Random rand = new Random();
  
  Vector v = new Vector();
  v.setX(rand.nextInt(Game.WINDOW_WIDTH) - 50); //So you dont go right to the edge when the treasure is created
  v.setY(rand.nextInt(Game.WINDOW_HEIGHT) - 50);
   
  cookies = new Cookie(v, 30);      //Score fixed to 30, could be random
 }
 
 private void init()
 {
  addKeyListener(new TAdapter());
  setFocusable(true);
  setDoubleBuffered(true);
  
  try
  {
   background = ImageIO.read(getClass().getResourceAsStream("/images/background.jpeg"));
  }catch(Exception ex)
  {
   System.out.println("Error Loading Level 1 Background");
  }
  
  timer = new Timer(10, this);
 }
 
 @Override
 protected void paintComponent(Graphics g)
 {
  super.paintComponent(g);
  
  Graphics2D g2d = (Graphics2D) g;
  //draw background
  g2d.drawImage(background, 0, 0, null);
  
  g2d.drawString("Score:" +player.getScore(), 10, 30);
  
  //draw obstacles
  
  cookies.draw(g2d);   
  
  //draw character
  player.draw(g2d);
 
  g2d.dispose();
 }
 
 public void collisions()
 {
  if(player.checkCollision(cookies))
  {
   cookietimer[currentcookie]=secondscounter; 
   secondscounter=0;
   System.out.println("Cookie: " +currentcookie+ " Timer: " +cookietimer[currentcookie-1]);//For testing didnt need to be taken out
   currentcookie++;
  
   if(player.getScore()==50)
   {
    g.endGame(cookietimer);   
   }   
  }
 }
 
 public void movement()
 {
  player.doMove();
 }
 
 public void start()
 {
  timer.start();
 }
 
 public void stop()
 {
  timer.stop();   
 }

 @Override
 public void actionPerformed(ActionEvent e) 
 {
  collisions();
  movement();
  timer();
  repaint();   
 }
 
 public void timer()
 {  
  counter++;
  
  if (counter==100)
  {
   secondscounter++; 
   counter=0;
  }
 }
 
 private class TAdapter extends KeyAdapter
 {
  @Override
  public void keyPressed(KeyEvent e)
  {
   int move = 0;

   switch(e.getKeyCode())
   {
    case KeyEvent.VK_UP:
        move=1;
    break;
    case KeyEvent.VK_DOWN:
        move=2;
    break;
    case KeyEvent.VK_LEFT:
        move=3;
    break;
    case KeyEvent.VK_RIGHT:
        move=4;
    break;
   }
   
   player.move(move);
  }
  
  @Override
  public void keyReleased(KeyEvent e)
  {
   player.stop();
  }
  
  
 }
}
