package com.example.controller.databind.resolver;

import com.example.controller.databind.SerializeAllExcept;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

/**
 * Use {@link SimpleBeanPropertyFilter#serializeAllExcept(String...)} to build
 * {@code PropertyFilter}
 * 
 * @author Matt S.Y. Ho
 *
 */
public class SerializeAllExceptResolver extends DynamicFilterResolver<SerializeAllExcept> {

  @Override
  public PropertyFilter apply(SerializeAllExcept annotation) {
    return SimpleBeanPropertyFilter.serializeAllExcept(annotation.value());
  }

}
