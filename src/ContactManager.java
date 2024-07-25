import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ContactManager {

    private Scanner scanner = new Scanner(System.in);

    public void addContact() throws SQLException {
        System.out.println("Ajouter un contact");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nom: ");
        String name = scanner.nextLine();
        System.out.print("Téléphone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Type (personnel/professionnel): ");
        String type = scanner.nextLine();

        if (type.equalsIgnoreCase("personnel")) {
            addPersonalContact(new PersonalContact(id, name, phone, email));
        } else if (type.equalsIgnoreCase("professionnel")) {
            addProfessionalContact(new ProfessionalContact(id, name, phone, email));
        } else {
            System.out.println("Type de contact invalide.");
        }
    }

    private void addPersonalContact(PersonalContact contact) throws SQLException {
        String insertSQL = "INSERT INTO contacts_personnels (id, name, phone, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, contact.getId());
            preparedStatement.setString(2, contact.getName());
            preparedStatement.setString(3, contact.getPhone());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Contact personnel ajouté avec succès.");
        }
    }

    private void addProfessionalContact(ProfessionalContact contact) throws SQLException {
        String insertSQL = "INSERT INTO contacts_professionnels (id, name, phone, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, contact.getId());
            preparedStatement.setString(2, contact.getName());
            preparedStatement.setString(3, contact.getPhone());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Contact professionnel ajouté avec succès.");
        }
    }
    public void deleteContact() throws SQLException {
        System.out.println("Supprimer un contact");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Type (personnel/professionnel): ");
        String type = scanner.nextLine();

        if (type.equalsIgnoreCase("personnel")) {
            deleteContactFromTable(id, "contacts_personnels");
        } else if (type.equalsIgnoreCase("professionnel")) {
            deleteContactFromTable(id, "contacts_professionnels");
        } else {
            System.out.println("Type de contact invalide.");
        }
    }

    private void deleteContactFromTable(String id, String tableName) throws SQLException {
        String deleteSQL = "DELETE FROM " + tableName + " WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contact supprimé avec succès.");
            } else {
                System.out.println("Aucun contact trouvé avec cet ID.");
            }
        }
    }
}
