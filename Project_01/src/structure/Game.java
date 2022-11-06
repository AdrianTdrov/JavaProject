package structure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

// Game class used to store all games as variables

public class Game {
    public String name;
    public double rating;
    public Date releaseDate;
    public String developer;
    public double price;
    public ArrayList<String> tags;
    public String imageName;
    
    public Game(String name, double rating, Date releaseDate, String developer, double price, ArrayList<String> tags, String imageName) {
    	this.name = name;
    	this.rating = rating;
    	this.releaseDate = releaseDate;
    	this.developer = developer;
    	this.price = price;
    	this.tags = tags;
    	this.imageName = imageName;
    }
    
    // Generates tooltip text
    public String getToolTipText() {
        String pattern = "dd MMM, yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        StringBuilder toolTipText = new StringBuilder();
        toolTipText.append(this.getName() + ", ");
        toolTipText.append(String.format("Release date: %s, ", df.format(this.getReleaseDate())));
        toolTipText.append(String.format("Price: %.2fˆ", this.getPrice()));
        return toolTipText.toString();
    }
    
    // Generates info text
    public String getInfoText() {
        String pattern = "dd MMM, yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        StringBuilder infoText = new StringBuilder();
        infoText.append(this.getName() + '\n');
        infoText.append(String.format("Release date: %s\n", df.format(this.getReleaseDate())));
        infoText.append(String.format("Price: %.2fˆ\n", this.getPrice()));
        infoText.append(String.format("Rating: %.2f\n", this.getRating()));
        infoText.append(String.format("Developer: %s\n", this.getDeveloper()));
        StringBuilder tagsString = new StringBuilder();
        tagsString.append("Tags: ");
        for(String tag: this.getTags()) {
            tagsString.append(String.format("%s, ", tag));
        }
        tagsString.append('\n');
        infoText.append(tagsString.toString());
        infoText.delete(infoText.length() - 3, infoText.length());
        return infoText.toString();
    }

	public String getName() {
		return name;
	}

	public double getRating() {
		return rating;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getDeveloper() {
		return developer;
	}

	public double getPrice() {
		return price;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public String getImageName() {
		return imageName;
	}
	
	// Equals override
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Game game = (Game) o;
        if (name != game.name)
        	return false;
        
        return true;
	}
}