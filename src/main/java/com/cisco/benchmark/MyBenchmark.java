package com.cisco.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@State(Scope.Thread)
public class MyBenchmark {
  private static final Logger logger = (Logger) LoggerFactory.getLogger(MyBenchmark.class);

  static {
    // Set to TRACE to benchmark logging with output.
    logger.setLevel(Level.TRACE);
  }

  private static final UltimateAnswer answer = new UltimateAnswer();

  @Benchmark
  public void testStringConcat() {

    /*
     * Don't do this. It creates a StringBuilder to concatenate the Strings.
     */
    logger.trace("Logging: " + answer);
  }

  @Benchmark
  public void testToString() {
    /*
     * Don't do this. Won't get logged (because it's a trace message), but
     * toString() will still be called.
     */
    logger.trace("Logging: {}", answer.toString());
  }

  @Benchmark
  public void testJustReference() {

    /*
     * Do this. Won't call toString() unless logging is at trace level.
     */
    logger.trace("Logging: {}", answer);
  }

  @Benchmark
  public void testStringFormat() {

    /*
     * Don't do this. It creates a StringBuilder to concatenate the Strings.
     */
    logger.trace(String.format("Logging: %s", answer));
  }

  @Benchmark
  public void testIfStatement() {

    /*
     * Don't do this. It creates a StringBuilder to concatenate the Strings.
     */
    if (logger.isTraceEnabled()) {
      logger.trace("Logging: " + answer);
    }
  }
  
  @Benchmark
  public void testTwoArgumentsNoObject() {
    logger.trace("Logging: {}, {}", answer, answer.toTraceLoggingString());
  }
  
  @Benchmark
  public void testTwoArgumentsWithObject() {
    logger.trace("Logging: {}, {}", new Object[] {answer, answer.toTraceLoggingString()});
  }
  
}
