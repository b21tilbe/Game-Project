package TicTacToe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Game {

    private static final String PLAYER_1_MARKER = "X";
    private static final String PLAYER_2_MARKER = "O";

    private static String nextPlayerMarker = PLAYER_1_MARKER;
    private static JButton[] buttons = new JButton[9];
    private static boolean noobMode = false;

    public static void GUI() {
    	
        JFrame frame = new JFrame();

        // Skapa en panel för den översta panelen
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Min TicTacToe-spelet");
        topPanel.add(label);

        // Skapa panelen för responsiv del
        JPanel bottomPanel = new JPanel(new GridLayout(2, 3));
        JLabel label1 = new JLabel("X Poäng: ");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVerticalAlignment(SwingConstants.CENTER);
        JLabel label2 = new JLabel("O Poäng: ");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        
        
        JButton difficultyButton1 = new JButton("Noob");
        difficultyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noobMode = true;
            }
        });
        JButton difficultyButton2 = new JButton("Intermediet");
        JButton difficultyButton3 = new JButton("Expert");
        
        bottomPanel.add(label1);
        bottomPanel.add(label2);
        
        bottomPanel.add(difficultyButton1);
        bottomPanel.add(difficultyButton2);
        bottomPanel.add(difficultyButton3);

     // Skapa panelen för spelbrädet
        JPanel panel = new JPanel(new GridLayout(3, 3));
        JButton[] buttons = new JButton[9]; // Skapa en array för knapparna
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(200, 200));
            Font font = new Font("Arial", Font.BOLD, 70);
            button.setFont(font);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setText(nextPlayerMarker);
                    button.setEnabled(false);
                    if (nextPlayerMarker.equals(PLAYER_1_MARKER)) {
                        if (noobMode) {
                            int emptyCount = 0;
                            for (JButton button : buttons) {
                                if (button.getText().isEmpty()) {
                                    emptyCount++;
                                }
                            }
                            if (emptyCount > 0) {
                                int randomIndex = (int) (Math.random() * emptyCount);
                                int currentIndex = 0;
                                for (JButton button : buttons) {
                                    if (button.getText().isEmpty()) {
                                        if (currentIndex == randomIndex) {
                                            button.setText(PLAYER_2_MARKER);
                                            button.setEnabled(false);
                                            break;
                                        } else {
                                            currentIndex++;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        nextPlayerMarker = PLAYER_1_MARKER;
                    }
                }
            });


            panel.add(button);
            buttons[i] = button; // Lägg till knappen i arrayen
        }

        // Skapa en knapp för att återställa spelet
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame(buttons);
            }
        });
        bottomPanel.add(resetButton);


        // Placera panelerna i JFrame med BorderLayout
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        // Begränsa minsta storlek på JFrame-fönstret
        frame.setMinimumSize(new Dimension(200, 200));

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void resetGame(JButton[] buttons) {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        nextPlayerMarker = PLAYER_1_MARKER;
    }
    
    public static void main(String[] args) {
        GUI();
    }
}

