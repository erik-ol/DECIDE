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

  public static boolean[][] preliminaryUnlockingMatrix = new boolean[15][15];

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

    evaluateLogicalConnectorMatrix(logicalConnectorMatrix);

    boolean launch = calculateFUV(preliminaryUnlockingVector, preliminaryUnlockingMatrix);
    if(launch) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
    }
  }

  /**
   * Computes Final Unlocking Vector (FUV)
   * 
   * Function evaluates the PUM together with the PUV to determine whether the
   * interceptor should be launched
   * 
   * @param PUV the Preliminary Unlocking Matrix
   * @param PUM the Preliminary Unlocking Vector
   * @return true if all required tows in PUM, as specified by PUV,
   * contain only true values, otherwise false.
   */
  public static boolean calculateFUV(boolean[] PUV, boolean[][] PUM) {

    for(int i = 0; i < 15; i++) {

      if(PUV[i] == false) { // Condition 2
        continue;
      }

      for(int j = 0; j < 15; j++) {
        if(PUM[i][j] == false) { // Condition 1 and 3
          return false;
        }
      }
    }
    return true;
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
    conditionsMetVector[0]  = launchInterceptorConditionsParameters.lic0(planarPoints.length, planarPoints);
    conditionsMetVector[1]  = launchInterceptorConditionsParameters.lic1(planarPoints.length, planarPoints);
    conditionsMetVector[2]  = launchInterceptorConditionsParameters.lic2(planarPoints.length, planarPoints);
    conditionsMetVector[3]  = launchInterceptorConditionsParameters.lic3(planarPoints.length, planarPoints);
    conditionsMetVector[4]  = launchInterceptorConditionsParameters.lic4(planarPoints.length, planarPoints);
    conditionsMetVector[5]  = launchInterceptorConditionsParameters.lic5(planarPoints, planarPoints.length);
    conditionsMetVector[6]  = launchInterceptorConditionsParameters.lic6(planarPoints.length, planarPoints);
    conditionsMetVector[7]  = launchInterceptorConditionsParameters.lic7(planarPoints, planarPoints.length);
    conditionsMetVector[8]  = launchInterceptorConditionsParameters.lic8(planarPoints.length, planarPoints);
    conditionsMetVector[9]  = launchInterceptorConditionsParameters.lic9(planarPoints.length, planarPoints);
    conditionsMetVector[10] = launchInterceptorConditionsParameters.lic10(planarPoints.length, planarPoints);
    conditionsMetVector[11] = launchInterceptorConditionsParameters.lic11(planarPoints.length, planarPoints);
    conditionsMetVector[12] = launchInterceptorConditionsParameters.lic12(planarPoints, planarPoints.length);
    conditionsMetVector[13] = launchInterceptorConditionsParameters.lic13(planarPoints.length, planarPoints);
    conditionsMetVector[14] = launchInterceptorConditionsParameters.lic14(planarPoints.length, planarPoints);
  }

  /**
   * Initializes the preliminary unlocking matrix (PUM) based on if corresponding elements in the conditions met vector (CMV) fullfill the operator in the logical connector matrix (LCM).
   * @param logicalConnectorMatrix - The LCM matrix object where the operator to apply to each CMV pair are defined.
   */
  public static void evaluateLogicalConnectorMatrix(LogicalConnector[][] logicalConnectorMatrix)
  {
    
    for (int i = 0; i<15; i++) {
      for (int j = 0; j<15; j++) {
        if (logicalConnectorMatrix[i][j] == LogicalConnector.ANDD)
        {
          preliminaryUnlockingMatrix[i][j] = conditionsMetVector[i] & conditionsMetVector[j];
        }
        else if (logicalConnectorMatrix[i][j] == LogicalConnector.ORR)
        {
          preliminaryUnlockingMatrix[i][j] = conditionsMetVector[i] | conditionsMetVector[j];
        }
        else if (logicalConnectorMatrix[i][j] == LogicalConnector.NOTUSED)
        {
          preliminaryUnlockingMatrix[i][j] = true;
        }
      }
    }
  }
}