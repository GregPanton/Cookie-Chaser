package com.greginc.screens;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import com.greginc.game.Game;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;

public class StartGamePanel extends JPanel
{
 //Attributes of the panel
 private Game game;//Links to the game class so we can talk to the controller
 private BufferedImage background;//Background image being used
    
 public StartGamePanel(Game g) //This is the contrstuctor 
 {
  game = g;
  init();
 }   
 
 private void init()
 {
  try
  {
   background = ImageIO.read(getClass().getResourceAsStream("/images/background.jpeg")); 
  }catch(Exception ex)
  {
   System.out.println("Error loading Start Screen background image");
  }
  
  setFocusable(true);
  addKeyListener(new TAdapter());
 }           
 
 @Override
 public void paintComponent(Graphics g)
 {
  super.paintComponent(g); //always need to do this
  
  g.drawImage(background, 0, 0, null);
  g.drawString("Press P to play game", 250, 300);
 }
 
 private class TAdapter extends KeyAdapter
 {
  @Override   
  public void keyReleased(KeyEvent e)
  { 
      
  }
  
  @Override
  public void keyPressed(KeyEvent e)
  {
   if(e.getKeyCode()== KeyEvent.VK_P)
   {
    System.out.println("Playing Game...");
    game.startGame(); 
   }   
  }
 }
}