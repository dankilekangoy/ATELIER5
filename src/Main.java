import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Supprimer un contact");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        manager.addContact();
                        break;
                    case 2:
                        manager.deleteContact();
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez choisir une option valide.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
