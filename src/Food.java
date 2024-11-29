class Food {

    private int index;
    private String title;
    private int price;
    private FoodType category;
    private Boolean availability;
    private int stock;

    static int count = 1;

    Food(String food_name, int food_price, FoodType food_category, Boolean food_availability, int stock) {
        this.index = count++;
        this.title = food_name;
        this.price = food_price;
        this.category = food_category;
        this.availability = food_availability;
        this.stock = stock;
    }

    protected int get_index() {
        return this.index;
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

    protected int get_stock() {
        return this.stock;
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

    protected void set_stock(int new_stock) {
        this.stock = new_stock;
    }
}

