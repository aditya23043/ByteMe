import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class Menu {

    private static ArrayList<Food> MenuList = new ArrayList<Food>();

    public static void add(String food_name, int food_price, FoodType food_category, Boolean food_availability, int stock) {
        Food food = new Food(food_name, food_price, food_category, food_availability, stock);
        MenuList.add(food);
    }

    public static ArrayList<Food> get_list() {
        return MenuList;
    }

    public static void sample_values() {
        add("Pav Bhaji", 100, FoodType.MEAL, true, 10);
        add("Dosa", 50, FoodType.MEAL, false, 12);
        add("Fruit Punch", 75, FoodType.BEVERAGE, true, 15);
    }

}
