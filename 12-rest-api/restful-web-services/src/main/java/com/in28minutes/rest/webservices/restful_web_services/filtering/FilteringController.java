package com.in28minutes.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.ser.FilterProvider;
import tools.jackson.databind.ser.std.SimpleBeanPropertyFilter;
import tools.jackson.databind.ser.std.SimpleFilterProvider;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    @JsonView(Views.Public.class)
    public String filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        // Dynamic Filtering - Used to filter some fields for a particular API by passing instructions during parsing by Jackson
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        JsonMapper jsonMapper = JsonMapper.builder().build();

        return jsonMapper.writer(filters).writeValueAsString(someBean);
    }

   @GetMapping("/filtering-list")
    public String filteringList() {
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        JsonMapper jsonMapper = JsonMapper.builder().build();

        return jsonMapper.writer(filters).writeValueAsString(list);
    }
}
