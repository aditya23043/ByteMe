import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class Dashboard {

    Scanner scanner;

    public void init() throws CustomException, InterruptedException {
        choose_user_type();
    }

    private void choose_user_type() throws CustomException, InterruptedException {
        Header.clearScreen();
        Header.title();
        Header.top("Dashboard");
        Header.content("1. Admin");
        Header.content("2. Customer");
        Header.content("3. Show GUI");
        Header.content("q. Quit");
        Header.bottom();

        System.out.print("\n\t\033[36m>>\033[0m Choose: ");
        scanner = new Scanner(System.in);
        int choice = 1;
        try{
            choice = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("\n\t\033[36mBye Bye!\033[0m");
            TimeUnit.MILLISECONDS.sleep(500);
            System.exit(0);
        }

        switch (choice) {
            case 1:
                Admin admin = new Admin();
                admin.dashboard();
                break;
            case 2:
                Customer customer = new Customer();
                customer.dashboard();
                break;
            case 3:
                MainFrame.render();
                break;
            default:
                throw new CustomException("Invalid Choice!");
        }
    }


}

