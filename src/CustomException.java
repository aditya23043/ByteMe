public class CustomException extends Exception {

    CustomException(String error) {
        super(error);
        // System.out.printf("\n\t\033[31m%s\033[0m\n\n", error);
        // try {
        //     TimeUnit.SECONDS.sleep(1);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        // System.exit(1);
        // return;
    }

}

