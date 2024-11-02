import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Admin {

    Scanner scanner = new Scanner(System.in);

    protected void dashboard() throws CustomException, InterruptedException {
        while (true) {
            Header.clearScreen();
            Header.title();

            Header.top("Menu");
                
            }
            Header.bottom();

            Header.top("Admin Dashboard");
            Header.content("1. Manage Menu");
            Header.content("2. Manage Orders");
            Header.content("3. Daily Sales Report");
            Header.content("q. Quit");
            Header.bottom();

            int choice = 0;

            System.out.print("\n\t\033[36m>>\033[0m Choose: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                break;
            }

            handle_choice(choice);
        }
    }

    private void handle_choice(int choice) throws CustomException, InterruptedException {

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
            default:
                throw new CustomException("Invalid Choice!");
        }
    }

    private void manage_menu() throws CustomException, InterruptedException {

        Header.clearScreen();
        Header.title();
        Header.top("Manage Menu");

        Header.content("1. Add new item");
        Header.content("2. Update item");
        Header.content("3. Remove items");
        Header.content("q. Quit");

        Header.bottom();

        int choice = 0;

        System.out.print("\n\t\033[36m>>\033[0m Choose: ");
        try {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\n\tEnter the new food item's name: ");
                    String name = scanner.nextLine();
                    if (name.length() == 0) {
                        throw new CustomException("Food name cannot be blank!");
                    }

                    System.out.print("\tEnter the new food item's price (Rs.): ");
                    int price = scanner.nextInt();
                    if (price <= 0) {
                        throw new CustomException("Food price cannot be less than or equal to zero");
                    }
                    scanner.nextLine();

                    System.out.print("\tIs the food available? (y/n): ");
                    String available = scanner.nextLine();
                    if (!(available.equals("y") || available.equals("n") || available.equals("yes") || available.equals("no"))) {
                        throw new CustomException("Invalid Input!");
                    }

                    Header.top("Food Category");
                    Header.content("1. BEVERAGE");
                    Header.content("2. SNACK");
                    Header.content("3. MEAL");
                    Header.content("4. EVM");
                    Header.content("5. DESERT");
                    Header.content("6. COMBO");
                    Header.bottom();
                    System.out.print("\n\tEnter the food item's category: ");
                    String category = scanner.nextLine();
                    if (!(category.equals("BEVERAGE") || category.equals("SNACK") || category.equals("MEAL") || category.equals("EVM") || category.equals("DESERT") || category.equals("COMBO"))) {
                        throw new CustomException("Invalid Input!");
                    }
                    Boolean b_available;
                    if(available.equals("y") || available.equals("yes")){
                        b_available = true;
                    } else {
                        b_available = false;
                    }

                    FoodType food_type = FoodType.valueOf(category);

                    Menu.add(name, price, food_type, b_available);

                    System.out.println("\n\t\033[32mItem added successfully!\033[0m");
                    TimeUnit.SECONDS.sleep(1);

                    break;
                case 2:
                    break;
                case 3:
                    break;
            }

        } catch (InputMismatchException e) {
            // NOTE: Return to the previous menu when any char entered
            return;
        }


    }

    private void manage_orders() {

            Header.clearScreen();
            Header.title();
            Header.top("Manage Orders");

            Header.content("1. View Pending Orders");
            Header.content("2. Update order status");
            Header.content("3. Process refunds");
            Header.content("4. Handle special requests");
            Header.content("q. Quit");

            Header.bottom();

            int choice = 0;

            System.out.print("\n\t\033[36m>>\033[0m Choose: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                return;
            }
        }

    private void sales_report() {
    }

}

