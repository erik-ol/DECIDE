public class Main {
    public static void main(String[] args) {
        MainTest tests = new MainTest();

        tests.cmvTrue();
        tests.cmvFalse();
        tests.cmvAllLICParametersZero();

        tests.pumAlwaysTrueForNotUsed();
        tests.lcmOperationResults();

        tests.fuvTrueWhenAllPuvFalse();
        tests.fuvFalseWhenSpecifiedRowHaveFalse();
        tests.fuvTrueWhenSpecifiedRowContainOnlyTrue();

        tests.lic0Negative();
        tests.lic0positive();
        
        tests.lic1Negative();
        tests.lic1Positive();

        tests.lic2ValidateAngleTooFewPoints();
        tests.lic2ValidateAngleTooManyPoints();
        tests.lic2ValidateAnglePointEqualToVertex();
        tests.lic2ValidateAngleTwoEqualPoints();
        tests.lic2ValidateAngleDifferentPoints();

        tests.lic3FalseForRandomizedLineOfPoints();
        tests.lic3TrueWhenTriangleAreaSufficient();

        tests.lic4FalseWhenMoreQuadsThanPoints();
        tests.lic4FalseFromQuadOverlap();
        tests.lic4Positive();

        tests.lic5TrueWhenXDecreases();
        tests.lic5FalseWhenXIncreases();
        tests.lic5FalseWhenXIsEqual();
        tests.lic5ThrowsExpection();

        tests.lic6FalseWhenTooFewPoints();
        tests.lic6TrueWhenPointFarFromLine();
        tests.lic6FalseWhenAllPointsNearLine();
        tests.lic6TrueWhenFirstLastCoincide();
        tests.lic6FalseWhenFirstLastCoincideAllClose();

        tests.lic7FalseWhenAllPointsSame();
        tests.lic7FalseWhenPointsPairEqual();
        tests.lic7FalseWhenNotEnoughPoints();
        tests.lic7TrueWhenOnePair();
        tests.lic7TrueWhenAllPair();

        tests.lic8TestNotEnoughDataPoints();
        tests.lic8CanFitR1();
        tests.lic8CannotFitR1();
        tests.lic8ThrowsExpectionForInvalidPTS();
        tests.lic8ThrowsExpectionForInvalidPTSWithRegardToNumPoints();

        tests.lic9ValidateAngleConsecutivePointsSeparationTooFewPoints();
        tests.lic9ValidateAngleConsecutivePointsSeparationTooManyPoints();
        tests.lic9ValidateAngleConsecutivePointsSeparationNoConsecutivePoints();
        tests.lic9ValidateAngleConsecutivePointsSeparationInvalidAngle();
        tests.lic9ValidateAngleConsecutivePointsSeparationValidParameters();

        tests.lic10FalseWhenTooFewPoints();
        tests.lic10TrueWhenAreaExceedsThreshold();
        tests.lic10FalseWhenAreaBelowThreshold();
        tests.lic10FalseWhenCollinear();
        tests.lic10TrueWhenAreaJustAboveThreshold();

        tests.lic11ValidateConsecutivePointsSeparationTooFewPoints();
        tests.lic11ValidateConsecutivePointsSeparationTooManyPoints();
        tests.lic11ValidateConsecutivePointsSeparationNoConsecutivePoints();
        tests.lic11ValidateConsecutivePointsSeparationValidParameters();

        tests.lic12FalseWhenNotEnoughPoints();
        tests.lic12FalseWhenAllPointsSame();
        tests.lic12FalseWhenPointsPairEqual1();
        tests.lic12TrueWhenAllPair();
        tests.lic12FalseWhenPointsPairEqual2();
        tests.lic12FalseWhenPointsPairGreater();

        tests.lic13TestNotEnoughDataPoints();
        tests.lic13CanFitR2ButNotR1();
        tests.lic13FitR1ButNotR2();
        tests.lic13FitNone();
        tests.lic13FitBothR1AndR2();
        tests.lic13ThrowsExpectionForInvalidRADIUS2();

        tests.lic14FalseWhenTooFewPoints();
        tests.lic14TrueWhenBothConditionsMet();
        tests.lic14FalseWhenOnlyFirstConditionMet();
        tests.lic14FalseWhenOnlySecondConditionMet();
        tests.lic14FalseWhenCollinear();
    }
}
