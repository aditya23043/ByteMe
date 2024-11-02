import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Dashboard {

    public void init() throws CustomException, InterruptedException {
        while (true){
            choose_user_type();
        }
    }

    private void choose_user_type() throws CustomException, InterruptedException {
        Header.clearScreen();
        Header.title();
        Header.top("Dashboard");
        Header.content("1. Admin");
        Header.content("2. Student");
        Header.content("q. Quit");
        Header.bottom();

        System.out.print("\n\t>> Choose: ");
        Scanner scanner = new Scanner(System.in);
        int choice = 1;
        try{
            choice = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("\n\t\033[32mBye Bye!\033[0m");
            TimeUnit.SECONDS.sleep(1);
            System.exit(0);
        }

        switch (choice) {
            case 1:
                Admin admin = new Admin();
                admin.dashboard();
                break;
            case 2:
                Student student = new Student();
                break;
            default:
                throw new CustomException("Invalid Choice!");
        }
    }


}

