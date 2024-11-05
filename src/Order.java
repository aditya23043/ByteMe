import java.util.ArrayList;
import java.util.Scanner;

public class Order {

    private static ArrayList<Order> order_list = new ArrayList<Order>();
    static int index = 1;

    private int order_id;
    private OrderStatus order_status;
    private String customer_address;
    private ArrayList<FoodPair> food_list = new ArrayList<>();
    private String special_requests;

    Order(ArrayList<FoodPair> _food_list, String _address, String special_reqs) {
        this.order_id = index;
        this.customer_address = _address;
        this.special_requests = special_reqs;
        index++;
        for (FoodPair foodPair : _food_list) {
            food_list.add(foodPair);
        }
        this.order_status = OrderStatus.PROCESSING;
        order_list.add(this);
    }

    public static ArrayList<Order> get_list() {
        return order_list;
    }

    public int get_id() {
        return this.order_id;
    }

    public ArrayList<FoodPair> get_food_list() {
        return this.food_list;
    }

    public int get_qty() {
        int qty = 0;
        for (FoodPair food_pair : food_list) {
            qty += food_pair.quantity;
        }
        return qty;
    }

    public String get_status() {
        return order_status.toString();
    }

    public void set_status() {
        int sno = 1;
        Header.top("Order Status");
        for (OrderStatus order_status : OrderStatus.values()) {
            Header.content(sno + ". " + order_status.toString());
            sno++;
        }
        Header.bottom();
        System.out.print("\n\t\033[36m>>\033[0m Choose: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        OrderStatus orderStatus = OrderStatus.PROCESSING;
        switch (choice) {
            case 1:
                orderStatus = OrderStatus.PROCESSING;
                break;
            case 2:
                orderStatus = OrderStatus.DELIVERED;
                break;
            case 3:
                orderStatus = OrderStatus.DENIED;
                break;
            case 4:
                orderStatus = OrderStatus.CANCELLED;
                break;
            default:
                Util.throw_error("Invalid choice!");
                break;
        }
        this.order_status = orderStatus;
    }

    public void set_status(OrderStatus new_status) {
        this.order_status = new_status;
    }

    public void cancel() {
        if(order_status.equals(OrderStatus.CANCELLED)){
            Util.throw_error("Order is already cancelled!");
        } else {
            order_status = OrderStatus.CANCELLED;
        }
    }

    public void reorder() {
        Order new_order = new Order(this.food_list, this.customer_address, this.special_requests);
    }

    public int get_amt() {
        int total_price = 0;
        for (FoodPair food_pair : food_list) {
            total_price += (food_pair.food.get_price()*food_pair.quantity);
        }
        return total_price;
    }

    public String get_special_reqs() {
        return this.special_requests.isEmpty() ? "None" : this.special_requests;
    }
}
