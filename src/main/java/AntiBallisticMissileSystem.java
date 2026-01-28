/**
 * LIC operations for the logical connector matrix
 */
enum LogicalConnector
{
  ANDD,
  ORR,
  NOTUSED,
}

/**
 * Handles the anti-ballistic missile system
 */
public class AntiBallisticMissileSystem
{
  public static boolean[] conditionsMetVector = new boolean[15];

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
    initConditionsMetVector(launchInterceptorConditionParameters, planarPoints);

    System.out.println("NO");
  }

  /**
   * Initializes the conditions met vector (CMV) of which the elements shall
   * represent all the launch interceptor conditions (LICs) in order.
   *
   * @param launchInterceptorConditionsParameters - The LIC object of which the condition parameters has been defined
   * @param planarPoints                          - The array of Points to be validated by each LIC
   */
  public static void initConditionsMetVector(LaunchInterceptorConditionParameters launchInterceptorConditionsParameters, Point[] planarPoints)
  {
    conditionsMetVector[0]  = launchInterceptorConditionsParameters.doesTwoConsecutivePointsFurtherThanLength1(planarPoints.length, planarPoints);
    conditionsMetVector[1]  = launchInterceptorConditionsParameters.lic_1(planarPoints.length, planarPoints);
    conditionsMetVector[2]  = launchInterceptorConditionsParameters.validateAngle(planarPoints.length, planarPoints);
    conditionsMetVector[3]  = launchInterceptorConditionsParameters.validateTriangleArea(planarPoints.length, planarPoints);
    conditionsMetVector[4]  = false;
    conditionsMetVector[5]  = launchInterceptorConditionsParameters.lic5(planarPoints, planarPoints.length);
    conditionsMetVector[6]  = false;
    conditionsMetVector[7]  = launchInterceptorConditionsParameters.doesPointsSeparatedByKFurtherThanLength1(planarPoints, planarPoints.length);
    conditionsMetVector[8]  = launchInterceptorConditionsParameters.lic8(planarPoints.length, planarPoints);
    conditionsMetVector[9]  = launchInterceptorConditionsParameters.validateAngleConsecutivePointsSeparation(planarPoints.length, planarPoints);
    conditionsMetVector[10] = false;
    conditionsMetVector[11] = launchInterceptorConditionsParameters.validateConsecutivePointsSeparation(planarPoints.length, planarPoints);
    conditionsMetVector[12] = launchInterceptorConditionsParameters.doesPointsSeparatedByKApartByRange(planarPoints, planarPoints.length);
    conditionsMetVector[13] = launchInterceptorConditionsParameters.lic13(planarPoints.length, planarPoints);
    conditionsMetVector[14] = false;
  }
}