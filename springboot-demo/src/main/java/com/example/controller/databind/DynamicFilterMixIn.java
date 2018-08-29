package com.example.controller.databind;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * 
 * @author Matt S.Y. Ho
 *
 */
@JsonFilter(DynamicFilterProvider.FILTER_ID)
public class DynamicFilterMixIn {

}
