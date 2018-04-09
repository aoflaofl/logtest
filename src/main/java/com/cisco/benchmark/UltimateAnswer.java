package com.cisco.benchmark;

import java.util.HashMap;
import java.util.Map;

public class UltimateAnswer {
  private final Map<String, String> map = new HashMap<String, String>();

  {
    map.put("41", "Almost");
    map.put("42", "Yep");
    map.put("43", "Too Far");
  }

  @Override
  public String toString() {
    return map.toString();
  }

  String toTraceLoggingString() {
    return "Trying to figure out the Ultimate Answer to Life, the Universe and Everything.";
  }
}
