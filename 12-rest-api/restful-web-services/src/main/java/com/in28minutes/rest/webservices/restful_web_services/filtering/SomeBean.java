package com.in28minutes.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

//@JsonIgnoreProperties({"field1", "field2"}) // For ignoring multiple fields together
//@JsonIgnoreProperties("field1") // Static Filtering at the class level - Ignoring all the fields in all JSON responses that are defined in the property
@JsonFilter("SomeBeanFilter") // Dynamic Filtering - Map to the filter in FilterController
public class SomeBean {
    @JsonView(Views.Public.class)
    private String field1;

    // More Preferred
//    @JsonIgnore // Static Filtering - Removes this field from all JSON responses which contain this Bean
    @JsonView(Views.Public.class)
    private String field2;

    @JsonView(Views.Public.class)
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        super();
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
