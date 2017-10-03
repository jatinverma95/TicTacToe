package lecture32;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
	public static final int BOARD_SIZE = 3;
	boolean crossTurn = true;

	public JButton[][] buttonss = new JButton[BOARD_SIZE][BOARD_SIZE];

	private static enum gameStatus {
		Incomplete, XWins, ZWins, Tie;

	}

	public TTT() {
		super.setTitle("TIC TAC TOE");
		super.setSize(600, 600);

		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);

		Font font = new Font("Comic Sans MS", 1, 150);

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				JButton button = new JButton();
				button.setFont(font);
				buttonss[i][j] = button;
				button.addActionListener(this);
				super.add(button);
				button.setBackground(Color.BLACK);
			}
		}

		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		this.makeMove(clickedButton);
		clickedButton.setBackground(Color.WHITE);

		gameStatus gs = this.getGameStatus();
		if (gs == gameStatus.Incomplete) {
			return;
		}

		declareWinner(gs);

		int choice = JOptionPane.showConfirmDialog(this, "Restart?");

		if (choice == JOptionPane.YES_OPTION) {
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					buttonss[i][j].setText("");
					buttonss[i][j].setBackground(Color.BLACK);
				}
			}
			crossTurn = true;

		} else {
			super.dispose();
		}

	}

	public void declareWinner(gameStatus gs) {
		if (gs == gameStatus.XWins) {
			JOptionPane.showMessageDialog(this, "X Wins");
		} else if (gs == gameStatus.ZWins) {
			JOptionPane.showMessageDialog(this, "Z Wins");
		} else {
			JOptionPane.showMessageDialog(this, "Its a Tie");
		}

	}

	private gameStatus getGameStatus() {
		String text = "";
		String text1 = "";
		String text2 = "";
		int row = 0;
		int col = 0;
		// row
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = buttonss[row][col].getText();
				text2 = buttonss[row][col + 1].getText();

				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}

				col++;
			}

			if (col == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return gameStatus.XWins;
				} else {
					return gameStatus.ZWins;
				}
			}
			row++;
		}

		row = 0;
		col = 0;
		// col
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = buttonss[row][col].getText();
				text2 = buttonss[row + 1][col].getText();

				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}

				row++;
			}

			if (row == BOARD_SIZE - 1) {
				if (text1.equals("X")) {
					return gameStatus.XWins;
				} else {
					return gameStatus.ZWins;
				}
			}
			col++;
		}
		row = 0;
		col = 0;
		// diagonal1
		while (row < BOARD_SIZE - 1) {
			text1 = buttonss[row][col].getText();
			text2 = buttonss[row + 1][col + 1].getText();

			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row++;
			col++;
		}

		if (col == BOARD_SIZE - 1) {
			if (text1.equals("X")) {
				return gameStatus.XWins;
			} else {
				return gameStatus.ZWins;
			}

		}

		// diagonal2

		row = BOARD_SIZE - 1;
		col = 0;

		while (row > 0) {
			text1 = buttonss[row][col].getText();
			text2 = buttonss[row - 1][col + 1].getText();

			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row--;
			col++;
		}

		if (row == 0) {
			if (text1.equals("X")) {
				return gameStatus.XWins;
			} else {
				return gameStatus.ZWins;
			}

		}

		for (

		int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				text = buttonss[i][j].getText();
				if (text.length() == 0) {
					return gameStatus.Incomplete;
				}
			}
		}
		return gameStatus.Tie;
	}

	public void makeMove(JButton clickedButton) {
		String text = clickedButton.getText();

		if (!text.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid Button!");

			return;

		}
		if (crossTurn) {

			clickedButton.setText("X");
		} else {
			clickedButton.setText("0");
		}
		crossTurn = !crossTurn;
	}
}
