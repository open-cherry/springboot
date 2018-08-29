package com.example.controller.databind.resolver;

import com.example.controller.databind.FilterOutAllExcept;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;


/**
 * Use {@link SimpleBeanPropertyFilter#filterOutAllExcept(String...)} to build
 * {@code PropertyFilter}
 * 
 * @author Matt S.Y. Ho
 *
 */
public class FilterOutAllExceptResolver extends DynamicFilterResolver<FilterOutAllExcept> {

  @Override
  public PropertyFilter apply(FilterOutAllExcept annotation) {
    return SimpleBeanPropertyFilter.filterOutAllExcept(annotation.value());
  }

}
