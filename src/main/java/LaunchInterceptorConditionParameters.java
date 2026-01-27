/**
 * Holds parameters for the Launch Interceptor Conditions
 */
public class LaunchInterceptorConditionParameters
{
    private double LENGTH1;
    private double RADIUS1;
    private double EPSILON;
    private double AREA1;
    private int Q_PTS;
    private int QUADS;
    private double DIST;
    private int N_PTS;
    private int K_PTS;
    private int A_PTS;
    private int B_PTS;
    private int C_PTS;
    private int D_PTS;
    private int E_PTS;
    private int F_PTS;
    private int G_PTS;
    private double LENGTH2;
    private double RADIUS2;
    private double AREA2;
    
    /**
     * Connstructs the LaunchInterceptorConditionParameters
     * @param LENGTH1 Length in LICs 0, 7, 12
     * @param RADIUS1 Radius in LICs 1, 8, 13
     * @param EPSILON Deviation from PI in LICs 2, 9
     * @param AREA1 Area in LICs 3, 10, 14
     * @param Q_PTS No. of consecutive points in LIC 4
     * @param QUADS No. of quadrantsin LIC 4
     * @param DIST Distance in LIC 6
     * @param N_PTS No. of consecutive pts. in LIC 6
     * @param K_PTS No. of int. pts. in LICs 7, 12
     * @param A_PTS No. of int. pts. in LICs 8, 13
     * @param B_PTS No. of int. pts. in LICs 8, 13
     * @param C_PTS No. of int. pts. in LIC 9
     * @param D_PTS No. of int. pts. in LIC 9
     * @param E_PTS No. of int. pts. in LICs 10, 14
     * @param F_PTS No. of int. pts. in LICs 10, 14
     * @param G_PTS No. of int. pts. in LIC 11
     * @param LENGTH2 Maximum length in LIC 12
     * @param RADIUS2 Maximum radius in LIC 13
     * @param AREA2 Maximum area in LIC 14
     */
    public LaunchInterceptorConditionParameters( double LENGTH1, double RADIUS1, double EPSILON, double AREA1, int Q_PTS, int QUADS,
                                                 double DIST, int N_PTS, int K_PTS, int A_PTS, int B_PTS, int C_PTS, int D_PTS, int E_PTS,
                                                 int F_PTS, int G_PTS, double LENGTH2, double RADIUS2, double AREA2)
    {

        this.LENGTH1 = LENGTH1;
        this.RADIUS1 = RADIUS1;
        this.EPSILON = EPSILON;
        this.AREA1 = AREA1;
        this.Q_PTS = Q_PTS;
        this.QUADS = QUADS;
        this.DIST = DIST;
        this.N_PTS = N_PTS;
        this.K_PTS = K_PTS;
        this.A_PTS = A_PTS;
        this.B_PTS = B_PTS;
        this.C_PTS = C_PTS;
        this.D_PTS = D_PTS;
        this.E_PTS = E_PTS;
        this.F_PTS = F_PTS;
        this.G_PTS = G_PTS;
        this.LENGTH2 = LENGTH2;
        this.RADIUS2 = RADIUS2;
        this.AREA2 = AREA2;
    }
    
    /**
     * Launch Interceptor Condition 0:
     * Verifies if there exists at least one set of two consecutive 
     * data points that are at a distance greater than length LENGTH1 apart
     * 
     * @param planarPointAmount - Number of planar points
     * @param planarPoints      - Planar points array
     * 
     * @return True iff at least one set of 2 consecutive data points that are a distance greater than length LENGTH1 apart
     */
    public boolean doesTwoConsecutivePointsFurtherThanLength1(int planarPointAmount, Point[] planarPoints)
    {
        // Check all consecutive points
        for (int point = 1; point < planarPointAmount; point++){
            double deltaX = planarPoints[point].getX() - planarPoints[point-1].getX();
            double deltaY = planarPoints[point].getY() - planarPoints[point-1].getY();
            double distance = (deltaX * deltaX) + (deltaY * deltaY);
            
            // Compare the squared distance rather than taking square root
            double distance_required = this.LENGTH1 * this.LENGTH1;

            if (distance > distance_required) return true;
        }
        return false;
    }

    /**
     * Launch Interceptor Condition 1
     * @return True iff there exists 3 consecutive points that cannot all be contained within or on a circle of radius RADIUS1
     */
    public boolean lic_1(int planarPointAmount, Point[] planarPoints)
    {
        for (int i = 2; i < planarPointAmount; i++)
        {
            // compare pairwise distances
            for (int point1 = i-2; point1 <= i; point1++)
            {
                for (int point2 = i-2; point2 <= i; point2++)
                {

                    double deltaX = planarPoints[point1].getX() - planarPoints[point2].getX();
                    double deltaY = planarPoints[point1].getY() - planarPoints[point2].getY();
                    double distance = (deltaX*deltaX)+(deltaY*deltaY);

                    // compare squared distances rather than taking square root
                    double diameterSquared = RADIUS1*RADIUS1*4;

                    if (distance > diameterSquared) return true;

                    double diameter_squared = RADIUS1*RADIUS1*4;

                    if (distance > diameter_squared) return true;
                }
            }
        }

        // no points met conditions
        return false;
    }

    /**
     * Launch Interceptor Condition 2:
     * Verifies if there exists at least one set of three consecutive
     * data points which form an angle such that (PI + EPSILON) < angle
     * or angle < (PI - EPSILON), as a part of the LICs.
     *
     * @param planarPoints      - The array of Points to validate
     * @param planarPointAmount - The amount of planar Points within the planarPoints array
     *
     * @return true if there exists a set of three consecutive data points
     *         that meets the conditions and false otherwise.
     */
    public boolean validateAngle(int planarPointAmount, Point[] planarPoints)
    {
        // Check if the array has enough elements
        if(planarPointAmount < 3)
            return false;

        for(int point = 0; point < planarPointAmount; point += 3)
        {
            double[] firstPoint = {planarPoints[point].getX(), planarPoints[point].getY()};
            double[] vertex     = {planarPoints[point + 1].getX(), planarPoints[point + 1].getY()};
            double[] lastPoint  = {planarPoints[point + 2].getX(), planarPoints[point + 2].getY()};

            // Check if the first and last point coincides with the vertex
            double epsilon = 1e-9;
            boolean firstNotVertex = Math.abs(firstPoint[0] - vertex[0]) > epsilon || Math.abs(firstPoint[1] - vertex[1]) > epsilon;
            boolean lastNotVertex  = Math.abs(lastPoint[0] - vertex[0]) > epsilon || Math.abs(lastPoint[1] - vertex[1]) > epsilon;

            if(firstNotVertex && lastNotVertex)
            {
                double[] vertexToFirstPoint = {firstPoint[0] - vertex[0], firstPoint[1] - vertex[1]};
                double[] vertexToLastPoint  = {lastPoint[0] - vertex[0], lastPoint[1] - vertex[1]};

                double angle = Math.atan2((vertexToFirstPoint[0] * vertexToLastPoint[1]) + (vertexToFirstPoint[1] * vertexToLastPoint[0]),
                                          (vertexToFirstPoint[0] * vertexToLastPoint[0]) + (vertexToFirstPoint[1] * vertexToLastPoint[1]));

                if(angle < 0)
                    angle += 2 * Math.PI;

                if ((angle < (Math.PI - EPSILON)) || (angle > (Math.PI + EPSILON)))
                    return true;
            }
        }

        return false;
    }

    /**
     * Launch Interceptor Condition 3:
     * Verifies there exists at least one set of three
     * consecutive data points that are the vertices of
     * a triangle with area greater than AREA1.
     * 
     * @param planarPoints      - The array of Points to validate
     * @param planarPointAmount - The amount of planar Points within the planarPoints array
     * 
     * @return true if there exists a set of three consecutive datapoints
     *          that meets the conditions and false otherwise.
     */
    public boolean validateTriangleArea(int planarPointAmount, Point[] planarPoints)
    {

        for (int i = 2; i < planarPointAmount; i++)
        {

            double area = triangleArea(planarPoints[i-2], planarPoints[i-1], planarPoints[i]);
           
            if (area>AREA1) return true;
        }

        // no points met conditions
        return false;
    }
  
    /**
     * Calculates area of triangle from coordinates of corners
     *
     * @param corner0 - 1st corner
     * @param corner1 - 2nd corner
     * @param corner2 - 3rd corner
     *
     * @return area of triangle
     */
    private double triangleArea(Point corner0, Point corner1, Point corner2)
    {
        // shoelace formula for triangle area
        double determinant = ((corner0.getX()-corner2.getX()) *
                              (corner1.getY()-corner0.getY())) -
                             ((corner0.getX()-corner1.getX()) *
                              (corner2.getY()-corner0.getY()));

        double area = Math.abs(determinant)/2;

        return area;
    }

    /**
     * Launch Interceptor Condition 5
     *
     * @param planarPointAmount - the number of planar points
     * @param planarPoints      - the planar points
     *
     * @return True if there exists at least one set of 2 consecutive data points
     *         such that the difference in x-coordinates is less than 0.
     */
    public static boolean lic5(Point[] planarPoints, int planarPointAmount)
    {
        if (planarPointAmount < 2)
        {
            return false;
        }

        for (int i = 0; i < planarPointAmount - 1; i++)
        {
            Point p1 = planarPoints[i];
            Point p2 = planarPoints[i + 1];
            double p1X = p1.getX();
            double p2X = p2.getX();

            if (p2X - p1X < 0)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Launch Interceptor Condition 7:
     * Verify if there are at least one set of 2 data points
     * that are separated by K_PTS (exclusively)
     * that has a distance larger than LENGHT1
     *
     * @param planarPointAmount - the number of planar points
     * @param planarPoints      - the planar points
     *
     * @return True if there exists at least one set of 2 data points separated
     *         by K_PTS exclusively have a distance larger than LENGTH1.
     */
    public boolean doesPointsSeparatedByKFurtherThanLength1(Point[] planarPoints, int planarPointAmount)
    {
        if (planarPointAmount < 3)
        {
            return false;
        }

        for (int i = K_PTS + 1; i < planarPointAmount; i++)
        {
            double deltaX = planarPoints[i].getX() - planarPoints[i - K_PTS - 1].getX();
            double deltaY = planarPoints[i].getY() - planarPoints[i - K_PTS - 1].getY();
            double distance = (deltaX * deltaX) + (deltaY * deltaY);

            double distance_required = this.LENGTH1 * this.LENGTH1;

            if (distance > distance_required)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies Launch Interceptor Condition 8
     * LIC8 is true iff:
     * There exists at least one triplet of consecutive points, separated by
     * A_PTS and B_PTS, that cannot be contained with or on a circle of radius RADIUS1
     * 
     * @param planarPointAmount - number of points in the planarPoints array
     * @param planarPoints      - the array of 2D coordinates
     * 
     * @return true if the LIC13 condition is satisfied; false otherwise
     */
    public boolean lic8(int planarPointAmount, Point[] planarPoints)
    {

        if(planarPointAmount < 5)
        {
            return false;
        }

        for(int i = 0; i + A_PTS + B_PTS + 2 < planarPointAmount; i++)
        {
            Point p1 = planarPoints[i];
            Point p2 = planarPoints[i+A_PTS+1];
            Point p3 = planarPoints[i+A_PTS+B_PTS+2];

            double diameterSquared = 4*Math.pow(RADIUS1, 2);

            // Compute squared distances between points
            double distance12 = Math.pow(p1.getX()-p2.getX(), 2) + Math.pow(p1.getY()-p2.getY(), 2);
            double distance13 = Math.pow(p1.getX()-p3.getX(), 2) + Math.pow(p3.getY()-p3.getY(), 2);
            double distance23 = Math.pow(p2.getX()-p3.getX(), 2) + Math.pow(p2.getY()-p3.getY(), 2);
            
            if (distance12 > diameterSquared || distance13 > diameterSquared || distance23 > diameterSquared)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Launch Interceptor Condition 9:
     * Verifies if there exists at least one set of three points separated
     * exactly C_PTS and D_PTS consecutive intervening points, respectively,
     * such that: angle < (PI - EPSILON) or angle > (PI + EPSILON), where
     * angle is the angle between the three points. This as a part of the LICs.
     *
     * @param planarPoints      - The array of Points to validate
     * @param planarPointAmount - The amount of planar Points within the planarPoints array
     *
     * @return true if the points are separated by C_PTS and D_PTS as well as
     *         fulfilling the validateAngle() condition, otherwise false.
     */
    public boolean validateConsecutivePointsSeparation(int planarPointAmount, Point[] planarPoints)
    {
        if((planarPointAmount < 5) || (1 <= C_PTS) || (1 <= D_PTS) || ((C_PTS + D_PTS) <= (planarPointAmount - 3)))
            return false;

        for(int point = 0; point < planarPointAmount; point += (3 + C_PTS + D_PTS))
        {
            Point firstPoint = planarPoints[point];
            Point vertex     = planarPoints[point + C_PTS + 1];
            Point lastPoint  = planarPoints[point + C_PTS + 1 + D_PTS + 1];

            Point[] cornerPoints = {firstPoint, vertex, lastPoint};

            if(validateAngle(cornerPoints.length, cornerPoints))
                return true;
        }

        return false;
    }

    /**
     * Launch Interceptor Condition 12:
     * Verify if there exists at least one set of 2 points
     * that are separated by K_PTS (exclusively)
     * that is longer than LENGHT1
     * But shorter than LENGTH2
     * 
     * @param planarPointAmount - Number of planar points
     * @param planarPoints      - Array of planar points
     *
     * @return True if there exists at least one set of 2 points separated by K_PTS
     *         exclusively that is longer than LENGTH1 but shorter than LENGHT2
     */
    public boolean doesPointsSeparatedByKApartByRange(Point[] planarPoints, int planarPointAmount)
    {
        if (planarPointAmount < 3)
        {
            return false;
        }
        for (int i = K_PTS + 1; i < planarPointAmount; i++)
        {
            double deltaX = planarPoints[i].getX() - planarPoints[i - K_PTS - 1].getX();
            double deltaY = planarPoints[i].getY() - planarPoints[i - K_PTS - 1].getY();
            double distance = (deltaX * deltaX) + (deltaY * deltaY);

            double distance_required_1 = this.LENGTH1 * this.LENGTH1;
            double distance_required_2 = this.LENGTH2 * this.LENGTH2;

            if ((distance > distance_required_1)&&(distance < distance_required_2))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Verifies Launch Interceptor Condition 13
     * LIC13 is true iff:
     * 1) There exists at least one triplet of points, separated by A_PTS and B_PTS,
     * that cannot be contained with or on a circle of radius RADIUS1, and
     * 2) There exists at least one triplet of points, separated by A_PTS and B_PTS,
     * that can be contained with or on a circle of radius RADIUS2.
     * 
     * @param planarPointAmount - number of points in the planarPoints array
     * @param planarPoints      - the array of 2D coordinates
     * 
     * @return true if the LIC13 condition is satisfied; false otherwise
     */
    public boolean lic13(int planarPointAmount, Point[] planarPoints)
    {

        if(planarPointAmount < 5)
        {
            return false;
        }

        boolean condition1 = false;
        boolean condition2 = false;

        for(int i = 0; i + A_PTS + B_PTS + 2 < planarPointAmount; i++)
        {
            Point p1 = planarPoints[i];
            Point p2 = planarPoints[i+A_PTS+1];
            Point p3 = planarPoints[i+A_PTS+B_PTS+2];

            double diameter1Squared = 4*Math.pow(RADIUS1, 2);
            double diameter2Squared = 4*Math.pow(RADIUS2, 2);

            // Compute squared distances between points
            double distance12 = Math.pow(p1.getX()-p2.getX(), 2) + Math.pow(p1.getY()-p2.getY(), 2);
            double distance13 = Math.pow(p1.getX()-p3.getX(), 2) + Math.pow(p1.getY()-p3.getY(), 2);
            double distance23 = Math.pow(p2.getX()-p3.getX(), 2) + Math.pow(p2.getY()-p3.getY(), 2);
            
            if(!(distance12 <= diameter1Squared && distance13 <= diameter1Squared && distance23 <= diameter1Squared))
            {
                condition1 = true;
            }
            if(distance12 <= diameter2Squared && distance13 <= diameter2Squared && distance23 <= diameter2Squared)
            {
                condition2 = true;
            }
        }
        return condition1 && condition2;
    }
}

