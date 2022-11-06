package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import structure.Game;
import gui.CartFrame;

// Cart item GUI class
public class CartItemPanel extends JPanel {
	Game game;
	
	public CartItemPanel(Game game) {
		this.game = game;
		this.setLayout(new GridLayout(1, 6, 10, 10));
		Border border = BorderFactory.createLineBorder(Color.gray);
		this.setBorder(border);
		
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon(MainWindow.class.getResource(String.format("/resources/%s", game.getImageName()))));
		iconLabel.setToolTipText(this.game.getToolTipText());
		this.add(iconLabel);
		
		JLabel nameLabel = new JLabel(this.game.getName());
		this.add(nameLabel);
		
		JLabel priceLabel = new JLabel(String.format("%.2fˆ", this.game.getPrice()));
		this.add(priceLabel);
		
		JLabel countLabel = new JLabel(String.format("%d", MainWindow.cart.get(game)));
		this.add(countLabel);
		
		JLabel totalPriceLabel = new JLabel(String.format("%.2fˆ", this.game.getPrice() * MainWindow.cart.get(game)));
		this.add(totalPriceLabel);
		
		JButton cancelButton = new JButton("X");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.cart.put(game, MainWindow.cart.get(game) - 1);
				CartFrame.repaintCartFrame();
			}
		});
		this.add(cancelButton);
	}
}
