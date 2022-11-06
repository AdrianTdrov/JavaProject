package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import structure.Game;
import javax.swing.JLabel;
import javax.swing.JButton;

// Cart GUI class
public class CartFrame extends JFrame {

	private static JPanel contentPane;
	private static JPanel itemsPanel;
	public static JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartFrame frame = new CartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CartFrame() {
		setTitle("Cart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		itemsPanel = new JPanel();
		itemsPanel.setBounds(5, 61, 835, 410);
		contentPane.add(itemsPanel);
		itemsPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Game");
		lblNewLabel.setBounds(10, 22, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(289, 22, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Count");
		lblNewLabel_2.setBounds(435, 22, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total Price");
		lblNewLabel_3.setBounds(582, 22, 96, 13);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Total:");
		lblNewLabel_4.setBounds(552, 497, 147, 13);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Buy");
		btnNewButton.setBounds(750, 493, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.cart.clear();
				repaintCartFrame();
			}
		});
		contentPane.add(btnNewButton);
		
		repaintCartFrame();
		
		
	}
	
	
	// Repaints cart frame when called
	public static void repaintCartFrame() {
		itemsPanel.removeAll();
		double totalPrice = 0;
		for (Map.Entry<Game, Integer> entry: MainWindow.cart.entrySet()) {
			if (entry.getValue() != 0)
				itemsPanel.add(new CartItemPanel(entry.getKey()));
			totalPrice += entry.getKey().price * entry.getValue();
		}
		itemsPanel.revalidate();
		itemsPanel.repaint();
		
		Iterator<Game> it = MainWindow.cart.keySet().iterator();
		while(it.hasNext()) {
			if (MainWindow.cart.get(it.next()) == 0)
				it.remove();
		}
		
		
		lblNewLabel_4.setText(String.format("Total: %.2fˆ", totalPrice));
		System.out.println(MainWindow.cart);
	}
}
