/**
 * Holds parameters for the Launch Interceptor Conditions
 */
public class LaunchInterceptorConditionParameters {
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
                                                 int F_PTS, int G_PTS, double LENGTH2, double RADIUS2, double AREA2) {

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
     * Launch Interceptor Condition 0
     * @param planarPointAmount
     * @param planarPoints
     * @return True iff at least one set of 2 consecutive data points that are a distance greater than length LENGTH1 apart
     */
    public boolean lic_0(int planarPointAmount, Point[] planarPoints){
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
    public boolean lic_1(int planarPointAmount, Point[] planarPoints) {

        for (int i = 2; i < planarPointAmount; i++) {
            // compare pairwise distances
            for (int point1 = i-2; point1 <= i; point1++) {
                for (int point2 = i-2; point2 <= i; point2++) {

                    double deltaX = planarPoints[point1].getX() - planarPoints[point2].getX();
                    double deltaY = planarPoints[point1].getY() - planarPoints[point2].getY();
                    double distance = (deltaX*deltaX)+(deltaY*deltaY);

                    // compare squared distances rather than taking square root
                    double diameter_squared = RADIUS1*RADIUS1*4;

                    if (distance > diameter_squared) return true;
                }
            }
        }

        // no points met conditions
        return false;
    }
    /**
     * Launch Interceptor Condition 5
     * 
     * @param planarPoints the planar points
     * @param planarPointAmount the number of planar points
     * @return True if there exists at least one set of 2 consecutive data points such that the difference in x-coordinates is less than 0.
     */
    public static boolean lic5(Point[] planarPoints, int planarPointAmount) {

        if(planarPointAmount < 2) {
            return false;
        }

        for (int i = 0; i < planarPointAmount-1; i++) {
            Point p1 = planarPoints[i];
            Point p2 = planarPoints[i+1];
            double p1X = p1.getX();
            double p2X = p2.getX();

            if(p2X - p1X < 0) {
                return true;
            }
        }
        return false;
    }
}