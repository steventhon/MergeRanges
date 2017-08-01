import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * The ZipcodesTestRunner runs the tests of ZipcodesTest.java
 *
 * @author  Steven Thon
 * @version 1.0
 * @since   2017-07-30
 */

public class ZipcodesTestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(ZipcodesTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }
}