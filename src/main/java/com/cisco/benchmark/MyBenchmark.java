/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

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