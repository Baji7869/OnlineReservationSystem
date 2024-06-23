import java.util.*;

class Reservation {
    String trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;
    String pnr;

    Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to, String pnr) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
        this.pnr = pnr;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + ", Train: " + trainName + " (" + trainNumber + "), Class: " + classType + ", Date: " + dateOfJourney + ", From: " + from + ", To: " + to;
    }
}

public class OnlineReservationSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        users.put("user1", "password1");
        users.put("user2", "password2");

        if (login()) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nOnline Reservation System");
                System.out.println("1. Reserve Ticket");
                System.out.println("2. Cancel Ticket");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        reserveTicket();
                        break;
                    case 2:
                        cancelTicket();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        } else {
            System.out.println("Invalid login credentials.");
        }
    }

    private static boolean login() {
        System.out.print("Enter login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return password.equals(users.get(loginId));
    }

    private static void reserveTicket() {
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter Class Type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter From (Place): ");
        String from = scanner.nextLine();
        System.out.print("Enter To (Destination): ");
        String to = scanner.nextLine();
        String pnr = UUID.randomUUID().toString().substring(0, 8);

        Reservation reservation = new Reservation(trainNumber, trainName, classType, dateOfJourney, from, to, pnr);
        reservations.put(pnr, reservation);
        System.out.println("Reservation successful. Your PNR is " + pnr);
    }

    private static void cancelTicket() {
        System.out.print("Enter PNR Number: ");
        String pnr = scanner.nextLine();
        Reservation reservation = reservations.get(pnr);

        if (reservation != null) {
            System.out.println("Reservation Details: " + reservation);
            System.out.print("Confirm cancellation (yes/no): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found with PNR " + pnr);
        }
    }
}
