import java.util.List;

public class Reservation {
    private Movie movie;
    private Showtime showtime;
    private List<String> seats;

    public Reservation(Movie movie, Showtime showtime, List<String> seats) {
        this.movie = movie;
        this.showtime = showtime;
        this.seats = seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public List<String> getSeats() {
        return seats;
    }
}
