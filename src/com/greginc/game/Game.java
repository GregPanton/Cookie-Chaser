package com.greginc.game;

import com.greginc.levels.End;
import com.greginc.levels.Level1;
import com.greginc.screens.StartGamePanel;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Game 
{
 public static final int WINDOW_WIDTH= 600;
 public static final int WINDOW_HEIGHT= 600;
 
 private JFrame gameWindow; // The Game's main window
 private StartGamePanel startScreen;
 private Level1 lvl1;
 private End end;
 
 public Game()
 {
  initWindow(); 
  initScreens();  
 }
 
 private void initWindow()
 {
  gameWindow = new JFrame();
  gameWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
  gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  gameWindow.getContentPane().setLayout(new CardLayout());
  gameWindow.setResizable(false);
  gameWindow.setLocationRelativeTo(null);//Centres window to screen
  gameWindow.setTitle("My First Game");
 }
 
 private void initScreens()
 {
  startScreen = new StartGamePanel(this);  
  startScreen.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));   
     
  lvl1 = new Level1(this);
  lvl1.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
  
  end = new End(this);
  end.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
  
  gameWindow.getContentPane().add(startScreen, "StartScreen");
  gameWindow.getContentPane().add(lvl1, "Level1");
  gameWindow.getContentPane().add(end, "End");
 }
 
 public void showStartScreen()
 {
  gameWindow.setVisible(true);
  startScreen.requestFocus();
 }
 
 public void startGame()
 {
  CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
  cl.next(gameWindow.getContentPane());
  lvl1.requestFocus();
  lvl1.start(); 
 }
 
 public void endGame(int[] cookietimer)
 {
  CardLayout cl = (CardLayout) gameWindow.getContentPane().getLayout();
  cl.next(gameWindow.getContentPane());
  end.requestFocus();
  end.array(cookietimer);
 }
 
 public static void main(String[] args) 
 {
  Game g = new Game();
  g.showStartScreen();
 }
}
