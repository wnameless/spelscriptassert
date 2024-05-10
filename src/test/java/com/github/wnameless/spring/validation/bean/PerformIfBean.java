package com.github.wnameless.spring.validation.bean;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.github.wnameless.spring.validation.SpELScriptAssert;

@SpELScriptAssert(script = "boolFalse", performIf = "boolTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "boolFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "numTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "numFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "collTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "collFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "mapTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "mapFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "byteAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "byteAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "shortAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "shortAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "intAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "intAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "longAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "longAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "floatAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "floatAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "doubleAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "doubleAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "booleanAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "booleanAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "charAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "charAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "strAryTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "strAryFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "charSequenceTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "charSequenceFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "boolOptTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "boolOptFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "optTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "optFalse")
@SpELScriptAssert(script = "boolFalse", performIf = "objTrue")
@SpELScriptAssert(script = "boolFalse", performIf = "objFalse")
public class PerformIfBean {

  public boolean boolTrue = true;
  public boolean boolFalse = false;

  public Number numTrue = 1;
  public Number numFalse = 0;

  public Collection<?> collTrue = List.of("a", "b");
  public Collection<?> collFalse = List.of();

  public Map<?, ?> mapTrue = Map.of(1, 2);
  public Map<?, ?> mapFalse = Map.of();

  public byte[] byteAryTrue = new byte[] {1, 2};
  public byte[] byteAryFalse = new byte[] {};

  public short[] shortAryTrue = new short[] {1, 2};
  public short[] shortAryFalse = new short[] {};

  public int[] intAryTrue = new int[] {1, 2};
  public int[] intAryFalse = new int[] {};

  public long[] longAryTrue = new long[] {1, 2};
  public long[] longAryFalse = new long[] {};

  public float[] floatAryTrue = new float[] {1, 2};
  public float[] floatAryFalse = new float[] {};

  public double[] doubleAryTrue = new double[] {1, 2};
  public double[] doubleAryFalse = new double[] {};

  public boolean[] booleanAryTrue = new boolean[] {false, true};
  public boolean[] booleanAryFalse = new boolean[] {};

  public char[] charAryTrue = new char[] {1, 2};
  public char[] charAryFalse = new char[] {};

  public String[] strAryTrue = new String[] {"a", "b"};
  public String[] strAryFalse = new String[] {};

  public CharSequence charSequenceTrue = "ab";
  public CharSequence charSequenceFalse = "  ";

  public Optional<Boolean> boolOptTrue = Optional.of(true);
  public Optional<Boolean> boolOptFalse = Optional.of(false);

  public Optional<String> optTrue = Optional.of("");
  public Optional<String> optFalse = Optional.empty();

  public Object objTrue = LocalDateTime.now();
  public Object objFalse = null;

}
