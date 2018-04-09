package com.cisco.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@State(Scope.Thread)
public class MyBenchmark {
  private static final Logger logger = LoggerFactory.getLogger(MyBenchmark.class);
  private static final UltimateAnswer answer = new UltimateAnswer();

  @Benchmark
  public void testStringConcat() {

    /*
     * Don't do this. It creates a StringBuilder to concatenate the Strings.
     */
    logger.info("Logging: " + answer);
  }

  @Benchmark
  public void testToString() {
    /*
     * Don't do this. Won't get logged (because it's a trace message), but
     * toString() will still be called.
     */
    logger.info("Logging: {}", answer.toString());
  }

  @Benchmark
  public void testJustReference() {

    /*
     * Do this. Won't call toString() unless logging is at trace level.
     */
    logger.info("Logging: {}", answer);
  }

  @Benchmark
  public void testStringFormat() {

    /*
     * Don't do this. It creates a StringBuilder to concatenate the Strings.
     */
    logger.info(String.format("Logging: %s", answer));
  }

  @Benchmark
  public void testIfStatement() {

    /*
     * Don't do this. It creates a StringBuilder to concatenate the Strings.
     */
    if (logger.isInfoEnabled()) {
      logger.info("Logging: " + answer);
    }
  }
}
