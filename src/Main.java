import java.util.LinkedList; // for the linked list
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static LinkedList<Movie> movieList = new LinkedList<>();
    private static LinkedList<Employee> employees = new LinkedList<>(); // Moved to the class level

    private static int loyaltyPoints = 0;
    private static Cinema cinema1 = new Cinema("Maple Cinema", "Montreal", 5);

    private static double initialTicketPrice = 10.0; // Initialize with your desired initial ticket price

    private static double calculateAdjustedPrice(int newAttendance) {
        // Calculate the adjusted ticket price based on newAttendance
        // You need to implement your logic for calculating the adjusted price here
        // For example, you can decrease the price if attendance is high or increase it if low
        return initialTicketPrice * (1 - (newAttendance * 0.1)); // Just an example adjustment
        
        
        
    }




    //attendance info for attendance monitoring
    class AttendanceInfo {
        private int attendance;
        private double ticketPrice;

        public AttendanceInfo(int attendance, double ticketPrice) {
            this.attendance = attendance;
            this.ticketPrice = ticketPrice;
        }

        public int getAttendance() {
            return attendance;
        }

        public void setAttendance(int attendance) {
            this.attendance = attendance;
        }

        public double getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }
    }

    // Initialize movieAttendance HashMap
    private static HashMap<String, AttendanceInfo> movieAttendance = new HashMap<>();


    public static void main(String[] args) {
        // Init. data structures, database connections, etc.
        // Load initial movie and showtime data from the db

        double initialTicketPrice = 10.0;

        // Create instances of the two movies
        Movie movie1 = new Movie("Barbie", 120, "PG rating", "The film follows Barbie (Margot Robbie) and Ken (Ryan Gosling) on a journey of self-discovery following an existential crisis.","Comedy");
        Movie movie2 = new Movie("Oppenheimer", 120, "R rating", "Earlier in the film, Oppenheimer quotes the Bhagavad Gita when reading Sanskrit. He reads the line: “Now I Become Death, Destroyer of Worlds”.","Drama");
        Movie movie3 = new Movie ("Ace Ventura: Pet Detective",126,"PG-13","Your average Jim carrey movie...","Comedy");

        // Add the movies to the linked list
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        // Call a method to display the main menu and handle user interactions
        showMainMenu();



        // Close database connections and perform cleanup tasks before exiting
        // database.closeConnection();

        // Create a Cinema object

        // Add showtimes for movies in each hall
        cinema1.addShowtime(new Showtime("12:00 PM", "2:00 PM", "Cinema Hall A"));
        cinema1.addShowtime(new Showtime("3:00 PM", "5:00 PM", "Cinema Hall B"));
        // Add more showtimes for other cinema halls

        // Now, the Cinema object represents a specific cinema venue,
        // and you can use it to manage showtimes and movie screenings.

        // Create an Employee object
        Employee employee1 = new Employee("Samuel", "Ticket Sales", 3200.0);

       

        // Assuming ticket price is $10 and the employee sells 50 tickets
        double ticketPrice = 10.0;
        int ticketsSold = 50;
        employee1.updateTicketsSoldAndRevenue(ticketsSold, ticketPrice);

        // Display employee details (including total revenue)
        employee1.displayEmployeeDetails();



    }


    private static void showMainMenu() {
        int choice;
        do {
            System.out.println("-------- Cinema Ticket Reservation System --------");
            System.out.println("1. Search Movies");
            System.out.println("2. Reserve Tickets");
            System.out.println("3. Rate and Comment on Movies");
            System.out.println("4. View Recommended Movies : Comedy or Drama");
            System.out.println("5. Customize Seats");
            System.out.println("6. Check Loyalty Points");
            System.out.println("7. Employee Management");
            System.out.println("8. Attendance Monitoring");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Handle the user's choice by calling respective methods
            switch (choice) {
                case 1:
                    // Call a method to search and display available movies
                    searchMovies();
                    break;
                case 2:
                    // Call a method to reserve tickets
                    reserveTickets();
                    break;
                case 3:
                    // Call a method to handle movie rating and comments
                    //scanner.nextLine();
                    rateAndCommentMovies();
                    break;
                case 4:
                    // Call a method to show recommended movies
                    viewRecommendedMovies();
                    break;
                case 5:
                    // Call a method to customize seats
                    // customizeSeats();
                    break;
                case 6:
                    // Call a method to check loyalty points
                    checkLoyaltyPoints();
                    break;
                case 7:
                    // Call a method for employee management
                    employeeManagement();
                    break;
                case 8:
                    // Call a method for attendance monitoring
                    attendanceMonitoring();
                    break;
                case 9:
                    System.out.println("Exiting Cinema Ticket Reservation System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice !=9);
    }


    private static void attendanceMonitoring() {
        System.out.println("-------- Attendance Monitoring --------");

        // Display movies for attendance monitoring
        System.out.println("Movies for Attendance Monitoring:");
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            AttendanceInfo attendanceInfo = movieAttendance.get(movie.getTitle());

            if (attendanceInfo != null) {
                System.out.println((i + 1) + ". " + movie.getTitle() + " (Attendance: " + attendanceInfo.getAttendance() + ")");
            } else {
                System.out.println((i + 1) + ". " + movie.getTitle() + " (Attendance: N/A)");
            }
        }

        // Choose a movie for attendance adjustment
        System.out.print("Select a movie to adjust attendance (enter the movie number): ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (movieChoice < 1 || movieChoice > movieList.size()) {
            System.out.println("Invalid movie selection. Please try again.");
            return;
        }

        Movie selectedMovie = movieList.get(movieChoice - 1);
        AttendanceInfo attendanceInfo = movieAttendance.get(selectedMovie.getTitle());

        // Update attendance count and adjust ticket price
        System.out.print("Enter the new attendance count for " + selectedMovie.getTitle() + ": ");
        int newAttendance = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (attendanceInfo != null) {
            attendanceInfo.setAttendance(newAttendance);

            double adjustedPrice = calculateAdjustedPrice(newAttendance);
            attendanceInfo.setTicketPrice(adjustedPrice);

            System.out.println("Attendance and ticket price for " + selectedMovie.getTitle() + " updated successfully.");
        } else {
            System.out.println("Attendance information not available for " + selectedMovie.getTitle());
        }
    }


    private static void employeeManagement() {
        int empChoice;
        do {
            System.out.println("-------- Employee Management --------");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            empChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Handle employee management choices
            switch (empChoice) {
                case 1:
                    // Add an employee
                    addEmployee();
                    break;
                case 2:
                    // Display all employees
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (empChoice != 3);
    }

    private static void addEmployee()
    {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee department: ");
        String department = scanner.nextLine();

        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the number of tickets sold by the employee: ");
        int ticketsSold = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Employee employee = new Employee(name, department, salary);
        employee.updateTicketsSoldAndRevenue(ticketsSold, 10.0); // Assuming ticket price is $10
        employees.add(employee);
        System.out.println("Employee added successfully.");

        // Update movie and showtime information
        updateMovieAndShowtimeInfo();
    }

    private static void updateMovieAndShowtimeInfo()
    {
        System.out.println("Enter the movie information for the employee:");
        System.out.print("Movie Title: ");
        String title = scanner.nextLine();

        System.out.print("Movie Duration (in minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Movie Rating: ");
        String rating = scanner.nextLine();

        System.out.print("Movie Description: ");
        String description = scanner.nextLine();

        System.out.print("Movie Genre: ");
        String genre = scanner.nextLine();

        Movie movie = new Movie(title, duration, rating, description, genre);
        movieList.add(movie);

        System.out.println("Enter the showtime information for the employee:");
        System.out.print("Showtime Start: ");
        String startTime = scanner.nextLine();

        System.out.print("Showtime End: ");
        String endTime = scanner.nextLine();

        System.out.print("Cinema Hall: ");
        String cinemaHall = scanner.nextLine();

        Cinema cinema = new Cinema("Maple Cinema", "Montreal", 5);
        cinema.addShowtime(new Showtime(startTime, endTime, cinemaHall));

        System.out.println("Movie and Showtime information updated successfully.");
    }


    private static void displayAllEmployees()
    {
        for (Employee employee : employees)
        {
            employee.displayEmployeeDetails();
        }
    }



    private static void rateAndCommentMovies()
    {
        System.out.println("-------- Rate and Comment on Movies --------");
        System.out.println("Available Movies:");
        for (int i = 0; i < movieList.size(); i++)
        {
            System.out.println((i + 1) + ". " + movieList.get(i).getTitle());
        }
        System.out.print("Select a movie to rate and comment (enter the movie number): ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (movieChoice < 1 || movieChoice > movieList.size())
        {
            System.out.println("Invalid movie selection. Please try again.");
            return;
        }

        Movie selectedMovie = movieList.get(movieChoice - 1);

        // Get user rating
        System.out.print("Enter your rating for the movie (0.0 to 10.0): ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        // Get user comment
        System.out.print("Enter your comment for the movie: ");
        String comment = scanner.nextLine();

        // Add the rating and comment to the movie
        selectedMovie.updateAverageRating(rating);
        selectedMovie.addComment(comment);

        System.out.println("Thank you for rating and commenting on the movie!");
    }
    private static void viewRecommendedMovies()
    {
        System.out.println("--------Recommended Movies -----------");
        System.out.println("Please enter the genre you want to see: ");
        String genretolook = scanner.nextLine();
        //Crée une liste qui permets de storer les films a cherchers
        LinkedList<Movie> recommendedMovies = new LinkedList<>();
        //Créer un loop qui permets de voir tout les films de notre catalogue
        for (Movie movie: movieList)
        { //Vérifier que le film n'as jamais été vu en vérifiant le genre,les notes et les commentaires
            // ( deux sont nécessaire ,car un utilisateur peux noter un film
            // 0/10, pour pas que l'utilisateur aye une recommantion d'un film qu'il as déjà vu mais juste hais
            if (movie.getAverageRating() == 0.0 && movie.getComments().isEmpty() && movie.getGenre().equalsIgnoreCase(genretolook))
            { //Si le film n'as jamais été vu et correspond au genre que l'utilisateur veux voir;
                recommendedMovies.add(movie);
            }
        }
        //Maintentant faut afficher la nouvelle liste:
        if (recommendedMovies.isEmpty())
        { //Si la liste contient aucun film:
            System.out.println("There's no recommended movie for you, pick another genre or you write something random...");
        }
        else
        {
            //Si la liste contient au moins 1 film
            System.out.print("Here's the list you ask: ");
            for (int i = 0; i < recommendedMovies.size(); i++)
            {
                System.out.println((i + 1) + ". " + recommendedMovies.get(i).getTitle());
            }
        }
    }
    private static void searchMovies()
    {
        System.out.println("-------- Search Movies --------");
        System.out.println("Available Movies:");
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1) + ". " + movieList.get(i).getTitle());
        }
        System.out.println();
    }
    private static void reserveTickets()
    {
        System.out.println("-------- Ticket Reservation --------");
        System.out.println("Available Movies:");
        //Loop le catalogue de film et les montres
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            System.out.println((i + 1) + ". " + movie.getTitle() + " (" + movie.getGenre() + ")");
        }
        //Pour choisir le film
        System.out.print("Enter the movie number you want to reserve tickets for: ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        //Si le choix est pas reconnu
        if (movieChoice < 1 || movieChoice > movieList.size()) {
            System.out.println("Invalid movie selection. Please try again.");
            return;
        }
        //L'enlève de la liste
        Movie selectedMovie = movieList.get(movieChoice - 1);

        // Le nombre de tickets que l'utilisateur vas acheter
        System.out.print("Enter the number of tickets to reserve: ");
        int numTickets = scanner.nextInt();
        scanner.nextLine();

        if (numTickets <= 0)
        { //Si l'utilisateur entre une valeur négatife
            System.out.println("Invalid number of tickets. Please enter a positive number.");
            return;
        }
        if (numTickets == 0)
        { //Si l'utilisateur entre 0,
            System.out.println("Why would you pick 0 as an option? You don't buy a ticket if you pick 0...");
            return;
        }
        // Loop pour réserver les sièges
        for (int i = 0; i < numTickets; i++) {
            //Le numéro de l'allée
            System.out.print("Enter the row number for seat " + (i + 1) + ": ");
            int row = scanner.nextInt() - 1;
            //Le numéro du siège
            System.out.print("Enter the seat number for seat " + (i + 1) + ": ");
            int seat = scanner.nextInt() - 1;

            // Sers à reserver les sièges
            boolean success = cinema1.reserveSeat(movieChoice - 1, row, seat);
            //Si ça fonctinne:
            if (success)
            {
                System.out.println("Seat " + (row + 1) + "-" + (seat + 1) + " reserved successfully.");
            }
            //sinon:
            else
            {
                System.out.println("Seat " + (row + 1) + "-" + (seat + 1) + " is already reserved or invalid. Please choose another seat.");
                i--; // Permets de recommencer la selection
            }


        }

        // Calcul le prix selon le nombre de tickets
        double ticketPrice = 10.0;
        double totalPrice = ticketPrice * numTickets;
        //Pour faire une réduction
        double discountAmount = 0.0;
        //Si le user acheter plus qu'un ticket, sinon fais juste couter 10$
        if (numTickets >= 2)
        {
            // Si le client acheter 2+ tickets
            discountAmount = totalPrice * 0.1; // 10% de réductions
            totalPrice *= 0.9; // Apply the discount
        }
        //2pts par billets sont ajouter
        loyaltyPoints += 2 * numTickets;
        //Les details de l'achats, pas nécessaire mais c'est user friendly
        System.out.println("\nReservation Details:");
        System.out.println("Movie: " + selectedMovie.getTitle() + " (" + selectedMovie.getGenre() + ")");
        System.out.println("Number of Tickets: " + numTickets);
        System.out.println("Ticket Price: $" + ticketPrice);
        System.out.println("Total Price: $" + totalPrice);

        //Juste pour montrer le nombre d'argent sauvé
        if (discountAmount > 0)
        {
            System.out.println("You saved: $" + discountAmount);
        }
        System.out.println("\nTickets reserved successfully!");
        //Pour montrer le nombre de points que j'ai
        System.out.println("You have now " + loyaltyPoints +  " " + "loyaltyPoints ");
    }

    private static void checkLoyaltyPoints()
    {
        System.out.println("-------- Loyalty Points --------");
        System.out.println("You currently have " + loyaltyPoints + " loyalty points.");
    }


}
