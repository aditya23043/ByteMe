public class Header {

    private static final int TOTAL_WIDTH = 40;
    // one for left border and one for right
    private static final int PADDING = 2+2;

    public static void top() {

        System.out.println();
        System.out.println();
        System.out.print("\t┌");
        for (int i = 0; i < TOTAL_WIDTH / 2 - 4; i++) {
            System.out.print("─");
        }
        System.out.print("Byte Me!");
        for (int i = 0; i < TOTAL_WIDTH / 2 - 4; i++) {
            System.out.print("─");
        }
        System.out.print("┐");
        System.out.println();

        System.out.print("\t│  ");
        for (int i = 0; i < TOTAL_WIDTH - PADDING; i++) {
            System.out.print(" ");
        }
        System.out.print("  │");
        System.out.println();

    }

    public static void content(String text) {
        // left border
        System.out.print("\t│  ");

        // main content
        int num_of_chars_written_in_this_line = 0;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (currentChar == '\n') {
                for (int j = 0; j < TOTAL_WIDTH - 4 - num_of_chars_written_in_this_line; j++) {
                    System.out.print(" ");
                }
                System.out.print("  │");
                System.out.println();
                System.out.print("\t│  ");
                num_of_chars_written_in_this_line = 0;
            } 
            else if (currentChar == '\t') {
                System.out.print("    ");
                num_of_chars_written_in_this_line += 4;
            }
            else {
                System.out.print(currentChar);
                num_of_chars_written_in_this_line++;
            }

            if (num_of_chars_written_in_this_line >= TOTAL_WIDTH - 4) {
                System.out.print("  │");
                System.out.println();
                System.out.print("\t│  ");
                num_of_chars_written_in_this_line = 0;
            }
        }

        for (int j = 0; j < TOTAL_WIDTH - 4 - num_of_chars_written_in_this_line; j++) {
            System.out.print(" ");
        }

        // right border
        System.out.print("  │");
        System.out.println();
    }


    public static void bottom() {

        System.out.print("\t│  ");
        for (int i = 0; i < TOTAL_WIDTH - PADDING; i++) {
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
