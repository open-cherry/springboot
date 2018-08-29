package com.example.controller.databind;


import java.lang.annotation.*;

/**
 * {@link SerializeAllExceptResolver}
 * 
 * @author Matt S.Y. Ho
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SerializeAllExcept {

  String[] value();
  
}
