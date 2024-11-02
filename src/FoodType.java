public enum FoodType {
    BEVERAGE,
    SNACK,
    MEAL,
    EVM,
    DESERT,
    COMBO,
    NULL;

    public static void print() {
        Header.top("Food Category");
        int index = 1;
        for (FoodType fType : FoodType.values()) {
            Header.content(index + ". " + fType);
            index++;
        }
        Header.bottom();
    }
}
