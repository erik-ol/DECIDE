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



    //// TEST CASES FOR LIC 14

    /**
     * Negative test for LIC14 with invalid input.
     * Verifies that LIC14 returns false when NUMPOINTS < 5.
     */
    @Test
    public void lic14FalseWhenTooFewPoints() {
        // E_PTS=1, F_PTS=1, AREA1=1.0, AREA2=10.0
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 1.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 10.0);
        Point[] planarPoints = {
            new Point(0, 0),
            new Point(5, 0),
            new Point(0, 5),
            new Point(1, 1),
        };
        assertFalse(licHandler.lic14(planarPoints.length, planarPoints));
    }

    /**
     * Positive test for LIC14.
     * Verifies that LIC14 returns true when one triplet has area > AREA1
     * and another triplet has area < AREA2.
     */
    @Test
    public void lic14TrueWhenBothConditionsMet() {
        // E_PTS=1, F_PTS=1, AREA1=1.0, AREA2=100.0
        // Triplet from i=0: (0,0),(10,0),(0,10) => area=50 > 1.0 AND 50 < 100.0
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 1.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 100.0);
        Point[] planarPoints = {
            new Point(0, 0),
            new Point(99, 99),  // skipped
            new Point(10, 0),
            new Point(99, 99),  // skipped
            new Point(0, 10),
        };
        assertTrue(licHandler.lic14(planarPoints.length, planarPoints));
    }

    /**
     * Negative test for LIC14.
     * Verifies that LIC14 returns false when area > AREA1 is satisfied but
     * no triplet has area < AREA2.
     */
    @Test
    public void lic14FalseWhenOnlyFirstConditionMet() {
        // E_PTS=1, F_PTS=1, AREA1=1.0, AREA2=0.0 (no area can be < 0)
        // Triplet: (0,0),(10,0),(0,10) => area=50 > 1.0 but 50 is not < 0
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 1.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0.0);
        Point[] planarPoints = {
            new Point(0, 0),
            new Point(99, 99),
            new Point(10, 0),
            new Point(99, 99),
            new Point(0, 10),
        };
        assertFalse(licHandler.lic14(planarPoints.length, planarPoints));
    }

    /**
     * Negative test for LIC14.
     * Verifies that LIC14 returns false when area < AREA2 is satisfied but
     * no triplet has area > AREA1.
     */
    @Test
    public void lic14FalseWhenOnlySecondConditionMet() {
        // E_PTS=1, F_PTS=1, AREA1=1000.0, AREA2=100.0
        // Triplet: (0,0),(1,0),(0,1) => area=0.5, not > 1000, but 0.5 < 100
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 1000.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 100.0);
        Point[] planarPoints = {
            new Point(0, 0),
            new Point(99, 99),
            new Point(1, 0),
            new Point(99, 99),
            new Point(0, 1),
        };
        assertFalse(licHandler.lic14(planarPoints.length, planarPoints));
    }

    /**
     * Negative test for LIC14.
     * Verifies that LIC14 returns false when points are collinear (area = 0),
     * so area > AREA1 is never met (for AREA1 > 0).
     */
    @Test
    public void lic14FalseWhenCollinear() {
        // E_PTS=1, F_PTS=1, AREA1=1.0, AREA2=100.0
        // Collinear: (0,0),(5,0),(10,0) => area=0, not > 1.0
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 1.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 100.0);
        Point[] planarPoints = {
            new Point(0, 0),
            new Point(99, 99),
            new Point(5, 0),
            new Point(99, 99),
            new Point(10, 0),
        };
        assertFalse(licHandler.lic14(planarPoints.length, planarPoints));
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