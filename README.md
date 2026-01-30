# DECIDE REPORT

---

## Build instructions

<details>
<summary><span style="font-size:18px; font-weight:bold;">Option 1: Using IntelliJ IDEA</span></summary>

#### Requirements
 - JDK 22 (OpenJDK 22.0.1 recommended)
 - IntelliJ IDEA (Community or Ultimate Edition)
 - Maven version: 3.2.5 (IntelliJ has built-in Maven)

Clone the repository
```bash
git clone https://github.com/erik-ol/DECIDE.git
```
>Note: The file structure is required to use Maven successfully.

>Note: Make sure pom.xml is located in the root directory.

<details>
<summary><span style="font-size:15px; font-weight:bold;">Test the code</span></summary>

1. Open the repository in IntelliJ IDEA.
2. IntelliJ will automatically import Maven dependencies.
3. To build and run tests:
   - Open `pom.xml`
   - Navigate to the **Maven** toolbar and **Run Maven Build** 

</details>

<details>
<summary><span style="font-size:15px; font-weight:bold;">Execute the code</span></summary>

Run the code by navigating to `DECIDE/src/main/java`, open `Main.java` and select **Run Main**.

</details>
</details>

---
<details>
<summary><span style="font-size:18px; font-weight:bold;">Option 2: Using terminal</span></summary>

#### Requirements
- Java (JDK) 1.8 (Java 8) (recommended)
- Maven version: 3.2.5

Clone the repository
```bash
git clone https://github.com/erik-ol/DECIDE.git
```
<details>
<summary><span style="font-size:15px; font-weight:bold;">Test the code</span></summary>

Enter the root directory
```bash
cd DECIDE
```
>Note: Make sure pom.xml is located in the root directory.
Ensure old builds are removed

>Note: The file structure is required to use Maven successfully

Remove any pre-existing builds
```bash
mvn clean
```
>Note: Maven will automatically download JUnit 4 and required dependencies.

Compile the code
```bash
mvn compile
```
Run tests
```bash
mvn test
```

</details>

<details>
<summary><span style="font-size:15px; font-weight:bold;">Execute the code</span></summary>

Navigate to the correct directory
```bash
cd DECIDE/src/main/java
```
Execute the code
```bash
java Main
```

</details>

</details>

---
## Dependencies
**Testing:**
 - Maven: junit:junit:4.13.2
 - Maven: org.hamcrest:hamcrest-core:1.3

---

## Way of working
Following this instance of the project the team members have been taught new tools and practices resulting in the current state. Right now all members have gotten a general hang of the tools and practises but still with some minor mistakes. The current state following this instance of the project would be somewhere in between "In Use" and "In Place" in accordance to the Essence standard. In regard to the checklist for Way-of-Working the following notations can be made
 - Some tools and practices were not found initially, but instead when the related issue occurred.
 - The knowledge gap regarding certain tools was initially established, where, for instance, some members had not used GitHub or JUnit previously.
 - The whole team is using the practises and tools to perform their work, but there was a learning faze when certain tools were new where some "mistakes" were made.
 - The practices are not fully naturally applied for the whole team yet, the learning phase is still ongoing. At the same time it is notices that all members are tuning their skills constantly.

The tools and practices thus far are then too summarized as
 - GitHub is to be used as the version handling platform
 - Commenting follow the JavaDoc standard in code
 - Naming conventions are to follow camel case
 - JUnit 4 together with Maven is to be used for testing

 - Commits follow the Conventional Commits standard
 - Pull-requests are to be squash-merged with main

 - Everyone is to be available during regular work hours
 - Everyone is responsible for reviewing and approving pull-requests

The key obstacles, in regard to reaching the next state, would then be concluded as
 - Making all team members comfortable enough with all tools and practices such that is becomes natural. 
 - Leaving thorough reviews making it possible to learn from mistakes to then be able to apply tools and practices naturally.
 - Increase awareness of the whole teams methods in use to catch mistakes early on.
 - Increase communication so the whole team are actively involved in occurring issues, making sure everyone is informed.
---
## Statements of contributions

| Name                           | Contribution                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Josefine "joss2002" Nyholm     | <ul><li>[x] LIC-2, angle verification for three consecutive data points<ul><li>[x] Implemented LIC-2 logic in `lic2()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** and **Maven** corresponding to LIC-2 within `MainTest.java`</li></ul></li><li>[x] LIC-9, angle verification for three consecutive data points separated by `C_PTS` and `D_PTS`<ul><li>[x] Implemented LIC-9 logic in `lic9()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** and **Maven** corresponding to LIC-9 within `MainTest.java`</li></ul></li><li>[x] LIC-11, verification for two consecutive data points separated by `G_PTS`<ul><li>[x] Implemented LIC-11 logic in `lic11()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** and **Maven** corresponding to LIC-11 within `MainTest.java`</li></ul><li>[x] CMV, constructed the CMV based on the LICs<ul><li>[x] Implemented CMV logic in `initConditionsMetVector()` within `AntiBallisticMissileSystem.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** and **Maven** corresponding to the CMV within `MainTest.java`</li></ul></li></ul> |
| Albin "zzimbaa" Blomqvist      | <ul><li>[x] LIC-5, verification for two consecutive data points such that X[j] - X[i] &lt; 0<ul><li>[x] Implemented LIC-5 logic in `lic5()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-5 within `MainTest.java`</li></ul></li><li>[x] LIC-8, verification for three data points separated by `A_PTS` and `B_PTS` that cannot be contained within or on a circle of radius `RADIUS1`<ul><li>[x] Implemented LIC-8 logic in `lic8()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-8 within `MainTest.java`</li></ul></li><li>[x] LIC-13, verification for three data points separated by `A_PTS` and `B_PTS` that cannot be contained within or on a circle of radius `RADIUS2`<ul><li>[x] Implemented LIC-13 logic in `lic13()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-13 within `MainTest.java`</li></ul></li><li>[x] FUV, calculating the Final Unlocking Vector based on the the Preliminary Unlocking Matrix and Preliminary Unlocking Vector<ul><li>[x] Implemented FUV logic in `calculateFuv()` within `AntiBallisticMissileSystem.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to FUV within `MainTest.java`</li></ul> |
| Erik Olsson "erik-ol"          | <ul><li>[x] LIC-1, verification for three consecutive data points that cannot be contained within or on a circle of radius `RADIUS1`<ul><li>[x] Implemented LIC-1 logic in `lic1()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-1 within `MainTest.java`</li></ul></li><li>[x] LIC-3, verification for three consecutive data points that are the vertices of a triangle with area greater than `AREA1`<ul><li>[x] Implemented LIC-3 logic in `lic3()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-3 within `MainTest.java`</li></ul></li><li>[x] LIC-4, verification for `Q_PTS` consecutive data points that lie in more than `QUADS` quadrants<ul><li>[x] Implemented LIC-4 logic in `lic4()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-4 within `MainTest.java`</li></ul></li><li>[x] LCM & PUM, calculating the Preliminary Unlocking Matrix based on the operations in the Logical Connector Matrix<ul><li>[x] Implemented LCM logic in `evaluateLogicalConnectorMatrix()` within `AntiBallisticMissileSystem.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LCM within `MainTest.java`</li></ul> |
| Avid "HotFazz" Fayaz           | <ul><li>[x] LIC-6, verification for `N_PTS` consecutive data points where at least one point lies a distance greater than `DIST` from the line joining the first and last of these points<ul><li>[x] Implemented LIC-6 logic in `lic6()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-6 within `MainTest.java`</li></ul></li><li>[x] LIC-10, verification for three data points separated by `E_PTS` and `F_PTS` that form a triangle with area greater than `AREA1`<ul><li>[x] Implemented LIC-10 logic in `lic10()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-10 within `MainTest.java`</li></ul></li><li>[x] LIC-14, verification for three data points separated by `E_PTS` and `F_PTS` that form a triangle with area greater than `AREA1` and another set with area less than `AREA2`<ul><li>[x] Implemented LIC-14 logic in `lic14()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-14 within `MainTest.java`</li></ul></li></ul> |
| Chow Pun Chun "MrNoodlez-1227" | <ul><li>[x] LIC-0, verification for at least one pair of two consecutive data points needs to be more than `LENGTH1` apart<ul><li>[x] Implemented LIC-0 logic in `lic0()` within `LaunchInterceptorConditionParameters.java`</li></ul><ul><li>[x] Imeplemnted relevant test methods in accordance to **JUnit 4** corresponding to LIC-0 within `MainTest.java`</li></ul></li><li> [x] LIC-7, verification for at least one pair of points separated by `K_PTS` number of points in between exclusively that is more than `LENGTH1` apart<ul><li>[x] Implemented LIC-7 logic in `lic7()` within `LaunchInterceptorConditionParameters`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-7 within `MainTest.java`</li></ul></li><li>[x] LIC-12, verification verification for at least one pair of points separated by `K_PTS` number of points in between exclusively that is more than `LENGTH1` but less than `LENGTH2` apart <ul><li>[x] Implemented LIC-12 logic in `lic12()` within `LaunchInterceptorConditionParameters`</li></ul><ul><li>[x] Implemented relevant test methods in accordance to **JUnit 4** corresponding to LIC-12 within `MainTest.java`</li></ul></ul> |

