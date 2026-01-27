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

    /**
     * Negative test for LIC8 with invalid input
     * Verifies that LIC8 returns false when the number of input points is < 5.
     */
    @Test
    public void lic8TestInvalidInput() {
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 5, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        Point[] planarPoints = {
            new Point(0,0),
            new Point(0,0),
            new Point(0,0),
        };
        assertFalse(licHandler.lic8(planarPoints.length, planarPoints));
    }


    /**
     * Positive test for LIC8
     * Verifies that LIC8 returns true when there exist at least one triplet 
     * of consequtive points separated by A_PTS and B_PTS, that cannot fit 
     * within a circle of radius RADIUS1.
     */
    @Test
    public void lic8CanFitR1() {
        // Uses A_PTS = B_PTS = 1 and RADIUS1 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 5, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        Point[] planarPoints = {
            new Point(0,10),
            new Point(0,0), // skipped
            new Point(20,0),
            new Point(20,0), // skipped
            new Point(30,0),
        };
        assertTrue(licHandler.lic8(planarPoints.length, planarPoints));

    }

    /**
     * Negative test for LIC8
     * Verifies that LIC8 returns false when there no triplet of consecutive
     * points, separated by A_PTS and B_PTS, that cannot fit within a circle of
     * radius RADIUS1.
     */
    @Test
    public void lic8CannotFitR1() {
        // Uses A_PTS = B_PTS = 1 and RADIUS1 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 5, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        Point[] planarPoints = {
            new Point(0,0),
            new Point(0,0), // skipped
            new Point(1,0),
            new Point(0,1), // skipped
            new Point(0,0),
        };
        assertFalse(licHandler.lic8(planarPoints.length, planarPoints));
    }

    /**
     * Test situation where number of points < 3
     */
    @Test
    public void lic12FalseWhenNotEnoughPoints(){
        for (int points = 1; points < 3; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                pointArray[point] = new Point(0, 0);
            }

            LaunchInterceptorConditionParameters lic12Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0);

            boolean res = lic12Handler.doesPointsSeparatedByKApartByRange(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation where all points are the same
     */
    @Test
    public void lic12FalseWhenAllPointsSame(){
        for (int points = 3; points < 10; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                pointArray[point] = new Point(0, 0);
            }

            LaunchInterceptorConditionParameters lic12Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0);

            boolean res = lic12Handler.doesPointsSeparatedByKApartByRange(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation when points pair differed by exactly LENGHT1
     */
    @Test
    public void lic12FalseWhenPointsPairEqual1(){
        for (int points = 10; points < 20; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                if (point < (points / 2) ){
                    pointArray[point] = new Point(1, 1);
                }
                else{
                    pointArray[point] = new Point(3, 4);
                }
            }

            LaunchInterceptorConditionParameters lic12Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points / 2, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0);

            boolean res = lic12Handler.doesPointsSeparatedByKApartByRange(pointArray, points);
            System.out.println(res);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation where all pairs longer than LENGTH1, shorter than LENGTH2
     */
    @Test
    public void lic12TrueWhenAllPair(){
        for (int points = 10; points < 20; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                if (point < (points / 2) ){
                    pointArray[point] = new Point(1, 1);
                }
                else{
                    pointArray[point] = new Point(5, 11);
                }
            }

            LaunchInterceptorConditionParameters lic12Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points / 2, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0);

            boolean res = lic12Handler.doesPointsSeparatedByKApartByRange(pointArray, points);
            Assert.assertTrue(res);
        }
    }

    /**
     * Test situation where all pairs equal to LENGTH2
     */
    @Test
    public void lic12FalseWhenPointsPairEqual2(){
        for (int points = 10; points < 20; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                if (point < (points / 2) ){
                    pointArray[point] = new Point(1, 1);
                }
                else{
                    pointArray[point] = new Point(6, 13);
                }
            }

            LaunchInterceptorConditionParameters lic12Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points / 2, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0);

            boolean res = lic12Handler.doesPointsSeparatedByKApartByRange(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation where all pairs strictly larger than LENGTH2
     */
    @Test
    public void lic12FalseWhenPointsPairGreater(){
        for (int points = 10; points < 20; points++){
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++){
                if (point < (points / 2) ){
                    pointArray[point] = new Point(1, 1);
                }
                else{
                    pointArray[point] = new Point(10, 20);
                }
            }

            LaunchInterceptorConditionParameters lic12Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points / 2, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0);

            boolean res = lic12Handler.doesPointsSeparatedByKApartByRange(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Negative test for LIC13 with invalid input
     * Verifies that LIC13 returns false when the number of input points is < 5.
     */
    @Test
    public void lic13TestInvalidInput() {
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);
        Point[] planarPoints = {
            new Point(0,0),
            new Point(0,0),
            new Point(0,0),
        };
        assertFalse(licHandler.lic13(planarPoints.length, planarPoints));
    }


    /**
     * Positive test for LIC13
     * Verifies that LIC13 returns true when there exist a triplet of points
     * that cannot fit within a circle of radius RADIUS1, but there exists a 
     * triplet (possibly different triplet) of points that can fit within a
     * circle of radius RADIUS2.
     */
    @Test
    public void lic13CanFitR2ButNotR1() {
        // Uses A_PTS = B_PTR = RADIUS1 = 1 and RADIUS2 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);
        Point[] planarPoints = {
            new Point(0,0),
            new Point(10,0), // skipped
            new Point(3,0),
            new Point(20,0), // skipped
            new Point(0,3),
        };
        assertTrue(licHandler.lic13(planarPoints.length, planarPoints));

    }

    /**
     * Negative test for LIC13
     * Verifies that LIC13 returns false when there exists a triplet of points
     * that can fit within a circle of radius RADIUS1.
     */
    @Test
    public void lic13FitR1ButNotR2() {
        // Uses A_PTS = B_PTR = RADIUS1 = 1 and RADIUS2 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);
        Point[] planarPoints = {
            new Point(0,0),
            new Point(0,0), // skipped
            new Point(1,0),
            new Point(0,1), // skipped
            new Point(0,0),
        };
        assertFalse(licHandler.lic13(5, planarPoints));
    }

    /**
     * Negative test for LIC13.
     * Verifies that LIC13 returns false when there exist no triplet of points
     * that fit a circle of radius RADIUS1 nor a circle of radius RADIUS2.
     */
    @Test
    public void lic13FitNone() {
        // Uses A_PTS = B_PTR = RADIUS1 = 1 and RADIUS2 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);
        Point[] planarPoints = {
            new Point(0,0),
            new Point(10,0),
            new Point(20,0),
            new Point(0,10),
            new Point(0,20),
        };
        assertFalse(licHandler.lic13(planarPoints.length, planarPoints));    
    }

    /**
     * Negative test for LIC13.
     * Verifies that LIC13 returns false when there exist triplet(s) of points
     * that fit both a circle of radius RADIUS1 and a circle of radius RADIUS2.
     */
    @Test
    public void lic13FitBothR1AndR2() {
        // Uses A_PTS = B_PTR = RADIUS1 = 1 and RADIUS2 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);
        Point[] planarPoints = {
            new Point(0.0,0.0),
            new Point(0.2,0),
            new Point(0.4,0.0),
            new Point(0.2,0.2),
            new Point(0.4,0.2),
            new Point(0.3, 0.1)
        };
        assertFalse(licHandler.lic13(planarPoints.length, planarPoints));    
    }   
}