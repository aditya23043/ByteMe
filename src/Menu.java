import java.util.ArrayList;

class Food {

    private String title;
    private int price;
    private FoodType category;
    private Boolean availability;

    Food(String food_name, int food_price, FoodType food_category, Boolean food_availability) {
        this.title = food_name;
        this.price = food_price;
        this.category = food_category;
        this.availability = food_availability;
    }

    protected String get_title() {
        return this.title;
    }

    protected int get_price() {
        return this.price;
    }

    protected FoodType get_category() {
        return this.category;
    }

    protected Boolean get_availability() {
        return this.availability;
    }

    protected void set_title(String new_title) {
        this.title = new_title;
    }

    protected void set_price(int new_price) {
        this.price = new_price;
    }

    protected void set_category(FoodType new_category) {
        this.category = new_category;
    }

    protected void set_availability(Boolean new_availability) {
        this.availability = new_availability;
    }
}

public class Menu {

    private static ArrayList<Food> MenuList = new ArrayList<Food>();

    public static void add(String food_name, int food_price, FoodType food_category, Boolean food_availability) {
        Food food = new Food(food_name, food_price, food_category, food_availability);
        MenuList.add(food);
    }

    public static ArrayList<Food> get_list() {
        return MenuList;
    }

    public static void sample_values() {
        add("Pav Bhaji", 100, FoodType.MEAL, true);
        add("Dosa", 50, FoodType.MEAL, false);
        add("Fruit Punch", 75, FoodType.BEVERAGE, true);
    }



}
