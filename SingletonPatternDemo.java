// Singleton Class
class DatabaseConnection {
  private static DatabaseConnection instance;

  private DatabaseConnection() {
      // Private constructor to prevent instantiation
  }

  public static DatabaseConnection getInstance() {
      if (instance == null) {
          instance = new DatabaseConnection();
      }
      return instance;
  }

  public void connect() {
      System.out.println("Connecting to the database...");
  }
}

// Demo
public class SingletonPatternDemo {
  public static void main(String[] args) {
      DatabaseConnection connection1 = DatabaseConnection.getInstance();
      DatabaseConnection connection2 = DatabaseConnection.getInstance();

      connection1.connect();
      System.out.println(connection1 == connection2); // Will print true
  }
}
