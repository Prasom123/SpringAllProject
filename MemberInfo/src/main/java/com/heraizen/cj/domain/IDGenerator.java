package com.heraizen.cj.domain;

public abstract class IDGenerator
{
  private static int count=5005;
  public static String suffix="IND";
  public static String prifix="ASHO";
  public static String getNewId()
  {   
	  count++;
	  String  mid=prifix+count+suffix;
    return mid;
  }
}