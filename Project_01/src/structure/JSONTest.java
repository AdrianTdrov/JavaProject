package structure;


import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONTest {

	public static void main(String[] args) throws Exception {
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
		for (Game game: games)
			System.out.println(game.getInfoText());
	}

}
