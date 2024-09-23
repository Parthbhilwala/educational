import java.util.Scanner;

// Logger Interface (SOLID Principle: Dependency Inversion)
interface ILogger {
    void log(String message);
}

// Console Logger Implementation
class ConsoleLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Custom Exception for Transient Errors
class TransientError extends Exception {
    public TransientError(String message) {
        super(message);
    }
}

// Rocket Class following Single Responsibility and Open/Closed Principles
class Rocket {
    private int fuel = 100;
    private int altitude = 0;
    private int speed = 0;
    private int stage = 1;
    private ILogger logger;
    private boolean preLaunchComplete = false;

    public Rocket(ILogger logger) {
        this.logger = logger;
    }

    // Pre-Launch Checks
    public void startChecks() {
        logger.log("Pre-Launch Checks: All systems are 'Go' for launch.");
        preLaunchComplete = true;
    }

    // Launch rocket
    public void launch() throws Exception {
        if (!preLaunchComplete) {
            throw new Exception("Cannot launch without completing pre-launch checks!");
        }
        logger.log("Launch initiated...");
        simulateFlight(1);
    }

    // Fast forward the simulation
    public void fastForward(int seconds) {
        simulateFlight(seconds);
    }

    // Simulate the rocket's flight by X seconds
    private void simulateFlight(int seconds) {
        try {
            for (int i = 0; i < seconds; i++) {
                if (fuel <= 0) {
                    throw new TransientError("Mission Failed due to insufficient fuel.");
                }
                updateStatus();
                if (altitude >= 100) {
                    logger.log("Orbit achieved! Mission Successful.");
                    return;
                }
            }
        } catch (TransientError e) {
            logger.log(e.getMessage());
        } catch (Exception e) {
            logger.log("An unexpected error occurred during flight.");
        }
    }

    // Update rocket parameters every second
    private void updateStatus() {
        fuel -= 10;  // Decrease fuel by 10% per second
        altitude += 10;  // Increase altitude by 10 km per second
        speed += 1000;  // Increase speed by 1000 km/h per second

        if (altitude >= 50 && stage == 1) {
            logger.log("Stage 1 complete. Separating stage. Entering Stage 2.");
            stage = 2;
        }

        logger.log("Stage: " + stage + ", Fuel: " + fuel + "%, Altitude: " + altitude + " km, Speed: " + speed + " km/h");
    }
}

// Rocket Launch Simulator Class
class RocketLaunchSimulator {
    private Rocket rocket;

    public RocketLaunchSimulator() {
        ILogger logger = new ConsoleLogger();
        this.rocket = new Rocket(logger);
    }

    // Handle user commands
    public void handleCommand(String command) {
        String[] input = command.split(" ");
        String action = input[0];
        String param = input.length > 1 ? input[1] : "";

        try {
            switch (action) {
                case "start_checks":
                    rocket.startChecks();
                    break;
                case "launch":
                    rocket.launch();
                    break;
                case "fast_forward":
                    if (!param.isEmpty()) {
                        int seconds = Integer.parseInt(param);
                        rocket.fastForward(seconds);
                    } else {
                        throw new Exception("No time provided for fast forward.");
                    }
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        } catch (Exception e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        RocketLaunchSimulator simulator = new RocketLaunchSimulator();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter commands: ");
        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            simulator.handleCommand(command);
        }
        scanner.close();
    }
}
