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
        if (text.length() > TOTAL_WIDTH - PADDING) {

            int index = 0;
            int text_len_left = text.length();
            int num_of_chars_written_in_this_line = 0;
            int count;
            while (text_len_left > 0) {
                count = index + 1;
                if (text.charAt(index) == '\n') {
                    for (int i = 0; i < TOTAL_WIDTH - PADDING - num_of_chars_written_in_this_line; i++) {
                        System.out.print(" ");
                    }
                    System.out.print("  │");
                    System.out.println();
                    System.out.print("\t│  ");
                    num_of_chars_written_in_this_line = 0;
                    count = 0;

                } else {
                    System.out.print(text.charAt(index));
                }
                num_of_chars_written_in_this_line++;
                if (count % 36 == 0 && index != 0) {
                    System.out.print("  │");
                    System.out.println();
                    System.out.print("\t│  ");
                    num_of_chars_written_in_this_line = 0;
                }
                text_len_left--;
                index++;

                if (text_len_left == 0) {
                    for (int i = 0; i < TOTAL_WIDTH - PADDING - num_of_chars_written_in_this_line; i++) {
                        System.out.print(" ");
                    }
                }
            }

        } else {

            int num_of_chars_written = 0;
            int total_num_of_chars_written = 0;

            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    for (int j = 0; j < TOTAL_WIDTH - PADDING - num_of_chars_written; j++) {
                        System.out.print(" ");
                    }
                    System.out.print("  │");
                    System.out.println();
                    System.out.print("\t│  ");
                    num_of_chars_written = 0;
                } else {
                    System.out.print(text.charAt(i));
                    num_of_chars_written++;
                    total_num_of_chars_written++;
                }
                // System.out.printf(":%d ", num_of_chars_written);
            }
            for (int j = 0; j < TOTAL_WIDTH - PADDING - num_of_chars_written; j++) {
                System.out.print(" ");
            }
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
