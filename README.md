# Quarkus Performance Testing with JMeter

This is a sample project only for performance testing with [JMeter](https://jmeter.apache.org/) and [JMeter DSL](https://abstracta.github.io/jmeter-java-dsl/).
The JMeter application is open source software, a 100% pure Java application designed to load test functional behavior and measure performance.
It is recommended to have, at least, **Java 11** and [Docker](https://www.docker.com/) installed.

## Overview

Performance testing gathers all the tests that verify an application’s speed, robustness, reliability, and correct sizing.
It examines several indicators such as a browser, page and network response times, server query processing time, number of acceptable concurrent users architected, CPU memory consumption, and number/type of errors which may be encountered when using an application.

JMeter may be used to test performance both on static and dynamic resources, Web dynamic applications.
It can be used to simulate a heavy load on a server, group of servers, network or object to test its strength or to analyze overall performance under different load types.
JMeter DSL is a simple Java API to run performance tests, using JMeter as engine, in a programmer-friendly way.

Here is a simple example test with 2 threads/users iterating 10 times each to send HTTP GET requests:

```java
public class PerformanceTest {

  @Test
  public void testTimePercentile99() throws IOException {
    TestPlanStats stats = testPlan(
        threadGroup(2, 10,
            httpSampler("http://localhost:8080/")
        ),
    ).run();

    assertThat(stats.overall().sampleTime().perc99()).isLessThan(Duration.ofSeconds(1));
  }
}
```

As shown in the previous example, it can be easily executed with JUnit, modularized in code, and easily integrated into any CI/CD pipeline.
Additionally, it makes it easy to debug the execution of test plans with the usual IDE debugger tools.

## Project structure

This project was generated with [code.quarkus.io](https://code.quarkus.io/).
All of the app's code goes in a folder named `src/main`.
The **unit tests** and **performance tests** are in the `src/test` and `src/performanceTest` folders.
Static files are placed in `src/main/resources` folder.

## Available gradle tasks

The tasks in [build.gradle](build.gradle) file were built with simplicity in mind to automate as much repetitive tasks as possible and help developers focus on what really matters.

The next tasks should be executed in a console inside the root directory:

- `./gradlew tasks` - Displays the tasks runnable from root project 'quarkus-app'.
- `./gradlew quarkusDev` - Runs this project with background compilation.
- `./gradlew check` - Runs all checks.
- `./gradlew test` - Runs the unit tests.
- `./gradlew performanceTest` - Run the performance tests.
- `./gradlew lint` - Runs several static code analysis.
- `./gradlew clean` - Deletes the build directory.
- `./gradlew javadoc` - Generates Javadoc API documentation for the main source code.
- `./gradlew build` - Assembles and tests this project.
- `./gradlew buildImage` - Builds a Docker image of the application.
- `./gradlew release` - Performs release, creates tag and pushes it to remote.
- `./gradlew help` - Displays a help message.

For more details, read the [Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html) documentation in the [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html).

## Linting

A linter is a static code analysis tool used to flag programming errors, bugs, stylistic errors and suspicious constructs.
This project uses [Checkstyle](https://checkstyle.sourceforge.io/) to find class design problems, method design problems, and other problems. It also has the ability to check code layout and formatting issues.

To enforce best practices, you can use the next command:

```bash
./gradlew lint
```

Depending on our editor, you may want to add an editor extension to lint and format your code while you type or on-save.

## Unit testing

Unit tests are responsible for testing of individual methods or classes by supplying input and making sure the output is as expected.

To execute the unit tests, you can use the next command:

```bash
./gradlew test
```

Use `./gradlew test -t` to keep executing unit tests in real time while watching for file changes in the background.
This project uses [JaCoCo](https://www.eclemma.org/jacoco/) which provides code coverage metrics for Java.
The minimum code coverage is set to 80%.
You can see the HTML coverage report opening the [index.html](build/reports/jacoco/test/html/index.html) file in your web browser.

It's a common requirement to run subsets of a test suite, such as when you're fixing a bug or developing a new test case.
For more details, you can see the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation.

## Performance testing

Performance testing is the practice of evaluating how a system performs in terms of responsiveness and stability under a particular workload.

To execute the performance tests, you can use the next command:

```bash
./gradlew performanceTest -PisJMeter
```

[JUnit 5](https://junit.org/junit5/), [JMeter DSL](https://abstracta.github.io/jmeter-java-dsl/) and [AssertJ](https://assertj.github.io/doc/) are used in performance tests.
Like unit tests, you can also run subsets of a test suite.
See the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation

You can see the HTML report opening the *index.html* file in your web browser from `target/jmeter-report` folder.

## Building

This project follows [Semantic Versioning](https://semver.org/) and uses git tags to define the current version of the project.
Use `./gradlew currentVersion` to print the current version extracted from SCM and `./gradlew release` to release the current version.

To build and create a JAR image, you can use the next command:

```bash
./gradlew build
```

This project contains a Dockerfile that you can use to build your Docker image. Use `./gradlew buildImage` after `build` command.

## Debugging

You can debug the source code, add breakpoints, inspect variables and view the application's call stack.
Also, you can use the IDE for debugging the source code, unit and functional tests.
You can customize the [log verbosity](https://docs.gradle.org/current/userguide/logging.html#logging) of gradle tasks using the `-i` or `--info` flag.

This project includes [Swagger](https://swagger.io/). To get a visual representation of the interface and send requests for testing purposes go to <http://localhost:8080/q/swagger-ui/>.

## Reference Documentation

For further reference, please consider the following sections:

- [Official Gradle documentation](https://docs.gradle.org)
- [Quarkus QuickStarts](https://github.com/quarkusio/quarkus-quickstarts)
- [JMeter User's Manual](https://jmeter.apache.org/usermanual/index.html)
- [JMeter DSL User guide](https://abstracta.github.io/jmeter-java-dsl/guide/)
- [Testing your Quarkus Application](https://quarkus.io/guides/getting-started-testing)
- [Testcontainers JUnit 5 Quickstart](https://www.testcontainers.org/quickstart/junit_5_quickstart/)
