import java.util.concurrent.TimeUnit;

public class Util {

    public static void throw_error(String text){
        System.out.print("\n\t\033[31m"+text+"\033[0m");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
