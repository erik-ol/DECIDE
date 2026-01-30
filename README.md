# DECIDE

An implementation of a hypothetical anti-ballistic missile system's launch decision algorithm. The system evaluates radar tracking data against 15 Launch Interceptor Conditions (LICs) to determine whether to launch an interceptor.

## Dependencies

- **Java**: JDK 8 or higher
- **JUnit 4**: For running unit tests
- **Maven**: For build automation and dependency management

## Build and Run Instructions

### Compile the project
```bash
javac -d out src/main/java/*.java
```

### Run with custom input
Create a main method or modify `AntiBallisticMissileSystem.java` to call the `decide()` method with your input parameters.

### Run tests
```bash
# Using Maven
mvn test

# Or compile and run tests manually with JUnit
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar -d out src/main/java/*.java src/test/java/*.java
java -cp out:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore MainTest
```

## Statements of contributions

| Name | GitHub | Contribution |
|------|--------|--------------|
| Josefine | [@joss2002](https://github.com/joss2002) | <ul><li>[x] LIC-2, angle verification for three consecutive data points<ul><li>[x] Implemented LIC-2 logic in `validateAngle()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** and **Maven** corresponding to LIC-2 within `MainTest.java`</li></ul></li><li>[x] LIC-9, angle verification for three consecutive data points separated by `C_PTS` and `D_PTS`<ul><li>[x] Implemented LIC-9 logic in `validateAngleConsecutivePointsSeparation()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** and **Maven** corresponding to LIC-9 within `MainTest.java`</li></ul></li><li>[x] LIC-11, verification for two consecutive data points separated by `G_PTS`<ul><li>[x] Implemented LIC-11 logic in `validateConsecutivePointsSeparation()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** and **Maven** corresponding to LIC-11 within `MainTest.java`</li></ul><li>[x] CMV, constructed the CMV based on the LICs<ul><li>[x] Implemented CMV logic in `initConditionsMetVector()` within `AntiBallisticMissileSystem.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** and **Maven** corresponding to the CMV within `MainTest.java`</li></ul></li></ul> |
| Albin | [@zzimbaa](https://github.com/zzimbaa) | <ul><li>[x] LIC-5, verification for two consecutive data points such that X[j] - X[i] &lt; 0<ul><li>[x] Implemented LIC-5 logic in `hasDecreasingConsecutivePoints()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-5 within `MainTest.java`</li></ul></li><li>[x] LIC-8, verification for three data points separated by `A_PTS` and `B_PTS` that cannot be contained within or on a circle of radius `RADIUS1`<ul><li>[x] Implemented LIC-8 logic in `hasTripleNotContainedInRadius1Circle` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-8 within `MainTest.java`</li></ul></li><li>[x] LIC-13, verification for three data points separated by `A_PTS` and `B_PTS` that cannot be contained within or on a circle of radius `RADIUS2`<ul><li>[x] Implemented LIC-13 logic in `hasPointsThatFitCircleOfRadius2ButNotRadius1()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-13 within `MainTest.java`</li></ul></li><li>[x] FUV, calculating the Final Unlocking Vector based on the the Preliminary Unlocking Matrix and Preliminary Unlocking Vector<ul><li>[x] Implemented FUV logic in `calculateFuv()` within `AntiBallisticMissileSystem.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to FUV within `MainTest.java`</li></ul> |
| Erik | [@erik-ol](https://github.com/erik-ol) | <ul><li>[x] LIC-1, verification for three consecutive data points that cannot be contained within or on a circle of radius `RADIUS1`<ul><li>[x] Implemented LIC-1 logic in `lic_1()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-1 within `MainTest.java`</li></ul></li><li>[x] LIC-3, verification for three consecutive data points that are the vertices of a triangle with area greater than `AREA1`<ul><li>[x] Implemented LIC-3 logic in `validateTriangleArea()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-3 within `MainTest.java`</li></ul></li><li>[x] LIC-4, verification for `Q_PTS` consecutive data points that lie in more than `QUADS` quadrants<ul><li>[x] Implemented LIC-4 logic in `validateQuadrants()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-4 within `MainTest.java`</li></ul></li><li>[x] LCM & PUM, calculating the Preliminary Unlocking Matrix based on the operations in the Logical Connector Matrix<ul><li>[x] Implemented LCM logic in `evaluateLogicalConnectorMatrix()` within `AntiBallisticMissileSystem.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LCM within `MainTest.java`</li></ul> |
