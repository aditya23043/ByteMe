import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Dashboard {

    Scanner scanner;
    Credentials credentials = new Credentials();

    public void init() throws CustomException, InterruptedException {
        choose_user_type();
    }

    private void choose_user_type() throws CustomException, InterruptedException {
        Header.clearScreen();
        Header.title();
        Header.top("Dashboard");
        Header.content("1. Admin");
        Header.content("2. Customer: Login");
        Header.content("3. Customer: Signup");
        Header.content("4. Show GUI");
        Header.content("q. Quit");
        Header.bottom();

        System.out.print("\n\t\033[36m>>\033[0m Choose: ");
        scanner = new Scanner(System.in);
        int choice = 1;
        try{
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            System.out.print("\n\t\033[36mBye Bye!\033[0m");
            TimeUnit.MILLISECONDS.sleep(500);
            System.exit(0);
        }

        switch (choice) {
            case 1:
                Admin admin = new Admin();
                admin.dashboard();
                break;
            case 2:
                System.out.print("\n\tEnter username: ");
                String username = scanner.nextLine();
                System.out.print("\tEnter password: ");
                String password = scanner.nextLine();
                if(credentials.authenticate(username, password)){
                    System.out.print("\n\t\033[32mLogged In Successfully!\033[0m");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Customer customer = new Customer(username, password);
                    customer.dashboard();
                }
                else {
                    Util.throw_error("Credentials don't match! Try Again!"); 
                    break;
                }
                break;
            case 3:
                System.out.print("\n\tEnter username: ");
                String username_signup = scanner.nextLine();
                System.out.print("\tEnter password: ");
                String password_signup = scanner.nextLine();
                System.out.print("\tEnter password again: ");
                String password_signup_2fa = scanner.nextLine();
                if (!password_signup.equals(password_signup_2fa)) {
                    Util.throw_error("Passwords do not match! Try Again!");
                    break;
                }
                System.out.print("\n\t\033[32mSigned In Successfully! Login To continue...\033[0m");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                credentials.write(username_signup, password_signup);
                break;
            case 4:
                MainFrame.render();
                break;
            default:
                throw new CustomException("Invalid Choice!");
        }
    }


}

