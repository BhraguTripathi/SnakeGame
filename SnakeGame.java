import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }  

    int boardWidth;
    int boardHeight;
    int tileSize = 25;
    
    //snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    //food
    Tile food;
    Random random;

    //game logic
    int velocityX;
    int velocityY;
    Timer gameLoop;
    int highScore = 0;

    boolean gameOver = false;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 1;
        velocityY = 0;
        
		//game timer
		gameLoop = new Timer(100, this); //how long it takes to start timer
        gameLoop.start();
	}	
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
        //Grid Lines
        for(int i = 0; i < boardWidth/tileSize; i++) {
            //(x1, y1, x2, y2)
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize); 
        }

        //Food
        g.setColor(Color.red);
        
        g.fill3DRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize, true);

        //Snake Head
        g.setColor(Color.green);
        
        g.fill3DRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize, true);
        
        //Snake Body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            
            g.fill3DRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize, true);
		}

        //Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            // Current score on left
            g.setColor(Color.white);
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
            
            // High score on right
            String highScoreText = "High Score: " + String.valueOf(highScore);
            FontMetrics scoreFm = g.getFontMetrics();
            int highScoreWidth = scoreFm.stringWidth(highScoreText);
            g.drawString(highScoreText, boardWidth - highScoreWidth - tileSize + 16, tileSize);
            
            // Game Over message centered above restart message
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.red);
            String gameOverMsg = "Game Over!";
            FontMetrics gameOverFm = g.getFontMetrics();
            int gameOverWidth = gameOverFm.stringWidth(gameOverMsg);
            int gameOverX = (boardWidth - gameOverWidth) / 2;
            int gameOverY = boardHeight / 2 - 40;
            g.drawString(gameOverMsg, gameOverX, gameOverY);
            
            // Center the restart message
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.yellow);
            String restartMsg = "Press any key to restart";
            FontMetrics fm = g.getFontMetrics();
            int msgWidth = fm.stringWidth(restartMsg);
            int x = (boardWidth - msgWidth) / 2;
            int y = boardHeight / 2;
            g.drawString(restartMsg, x, y);
        }
        else {
            // Current score on left
            g.setColor(Color.white);
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
            
            // High score on right
            String highScoreText = "High Score: " + String.valueOf(highScore);
            FontMetrics scoreFm = g.getFontMetrics();
            int highScoreWidth = scoreFm.stringWidth(highScoreText);
            g.drawString(highScoreText, boardWidth - highScoreWidth - tileSize + 16, tileSize);
        }
	}

    public void placeFood(){
        food.x = random.nextInt(boardWidth/tileSize);
		food.y = random.nextInt(boardHeight/tileSize);
	}

    public void move() {
        //eat food
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        //move snake body
        for (int i = snakeBody.size()-1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) { 
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        //move snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //game over conditions
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);

            //collide with snake head
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        if (snakeHead.x < 0 || snakeHead.x >= boardWidth/tileSize || //passed left border or right border
            snakeHead.y < 0 || snakeHead.y >= boardHeight/tileSize ) { //passed top border or bottom border
            gameOver = true;
            snakeHead.x = Math.max(0, Math.min(snakeHead.x, (boardWidth/tileSize) - 1));
            snakeHead.y = Math.max(0, Math.min(snakeHead.y, (boardHeight/tileSize) - 1));
        }
        
        //update high score
        if (gameOver && snakeBody.size() > highScore) {
            highScore = snakeBody.size();
        }
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void restart() {
        snakeHead = new Tile(5, 5);
        snakeBody.clear();
        placeFood();
        velocityX = 1;
        velocityY = 0;
        gameOver = false;
        gameLoop.restart();
    }

    @Override
    public void actionPerformed(ActionEvent e) { //called every x milliseconds by gameLoop timer
        if (!gameOver) {
            move();
        }
        repaint();
    }  

    @Override
    public void keyPressed(KeyEvent e) {
        
        // If game is over, restart on any key press
        if (gameOver) {
            restart();
            return;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    //not needed
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
