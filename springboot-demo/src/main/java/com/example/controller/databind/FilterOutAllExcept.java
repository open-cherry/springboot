package com.example.controller.databind;


import java.lang.annotation.*;

/**
 * {@link FilterOutAllExceptResolver}
 * 
 * @author Matt S.Y. Ho
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FilterOutAllExcept {

  String[] value();

}
