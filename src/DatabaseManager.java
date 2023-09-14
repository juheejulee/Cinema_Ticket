import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:C:/database/my_database.db"; // SQLite DB is located here
    private Connection connection;

    // Constructor to init the DatabaseManager and establish a connection
    public DatabaseManager() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the database
            connection = DriverManager.getConnection(DATABASE_URL);

            System.out.println("Connected to SQLite DB.");

            // Create tables and set up the database schema
            createTables();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create tables and set up the DB schema
    private void createTables() {
        try (Statement stmt = connection.createStatement()) {
            // Create table for movies
            String createMovieTableSQL = "CREATE TABLE IF NOT EXISTS movies (" +
                    "id INTEGER PRIMARY KEY," +
                    "title TEXT NOT NULL," +
                    "duration INTEGER NOT NULL," +
                    "classification TEXT NOT NULL," +
                    "synopsis TEXT NOT NULL," +
                    "averageRating REAL NOT NULL" +
                    ")";
            stmt.executeUpdate(createMovieTableSQL);

            // Add more create table statements here, if needed.

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add more methods for inserting data, querying the DB, etc.
    // Close the connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to SQLite database closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test the DatabaseManager class
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        // You can now use the DatabaseManager to interact with the SQLite database.
        // For example, you can insert data into the tables, query data, etc.

        // Don't forget to close the connection when done with the database.
        databaseManager.closeConnection();
    }
}
