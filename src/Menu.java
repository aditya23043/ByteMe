import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

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

    public static void show_gui() {
        MenuApp app = new MenuApp();
    }

}

class MenuApp {
    MenuApp() {
        MenuFrame frame = new MenuFrame();
        frame.show();
    }
}

class MenuFrame extends JFrame {
    MenuFrame() {
        this.setTitle("Menu");
        this.setResizable(false);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String menu_food_title[] = new String[Menu.get_list().size()];
        int index = 0;
        for (Food _food : Menu.get_list()) {
            menu_food_title[index] = _food.get_title();
            index++;
        }

        JList<String> list = new JList<>(menu_food_title);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(50);
        list.setFixedCellWidth(200);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        this.add(panel);

    }
}
