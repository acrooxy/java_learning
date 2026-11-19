import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FocusTimer extends JFrame implements ActionListener {
    private JTextField minutesInput;
    private JLabel timeLabel;
    private JLabel statusLabel;
    private JButton startButton;
    private JButton resetButton;
    private Timer timer;

    private int secondsLeft = 25 * 60;
    private boolean isRunning = false;
    private boolean sessionStarted = false;

    public FocusTimer() {
        setTitle("Focus Timer");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        mainPanel.setBackground(new Color(244, 246, 252));

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(244, 246, 252));
        inputPanel.add(new JLabel("Focus time in minutes:"));

        minutesInput = new JTextField("25", 4);
        minutesInput.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(minutesInput);

        timeLabel = new JLabel("25:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 64));
        timeLabel.setForeground(new Color(45, 55, 105));

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
        bottomPanel.setBackground(new Color(244, 246, 252));

        statusLabel = new JLabel("Ready to focus.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        bottomPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(244, 246, 252));

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        buttonPanel.add(startButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(timeLabel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);

        timer = new Timer(1000, this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == timer) {
            secondsLeft--;
            updateTimeLabel();

            if (secondsLeft <= 0) {
                secondsLeft = 0;
                updateTimeLabel();
                timer.stop();
                isRunning = false;
                sessionStarted = false;
                startButton.setText("Start");
                statusLabel.setText("Session complete! Take a short break.");
            }
        } else if (event.getSource() == startButton) {
            if (isRunning) {
                timer.stop();
                isRunning = false;
                startButton.setText("Start");
                statusLabel.setText("Paused.");
            } else {
                if (!sessionStarted && !setSessionLength()) {
                    return;
                }

                sessionStarted = true;
                isRunning = true;
                timer.start();
                startButton.setText("Pause");
                statusLabel.setText("Stay focused!");
            }
        } else if (event.getSource() == resetButton) {
            timer.stop();
            secondsLeft = 25 * 60;
            isRunning = false;
            sessionStarted = false;
            minutesInput.setText("25");
            startButton.setText("Start");
            statusLabel.setText("Ready to focus.");
            updateTimeLabel();
        }
    }

    private boolean setSessionLength() {
        try {
            int minutes = Integer.parseInt(minutesInput.getText());

            if (minutes < 1 || minutes > 180) {
                statusLabel.setText("Choose a time from 1 to 180 minutes.");
                return false;
            }

            secondsLeft = minutes * 60;
            updateTimeLabel();
            return true;
        } catch (NumberFormatException e) {
            statusLabel.setText("Enter a whole number of minutes.");
            return false;
        }
    }

    private void updateTimeLabel() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FocusTimer window = new FocusTimer();
            window.setVisible(true);
        });
    }
}

