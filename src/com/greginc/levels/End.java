package com.greginc.levels;

import com.greginc.game.Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class End extends JPanel
{
 private Game g;
 private BufferedImage background;
 int newcookietimer[]= new int[6];
 int spacing=15;
 int cookienum = 1;
 
 public End(Game g)
 {
  this.g=g;
  init();
 }
 
 private void init()
 {
  try
  {
   background = ImageIO.read(getClass().getResourceAsStream("/images/background.jpeg"));
  }catch(Exception ex)
  {
   System.out.println("Error Loading Level 1 Background");
  }   
 }
 
 public void array(int[] cookietimer)
 {
  for(int i=0; i<5; i++)
  {
   newcookietimer[i]=cookietimer[i];
  }  
 }
 
 protected void paintComponent(Graphics g)
 {
  super.paintComponent(g);
  
  Graphics2D g2d = (Graphics2D) g;
  
  g2d.drawImage(background, 0, 0, null);
  g.drawString("Well done! You completed the game!", 200, 300);
  
  for(int i=0; i<5; i++)
  {
   g.drawString("\n Time to collect Cookie "+cookienum+ "="+newcookietimer[i]+" Seconds",25, spacing);
   cookienum++;
   spacing+=20;
  }
  
  g2d.dispose();
 }
}

