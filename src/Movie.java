import java.util.ArrayList;
import java.util.List;

public class Movie {
    // Fields to store movie information
    private String title;
    private int duration;
    private String classification;
    private String synopsis;
    private String genre;
    private double averageRating;
    private List<String> comments;

    // Constructor to initialize movie details
    public Movie(String title, int duration, String classification, String synopsis, String genre) {
        this.title = title;
        this.duration = duration;
        this.classification = classification;
        this.synopsis = synopsis;
        this.genre = genre;
        this.averageRating = 0.0; // Initialize the average rating to 0.0
        this.comments = new ArrayList<>(); // Initialize the comments list
    }

    // Getters and setters for movie fields
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
    public List<String> getComments() {return comments;}

    public double getAverageRating() {
        return averageRating;
    }

    // Method to add a new comment to the movie
    public void addComment(String comment) {
        comments.add(comment);
    }


    // Method to calculate and update the average rating based on new user ratings
    public void updateAverageRating(double newRating)
    {
        int totalRatings = comments.size();
        double sumOfRatings = averageRating * totalRatings;
        sumOfRatings += newRating;
        averageRating = sumOfRatings / (totalRatings + 1);
    }

}

