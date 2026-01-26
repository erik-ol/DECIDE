import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Random;

import org.junit.*;

/**
 * Test class 
 */
public class MainTest {

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

    /**
     * Test situation where all points are the same
     */
    @Test
    public void lic7FalseWhenAllPointsSame(){
        for (int points = 3; points < 10; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                pointArray[point] = new Point(0, 0);
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.lic7(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation when points pair differed by exactly LENGHT1
     */
    @Test
    public void lic7FalseWhenPointsPairEqual(){
        for (int points = 3; points < 10; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                pointArray[point] = new Point(point * 2, point * 3);
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters((points - 1) * 4, 0, 0, 0, 0, 0, 0, 0, points - 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.lic7(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation where number of points < 3
     */
    @Test
    public void lic7FalseWhenNotEnoughPoints(){
        for (int points = 1; points < 3; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                pointArray[point] = new Point(0, 0);
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.lic7(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation where there is one exact pair of points longer than LENGTH1
     */
    @Test
    public void lic7TrueWhenOnePair(){
        for (int points = 10; points < 20; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                if (point != (points % 10 + 2) ){
                    pointArray[point] = new Point(1, 1);
                }
                else{
                    pointArray[point] = new Point(point * 2 + 1, point * 3 + 1);
                }
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points % 10 + 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.lic7(pointArray, points);
            Assert.assertTrue(res);
        }
    }

    /**
     * Test situation where all pairs longer than LENGTH1
     */
    @Test
    public void lic7TrueWhenAllPair(){
        for (int points = 10; points < 20; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                if (point < (points / 2) ){
                    pointArray[point] = new Point(1, 1);
                }
                else{
                    pointArray[point] = new Point(point * 2 + 1, point * 3 + 1);
                }
            }

            for (int point = 0; point < points; point++){
                System.out.print(pointArray[point].getX());
                System.out.print(", ");
                System.out.print(pointArray[point].getY());
                System.out.println();
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points / 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.lic7(pointArray, points);
            Assert.assertTrue(res);
        }
    }
    
}