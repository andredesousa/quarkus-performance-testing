package performance;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.htmlReporter;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;
import static us.abstracta.jmeter.javadsl.JmeterDsl.testPlan;
import static us.abstracta.jmeter.javadsl.JmeterDsl.threadGroup;

import java.io.IOException;
import java.time.Duration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

@Testcontainers
class PerformanceTest {

  static TestPlanStats stats;

  @Container
  static GenericContainer<?> APP_CONTAINER = new GenericContainer<>(DockerImageName.parse("quarkus-api")).withExposedPorts(8080);

  @BeforeAll
  static void beforeAll() throws IOException {
    stats = testPlan(
        threadGroup(10, 100,
            httpSampler("http://localhost:" + APP_CONTAINER.getMappedPort(8080) + "/")
        ),
        htmlReporter("build/jmeter-report")
    ).run();
  }

  @Test
  void testTimePercentile99() {
    assertThat(stats.overall().sampleTime().perc99()).isLessThan(Duration.ofSeconds(1));
  }

  @Test
  void testTimePercentile95() {
    assertThat(stats.overall().sampleTime().perc95()).isLessThan(Duration.ofMillis(800));
  }

  @Test
  void testErrorsCount() {
    assertThat(stats.overall().errors().total()).isZero();
  }

  @Test
  void testRequestsPerSecond() {
    assertThat(stats.overall().samples().perSecond()).isGreaterThan(50);
  }
}
