import java.util.ArrayList;

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
