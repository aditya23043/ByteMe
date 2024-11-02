import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {

    Scanner scanner = new Scanner(System.in);

    private void dashboard() {
        Header.clearScreen();
        Header.title();
        Header.top("Admin Dashboard");

        Header.content("1. Manage Menu");
        Header.content("2. Manage Orders");
        Header.content("3. Daily Sales Report");
        Header.content("q. Quit");

        // Header.content("1. Add new item");
        // Header.content("2. Update item");
        // Header.content("3. Remove items");
        // Header.content("4. View Pending Orders");
        // Header.content("5. Update order status");
        // Header.content("6. Process refunds");
        // Header.content("7. Handle special requests");
        // Header.content("8. Order priority");

        Header.bottom();
        int choice = 0;

        System.out.print("\n\t>> Choose: ");
        try{
            choice = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            return;
        }

        handle_choice(choice);
    }

    private void handle_choice(int choice) {

        switch (choice) {
            case 1:
                manage_menu();
                break;
            case 2:
                manage_orders();
                break;
            case 3:
                sales_report();
                break;
        }

    }

    private void manage_menu() {

        Header.clearScreen();
        Header.title();
        Header.top("ADMIN: Manage Menu");

        Header.content("1. Add new item");
        Header.content("2. Update item");
        Header.content("3. Remove items");
        Header.content("q. Quit");

        Header.bottom();
    }

    private void manage_orders() {

        Header.clearScreen();
        Header.title();
        Header.top("ADMIN: Manage Orders");

        Header.content("4. View Pending Orders");
        Header.content("5. Update order status");
        Header.content("6. Process refunds");
        Header.content("7. Handle special requests");

        Header.bottom();
    }

}

