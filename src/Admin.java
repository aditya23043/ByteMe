import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Admin {

    Scanner scanner = new Scanner(System.in);

    protected void dashboard() throws CustomException, InterruptedException {
        while (true) {
            Header.clearScreen();

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

        Header.top("Menu");
        for (Food food_item : Menu.get_list()) {
            Header.imp(food_item.get_title() + " [ID: " + food_item.get_index() + "]");
            Header.content("Price: ₹" + food_item.get_price() + "\nType: " + food_item.get_category() + "\nAvailable: " + food_item.get_availability());
            if (!Menu.get_list().getLast().equals(food_item)) {
                Header.content("\n");
            }
        }
        Header.bottom();

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
                // add new item
                case 1:
                    System.out.print("\n\tEnter the new food item's name: ");
                    String name = scanner.nextLine();
                    if (name.length() == 0) {
                        throw new CustomException("Food name cannot be blank!");
                    }

                    System.out.print("\tEnter the new food item's price (Rs.): ");
                    int price = scanner.nextInt();
                    scanner.nextLine();
                    if (price <= 0) {
                        throw new CustomException("Food price cannot be less than or equal to zero");
                    }

                    System.out.print("\tIs the food available? (y/n): ");
                    String available = scanner.nextLine();
                    if (!(available.equals("y") || available.equals("n") || available.equals("yes") || available.equals("no"))) {
                        throw new CustomException("Invalid Input!");
                    }

                    FoodType.print();
                    System.out.print("\n\tEnter the food item's category: ");
                    int category_choice = scanner.nextInt();
                    scanner.nextLine();
                    FoodType category = FoodType.NULL;
                    switch (category_choice) {
                        case 1:
                            category = FoodType.BEVERAGE;
                            break;
                        case 2:
                            category = FoodType.SNACK;
                            break;
                        case 3:
                            category = FoodType.MEAL;
                            break;
                        case 4:
                            category = FoodType.EVM;
                            break;
                        case 5:
                            category = FoodType.DESERT;
                            break;
                        case 6:
                            category = FoodType.COMBO;
                            break;
                        case 7:
                            category = FoodType.NULL;
                            break;
                        default:
                            throw new CustomException("Invalid Input!");
                    }
                    Boolean b_available;
                    if(available.equals("y") || available.equals("yes")){
                        b_available = true;
                    } else {
                        b_available = false;
                    }

                    Menu.add(name, price, category, b_available);

                    System.out.println("\n\t\033[32mItem added successfully!\033[0m");
                    TimeUnit.SECONDS.sleep(1);

                    break;

                // update item
                case 2:
                    // ID is written besides the name of the item in the menu list btw
                    System.out.print("\n\tEnter the ID of the item you want to update: ");
                    int update_item_choice = scanner.nextInt();
                    scanner.nextLine();
                    if (update_item_choice < 0 || update_item_choice > Menu.get_list().size() - 1) {
                        throw new CustomException("ID Not Found!");
                    }
                    Food food_item_to_be_updated = Menu.get_list().get(update_item_choice);

                    System.out.print("\tEnter the updated food item title: ");
                    String new_name = scanner.nextLine();
                    if (new_name.length() == 0) {
                        throw new CustomException("Food name cannot be blank!");
                    }
                    food_item_to_be_updated.set_title(new_name);

                    System.out.print("\tEnter the updated food item price: ");
                    int new_price = scanner.nextInt();
                    scanner.nextLine();
                    if (new_price <= 0) {
                        throw new CustomException("Food price cannot be less than or equal to zero");
                    }
                    food_item_to_be_updated.set_price(new_price);

                    FoodType.print();
                    System.out.print("\n\tEnter the updated food item category: ");
                    FoodType new_category = FoodType.NULL;
                    switch (scanner.nextInt()) {
                        case 1:
                            new_category = FoodType.BEVERAGE;
                            break;
                        case 2:
                            new_category = FoodType.SNACK;
                            break;
                        case 3:
                            new_category = FoodType.MEAL;
                            break;
                        case 4:
                            new_category = FoodType.EVM;
                            break;
                        case 5:
                            new_category = FoodType.DESERT;
                            break;
                        case 6:
                            new_category = FoodType.COMBO;
                            break;
                        case 7:
                            new_category = FoodType.NULL;
                        default:
                            throw new CustomException("Invalid Input!");
                    }
                    scanner.nextLine();
                    food_item_to_be_updated.set_category(new_category);

                    System.out.print("\tIs the updated item available? (y/n): ");

                    String new_item_available = scanner.nextLine();
                    if (!(new_item_available.equals("y") || new_item_available.equals("n") || new_item_available.equals("yes") || new_item_available.equals("no"))) {
                        throw new CustomException("Invalid Input!");
                    }
                    Boolean b_new_item_available;
                    if(new_item_available.equals("y") || new_item_available.equals("yes")){
                        b_new_item_available = true;
                    } else {
                        b_new_item_available = false;
                    }
                    food_item_to_be_updated.set_availability(b_new_item_available);

                    break;
                // remove item
                case 3:
                    System.out.print("\n\tEnter the ID of the item you want to update: ");
                    int remove_item_index = scanner.nextInt();
                    scanner.nextLine();
                    if (remove_item_index < 0 || remove_item_index > Menu.get_list().size() - 1) {
                        throw new CustomException("ID Not Found!");
                    }
                    Menu.get_list().remove(remove_item_index);
                    System.out.println("\n\t\033[32mItem removed successfully!\033[0m");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                default:
                    throw new CustomException("Invalid Choice!");
            }

        } catch (InputMismatchException e) {
            // NOTE: Return to the previous menu when any char entered
            return;
        }


    }

    private void manage_orders() {

            Header.clearScreen();
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

