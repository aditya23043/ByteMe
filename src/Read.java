import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
class Read {
    public static void main(String[] args) {
        if(args.length < 1 || args.length > 1){
            System.out.println("ERROR! Wrong Usage!");
            System.out.println("Usage: java Read.java <filename>");
            System.exit(-1);
        }
        System.out.println(args[0]);

        ArrayList<FoodPair> shopping_cart = new ArrayList<>();
        System.out.println();

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(args[0])));
            shopping_cart = (ArrayList<FoodPair>) in.readObject();
            if (shopping_cart.isEmpty()) {
                Header.content_center("- NONE -");
            } else {
                for (FoodPair food_pair : shopping_cart) {
                    System.out.println("\tID "+food_pair.food.get_index()+" : "+food_pair.food.get_title()+" : ₹"+food_pair.food.get_price()+" x "+food_pair.quantity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
