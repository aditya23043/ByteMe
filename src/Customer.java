import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Customer {

    // util fields
    enum CUSTOMER_TYPE {
        VIP,
        REGULAR
    }

    Scanner scanner = new Scanner(System.in);

    // actual fields
    private CUSTOMER_TYPE cType;
    private ArrayList<FoodPair> shopping_cart = new ArrayList<>();

    private void add_item(int id, int qty) throws CustomException {
        Boolean contains = false;
        for (Food food : Menu.get_list()) {
            if (food.get_index() == id) {
                if (!food.get_availability()) {
                    throw new CustomException("Food Not Available as of this moment!");
                }
                for(FoodPair food_pair : shopping_cart){
                    if (food_pair.food.equals(food)) {
                        contains = true;
                        food_pair.quantity += qty;
                        break;
                    }
                }
                if (!contains) {
                    shopping_cart.add(new FoodPair(Menu.get_list().get(id - 1), qty));
                }
                break;
            }
            else if (Menu.get_list().getLast().equals(food)) {
                throw new CustomException("Item not found!");
            }
        }
    }

    public void dashboard() throws CustomException {
        while (true) {
            Header.clearScreen();
            Header.top("Customer Dashboard");

            Header.content("1. Place Order");
            Header.content("2. Orders");
            Header.content("3. Item Review");
            Header.content("q. Quit");

            Header.bottom();

            int choice = 0;

            System.out.print("\n\t\033[36m>>\033[0m Choose: ");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                break;
            }
            handle_choice(choice);
        }
    }

    private void handle_choice(int choice) throws CustomException {
        switch (choice) {
            // browse menu
            case 1:

                ArrayList<Food> menu_list = new ArrayList<Food>();
                for (Food something : Menu.get_list()) {
                    menu_list.add(something);
                }
                String table_title = "Menu";
                Boolean sorted = false;

                while (true) {
                    Header.clearScreen();

                    Header.top(table_title);
                    for (Food food_item : menu_list) {
                        Header.imp(food_item.get_title() + " [ID: " + food_item.get_index() + "]");
                        Header.content("Price: ₹" + food_item.get_price() + "\nType: " + food_item.get_category()
                                + "\nAvailable: " + food_item.get_availability());
                        if (!menu_list.getLast().equals(food_item)) {
                            Header.content("\n");
                        }
                    }
                    if (menu_list.isEmpty()) {
                        Header.content_center("- NONE -");
                    }
                    Header.bottom();

                    Header.top("Options");
                    Header.content("1. Search");
                    Header.content("2. Filter by category");
                    Header.content("3. Sort by price");
                    Header.content("4. Original Menu");
                    Header.content("5. Add to Cart");
                    Header.content("6. View Cart");
                    Header.content("7. Checkout");
                    Header.content("q. Quit");
                    Header.bottom();

                    choice = 0;
                    System.out.print("\n\t\033[36m>>\033[0m Choose: ");
                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        break;
                    }
                    scanner.nextLine();

                    switch (choice) {
                        // case insensitive pattern matching (wanted to implement fuzzy finding but not able to rn)
                        case 1:
                            menu_list.clear();
                            for (Food something : Menu.get_list()) {
                                menu_list.add(something);
                            }
                            System.out.print("\n\tEnter the pattern to match: ");
                            String pattern = scanner.nextLine();
                            menu_list = menu_list.stream().filter(x -> x.get_title().toLowerCase().contains(pattern.toLowerCase()) || x.get_category().toString().toLowerCase().contains(pattern.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
                            table_title = "Menu [SEARCH]";
                            break;
                        case 2:
                            FoodType.print();
                            FoodType _category = FoodType.NULL;
                            System.out.print("\n\tChoose filter: ");
                            switch (scanner.nextInt()) {
                                case 1:
                                    _category = FoodType.BEVERAGE;
                                    table_title = "Menu [Filter:BEVERAGE]";
                                    break;
                                case 2:
                                    _category = FoodType.SNACK;
                                    table_title = "Menu [Filter:SNACK]";
                                    break;
                                case 3:
                                    _category = FoodType.MEAL;
                                    table_title = "Menu [Filter:MEAL]";
                                    break;
                                case 4:
                                    _category = FoodType.EVM;
                                    table_title = "Menu [Filter:EVM]";
                                    break;
                                case 5:
                                    _category = FoodType.DESERT;
                                    table_title = "Menu [Filter:DESERT]";
                                    break;
                                case 6:
                                    _category = FoodType.COMBO;
                                    table_title = "Menu [Filter:COMBO]";
                                    break;
                                case 7:
                                    _category = FoodType.NULL;
                                    table_title = "Menu [Filter:NULL]";
                                    break;
                                default:
                                    throw new CustomException("Invalid Input!");
                            }
                            scanner.nextLine();
                            final FoodType category = _category;
                            // NOTE: this is the cool stuff yooo
                            // i hate java but this stream and comparator stuff in java i really like
                            // this is somewhat like map in haskell where you can parse through and filter out stuff in just one line which is actually way more verbose than i thought it would be
                            // btw if you are reading this, you should try out haskell, it really makes understanding of basic "functions" (look what i did there) intuitive
                            menu_list = menu_list.stream().filter(x -> x.get_category() == category).collect(Collectors.toCollection(ArrayList::new));
                            break;
                        case 3:
                            if (sorted) {
                                System.out.println("\n\t\033[31m Already Sorted!\033[0m");
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Collections.sort(menu_list, new PriceComparator());
                                table_title = "Menu [Sort: Price]";
                                sorted = true;
                            }
                            break;
                        case 4:
                            menu_list.clear();
                            for (Food something : Menu.get_list()) {
                                menu_list.add(something);
                            }
                            table_title = "Menu";
                            sorted = false;
                            break;
                        case 5:
                            System.out.print("\n\tEnter Food Item ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("\n\tEnter the Quantity: ");
                            int qty = scanner.nextInt();
                            scanner.nextLine();
                            add_item(id,qty);
                            System.out.print("\n\t\033[32mSuccessfully added item to cart!\033[0m");
                            try {
                                TimeUnit.MILLISECONDS.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            break;
                        case 6:
                            Header.top("Shopping Cart");
                            for (FoodPair food_pair : shopping_cart) {
                                Header.content("ID "+food_pair.food.get_index()+" : "+food_pair.food.get_title()+" : ₹"+food_pair.food.get_price()+" x "+food_pair.quantity);
                            }
                            if (shopping_cart.isEmpty()) {
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
                        case 7:
                            if (this.shopping_cart.isEmpty()) {
                                throw new CustomException("Shopping cart is empty!");
                            }
                            else {
                                Order order = new Order(this.shopping_cart);
                                System.out.println(Order.get_list().size());
                                System.out.print("\n\t\033[32mSuccessfully placed the order!\033[0m");
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                shopping_cart.clear();
                            }

                            break;
                        default:
                            throw new CustomException("Invalid Choice!");
                    }

                }
                break;
            // orders
            case 2:
                Header.clearScreen();
                Header.top("Orders");
                for (Order order : Order.get_list()) {
                    Header.content("Order ID: " + order.get_id());
                    Header.content("Order Qty: " + order.get_qty()+"\n");
                    Header.content("Status: " + order.get_status()+"\n");
                    for (FoodPair food_pair : order.get_food_list()) {
                        Header.content("\t ID:"+food_pair.food.get_index()+" "+food_pair.food.get_title() + " : " + food_pair.food.get_price() + " x "
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
                System.out.print("\n\tPress enter to continue...");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            // item review
            case 3:
               break;
            default:
                throw new CustomException("Invalid Choice!");
        }
    }

}

class PriceComparator implements Comparator<Food> {
    @Override
    public int compare(Food a, Food b) {
        if (a.get_price() == b.get_price()) {
            return 0;
        }
        else if (a.get_price() > b.get_price()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}

