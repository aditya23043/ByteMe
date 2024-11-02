import java.util.concurrent.TimeUnit;

public class CustomException extends Exception {

    CustomException(String error) throws InterruptedException {
        System.out.printf("\n\t\033[31m%s\033[0m\n\n", error);
        TimeUnit.SECONDS.sleep(1);
        System.exit(1);
    }

}

