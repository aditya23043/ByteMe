import java.util.ArrayList;

public class Order {

    private static ArrayList<Order> order_list = new ArrayList<Order>();
    static int index = 1;

    private int order_id;
    private OrderStatus order_status;
    private ArrayList<FoodPair> food_list = new ArrayList<>();

    Order(ArrayList<FoodPair> _food_list) {
        this.order_id = index;
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
}
