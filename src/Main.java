import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

/**
 * Class: Main
 * Author: Aditya Gautam
 */
public class Main {
 
    // for enabling anti-aliasing for text in linux (or other barebones environments)
    static {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
    }

    /**
     * @param args
     * @throws CustomException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws CustomException, InterruptedException {

        boolean running = true;
        Menu.sample_values();

        // CLI
        Dashboard dashboard = new Dashboard();

        while (running) {
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
