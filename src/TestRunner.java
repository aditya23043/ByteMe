import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

class TestRunner {
    public static void main(String[] args) {
        Result result = new JUnitCore.runClasses(Test.class);
        for (Failiure failiure : result.getFailiures) {
            System.out.println(failiure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
