import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
                Util.throw_error("Invalid Choice!");
        }
    }

    private void manage_menu() throws CustomException, InterruptedException {

        while (true) {
            Header.clearScreen();

            Header.top("Menu");
            if (Menu.get_list().isEmpty()) {
                Header.content_center("- NONE -");
            }
            for (Food food_item : Menu.get_list()) {
                Header.imp(food_item.get_title() + " [ID: " + food_item.get_index() + "]");
                Header.content("Price: ₹" + food_item.get_price() + "\nType: " + food_item.get_category()
                        + "\nAvailable: " + food_item.get_availability());
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
                            Util.throw_error("Food name cannot be blank!");
                        }

                        System.out.print("\tEnter the new food item's price (Rs.): ");
                        int price = scanner.nextInt();
                        scanner.nextLine();
                        if (price <= 0) {
                            Util.throw_error("Food price cannot be less than or equal to zero");
                        }

                        System.out.print("\tIs the food available? (y/n): ");
                        String available = scanner.nextLine();
                        if (!(available.equals("y") || available.equals("n") || available.equals("yes")
                                || available.equals("no"))) {
                            Util.throw_error("Invalid Input!");
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
                                Util.throw_error("Invalid Input!");
                        }
                        Boolean b_available;
                        if (available.equals("y") || available.equals("yes")) {
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
                        if (Menu.get_list().isEmpty()) {
                            Util.throw_error("No more items left!");
                            break;
                        }
                        System.out.print("\n\tEnter the ID of the item you want to update: ");
                        int update_item_choice = scanner.nextInt();
                        scanner.nextLine();
                        // if (update_item_choice < 0 || update_item_choice > Menu.get_list().size()) {
                        //     Util.throw_error("ID Not Found!");
                        // }
                        Food food_item_to_be_updated;

                        for (Food food : Menu.get_list()) {
                            if (food.get_index() == update_item_choice) {
                                food_item_to_be_updated = food;
                                System.out.print("\tEnter the updated food item title: ");
                                String new_name = scanner.nextLine();
                                if (new_name.length() == 0) {
                                    Util.throw_error("Food name cannot be blank!");
                                }
                                food_item_to_be_updated.set_title(new_name);

                                System.out.print("\tEnter the updated food item price: ");
                                int new_price = scanner.nextInt();
                                scanner.nextLine();
                                if (new_price <= 0) {
                                    Util.throw_error("Food price cannot be less than or equal to zero");
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
                                        Util.throw_error("Invalid Input!");
                                }
                                scanner.nextLine();
                                food_item_to_be_updated.set_category(new_category);

                                System.out.print("\tIs the updated item available? (y/n): ");

                                String new_item_available = scanner.nextLine();
                                if (!(new_item_available.equals("y") || new_item_available.equals("n")
                                        || new_item_available.equals("yes") || new_item_available.equals("no"))) {
                                    Util.throw_error("Invalid Input!");
                                }
                                Boolean b_new_item_available;
                                if (new_item_available.equals("y") || new_item_available.equals("yes")) {
                                    b_new_item_available = true;
                                } else {
                                    b_new_item_available = false;
                                }
                                food_item_to_be_updated.set_availability(b_new_item_available);

                                System.out.println("\n\t\033[32mItem removed successfully!\033[0m");
                                TimeUnit.SECONDS.sleep(1);

                                break;
                            }
                            else if (Menu.get_list().getLast().equals(food)) {
                                Util.throw_error("ID Not Found!");
                            }
                        }

                        break;
                    // remove item
                    case 3:
                        if (Menu.get_list().isEmpty()) {
                            Util.throw_error("No more items left to remove!");
                            break;
                        }
                        System.out.print("\n\tEnter the ID of the item you want to remove: ");
                        int remove_item_index = scanner.nextInt();
                        scanner.nextLine();
                        for (Food food : Menu.get_list()) {
                            if (food.get_index() == remove_item_index) {
                                Menu.get_list().remove(food);

                                // status -> denied
                                for (Order order : Order.get_list()) {
                                    for(FoodPair foodPair : order.get_food_list()){
                                        if (foodPair.food.equals(food)) {
                                            if (order.get_status().equals(OrderStatus.PROCESSING.toString())) {
                                                order.set_status(OrderStatus.DENIED);
                                                break;
                                            }
                                        }
                                    }
                                }

                                System.out.println("\n\t\033[32mItem removed successfully!\033[0m");
                                TimeUnit.SECONDS.sleep(1);
                                break;
                            }
                            else if (Menu.get_list().getLast().equals(food)) {
                                Util.throw_error("ID Not Found!");
                            }
                        }
                        break;
                    default:
                        Util.throw_error("Invalid Choice!");
                        break;
                }

            } catch (InputMismatchException e) {
                // NOTE: Return to the previous menu when any char entered
                return;
            } finally {
                MainFrame.render();
            }

        }

    }

    private void manage_orders() {

        while (true) {
            Header.clearScreen();

            Header.top("Orders");
            for (Order order : Order.get_list()) {
                Header.content("Order ID: " + order.get_id());
                Header.content("Order Qty: " + order.get_qty());
                Header.content("Order Amount: ₹" + order.get_amt() + "\n");
                Header.content("Status: " + order.get_status() + "\n");
                for (FoodPair food_pair : order.get_food_list()) {
                    Header.content("\t ID:" + food_pair.food.get_index() + " " + food_pair.food.get_title()
                            + " : " + food_pair.food.get_price() + " x "
                            + food_pair.quantity);
                }
                if (Order.get_list().size() > 1 && !Order.get_list().getLast().equals(order)) {
                    Header.line();
                }
            }
            if (Order.get_list().isEmpty()) {
                Header.content_center("- NONE -");
            }
            Header.bottom();

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
                break;
            }

            switch (choice) {
                case 1:
                    int order_processing_count = 0;
                    Header.top("Pending Orders");
                    ArrayList<Order> order_list = new ArrayList<>();
                    for (Order order : Order.get_list()) {
                        order_list.add(order);
                        if (order.get_status().equals(OrderStatus.PROCESSING.toString())) {
                            order_processing_count++;
                        }
                    }
                    Collections.sort(order_list, new OrderComparator());
                    for (Order order : order_list) {
                        if (order.get_status().equals(OrderStatus.PROCESSING.toString())) {
                            Header.content("Order ID: " + order.get_id());
                            Header.content("Order Qty: " + order.get_qty());
                            Header.content("Order Amount: ₹" + order.get_amt() + "\n");
                            Header.content("Status: " + order.get_status() + "\n");
                            for (FoodPair food_pair : order.get_food_list()) {
                                Header.content("\t ID:" + food_pair.food.get_index() + " " + food_pair.food.get_title()
                                        + " : " + food_pair.food.get_price() + " x "
                                        + food_pair.quantity);
                            }
                            if (order_processing_count > 1 && !order_list.getLast().equals(order)) {
                                Header.line();
                            }
                        }
                    }
                    if (order_processing_count == 0) {
                        Header.content_center("- NONE -");
                    }
                    Header.bottom();
                    System.out.print("\n\tPress enter to continue...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    if (Order.get_list().isEmpty()) {
                        Util.throw_error("There are no orders as of now!");
                        break;
                    }
                    System.out.print("\n\tEnter the ID of the order you want to update the status of: ");
                    int order_id = scanner.nextInt();
                    scanner.nextLine();

                    for (Order order : Order.get_list()) {
                        if (order.get_id() == order_id) {
                            order.set_status();
                            break;
                        } else if (Order.get_list().getLast().equals(order)) {
                            Util.throw_error("Order not found!");
                        }
                    }

                    break;

                case 3:
                    int order_cancelled_count = 0;
                    Header.top("Refunds");
                    ArrayList<Order> _order_list = new ArrayList<>();
                    for (Order order : Order.get_list()) {
                        if (order.get_status().equals(OrderStatus.CANCELLED.toString()) || order.get_status().equals(OrderStatus.DENIED.toString())) {
                            _order_list.add(order);
                            order_cancelled_count++;
                        }
                    }
                    for (Order order : _order_list) {
                        Header.content("Order ID: " + order.get_id());
                        Header.content("Amount to be refunded: ₹" + order.get_amt());
                        if (order_cancelled_count > 1 && !_order_list.getLast().equals(order)) {
                            Header.line();
                        }
                    }
                    if (Order.get_list().isEmpty()) {
                        Header.content_center("- NONE -");
                    }
                    Header.bottom();
                    System.out.print("\n\tPress enter to continue...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    Header.top("Special Requests");
                    for (Order order : Order.get_list()) {
                        Header.content("Order ID: " + order.get_id());
                        Header.content("Request: " + order.get_special_reqs());
                        if (Order.get_list().size() > 1 && !Order.get_list().getLast().equals(order)) {
                            Header.line();
                        }
                    }
                    if (Order.get_list().isEmpty()) {
                        Header.content_center("- NONE -");
                    }
                    Header.bottom();
                    System.out.print("\n\tPress enter to continue...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private void sales_report() {
    }

}


class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        if (a.get_ctype().equals(CustomerType.VIP) && b.get_ctype().equals(CustomerType.REGULAR)) {
            return 1;
        }
        else if (a.get_ctype().equals(CustomerType.VIP) && b.get_ctype().equals(CustomerType.VIP)) {
            return 0;
        }
        else if (a.get_ctype().equals(CustomerType.REGULAR) && b.get_ctype().equals(CustomerType.VIP)) {
            return -1;
        }
        else if (a.get_id() == b.get_id()) {
            return 0;
        }
        else if (a.get_id() > b.get_id()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
