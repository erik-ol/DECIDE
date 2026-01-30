/**
 * Main class for running AntiBallisticMissileSystem.decide standalone
 */
public class Main {
    /**
     * Shows succesfull call to AntiBallisticMissileSystem.decide
     * @param args - unused
     */
    public static void main(String[] args) {

        Point[] points = {new Point(0, 0),
                          new Point(3, 0),
                          new Point(1, 2),
                          new Point(7, 0),
                          new Point(2, 4),
                          new Point(6, 1),
                          new Point(-1, -1)};

        LaunchInterceptorConditionParameters lic = new LaunchInterceptorConditionParameters(2, 1.5, 0.1,
                                                                                            1, 2, 1, 0,
                                                                                            3, 1, 1, 1,
                                                                                            1, 1, 1, 1,
                                                                                            1, 10, 10, 6);

        LogicalConnector[][] logicalConnectorMatrix = new LogicalConnector[15][15];
        for (int i = 0; i<15; i++) {
            for (int j = 0; j<15; j++) {
                logicalConnectorMatrix[i][j] = LogicalConnector.ANDD;
            }
        }

        boolean[] puv = new boolean[15];
        for (int i = 0; i<15; i++) {
            puv[i] = true;
        }

        AntiBallisticMissileSystem.decide(points.length, points, lic, logicalConnectorMatrix, puv);

    }
}
