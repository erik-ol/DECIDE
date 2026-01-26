import java.beans.Transient;

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
                pointArrayEqualSpace[point] = new Point(8 * point, 8 * point);
            }
            LaunchInterceptorConditionParameters lic0Handler = new LaunchInterceptorConditionParameters(64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean resI = lic0Handler.lic0(points, pointArraySame);
            boolean resII = lic0Handler.lic0(points, pointArrayEqualSpace);

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
            pointArray[49] = new Point(-8, -8);

            LaunchInterceptorConditionParameters lic0Handler = new LaunchInterceptorConditionParameters(64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic0Handler.lic0(points, pointArray);
            Assert.assertTrue(res);
        }

        
    }
    
}