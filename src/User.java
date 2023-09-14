import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private int loyaltyPoints;
    private List<Reservation> reservationHistory;

    // Constructor to initialize user details
    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.loyaltyPoints = 0;
        this.reservationHistory = new ArrayList<>();
    }

    // Getters and setters for user fields
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    // Method to add a new reservation to the user's reservation history
    public void addReservation(Reservation reservation) {
        reservationHistory.add(reservation);
    }

    // Method to display user details
    public void displayUserDetails() {
        System.out.println("User Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Reservation History:");
        for (Reservation reservation : reservationHistory) {
            System.out.println("Movie: " + reservation.getMovie().getTitle());
            System.out.println("Showtime: " + reservation.getShowtime().getStartTime());
            System.out.println("Seats: " + reservation.getSeats());
            System.out.println("----------------------");
        }
    }
}
