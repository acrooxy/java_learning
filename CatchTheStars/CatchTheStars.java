import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class CatchTheStars extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 450;
    private static final int STAR_SIZE = 24;
    private static final int PLAYER_WIDTH = 100;
    private static final int PLAYER_HEIGHT = 22;

    private final Random random = new Random();
    private final Timer timer;

    private int playerX = WIDTH / 2 - PLAYER_WIDTH / 2;
    private final int playerY = HEIGHT - 60;
    private int starX;
    private int starY;
    private int starSpeed = 4;
    private int score = 0;
    private int missedStars = 0;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean gameOver = false;

    public CatchTheStars() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(10, 18, 45));
        setFocusable(true);
        addKeyListener(this);

        resetStar();
        timer = new Timer(20, this);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Catch the Stars");
            CatchTheStars game = new CatchTheStars();

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setContentPane(game);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.setVisible(true);
            game.requestFocusInWindow();
        });
    }

    public void actionPerformed(ActionEvent event) {
        if (!gameOver) {
            if (moveLeft && playerX > 0) {
                playerX = playerX - 6;
                if (playerX < 0) {
                    playerX = 0;
                }
            }

            if (moveRight && playerX < WIDTH - PLAYER_WIDTH) {
                playerX = playerX + 6;
                if (playerX > WIDTH - PLAYER_WIDTH) {
                    playerX = WIDTH - PLAYER_WIDTH;
                }
            }

            starY = starY + starSpeed;

            boolean touchingPlayer = starY + STAR_SIZE >= playerY
                    && starY <= playerY + PLAYER_HEIGHT
                    && starX + STAR_SIZE >= playerX
                    && starX <= playerX + PLAYER_WIDTH;

            if (touchingPlayer) {
                score++;

                if (score % 5 == 0) {
                    starSpeed++;
                }

                resetStar();
            } else if (starY > HEIGHT) {
                missedStars++;

                if (missedStars >= 3) {
                    gameOver = true;
                } else {
                    resetStar();
                }
            }
        }

        repaint();
    }

    private void resetStar() {
        starX = random.nextInt(WIDTH - STAR_SIZE);
        starY = 55;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] backgroundX = {42, 112, 205, 310, 415, 520, 565};
        int[] backgroundY = {95, 210, 145, 270, 80, 185, 320};

        g.setColor(new Color(180, 200, 255));
        for (int i = 0; i < backgroundX.length; i++) {
            g.fillOval(backgroundX[i], backgroundY[i], 3, 3);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Catch the Stars", 20, 32);

        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Score: " + score, 20, 62);
        g.drawString("Missed: " + missedStars + " / 3", WIDTH - 115, 62);

        drawFallingStar(g);

        g.setColor(new Color(75, 190, 255));
        g.fillRoundRect(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT, 12, 12);
        g.setColor(Color.WHITE);
        g.drawString("CATCHER", playerX + 22, playerY + 16);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(new Color(200, 210, 235));
        g.drawString("Move with LEFT / RIGHT arrows or A / D", 160, HEIGHT - 15);

        if (gameOver) {
            g.setColor(new Color(22, 32, 70));
            g.fillRoundRect(150, 155, 300, 125, 18, 18);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("Game Over", 225, 195);
            g.setFont(new Font("Arial", Font.PLAIN, 17));
            g.drawString("Final score: " + score, 245, 225);
            g.drawString("Press R to play again", 215, 255);
        }
    }

    private void drawFallingStar(Graphics g) {
        int[] starXPoints = {starX + 12, starX + 16, starX + 23, starX + 18, starX + 19,
                starX + 12, starX + 5, starX + 6, starX + 1, starX + 8};
        int[] starYPoints = {starY, starY + 8, starY + 9, starY + 14, starY + 22,
                starY + 18, starY + 22, starY + 14, starY + 9, starY + 8};

        g.setColor(new Color(255, 210, 70));
        g.fillPolygon(starXPoints, starYPoints, 10);
    }

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A) {
            moveLeft = true;
        }

        if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D) {
            moveRight = true;
        }

        if (gameOver && event.getKeyCode() == KeyEvent.VK_R) {
            score = 0;
            missedStars = 0;
            starSpeed = 4;
            playerX = WIDTH / 2 - PLAYER_WIDTH / 2;
            gameOver = false;
            resetStar();
        }
    }

    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_A) {
            moveLeft = false;
        }

        if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_D) {
            moveRight = false;
        }
    }

    public void keyTyped(KeyEvent event) {
    }
}
