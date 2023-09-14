import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String[][][] seatsMatrix; // Matrice de sièges
    private static final int MAX_NUM_SEATS = 100; // Maximum number of seats in a hall
    private String name;
    private String location;
    private int numberOfHalls;
    private List<Showtime> showtimes;

    // Constructor to initialize cinema details
    public Cinema(String name, String location, int numberOfHalls)
    {
        this.name = name;
        this.location = location;
        this.numberOfHalls = numberOfHalls;
        this.showtimes = new ArrayList<>();
        //Me permets de faire une loop dans la matrice et remplis tout les sieges avec des 0 pour montrer que c'est disponible
        seatsMatrix = new String[numberOfHalls][MAX_NUM_SEATS][MAX_NUM_SEATS];
        for (int i = 0; i < numberOfHalls; i++) {
            for (int j = 0; j < MAX_NUM_SEATS; j++) {
                for (int k = 0; k < MAX_NUM_SEATS; k++) {
                    seatsMatrix[i][j][k] = "O"; //Montre que le siege est disponible
                }
            }
        }
    }
    // Permets de vérifier si les sièges sont disponibles lors de l'attribution
    public boolean reserveSeat(int hallIndex, int row, int seat)
    {
        // Vérifier si l'index de la salle, la rangée et le siège sont valides
        if (hallIndex >= 0 && hallIndex < numberOfHalls &&
                row >= 0 && row < MAX_NUM_SEATS && seat >= 0 && seat < MAX_NUM_SEATS) {
            // Vérifier si le siège est disponible
            if (seatsMatrix[hallIndex][row][seat].equals("O")) {
                seatsMatrix[hallIndex][row][seat] = "X"; // Le X indique que c'est pris
                return true; // Le siège est choisi maintenant
            } else {
                return false; // Si le siège est déjà réservé
            }
        } else {
            return false; // Si il y a un problème dans la sélection
        }
    }

    // Method to add a new showtime to the cinema
    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }
}
