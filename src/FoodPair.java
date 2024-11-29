import java.io.Serializable;

class FoodPair implements Serializable{
    protected Food food;
    protected int quantity;

    FoodPair(Food _a, int _b) {
        this.food = _a;
        this.quantity = _b;
    }
}

