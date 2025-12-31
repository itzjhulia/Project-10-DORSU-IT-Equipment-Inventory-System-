import java.util.Scanner;
import java.util.regex.Pattern;


public class DORSUInventorySystem {

    static Scanner sc = new Scanner(System.in);

    // Maximum number of equipment
    static final int MAX = 100;

    // Arrays
    static String[] names = new String[MAX];
    static String[] ids = new String[MAX];
    static String[] departments = new String[MAX];
    static String[] status = new String[MAX];
    static int[] quantity = new int[MAX];

    static int count = 0;

    public static void main(String[] args) {
        int choice;

        do {
            choice = getMenuChoice();

            switch (choice) {
                case 1:
                    addEquipment();
                    break;
                case 2:
                    searchEquipment();
                    break;
                case 3:
                    editEquipment();
                    break;
                case 4:
                    displayAllEquipment();
                    break;
                case 5:
                    computeTotalQuantity();
                    break;
                case 6:
                    System.out.println("Exiting program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    // ================= MENU =================
    static int getMenuChoice() {
        System.out.println("\n--- DORSU IT Equipment Inventory System ---");
        System.out.println("1. Add IT Equipment");
        System.out.println("2. Search Equipment by Name or ID");
        System.out.println("3. Edit Equipment Information");
        System.out.println("4. Display All Equipment Records");
        System.out.println("5. Compute Total Quantity");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
        return sc.nextInt();
    }

    // ================= ADD =================
    static void addEquipment() {
        sc.nextLine();

        System.out.print("Enter Equipment Name: ");
        String name = sc.nextLine();
        if (!validateName(name)) {
            System.out.println("Invalid name!");
            return;
        }

        System.out.print("Enter Equipment ID (IT-2024-000): ");
        String id = sc.nextLine();
        if (!validateEquipmentID(id)) {
            System.out.println("Error: Invalid equipment ID format");
            return;
        }

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        if (!validateDepartment(dept)) {
            System.out.println("Invalid department!");
            return;
        }

        System.out.print("Enter Status (Working/Damaged): ");
        String stat = sc.nextLine();
        if (!validateStatus(stat)) {
            System.out.println("Invalid status!");
            return;
        }

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        names[count] = name;
        ids[count] = id;
        departments[count] = dept;
        status[count] = stat;
        quantity[count] = qty;

        count++;
        System.out.println("Successfully added");
    }

    // ================= SEARCH =================
    static void searchEquipment() {
        sc.nextLine();
        System.out.print("Enter Equipment Name or ID: ");
        String key = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (names[i].equalsIgnoreCase(key) || ids[i].equalsIgnoreCase(key)) {
                System.out.println("\nEquipment Found:");
                System.out.println("Name: " + names[i]);
                System.out.println("ID: " + ids[i]);
                System.out.println("Department: " + departments[i]);
                System.out.println("Status: " + status[i]);
                System.out.println("Quantity: " + quantity[i]);
                return;
            }
        }
        System.out.println("Equipment not found");
    }

    // ================= EDIT =================
    static void editEquipment() {
        sc.nextLine();
        System.out.print("Enter Equipment ID to edit: ");
        String id = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (ids[i].equalsIgnoreCase(id)) {
                System.out.print("Enter new Status (Working/Damaged): ");
                String newStatus = sc.nextLine();
                if (!validateStatus(newStatus)) {
                    System.out.println("Invalid status!");
                    return;
                }
                status[i] = newStatus;
                System.out.println("Successfully updated");
                return;
            }
        }
        System.out.println("Equipment not found");
    }

    // ================= DISPLAY =================
    static void displayAllEquipment() {
        if (count == 0) {
            System.out.println("No equipment records.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("\nEquipment " + (i + 1));
            System.out.println("Name: " + names[i]);
            System.out.println("ID: " + ids[i]);
            System.out.println("Department: " + departments[i]);
            System.out.println("Status: " + status[i]);
            System.out.println("Quantity: " + quantity[i]);
        }
    }

    // ================= COMPUTE =================
    static void computeTotalQuantity() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += quantity[i];
        }
        System.out.println("Total Quantity of All Equipment: " + total);
    }

    // ================= VALIDATIONS =================
    static boolean validateName(String name) {
        return name.matches("[A-Za-z ]+");
    }

    static boolean validateEquipmentID(String id) {
        return Pattern.matches("IT-2024-\\d{3}", id);
    }

    static boolean validateDepartment(String dept) {
        return dept.length() >= 2;
    }

    static boolean validateStatus(String stat) {
        return stat.equalsIgnoreCase("Working") || stat.equalsIgnoreCase("Damaged");
        
    }
}
