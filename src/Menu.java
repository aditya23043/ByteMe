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
}

public class Menu {

    private static ArrayList<Food> MenuList = new ArrayList<Food>();

    public static void add(String food_name, int food_price, FoodType food_category, Boolean food_availability) {
        Food food = new Food(food_name, food_price, food_category, food_availability);
        MenuList.add(food);
    }

}
