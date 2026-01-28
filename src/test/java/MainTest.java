import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Random;

import org.junit.*;

import main.java.LaunchInterceptorConditionParameters;
import main.java.Point;

/**
 * Test class 
 */
public class MainTest
{
    /**
     * Test situations where lic0 should be false
     */
    @Test
    public void lic0Negative()
    {
        for (int points = 2; points < 20; points++)
        {
            // Case I: When all points are the same
            // Case II: When all points are equally spaced
            Point[] pointArraySame = new Point[points];
            Point[] pointArrayEqualSpace = new Point[points];

            pointArraySame[0] = new Point(0, 0);
            pointArrayEqualSpace[0] = new Point(0, 0);

            for (int point = 1; point < points; point++)
            {
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

    /**
     * Test situations where lic0 should be true
     */
    @Test
    public void lic0positive()
    {

        for (int points = 2; points < 20; points++)
        {

            Point[] pointArray = new Point[points];

            for (int point = 0; point < (points - 1); point++)
            {
                pointArray[point] = new Point(7 * point, 7 * point);
            }

            // Add outlier point
            pointArray[points - 1] = new Point(-8, -8);

            LaunchInterceptorConditionParameters lic0Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic0Handler.doesTwoConsecutivePointsFurtherThanLength1(points, pointArray);
            Assert.assertTrue(res);
        }
    }

    /**
     * Tests situations where lic1 should be false
     */
    @Test
    public void lic1Negative()
    {

        Random random = new Random();
        
        for (int points = 0; points<100; points++)
        {

            Point[] pointArray = new Point[points];

            for (int point = 0; point<points; point++)
            {
                double pointX = random.nextDouble()*9;
                double pointY = random.nextDouble()*9;
                pointArray[point] = new Point(pointX, pointY);

            }

            LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            
            Assert.assertFalse(licHandler.lic_1(points, pointArray));
        }
        
    }

    /**
     * Tests situations where lic1 should be true
     */
    @Test
    public void lic1Positive()
    {

        Random random = new Random();
        
        for (int points = 3; points<100; points++)
        {

            Point[] pointArray = new Point[points];

            for (int point = 0; point<points; point++)
            {
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

    /**
     * Tests LIC-2 angle verification for the case when the array is too short.
     */
    @Test
    public void lic2ValidateAngleTooFewPoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 2.0),
                          new Point(3.0, 4.0)};

        Assert.assertFalse(lic.validateAngle(points.length, points));
    }

    /**
     * Tests LIC-2 angle verification for the case when the array has one Point
     * too many.
     */
    @Test
    public void lic2ValidateAngleTooManyPoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 2.0),
                          new Point(3.0, 4.0),
                          new Point(1.0, 2.0),
                          new Point(3.0, 4.0)};

        Assert.assertTrue(lic.validateAngle(points.length, points));
    }

    /**
     * Tests LIC-2 angle verification for the case when a set of three data points
     * has a Point that coincides with the vertex.
     */
    @Test
    public void lic2ValidateAnglePointEqualToVertex()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 2.0),
                          new Point(1.0, 2.0),
                          new Point(3.0, 4.0)};

        Assert.assertFalse(lic.validateAngle(points.length, points));
    }

    /**
     * Tests LIC-2 angle verification for the case when a set of three data points
     * has two equal Points, excluding the vertex.
     */
    @Test
    public void lic2ValidateAngleTwoEqualPoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0);

        Point[] points = {new Point(1.0, 2.0),
                          new Point(3.0, 4.0),
                          new Point(1.0, 2.0)};

        Assert.assertTrue(lic.validateAngle(points.length, points));
    }

    /**
     * Tests LIC-2 angle verification for the case when all Points have different
     * values creating an angle > (PI + EPSILON).
     */
    @Test
    public void lic2ValidateAngleDifferentPoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 0.0),
                          new Point(0.0, 0.0),
                          new Point(0.1, -1.0)};

        Assert.assertTrue(lic.validateAngle(points.length, points));
    }

    /**
     * Tests that LIC-3 triangle verification is false for line of points where consecutive points are very close
     */
    @Test
    public void lic3FalseForRandomizedLineOfPoints()
    {
        Random random = new Random();

        for (int points = 0; points<100; points++)
        {
            Point[] pointArray = new Point[points];

            if (points>0) pointArray[0] = new Point(0, 0);

            for (int point = 1; point<points; point++)
            {
                double pointX = pointArray[point-1].getX() + random.nextDouble();
                double pointY = pointArray[point-1].getY() + random.nextDouble() - 0.5;
                pointArray[point] = new Point(pointX, pointY);

            }

            LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            Assert.assertFalse(licHandler.validateTriangleArea(points, pointArray));
        }
    }

    /**
     * Tests that LIC-3 triangle verification is true for a triangle with area greater than AREA1
     */
    @Test
    public void lic3TrueWhenTriangleAreaSufficient()
    {
        int points = 4;

        Point[] pointArray =
                {
                new Point(0.0, 0.0),
                new Point(1.0, 0.0),
                new Point(5.0, 0.0),
                new Point(2.0, 11.0)
        };

        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

        Assert.assertTrue(licHandler.validateTriangleArea(points, pointArray));

    }

    @Test
    /**
     * Tests situations where lic4 must be false due to more QUADS than Q_PTS
     */
    public void lic4FalseWhenMoreQuadsThanPoints() {

        Random random = new Random();
        
        for (int points = 0; points<100; points++) {

            Point[] pointArray = new Point[points];

            for (int point = 0; point<points; point++) {
                double pointX = (random.nextDouble() - 0.5) * 100;
                double pointY = (random.nextDouble() - 0.5) * 100;
                pointArray[point] = new Point(pointX, pointY);

            }

            LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 0, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            
            Assert.assertFalse(licHandler.validateQuadrants(points, pointArray));
        }
        
    }

    @Test
    /**
     * Tests situations where lic4 should be false due to quadrant priority
     */
    public void lic4FalseFromQuadOverlap() {

        int points = 4;

        Point[] pointArray = {
            new Point(0.0, 0.0),
            new Point(0.0, 0.0),
            new Point(0.0, 0.0),
            new Point(0.0, 0.0)
        };

        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        
        Assert.assertFalse(licHandler.validateQuadrants(points, pointArray));
        
    }

    @Test
    /**
     * Tests situations where lic4 should be true since each quadrant contains a point and QUADS is 3
     */
    public void lic4Positive() {

        int points = 4;

        Point[] pointArray = {
            new Point(1.0, -1.0),
            new Point(-1.0, -1.0),
            new Point(-1.0, 1.0),
            new Point(1.0, 1.0)
        };

        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 0, 0, 0, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        
        Assert.assertTrue(licHandler.validateQuadrants(points, pointArray));
        
    }

    /**
    * Test situation where the x-coordinate decreases
    */
    @Test

    public void lic5TrueWhenXDecreases()
    {
        Point[] points = {new Point(3.0, 0.0),
                          new Point(2.0, 0.0)};

        boolean res = LaunchInterceptorConditionParameters.lic5(points, 2);
        assertTrue(res);
    }

    /**
     * Test situation where the x-coordinate increases
     */
    @Test
    public void lic5FalseWhenXIncreases()
    {
        Point[] points = {new Point(2.0, 0.0),
                          new Point(3.0, 0.0)};

        boolean res = LaunchInterceptorConditionParameters.lic5(points, 2);
        assertFalse(res);
    }

    /**
     * Test situation where the x-coordinate stays the same
     */
    @Test
    public void lic5FalseWhenXIsEqual()
    {
        Point[] points = {new Point(2.0, 0.0),
                          new Point(2.0, 0.0)};

        boolean res = LaunchInterceptorConditionParameters.lic5(points, 2);
        assertFalse(res);
    }

    /**
     * Test situation where all points are the same
     */
    @Test
    public void lic7FalseWhenAllPointsSame()
    {
        for (int points = 3; points < 10; points++)
        {
            Point[] pointArray = new Point[points];

            for (int point = 0; point < points; point++)
            {
                pointArray[point] = new Point(0, 0);
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.doesPointsSeparatedByKFurtherThanLength1(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation when points pair differed by exactly LENGHT1
     */
    @Test
    public void lic7FalseWhenPointsPairEqual()
    {
        for (int points = 10; points < 20; points++)
        {
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++)
            {
                if (point != (points % 10 + 2))
                {
                    pointArray[point] = new Point(1, 1);
                }
                else
                {
                    pointArray[point] = new Point(3, 4);
                }
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points % 10 + 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.doesPointsSeparatedByKFurtherThanLength1(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation where number of points < 3
     */
    @Test
    public void lic7FalseWhenNotEnoughPoints()
    {
        for (int points = 1; points < 3; points++)
        {
            Point[] pointArray = new Point[points];

            for (int point = 0; point < points; point++)
            {
                pointArray[point] = new Point(0, 0);
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.doesPointsSeparatedByKFurtherThanLength1(pointArray, points);
            Assert.assertFalse(res);
        }
    }

    /**
     * Test situation where there is one exact pair of points longer than LENGTH1
     */
    @Test
    public void lic7TrueWhenOnePair()
    {
        for (int points = 10; points < 20; points++)
        {
            Point[] pointArray = new Point[points];

            for (int point = 0; point < points; point++)
            {
                if (point != (points % 10 + 2) ){
                    pointArray[point] = new Point(1, 1);
                }
                else
                {
                    pointArray[point] = new Point(10, 10);
                }
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points % 10 + 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.doesPointsSeparatedByKFurtherThanLength1(pointArray, points);
            Assert.assertTrue(res);
        }
    }

    /**
     * Test situation where all pairs longer than LENGTH1
     */
    @Test
    public void lic7TrueWhenAllPair()
    {
        for (int points = 10; points < 20; points++)
        {
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++)
            {
                if (point < (points / 2) )
                {
                    pointArray[point] = new Point(1, 1);
                }
                else
                {
                    pointArray[point] = new Point(point * 2 + 1, point * 3 + 1);
                }
            }

            for (int point = 0; point < points; point++)
            {
                System.out.print(pointArray[point].getX());
                System.out.print(", ");
                System.out.print(pointArray[point].getY());
                System.out.println();
            }

            LaunchInterceptorConditionParameters lic7Handler = new LaunchInterceptorConditionParameters(4, 0, 0, 0, 0, 0, 0, 0, points / 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

            boolean res = lic7Handler.doesPointsSeparatedByKFurtherThanLength1(pointArray, points);
            Assert.assertTrue(res);
        }
    }

    /**
     * Negative test for LIC8 with invalid input
     * Verifies that LIC8 returns false when the number of input points is < 5.
     */
    @Test
    public void lic8TestInvalidInput()
    {
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 5, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        Point[] planarPoints = {new Point(0,0),
                                new Point(0,0),
                                new Point(0,0)};

        assertFalse(licHandler.lic8(planarPoints.length, planarPoints));
    }

    /**
     * Positive test for LIC8
     * Verifies that LIC8 returns true when there exist at least one triplet
     * of consecutive points separated by A_PTS and B_PTS, that cannot fit
     * within a circle of radius RADIUS1.
     */
    @Test
    public void lic8CanFitR1()
    {
        // Uses A_PTS = B_PTS = 1 and RADIUS1 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 5, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        Point[] planarPoints = {new Point(0,10),
                                new Point(0,0), // skipped
                                new Point(20,0),
                                new Point(20,0), // skipped
                                new Point(30,0)};

        assertTrue(licHandler.lic8(planarPoints.length, planarPoints));
    }

    /**
     * Negative test for LIC8
     * Verifies that LIC8 returns false when there no triplet of consecutive
     * points, separated by A_PTS and B_PTS, that cannot fit within a circle of
     * radius RADIUS1.
     */
    @Test
    public void lic8CannotFitR1()
    {
        // Uses A_PTS = B_PTS = 1 and RADIUS1 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 5, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0);
        Point[] planarPoints = {new Point(0,0),
                                new Point(0,0), // skipped
                                new Point(1,0),
                                new Point(0,1), // skipped
                                new Point(0,0)};

        assertFalse(licHandler.lic8(planarPoints.length, planarPoints));
    }

    /**
     * LIC-9 consecutive points verification for the case when the Point array is
     * too short.
     */
    @Test
    public void lic9ValidateAngleConsecutivePointsSeparationTooFewPoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            1, 1, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 2.0),
                          new Point(2.0, 3.0)};

        Assert.assertFalse(lic.validateAngleConsecutivePointsSeparation(points.length, points));
    }

    /**
     * LIC-9 consecutive points verification for the case when too many Points.
     */
    @Test
    public void lic9ValidateAngleConsecutivePointsSeparationTooManyPoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            1, 1, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 0.0),
                          new Point(0.5, 0.0),
                          new Point(0.0, 0.0),
                          new Point(0.0, -0.5),
                          new Point(0.1, -1.0),
                          new Point(0.5, -1.0)};

        Assert.assertTrue(lic.validateAngleConsecutivePointsSeparation(points.length, points));
    }

    /**
     * LIC-9 consecutive points verification for the case when there are no
     * consecutive intervening Points.
     */
    @Test
    public void lic9ValidateAngleConsecutivePointsSeparationNoConsecutivePoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 0.0),
                          new Point(0.0, 0.0),
                          new Point(0.1, -1.0),
                          new Point(0.5, -1.0),
                          new Point(1.0, -1.0)};

        Assert.assertFalse(lic.validateAngleConsecutivePointsSeparation(points.length, points));
    }

    /**
     * LIC-9 consecutive points verification for the case when the angle
     * is not valid, where one periphery Points is equal to the vertex.
     */
    @Test
    public void lic9ValidateAngleConsecutivePointsSeparationInvalidAngle()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            1, 1, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 2.0),
                          new Point(1.0, 0.0),
                          new Point(1.0, 2.0),
                          new Point(2.0, 2.0),
                          new Point(3.0, 4.0)};

        Assert.assertFalse(lic.validateAngleConsecutivePointsSeparation(points.length, points));
    }

    /**
     * LIC-9 consecutive points verification for the case when all parameters
     * are valid.
     */
    @Test
    public void lic9ValidateAngleConsecutivePointsSeparationValidParameters()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, (Math.PI/2),
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            1, 1, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(1.0, 0.0),
                          new Point(0.5, 0.0),
                          new Point(0.0, 0.0),
                          new Point(0.0, -0.5),
                          new Point(0.1, -1.0)};

        Assert.assertTrue(lic.validateAngleConsecutivePointsSeparation(points.length, points));
    }

    /*
     * LIC-11 consecutive points verification for the case when the Point
     * array is too short.
     */
    @Test
    public void lic11ValidateConsecutivePointsSeparationTooFewPoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            1, 0, 0, 0);
        Point[] points = {new Point(1.0, 0.0),
                          new Point(0.0, 0.0)};

        Assert.assertFalse(lic.validateConsecutivePointsSeparation(points.length, points));
    }

    /**
     * LIC-11 consecutive points verification for the case when the Point
     * array is too long.
     */
    @Test
    public void lic11ValidateConsecutivePointsSeparationTooManyPoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            1, 0, 0, 0);
        Point[] points = {new Point(2.0, 0.0),
                          new Point(0.0, 0.0),
                          new Point(1.0, 0.0),
                          new Point(1.0, 1.0)};

        Assert.assertTrue(lic.validateConsecutivePointsSeparation(points.length, points));
    }

    /**
     * LIC-11 consecutive points verification for the case when there exist
     * no consecutive Points.
     */
    @Test
    public void lic11ValidateConsecutivePointsSeparationNoConsecutivePoints()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0);
        Point[] points = {new Point(2.0, 0.0),
                          new Point(0.0, 0.0),
                          new Point(1.0, 0.0)};

        Assert.assertFalse(lic.validateConsecutivePointsSeparation(points.length, points));
    }

    /**
     * LIC-11 consecutive points verification for the case when all parameters
     * are valid.
     */
    @Test
    public void lic11ValidateConsecutivePointsSeparationValidParameters()
    {
        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            0, 0, 0, 0,
                                                                                            1, 0, 0, 0);
        Point[] points = {new Point(2.0, 0.0),
                          new Point(0.0, 0.0),
                          new Point(1.0, 0.0)};

        Assert.assertTrue(lic.validateConsecutivePointsSeparation(points.length, points));
    }

    /**
     * Test situation where number of points < 3
     */
    @Test
    public void lic12FalseWhenNotEnoughPoints()
    {
        for (int points = 1; points < 3; points++)
        {
            Point[] pointArray = new Point[points];

            for (int point = 0; point < points; point++)
            {
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
    public void lic12FalseWhenAllPointsSame()
    {
        for (int points = 3; points < 10; points++)
        {
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++)
            {
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
    public void lic12FalseWhenPointsPairEqual1()
    {
        for (int points = 10; points < 20; points++)
        {
            Point[] pointArray = new Point[points];

            for (int point = 0; point < points; point++)
            {
                if (point < (points / 2))
                {
                    pointArray[point] = new Point(1, 1);
                }
                else
                {
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
    public void lic12TrueWhenAllPair()
    {
        for (int points = 10; points < 20; points++)
        {
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++)
            {
                if (point < (points / 2))
                {
                    pointArray[point] = new Point(1, 1);
                }
                else
                {
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
    public void lic12FalseWhenPointsPairEqual2()
    {
        for (int points = 10; points < 20; points++)
        {
            Point[] pointArray = new Point[points];
            for (int point = 0; point < points; point++)
            {
                if (point < (points / 2) )
                {
                    pointArray[point] = new Point(1, 1);
                }
                else
                {
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
    public void lic12FalseWhenPointsPairGreater()
    {
        for (int points = 10; points < 20; points++)
        {
            Point[] pointArray = new Point[points];

            for (int point = 0; point < points; point++)
            {
                if (point < (points / 2))
                {
                    pointArray[point] = new Point(1, 1);
                }
                else
                {
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
    public void lic13TestInvalidInput()
    {
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);

        Point[] planarPoints = {new Point(0,0),
                                new Point(0,0),
                                new Point(0,0)};

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
    public void lic13CanFitR2ButNotR1()
    {
        // Uses A_PTS = B_PTR = RADIUS1 = 1 and RADIUS2 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);

        Point[] planarPoints = {new Point(0,0),
                                new Point(10,0), // skipped
                                new Point(3,0),
                                new Point(20,0), // skipped
                                new Point(0,3)};

        assertTrue(licHandler.lic13(planarPoints.length, planarPoints));
    }

    /**
     * Negative test for LIC13
     * Verifies that LIC13 returns false when there exists a triplet of points
     * that can fit within a circle of radius RADIUS1.
     */
    @Test
    public void lic13FitR1ButNotR2()
    {
        // Uses A_PTS = B_PTR = RADIUS1 = 1 and RADIUS2 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);

        Point[] planarPoints = {new Point(0,0),
                                new Point(0,0), // skipped
                                new Point(1,0),
                                new Point(0,1), // skipped
                                new Point(0,0)};

        assertFalse(licHandler.lic13(5, planarPoints));
    }

    /**
     * Negative test for LIC13.
     * Verifies that LIC13 returns false when there exist no triplet of points
     * that fit a circle of radius RADIUS1 nor a circle of radius RADIUS2.
     */
    @Test
    public void lic13FitNone()
    {
        // Uses A_PTS = B_PTR = RADIUS1 = 1 and RADIUS2 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);

        Point[] planarPoints = {new Point(0,0),
                                new Point(10,0),
                                new Point(20,0),
                                new Point(0,10),
                                new Point(0,20)};

        assertFalse(licHandler.lic13(planarPoints.length, planarPoints));
    }

    /**
     * Negative test for LIC13.
     * Verifies that LIC13 returns false when there exist triplet(s) of points
     * that fit both a circle of radius RADIUS1 and a circle of radius RADIUS2.
     */
    @Test
    public void lic13FitBothR1AndR2()
    {
        // Uses A_PTS = B_PTR = RADIUS1 = 1 and RADIUS2 = 5
        LaunchInterceptorConditionParameters licHandler = new LaunchInterceptorConditionParameters(0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 5, 0);

        Point[] planarPoints = {new Point(0.0, 0.0),
                new Point(0.2, 0),
                new Point(0.4, 0.0),
                new Point(0.2, 0.2),
                new Point(0.4, 0.2),
                new Point(0.3, 0.1)};

        assertFalse(licHandler.lic13(planarPoints.length, planarPoints));
    }
}