/**
 * LIC operations for the logical connector matrix
 */
enum LogicalConnector {
  ANDD,
  ORR,
  NOTUSED,
}

/**
 * Handles the anti ballistic missile system
 */
public class AntiBallisticMissileSystem {
  /**
   * Determines whether an interceptor should be launched
   * based upon input radar tracking information and outputs
   * "YES" to standard out if it should, otherwise outputs "NO"
   * 
   * @param planarPointAmount amount of planar points
   * @param planarPoints array of Points representing radar echoes
   * @param launchInterceptorConditionParameters parameters for the launch interceptor conditions
   * @param logicalConnectorMatrix defines which individual launch interceptor conditions must be considered jointly in some way
   * @param preliminaryUnlockingVector represents which launch interceptor conditions actually matter in this particular launch determination
   */
  static void decide(int planarPointAmount, Point[] planarPoints, LaunchInterceptorConditionParameters launchInterceptorConditionParameters, LogicalConnector[][] logicalConnectorMatrix, boolean[] preliminaryUnlockingVector)
  {
    System.out.println("NO");
  }
}