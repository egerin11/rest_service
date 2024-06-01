package com.example.laba.aop;

import org.aspectj.lang.annotation.Pointcut;

/** The type Point cuts. */
public class PointCuts {
  /** All methods from controller. */
  @Pointcut(value = "execution(* com.example.laba.controller.*.*(..)) ")
  public void allMethodsFromController() {}

  /** All methods. */
  @Pointcut(value = "execution(* com.example.laba.service.*.*(..)) ")
  public void allMethods() {}
}
