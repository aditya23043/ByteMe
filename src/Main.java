/**
 * Class: Main
 * Author: Aditya Gautam
 */
public class Main {
    public static void main(String[] args) throws CustomException, InterruptedException {

        Menu.sample_values();

        Dashboard dashboard = new Dashboard();
        dashboard.init();

    }
}
