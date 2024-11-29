import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.JFrame;

public class Customer {

    // util fields
    Scanner scanner = new Scanner(System.in);
    int id;

    // cart storage stuff
    String cart_db_filename_prefix = "/home/adi/repo/ByteMe/src/database/tmp/";

    FileInputStream file_in;
    FileOutputStream file_out;

    File cart_temp;

    // actual fields
    private String username;
    private String password;

    private CustomerType cType = CustomerType.REGULAR;
    private ArrayList<FoodPair> shopping_cart = new ArrayList<>();

    Customer(String _username, String _password) {
        this.username = _username;
        this.password = _password;

        //cart storage temp file
        try{
            cart_temp = File.createTempFile("user_"+username+"_", ".tmp", new File(cart_db_filename_prefix));
            file_in = new FileInputStream(cart_temp);
            file_out = new FileOutputStream(cart_temp);
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void add_item(int id, int qty) throws CustomException {
        Boolean contains = false;
        for (Food food : Menu.get_list()) {
            if (food.get_index() == id) {
                if (!food.get_availability()) {
                    Util.throw_error("Food Not Available as of this moment!");
                    break;
                }
                for(FoodPair food_pair : shopping_cart){
                    if (food_pair.food.equals(food)) {
                        contains = true;
                        if (qty > food.get_stock()) {
                            Util.throw_error("Not Enough Stock!");
                            break;
                        }
                        food.set_stock(food.get_stock() - qty);
                        food_pair.quantity += qty;
                        System.out.print("\n\t\033[32mSuccessfully added item to cart!\033[0m");
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                if (!contains) {
                    if (qty > food.get_stock()) {
                        Util.throw_error("Not Enough Stock!");
                        break;
                    }
                    food.set_stock(food.get_stock() - qty);
                    shopping_cart.add(new FoodPair(food, qty));
                    System.out.print("\n\t\033[32mSuccessfully added item to cart!\033[0m");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            else if (Menu.get_list().getLast().equals(food)) {
                Util.throw_error("Item not found!");
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
            Header.content("4. Upgrade to VIP");
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
                    if (menu_list.isEmpty()) {
                        Header.content_center("- NONE -");
                    } else {
                        for (Food food_item : menu_list) {
                            Header.imp(food_item.get_title() + " [ID: " + food_item.get_index() + "]");
                            Header.content("Price: ₹" + food_item.get_price() + " │ Type: " + food_item.get_category()
                                    + " │ Available: " + food_item.get_availability() + " │ Stock: " + food_item.get_stock());
                            if (!menu_list.getLast().equals(food_item)) {
                                Header.content("\n");
                            }
                        }
                    }
                    Header.bottom();

                    Header.top("Shopping Cart");

                    // retrieve the cart data from temp file if not null
                    // try {
                    //     if (cart_temp.exists() && cart_temp.length() > 0) {
                    //         ObjectInputStream in = new ObjectInputStream(file_in);
                    //         shopping_cart = (ArrayList<FoodPair>) in.readObject();
                    //     }
                    // } catch (ClassNotFoundException | IOException e) {
                    //     e.printStackTrace();
                    // }

                    if (shopping_cart.isEmpty()) {
                        Header.content_center("- NONE -");
                    } else {
                        for (FoodPair food_pair : shopping_cart) {
                            Header.content("ID "+food_pair.food.get_index()+" : "+food_pair.food.get_title()+" : ₹"+food_pair.food.get_price()+" x "+food_pair.quantity);
                        }
                    }
                    int total_price = 0;
                    for (FoodPair food_pair : shopping_cart) {
                        total_price += (food_pair.food.get_price()*food_pair.quantity);
                    }
                    Header.line();
                    Header.content_center("Total Price: ₹"+total_price);
                    Header.bottom();

                    Header.top("Options");
                    Header.content("1. Search menu");
                    Header.content("2. Filter menu by category");
                    Header.content("3. Sort menu by price");
                    Header.content("4. Original Menu");
                    Header.content("5. Add to Cart");
                    Header.content("6. Remove Item from Cart");
                    Header.content("7. Modify Item Qty from Cart");
                    Header.content("8. Checkout");
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
                                    Util.throw_error("Invalid Choice");
                                    break;
                            }
                            scanner.nextLine();
                            final FoodType category = _category;
                            // NOTE: this is the cool stuff yooo
                            //() i hate java but this stream and comparator stuff in java i really like
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
                            id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("\n\tEnter the Quantity: ");
                            int qty = scanner.nextInt();
                            if (qty <= 0) {
                                Util.throw_error("Quantity cannot be equal to or less than zero!");
                                break;
                            }
                            scanner.nextLine();
                            add_item(id,qty);

                            // update the menu after adding stuff to cart cuz stock update hoga na
                            if (JFrame.getFrames().length != 0) {
                                MainFrame.render();
                            }

                            try {
                                ObjectOutputStream out = new ObjectOutputStream(file_out);
                                out.writeObject(shopping_cart);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case 6:
                            if (shopping_cart.isEmpty()) {
                                System.out.println("\n\t\033[31mCart is empty!\033[0m");
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            System.out.print("\n\tEnter Food Item ID: ");
                            id = scanner.nextInt();
                            scanner.nextLine();
                            for (FoodPair food_pair : shopping_cart) {
                                if (food_pair.food.get_index() == id) {
                                    // update the stock as well
                                    for (Food _food : Menu.get_list()) {
                                        if (_food.get_index() == id) {
                                            _food.set_stock(food_pair.food.get_stock()+food_pair.quantity);
                                            break;
                                        }
                                    }
                                    shopping_cart.remove(food_pair);
                                    System.out.print("\n\t\033[32mSuccessfully removed item from cart!\033[0m");
                                    try {
                                        TimeUnit.SECONDS.sleep(1);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                if (shopping_cart.getLast().equals(food_pair)) {
                                    System.out.print("\n\t\033[31mItem not found!\033[0m");
                                    try {
                                        TimeUnit.SECONDS.sleep(1);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            try {
                                ObjectOutputStream out = new ObjectOutputStream(file_out);
                                out.writeObject(shopping_cart);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case 7:
                            if (shopping_cart.isEmpty()) {
                                System.out.println("\n\t\033[31mCart is empty!\033[0m");
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            System.out.print("\n\tEnter Food Item ID: ");
                            id = scanner.nextInt();
                            scanner.nextLine();
                            int new_qty;
                            System.out.print("\n\tEnter the new quantity: ");
                            new_qty = scanner.nextInt();
                            scanner.nextLine();
                            if (new_qty < 1) {
                                System.out.println("\n\t\033[31mNew quantity can't be less than 1!\033[0m");
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            for (FoodPair food_pair : shopping_cart) {
                                if (food_pair.food.get_index() == id) {
                                    if (new_qty > food_pair.food.get_stock()+food_pair.quantity) {
                                        Util.throw_error("Not Enough Stock!");
                                        break;
                                    }
                                    // update the stock as well
                                    food_pair.food.set_stock(food_pair.food.get_stock()+food_pair.quantity-new_qty);
                                    food_pair.quantity = new_qty;
                                    System.out.print("\n\t\033[32mSuccessfully updated item's quantity!\033[0m");
                                    try {
                                        TimeUnit.SECONDS.sleep(1);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                if (shopping_cart.getLast().equals(food_pair)) {
                                    System.out.print("\n\t\033[31mItem not found!\033[0m");
                                    try {
                                        TimeUnit.SECONDS.sleep(1);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            try {
                                ObjectOutputStream out = new ObjectOutputStream(file_out);
                                out.writeObject(shopping_cart);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case 8:
                            if (this.shopping_cart.isEmpty()) {
                                System.out.print("\n\t\033[31mCart is empty!\033[0m");
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            else {
                                System.out.print("\tEnter delivery address: ");
                                String address = scanner.nextLine();
                                System.out.print("\tEnter any special requests (if any): ");
                                String special_reqs = scanner.nextLine();
                                Order order = new Order(this.shopping_cart, address, special_reqs, cType);
                                int final_price = 0;
                                for (FoodPair food_pair : shopping_cart) {
                                    final_price += (food_pair.food.get_price()*food_pair.quantity);
                                }
                                System.out.println("\tAmount to be paid: ₹"+final_price);
                                shopping_cart.clear();
                                System.out.print("\n\tPress enter to continue...");
                                try {
                                    System.in.read();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                System.out.print("\n\t\033[32mSuccessfully placed the order!\033[0m");

                                if (JFrame.getFrames().length != 0) {
                                    MainFrame.render();
                                }

                                try {
                                    TimeUnit.MILLISECONDS.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            break;
                        default:
                            System.out.println("\n\t\033[31mInvalid Choice!\033[0m");
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }
                }

                break;
            // orders
            case 2:
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
                    System.out.println("\n\t1. Cancel Order");
                    System.out.println("\t2. Re-order");
                    System.out.println("\tq. quit");
                    System.out.print("\n\t\033[36m>>\033[0m Choose: ");
                    choice = 0;
                    try {
                        choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                if (Order.get_list().isEmpty()) {
                                    Util.throw_error("No orders left!");
                                } else {
                                    System.out.print("\n\tEnter the ID of the order you want to cancel: ");
                                    int id = scanner.nextInt();

                                    for (Order order : Order.get_list()) {
                                        if (order.get_id() == id) {
                                            order.cancel();
                                            System.out.print("\n\t\033[32mOrder Cancelled Successfully!\033[0m");
                                            TimeUnit.SECONDS.sleep(1);
                                            break;
                                        } else if (Order.get_list().getLast().equals(order)) {
                                            Util.throw_error("Order not found!");
                                            break;
                                        }
                                    }
                                }
                                break;

                            case 2:
                                if (Order.get_list().isEmpty()) {
                                    Util.throw_error("No orders left!");
                                } else {
                                    System.out.print("\n\tEnter the ID of the order you want to re-order: ");
                                    int id = scanner.nextInt();

                                    for (Order order : Order.get_list()) {
                                        if (order.get_id() == id) {
                                            order.reorder();
                                            System.out.print("\n\t\033[32mOrder Re-ordered Successfully!\033[0m");
                                            TimeUnit.SECONDS.sleep(1);
                                            break;
                                        } else if (Order.get_list().getLast().equals(order)) {
                                            Util.throw_error("Order not found!");
                                            break;
                                        }
                                    }
                                }
                                break;

                            default:
                                Util.throw_error("Invalid Choice!");
                                break;
                        }
                    } catch (InputMismatchException e) {
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            // item review
            case 3:
               break;

            // vip
            case 4:
                System.out.println("\tPay Rs. 1000 to become a VIP member...(press enter to continue)");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.cType = CustomerType.VIP;
                break;
            default:
                Util.throw_error("Invalid Choice!");
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
