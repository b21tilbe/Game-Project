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

	private static Player player1 = new Player("X");
    private static Player player2 = new Computer("O");
    private static String nextPlayerMarker = player1.getMarker();

    private static String[][] board = new String[3][3];
    private static boolean noobMode = false;
    private static boolean intermediateMode = false;
    private static boolean expertMode = false;

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
                intermediateMode = false;
                expertMode = false;
            }
        });
        JButton difficultyButton2 = new JButton("Intermediate");
        difficultyButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	intermediateMode = true;
            	noobMode = false;
            	expertMode = false;
            }
        });
        JButton difficultyButton3 = new JButton("Expert");
        difficultyButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	expertMode = true;
            	intermediateMode = false;
            	noobMode = false;
            }
        });
        
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
            	//Noob-Mode
                @Override
                public void actionPerformed(ActionEvent e) {
                	 button.setText(nextPlayerMarker);
                	    button.setEnabled(false);
                	    if (nextPlayerMarker.equals(player1.getMarker())) {
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
                                            button.setText(player2.getMarker());
                                            button.setEnabled(false);
                                            break;
                                        } else {
                                            currentIndex++;
                                        }
                                    }
                                }
                            }
                        }
                        
                        //intermediate-mode
                        else if (intermediateMode) {
                            int emptyCount = 0;
                            for (int i = 0; i < buttons.length; i++) {
                                // Check if there are two X's next to each other
                                if (i < buttons.length - 2 && buttons[i].getText().equals("X") && buttons[i+1].getText().equals("X")) {
                                    // Find the third button and set its text to player2's marker
                                    JButton thirdButton = buttons[i+2];
                                    if (thirdButton.getText().isEmpty()) {
                                        thirdButton.setText(player2.getMarker());
                                        thirdButton.setEnabled(false);
                                        break;
                                    }
                                }
                                if (buttons[i].getText().isEmpty()) {
                                    emptyCount++;
                                }
                            }
                            if (emptyCount > 0) {
                                // If no two X's are next to each other, choose a random empty button
                                int randomIndex = (int) (Math.random() * emptyCount);
                                int currentIndex = 0;
                                for (JButton button : buttons) {
                                    if (button.getText().isEmpty()) {
                                        if (currentIndex == randomIndex) {
                                            button.setText(player2.getMarker());
                                            button.setEnabled(false);
                                            break;
                                        } else {
                                            currentIndex++;
                                        }
                                    }
                                }
                            }
                        }
                        //Expert-mode
                        else if (expertMode) {
                        	 int emptyCount = 0;
                        	    int rowCount = buttons.length / 3;
                        	    int colCount = buttons.length / rowCount;

                        	    // Check horizontal rows for two X's next to each other
                        	    for (int row = 0; row < rowCount; row++) {
                        	        for (int col = 0; col < colCount - 2; col++) {
                        	            if (buttons[row * 3 + col].getText().equals("X") && buttons[row * 3 + col + 1].getText().equals("X")) {
                        	                JButton thirdButton = buttons[row * 3 + col + 2];
                        	                if (thirdButton.getText().isEmpty()) {
                        	                    thirdButton.setText(player2.getMarker());
                        	                    thirdButton.setEnabled(false);
                        	                    return;
                        	                }
                        	            }
                        	        }
                        	    }

                        	    // Check vertical columns for two X's next to each other
                        	    for (int col = 0; col < colCount; col++) {
                        	        for (int row = 0; row < rowCount - 2; row++) {
                        	            if (buttons[row * 3 + col].getText().equals("X") && buttons[(row + 1) * 3 + col].getText().equals("X")) {
                        	                JButton thirdButton = buttons[(row + 2) * 3 + col];
                        	                if (thirdButton.getText().isEmpty()) {
                        	                    thirdButton.setText(player2.getMarker());
                        	                    thirdButton.setEnabled(false);
                        	                    return;
                        	                }
                        	            }
                        	        }
                        	    }

                        	    // Check diagonals for two X's next to each other
                        	    if (buttons[0].getText().equals("X") && buttons[4].getText().equals("X")) {
                        	        JButton thirdButton = buttons[8];
                        	        if (thirdButton.getText().isEmpty()) {
                        	            thirdButton.setText(player2.getMarker());
                        	            thirdButton.setEnabled(false);
                        	            return;
                        	        }
                        	    }
                        	    if (buttons[2].getText().equals("X") && buttons[4].getText().equals("X")) {
                        	        JButton thirdButton = buttons[6];
                        	        if (thirdButton.getText().isEmpty()) {
                        	            thirdButton.setText(player2.getMarker());
                        	            thirdButton.setEnabled(false);
                        	            return;
                        	        }
                        	    }

                        	    // Choose a random empty button if no two X's are next to each other
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
                        	                    button.setText(player2.getMarker());
                        	                    button.setEnabled(false);
                        	                    return;
                        	                } else {
                        	                    currentIndex++;
                        	                }
                        	            }
                        	        }
                        	    }
                        	}

                    } else {
                    	nextPlayerMarker = player1.getMarker();
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
        nextPlayerMarker = player1.getMarker();
    }
 
    public static void main(String[] args) {
        GUI();
    }
}