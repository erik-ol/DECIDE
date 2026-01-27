import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Random;

import org.junit.*;

/**
 * Test class 
 */
public class MainTest {
    // Tests to be added...


    //// TEST CASES FOR LIC 0
    @Test
    /**
     * Test situations where lic0 should be false
     */
    public void lic0Negative(){

        for (int points = 2; points < 20; points++){
            // Case I: When all points are the same
            // Case II: When all points are equally spaced
            Point[] pointArraySame = new Point[points];
            Point[] pointArrayEqualSpace = new Point[points];

            pointArraySame[0] = new Point(0, 0);
            pointArrayEqualSpace[0] = new Point(0, 0);

            for (int point = 1; point < points; point++){
                pointArraySame[point] = new Point(0, 0);
                pointArrayEqualSpace[point] = new Point(2 * point, 3 * point);
            }
            LaunchInterceptorConditionParameters lic0Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean resI = lic0Handler.doesTwoConsecutivePointsFurtherThanLength1(points, pointArraySame);
            boolean resII = lic0Handler.doesTwoConsecutivePointsFurtherThanLength1(points, pointArrayEqualSpace);

            Assert.assertFalse(resI);
            Assert.assertFalse(resII);
        }
	}
	
	@Test
    /**
     * Test situations where lic0 should be true
     */
    public void lic0positive(){

        for (int points = 2; points < 20; points++){

            Point[] pointArray = new Point[points];

            for (int point = 0; point < (points - 1); point++){
                pointArray[point] = new Point(7 * point, 7 * point);
            }
            // Add outlier point
            pointArray[points - 1] = new Point(-8, -8);

            LaunchInterceptorConditionParameters lic0Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic0Handler.doesTwoConsecutivePointsFurtherThanLength1(points, pointArray);
            Assert.assertTrue(res);
        }

        
    }

    @Test
    /**
     * Tests situations where lic1 should be false
     */
    public void lic1Negative() {

        Random random = new Random();
        
        for (int points = 0; points<100; points++) {

            Point[] pointArray = new Point[points];

            for (int point = 0; point<points; point++) {
                double pointX = random.nextDouble()*9;
                double pointY = random.nextDouble()*9;
                pointArray[point] = new Point(pointX, pointY);

            }

            LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            
            Assert.assertFalse(licHandler.lic_1(points, pointArray));
        }
        
    }

    @Test
    /**
     * Tests situations where lic1 should be true
     */
    public void lic1Positive() {

        Random random = new Random();
        
        for (int points = 3; points<100; points++) {

            Point[] pointArray = new Point[points];

            for (int point = 0; point<points; point++) {
                double pointX = random.nextDouble()*9;
                double pointY = random.nextDouble()*9;
                pointArray[point] = new Point(pointX, pointY);

            }

            // outlier point
            pointArray[random.nextInt(0, points)] = new Point(300+random.nextDouble()*10, 300+random.nextDouble()*10);

            LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            
            Assert.assertTrue(licHandler.lic_1(points, pointArray));
        }
        
    }

 
    //// TEST CASES FOR LIC 5

    /**
    * Test situation where the x-coordinate decreases
    */
    @Test
    public void lic5TrueWhenXDecreases() {
        Point[] points = {
            new Point(3.0, 0.0),
            new Point(2.0, 0.0)
        };

        boolean res = LaunchInterceptorConditionParameters.lic5(points, 2);
        assertTrue(res);
    }

    /**
    * Test situation where the x-coordinate increases
    */
    @Test
    public void lic5FalseWhenXIncreases() {
        Point[] points = {
            new Point(2.0, 0.0),
            new Point(3.0, 0.0)
        };

        boolean res = LaunchInterceptorConditionParameters.lic5(points, 2);
        assertFalse(res);
    }


    /**
    * Test situation where the x-coordinate stays the same
    */
    @Test
    public void lic5FalseWhenXIsEqual() {
        Point[] points = {
            new Point(2.0, 0.0),
            new Point(2.0, 0.0)
        };

        boolean res = LaunchInterceptorConditionParameters.lic5(points, 2);
        assertFalse(res);
    }

    
}