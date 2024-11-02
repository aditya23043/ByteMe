import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {

    // util fields
    enum CUSTOMER_TYPE {
        VIP,
        REGULAR
    }
    Scanner scanner = new Scanner(System.in);

    // actual fields
    private CUSTOMER_TYPE cType;
    private String name;


    public void dashboard() {
        Header.clearScreen();
        Header.title();
        Header.top("Student Dashboard");

        Header.content("1. Place Order");
        Header.content("2. Browse menu");
        Header.content("3. Proceed to Cart");
        Header.content("4. Orders");
        Header.content("5. Item Review");
        Header.content("q. Quit");

        Header.bottom();

        System.out.print("\n\t\033[36m>>\033[0m Choose: ");
        try{
            scanner.nextInt();
        }
        catch (InputMismatchException e) {
            return;
        }
    }

}

