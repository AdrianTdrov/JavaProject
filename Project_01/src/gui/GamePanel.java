package gui;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import structure.*;

// Game panel GUI class
public class GamePanel extends JPanel{
	Game game;
	
	public GamePanel(Game game) {
		this.game = game;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(215, 150));
		Border border = BorderFactory.createLineBorder(Color.gray);
		this.setBorder(border);
		
		JLabel nameLabel = new JLabel(this.game.getName());
		this.add(nameLabel, BorderLayout.NORTH);
		
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon(MainWindow.class.getResource(String.format("/resources/%s", game.getImageName()))));
		iconLabel.setToolTipText(this.game.getToolTipText());
		this.add(iconLabel, BorderLayout.CENTER);
		
		JButton infoButton = new JButton("\u24D8");
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(infoButton, game.getInfoText(), "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.add(infoButton, BorderLayout.EAST);
		
		JButton buyButton = new JButton("Buy" + String.format(" - %.2fˆ", this.game.price));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!MainWindow.cart.containsKey(game))
					MainWindow.cart.put(game, 0);
				MainWindow.cart.put(game, MainWindow.cart.get(game) + 1);
			}
		});
		this.add(buyButton, BorderLayout.SOUTH);
		
		
	}
	
}
