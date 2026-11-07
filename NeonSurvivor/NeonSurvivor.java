import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class NeonSurvivor extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    private static final int WIDTH = 960, HEIGHT = 640;
    private final Timer timer = new Timer(16, this);
    private final Random random = new Random();
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<Particle> particles = new ArrayList<>();
    private boolean up, down, left, right, firing, started, gameOver;
    private double playerX = WIDTH / 2.0, playerY = HEIGHT / 2.0;
    private double aimX = WIDTH / 2.0 + 100, aimY = HEIGHT / 2.0;
    private int health = 100, score, kills, wave = 1;
    private int shotCooldown, spawnCooldown, invulnerable, waveClock;
    private long frame;

    public NeonSurvivor() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(8, 13, 25));
        setFocusable(true);

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("NEON SURVIVOR");
            NeonSurvivor game = new NeonSurvivor();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setContentPane(game);
            window.pack();
            window.setLocationRelativeTo(null);

            window.setResizable(false);
            window.setVisible(true);
            game.requestFocusInWindow();
        });
    }

    @Override public void actionPerformed(ActionEvent event) {
        if (started && !gameOver) updateGame();
        repaint();
    }

    private void startGame() {
        playerX = WIDTH / 2.0;
        playerY = HEIGHT / 2.0;
        health = 100;
        score = 0;
        kills = 0;
        wave = 1;
        frame = 0;
        waveClock = 0;
        spawnCooldown = 30;
        shotCooldown = 0;
        invulnerable = 0;
        bullets.clear();
        enemies.clear();
        particles.clear();
        gameOver = false;
        started = true;
        requestFocusInWindow();
    }

    private void updateGame() {
        frame++;
        waveClock++;
        if (waveClock >= 1800) {
            waveClock = 0;
            wave++;
            burst(playerX, playerY, new Color(70, 230, 255), 28);
        }
        if (shotCooldown > 0) shotCooldown--;
        if (invulnerable > 0) invulnerable--;
        double dx = (right ? 1 : 0) - (left ? 1 : 0);
        double dy = (down ? 1 : 0) - (up ? 1 : 0);
        double length = Math.hypot(dx, dy);
        if (length > 0) {
            playerX += dx / length * 4.2;
            playerY += dy / length * 4.2;
        }
        playerX = clamp(playerX, 22, WIDTH - 22);
        playerY = clamp(playerY, 70, HEIGHT - 24);

        if (firing && shotCooldown == 0) shoot();
        if (spawnCooldown-- <= 0 && enemies.size() < 5 + wave * 2) {
            spawnEnemy();
            spawnCooldown = Math.max(18, 76 - wave * 5);
        }

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            b.x += b.vx;
            b.y += b.vy;
            b.life--;
            if (b.life <= 0 || b.x < 0 || b.x > WIDTH || b.y < 0 || b.y > HEIGHT) {
                bullets.remove(i);
                continue;
            }
            for (int j = enemies.size() - 1; j >= 0; j--) {
                Enemy e = enemies.get(j);
                if (distance(b.x, b.y, e.x, e.y) < e.radius + 5) {
                    e.hp--;
                    burst(b.x, b.y, new Color(255, 90, 115), 5);
                    bullets.remove(i);
                    if (e.hp <= 0) {
                        burst(e.x, e.y, new Color(255, 70, 110), 18);
                        enemies.remove(j);
                        kills++;
                        score += 100 * wave;
                    }
                    break;
                }
            }
        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            double ex = playerX - e.x, ey = playerY - e.y;
            double d = Math.max(0.01, Math.hypot(ex, ey));
            e.x += ex / d * e.speed;
            e.y += ey / d * e.speed;
            if (d < e.radius + 17 && invulnerable == 0) {
                health -= 12;
                invulnerable = 45;
                burst(playerX, playerY, new Color(255, 80, 100), 12);
                if (health <= 0) {
                    health = 0;
                    gameOver = true;
                    firing = false;
                }
            }
        }
        Iterator<Particle> it = particles.iterator();
        while (it.hasNext()) {
            Particle p = it.next();
            p.x += p.vx;
            p.y += p.vy;
            p.vx *= 0.97;
            p.vy *= 0.97;
            p.life--;
            if (p.life <= 0) it.remove();
        }
    }

    private void shoot() {
        double dx = aimX - playerX, dy = aimY - playerY;
        double d = Math.max(0.01, Math.hypot(dx, dy));
        bullets.add(new Bullet(playerX + dx / d * 20, playerY + dy / d * 20, dx / d * 11, dy / d * 11));
        shotCooldown = 11;
        burst(playerX + dx / d * 24, playerY + dy / d * 24, new Color(80, 245, 255), 3);
    }

    private void spawnEnemy() {
        int edge = random.nextInt(4);
        double x = edge < 2 ? random.nextInt(WIDTH) : (edge == 2 ? 0 : WIDTH);
        double y = edge >= 2 ? 70 + random.nextInt(HEIGHT - 70) : (edge == 0 ? 70 : HEIGHT);
        int hp = 1 + (wave - 1) / 4;
        double speed = 0.65 + Math.min(1.6, wave * 0.07) + random.nextDouble() * 0.25;
        enemies.add(new Enemy(x, y, hp, speed, 12 + Math.min(wave, 8)));

    }

    private void burst(double x, double y, Color color, int count) {
        for (int i = 0; i < count; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            double speed = 0.8 + random.nextDouble() * 3.6;
            particles.add(new Particle(x, y, Math.cos(angle) * speed, Math.sin(angle) * speed, color));
        }
    }

    @Override protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawArena(g);
        for (Particle p : particles) {
            float alpha = Math.max(0.05f, p.life / 35f);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(p.color);
            g.fillOval((int) p.x - 3, (int) p.y - 3, 6, 6);
        }
        g.setComposite(AlphaComposite.SrcOver);
        for (Bullet b : bullets) drawBullet(g, b);
        for (Enemy e : enemies) drawEnemy(g, e);
        if (started) drawPlayer(g);
        drawHud(g);
        if (!started) drawTitle(g);
        if (gameOver) drawGameOver(g);
        g.dispose();
    }

    private void drawArena(Graphics2D g) {
        g.setPaint(new GradientPaint(0, 0, new Color(12, 22, 40), 0, HEIGHT, new Color(5, 9, 20)));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(new Color(25, 54, 78, 75));
        for (int x = 0; x < WIDTH; x += 48) g.drawLine(x, 64, x, HEIGHT);
        for (int y = 64; y < HEIGHT; y += 48) g.drawLine(0, y, WIDTH, y);
        g.setColor(new Color(48, 145, 175, 70));
        g.drawRoundRect(10, 70, WIDTH - 20, HEIGHT - 82, 22, 22);
        for (int i = 0; i < 45; i++) {
            int x = (i * 173 + 41) % WIDTH;
            int y = 80 + (i * 97 + 19) % (HEIGHT - 90);
            g.setColor(new Color(68, 199, 222, 30 + (i % 4) * 12));
            g.fillOval(x, y, 2, 2);
        }
    }

    private void drawHud(Graphics2D g) {
        g.setColor(new Color(7, 14, 29, 225));
        g.fillRoundRect(18, 14, WIDTH - 36, 44, 18, 18);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.setColor(new Color(90, 235, 255));
        g.drawString("NEON SURVIVOR", 34, 42);
        g.setColor(Color.WHITE);
        g.drawString("WAVE " + wave, 245, 42);
        g.drawString("SCORE " + score, 360, 42);
        g.drawString("KILLS " + kills, 500, 42);
        g.setColor(new Color(39, 54, 69));
        g.fillRoundRect(690, 27, 218, 15, 8, 8);
        g.setPaint(new GradientPaint(690, 0, new Color(64, 239, 181), 908, 0, new Color(255, 77, 113)));
        g.fillRoundRect(690, 27, Math.max(0, 218 * health / 100), 15, 8, 8);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 11));
        g.drawString("HP " + health, 914, 40);
        g.setColor(new Color(173, 194, 214));
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.drawString("WASD / ARROWS move     MOUSE aim     HOLD CLICK fire", 24, HEIGHT - 14);
    }

    private void drawTitle(Graphics2D g) {
        g.setColor(new Color(3, 8, 18, 185));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setFont(new Font("SansSerif", Font.BOLD, 54));
        drawCentered(g, "NEON SURVIVOR", 240, new Color(83, 238, 255));
        g.setFont(new Font("SansSerif", Font.PLAIN, 19));
        drawCentered(g, "The city has fallen. Keep moving. Hold the line.", 292, Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        drawCentered(g, "PRESS ENTER OR CLICK TO START", 370, new Color(255, 220, 108));
        g.setFont(new Font("SansSerif", Font.PLAIN, 15));
        drawCentered(g, "WASD / arrows to move  |  Mouse to aim  |  Hold mouse button to fire", 410, new Color(181, 206, 225));
    }

    private void drawGameOver(Graphics2D g) {
        g.setColor(new Color(4, 6, 15, 205));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setFont(new Font("SansSerif", Font.BOLD, 50));
        drawCentered(g, "SIGNAL LOST", 270, new Color(255, 82, 116));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        drawCentered(g, "Score: " + score + "     Enemies defeated: " + kills + "     Wave: " + wave, 326, Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 18));
        drawCentered(g, "Press ENTER or click to try again", 380, new Color(255, 220, 108));
    }

    private void drawCentered(Graphics2D g, String text, int y, Color color) {
        g.setColor(color);
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(text, (WIDTH - metrics.stringWidth(text)) / 2, y);
    }
    private double clamp(double value, double min, double max) { return Math.max(min, Math.min(max, value)); }
    private double distance(double x1, double y1, double x2, double y2) { return Math.hypot(x1 - x2, y1 - y2); }

    @Override public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> up = true;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> left = true;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> right = true;
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> { if (!started || gameOver) startGame(); }
        }
    }
    @Override public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> left = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> right = false;
        }
    }
    @Override public void keyTyped(KeyEvent e) { }
    @Override public void mousePressed(MouseEvent e) {
        aimX = e.getX(); aimY = e.getY();
        if (!started || gameOver) startGame();
        else firing = true;
        requestFocusInWindow();
    }
    @Override public void mouseReleased(MouseEvent e) { firing = false; }
    @Override public void mouseMoved(MouseEvent e) { aimX = e.getX(); aimY = e.getY(); }
    @Override public void mouseDragged(MouseEvent e) { mouseMoved(e); }
    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { firing = false; }

    private static class Bullet {
        double x, y, vx, vy;
        int life = 75;
        Bullet(double x, double y, double vx, double vy) { this.x=x; this.y=y; this.vx=vx; this.vy=vy; }
    }
    private static class Enemy {
        double x, y, speed;
        int hp, maxHp, radius;
        Enemy(double x, double y, int hp, double speed, int radius) {
            this.x=x; this.y=y; this.hp=hp; this.maxHp=hp; this.speed=speed; this.radius=radius;
        }
    }
    private static class Particle {
        double x, y, vx, vy;
        int life = 35;
        Color color;
        Particle(double x, double y, double vx, double vy, Color color) {
            this.x=x; this.y=y; this.vx=vx; this.vy=vy; this.color=color;
        }
    }
}