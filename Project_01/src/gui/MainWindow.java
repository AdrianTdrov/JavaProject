package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import structure.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

// Main window GUI class
@SuppressWarnings({ "serial", "unused" })
public class MainWindow extends JFrame {
	
//	public static ArrayList<Game> cart = new ArrayList<Game>();
	public static Map<Game, Integer> cart = new LinkedHashMap<>();
	private JPanel contentPane;
	private int page = 1;
	private int sortingMode = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ParseException 
	 * @throws org.json.simple.parser.ParseException 
	 */
	public MainWindow() throws FileNotFoundException, IOException, ParseException, ParseException, org.json.simple.parser.ParseException {
		
		// Testing code
//		ArrayList<Game> games = new ArrayList<Game>();
//		Game game1 = new Game("Grand Theft Auto V", 0.91, new Date(2015 - 1900, 4, 14), "Rockstar Games", 25.30, new ArrayList<String>(Arrays.asList("Open World", "Action", "Multiplayer", "Automobile Sim", "Crime")), "gta_v.jpg");
//		Game game2 = new Game("ARK: Survival Evolved", 0.82, new Date(2017 - 1900, 8, 27), "Studio Wildcard", 24.99, new ArrayList<String>(Arrays.asList("Open World Survival Craft", "Survival", "Open World", "Multiplayer", "Dinosaurs")), "ark.jpg");
//		Game game3 = new Game("FIFA 22", 0.79, new Date(2021 - 1900, 10, 1), "EA Canada & EA Romania", 9.59, new ArrayList<String>(Arrays.asList("Soccer", "Sports", "Multiplayer", "Simulation", "Football")), "fifa_22.jpg");
//		Game game4 = new Game("Counter-Strike: Global Offensive", 0.88, new Date(2012 - 1900, 8, 21), "Valve", 0.00, new ArrayList<String>(Arrays.asList("FPS", "Shooter", "Multiplayer", "Competitive", "Action")), "cs_go.jpg");
//		games.add(game1);
//		games.add(game2);
//		games.add(game3);
//		games.add(game4);  
		
		ArrayList<Game> games = new ArrayList<>();
		Object obj = new JSONParser().parse(new FileReader("src/db.json"));
		JSONObject jo = (JSONObject) obj;
		JSONArray ja = (JSONArray) jo.get("games");
		Iterator itr = ja.iterator();
		while (itr.hasNext()) {
			JSONObject gameJSON = (JSONObject) itr.next();
			String name = (String) gameJSON.get("name");
			double rating = (double) gameJSON.get("rating");
			Date releaseDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) gameJSON.get("releaseDate"));
			String developer = (String) gameJSON.get("developer");
			double price = (double) gameJSON.get("price");
			ArrayList<String> tags = new ArrayList<>();
			JSONArray tagsJSON = (JSONArray) gameJSON.get("tags");
			Iterator itr2 = tagsJSON.iterator();
			while (itr2.hasNext())
				tags.add((String) itr2.next());
			String imageName = (String) gameJSON.get("imageName");
			games.add(new Game(name, rating, releaseDate, developer, price, tags, imageName));
			
		}
		
		
		setTitle("Game Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel gamePanel = new JPanel(new GridLayout(2, 2, 10, 10));
		gamePanel.setBounds(10, 55, 510, 310);
//		for (Game game: games) {
//			gamePanel.add(new GamePanel(game));
//		}
		for (int i = 0; i < 4; i++) {
			gamePanel.add(new GamePanel(games.get(i)));
		}
		contentPane.add(gamePanel);
		
		JButton btnNewButton = new JButton("Sort");
		btnNewButton.setToolTipText("Sort items by different criteria.");
		btnNewButton.setBounds(10, 10, 100, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sortingMode == 5)
					sortingMode = 0;
				sortingMode++;
				if (sortingMode == 1) {
					Collections.sort(games, new Comparator<Game>() {
						@Override
						public int compare(Game g1, Game g2) {
							return ((Double)g1.getPrice()).compareTo(g2.getPrice());
						}
					});
				}
				else if (sortingMode == 2) {
					Collections.sort(games, new Comparator<Game>() {
						@Override
						public int compare(Game g1, Game g2) {
							return ((Double)g2.getPrice()).compareTo(g1.getPrice());
						}
					});
				}
				else if (sortingMode == 3) {
					Collections.sort(games, new Comparator<Game>() {
						@Override
						public int compare(Game g1, Game g2) {
							return (g1.getName().compareTo(g2.getName()));
						}
					});
				}
				else if (sortingMode == 4) {
					Collections.sort(games, new Comparator<Game>() {
						@Override
						public int compare(Game g1, Game g2) {
							return (g2.getName().compareTo(g1.getName()));
						}
					});
				}
				else if (sortingMode == 5) {
					Collections.sort(games, new Comparator<Game>() {
						@Override
						public int compare(Game g1, Game g2) {
							return ((Double)g2.getRating()).compareTo(g1.getRating());
						}
					});
				}
				
				
				gamePanel.removeAll();
				for (int i = (page - 1) * 4; i < page * 4; i++)
					gamePanel.add(new GamePanel(games.get(i)));
				gamePanel.revalidate();
				gamePanel.repaint();
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/cart.jpg")));
		btnNewButton_3.setBounds(420, 10, 100, 35);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CartFrame cart = new CartFrame();
				cart.setVisible(true);
				cart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		contentPane.add(btnNewButton_3);
		
		
		
		
		JLabel lblNewLabel = new JLabel("1");
		lblNewLabel.setBounds(248, 379, 45, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("<");
		btnNewButton_4.setBounds(10, 375, 85, 21);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if (page != 1) {
					page = page - 1;
					lblNewLabel.setText(((Integer) page).toString());
					gamePanel.removeAll();
					for (int i = (page - 1) * 4; i < page * 4; i++)
						gamePanel.add(new GamePanel(games.get(i)));
					gamePanel.revalidate();
					gamePanel.repaint();
				}
			}
		});
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton(">");
		btnNewButton_5.setBounds(435, 375, 85, 21);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if (page != Math.ceil(games.size() / 4.0)) {
					page = page + 1;
					lblNewLabel.setText(((Integer) page).toString());
					System.out.println(page);
					gamePanel.removeAll();
					for (int i = (page - 1) * 4; i < (page * 4 < games.size() ? page * 4 : games.size()); i++)
						gamePanel.add(new GamePanel(games.get(i)));
					gamePanel.revalidate();
					gamePanel.repaint();
				}
			}
		});
		contentPane.add(btnNewButton_5);
		
		
	}
}
