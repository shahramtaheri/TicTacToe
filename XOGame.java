//Written by Shahram Taheri
//You can find several codes and their explanation on my YouTube channel.
//https://www.youtube.com/channel/UC2CVqCLGinsK-Ignzm5t18A

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class XOGame extends Canvas implements MouseListener
{
    private String message="Start", scoreboard1 ="X  0", scoreboard2="O  0";
    private int scoreX, scoreO;
    private final int StartX=200,startY=50,squareSize=60,n=10;
    private int [][]board=new int[n+2][n+2];
    private int playerTurn=1; //playerTurn=1---> Player X, playerTurn=2---->Player Y
    private boolean gameOver;
    private int numberOfPieces=0;
    public XOGame()
    {
        addMouseListener(this);
    }
    //paint() method will be executed by the painting subsystem whenever you component needs to be rendered.
    public void paint( Graphics g )
    {
        paintBoard(g);//draw n*n square board
        displayXO(g);//Display X and O on the Screen
        showScores(g);//Disolay scores
    }
    //-------------------------------------------
    public void displayXO(Graphics g){
        g.setFont(new Font ("Arial",Font.ITALIC, 32));
        for(int i=0;i<n+1;i++)
            for(int j=0;j<n+1;j++)
                if(board[i][j]==1) {
                    g.setColor(Color.red);
                    g.drawString("X",StartX+(j-1)*squareSize+squareSize/4 ,startY+(i-1)*squareSize+squareSize/2+squareSize/4);
                }
                else if(board[i][j]==2) {
                    g.setColor(Color.blue);
                    g.drawString("O",StartX+(j-1)*squareSize+squareSize/4 ,startY+(i-1)*squareSize+squareSize/2+squareSize/4);
                }
    }
    //------------------------------------------
    public void showScores(Graphics g){
      g.setFont(new Font ("Arial",Font.BOLD, 24));
        if(playerTurn==1)
         g.setColor(Color.red);
        else
          g.setColor(Color.blue);
        
        g.drawString(message,StartX+(n/2-2)*squareSize,40);
        g.setColor(Color.black);
        g.fillRect(5,50,150,150);
        g.setColor(Color.white);
        g.drawLine(5,100,155,100);
        g.drawLine(5,150,155,150);
        g.drawLine(78, 100, 78, 200);
        g.drawString("Scores",35,85);
        g.setColor(Color.red);
        g.drawString(scoreboard1,50,125);
        g.setColor(Color.blue);
        g.drawString(scoreboard2,50,175);

    }
    //-------------------------------------------
    public void paintBoard( Graphics g ){
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        g.fillRect(StartX,startY,n*squareSize,n*squareSize);
        g.setColor(Color.black);
        for(int i=0;i<=n;i++) {
          if(i==0 || i==n)
              g2.setStroke(new BasicStroke(3));
          else
              g2.setStroke(new BasicStroke(1));
            g.drawLine(StartX, startY+i*squareSize, StartX+n*squareSize, startY+i*squareSize);
            g.drawLine(StartX+i*squareSize, startY, StartX+i*squareSize, startY+n*squareSize);
        }
    }
    //-------------------------------------------
    public boolean isInPage(int x,int y){
      return ( x>StartX && x<StartX+n*squareSize
                    && y>startY && y<startY+n*squareSize);
    }
    //------------------------------------------
    public boolean isEmpty(int row, int col){
      return board[row][col]==0;
    }
    //-------------------------------------------
    public void togglePlayer(){
      if(playerTurn==1) {
                          message="Next Turn:O";
                          playerTurn=2;
                         }
      else{
                          message="Next Turn:X";
                          playerTurn=1;
                          }
    }
    //
    public void mouseClicked( MouseEvent evt )
    {
        if(!gameOver) {
            if(isInPage(evt.getX(),evt.getY())){ 
                int row=(evt.getY()-startY)/squareSize + 1;
                int col=(evt.getX()-StartX)/squareSize + 1;
                if(isEmpty(row,col)) {
                    numberOfPieces++;
                    board[row][col]=playerTurn;
                    int points=0;
                    if (board[row-1][col] == playerTurn && board[row-2][col] == playerTurn) points++;
                    if (board[row+1][col] == playerTurn && board[row+2][col] == playerTurn) points++;
                    if (board[row][col-1] == playerTurn && board[row][col-2] == playerTurn) points++;
                    if (board[row][col+1] == playerTurn && board[row][col+2] == playerTurn) points++;
                    if (board[row-1][col-1] == playerTurn && board[row-2][col-2] == playerTurn) points++;
                    if (board[row+1][col+1] == playerTurn && board[row+2][col+2] == playerTurn) points++;
                    if (board[row-1][col+1] == playerTurn && board[row-2][col+2] == playerTurn) points++;
                    if (board[row+1][col-1] == playerTurn && board[row+2][col-2] == playerTurn)  points++;
                    //middle cases
                    if (board[row-1][col] == playerTurn && board[row+1][col] == playerTurn) points++;
                    if (board[row][col-1] == playerTurn && board[row][col+1] == playerTurn) points++;
                    if (board[row-1][col+1] == playerTurn && board[row+1][col-1] == playerTurn) points++;
                    if (board[row-1][col-1] == playerTurn && board[row+1][col+1] == playerTurn)  points++;
                    

                    if(playerTurn==1)           
                         scoreX+=points;
                    else
                         scoreO+=points;     
                    
                    if(numberOfPieces==100) {
                      if(scoreX > scoreO)
                        message = "X Wins!";
                      else if (scoreO > scoreX)
                        message = "O Wins!";
                      else
                        message = "Tie!";
                      gameOver=true;
                    }
                    else 
                        togglePlayer();
                         
                }//end if(board[row][col]==0)
                scoreboard1= "X  " + scoreX;
                scoreboard2= "O  " + scoreO;

            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) { }
    @Override
    public void mouseExited(MouseEvent arg0) {}
    @Override
    public void mousePressed(MouseEvent arg0) {}
    @Override
    public void mouseReleased(MouseEvent arg0) { }
}