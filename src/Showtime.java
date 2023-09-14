public class Showtime {
    private String startTime;
    private String endTime;
    private String cinemaHall;

    // Constructor to initialize showtime details
    public Showtime(String startTime, String endTime, String cinemaHall) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cinemaHall = cinemaHall;
    }
    public String getStartTime() {
        return startTime;
    }

}
