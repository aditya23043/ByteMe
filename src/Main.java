import java.util.concurrent.TimeUnit;

/**
 * Class: Main
 * Author: Aditya Gautam
 */
public class Main {
    public static void main(String[] args) throws CustomException, InterruptedException {

        Menu.sample_values();

        Dashboard dashboard = new Dashboard();

        while (true) {
            try{
                dashboard.init();
            }
            catch (CustomException e) {
                System.out.print("\n\t\033[31m"+e.getMessage()+"\033[0m");
                TimeUnit.SECONDS.sleep(1);
                continue;
            }
        }

    }
}
