public class Header {

    private static final int TOTAL_WIDTH = 40;
    // one for left border and one for right
    private static final int PADDING = 2+2;

    public static void title() {
        System.out.println("""
\033[36m\
\t$$$$$$$\\              $$\\                     $$\\      $$\\           $$\\
\t$$  __$$\\             $$ |                    $$$\\    $$$ |          $$ |
\t$$ |  $$ |$$\\   $$\\ $$$$$$\\    $$$$$$\\        $$$$\\  $$$$ | $$$$$$\\  $$ |
\t$$$$$$$\\ |$$ |  $$ |\\_$$  _|  $$  __$$\\       $$\\$$\\$$ $$ |$$  __$$\\ $$ |
\t$$  __$$\\ $$ |  $$ |  $$ |    $$$$$$$$ |      $$ \\$$$  $$ |$$$$$$$$ |\\__|
\t$$ |  $$ |$$ |  $$ |  $$ |$$\\ $$   ____|      $$ |\\$  /$$ |$$   ____|    
\t$$$$$$$  |\\$$$$$$$ |  \\$$$$  |\\$$$$$$$\\       $$ | \\_/ $$ |\\$$$$$$$\\ $$\\ 
\t\\_______/  \\____$$ |   \\____/  \\_______|      \\__|     \\__| \\_______|\\__|
\t          $$\\   $$ |                                                     
\t          \\$$$$$$  |                                                     
\t           \\______/                                                      \033[0m""");
    }

    public static void top(String text) {

        System.out.print("\033[36m");
        int x = (TOTAL_WIDTH - text.length())/2;

        System.out.println();
        System.out.println();
        System.out.print("\t╭");
        for (int i = 0; i < x; i++) {
            System.out.print("─");
        }
        System.out.print(text);
        for (int i = 0; i < ((text.length()%2==0)? x : x+1) ; i++) {
            System.out.print("─");
        }
        System.out.print("╮");
        System.out.println();

        System.out.print("\t│  ");
        for (int i = 0; i < TOTAL_WIDTH - PADDING; i++) {
            System.out.print(" ");
        }
        System.out.print("  │");
        System.out.println();
        System.out.print("\033[0m");

    }

    public static void content(String text) {
        // left border
        System.out.print("\033[36m");
        System.out.print("\t│  ");
        System.out.print("\033[0m");

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
        System.out.print("\033[36m");
        System.out.print("  │");
        System.out.print("\033[0m");
        System.out.println();
    }


    public static void bottom() {

        System.out.print("\033[36m");
        System.out.print("\t│  ");
        for (int i = 0; i < TOTAL_WIDTH - PADDING; i++) {
            System.out.print(" ");
        }
        System.out.print("  │");
        System.out.println();

        System.out.print("\t╰");
        for (int i = 0; i < TOTAL_WIDTH; i++) {
            System.out.print("─");
        }
        System.out.print("╯");
        System.out.print("\033[0m");
        System.out.println();

    }

    public static void clearScreen() {
        System.out.println("\033[H");
        System.out.println("\033[2J");
    }

}
