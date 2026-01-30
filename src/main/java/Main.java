public class Main {
    public static void main(String[] args) {
        Point[] planarPoints = {new Point(0, 0),
                          new Point(3, 0),
                          new Point(1, 2),
                          new Point(7, 0),
                          new Point(2, 4),
                          new Point(6, 1)};

        LaunchInterceptorConditionParameters LaunchInterceptorConditions = new LaunchInterceptorConditionParameters(2, 1.5, 0.1,
                                                                                            1, 1, 0, 0,
                                                                                            3, 1, 1, 1,
                                                                                            1, 1, 1, 1,
                                                                                            1, 10, 10, 6);
        
        int planarPointAmount = planarPoints.length;

        LogicalConnector[][] logicalConnectorMatrix = new LogicalConnector[15][15];
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                logicalConnectorMatrix[i][j] = LogicalConnector.ORR;
            }
        }

        boolean[] preliminaryUnlockingVector = new boolean[15];
        for (int i = 0; i < 15; i++){
            preliminaryUnlockingVector[i] = true;
        }

        AntiBallisticMissileSystem.decide(planarPointAmount, planarPoints, LaunchInterceptorConditions, logicalConnectorMatrix, preliminaryUnlockingVector);
    }
}
