public class Header {

    private static final int TOTAL_WIDTH = 40;

    public static void top() {

        System.out.println();
        System.out.println();
        System.out.print("\t┌");
        for (int i = 0; i < TOTAL_WIDTH/2 - 4; i++) {
            System.out.print("─");
        }
        System.out.print("Byte Me!");
        for (int i = 0; i < TOTAL_WIDTH/2 - 4; i++) {
            System.out.print("─");
        }
        System.out.print("┐");
        System.out.println();

        System.out.print("\t│  ");
        for (int i = 0; i < TOTAL_WIDTH - 4; i++) {
            System.out.print(" ");
        }
        System.out.print("  │");
        System.out.println();

    }

    public static void content(String text) {
        // left border
        System.out.print("\t│  ");

        // main content
        if (text.length() > TOTAL_WIDTH - 4) {
            System.out.println(text.strip());
        }
        // System.out.print(text);

        // right border
        System.out.print("  │");
        System.out.println();
    }

    public static void bottom() {

        System.out.print("\t│  ");
        for (int i = 0; i < TOTAL_WIDTH - 4; i++) {
            System.out.print(" ");
        }
        System.out.print("  │");
        System.out.println();


        System.out.print("\t└");
        for (int i = 0; i < TOTAL_WIDTH; i++) {
            System.out.print("─");
        }
        System.out.print("┘");
        System.out.println();

    }

}
