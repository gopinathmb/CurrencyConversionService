package com.gopi.rest.webservices.restfulwebservices.filter;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean getSomeBean() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/filteringList")
    public List<SomeBean> getSomeBeans() {
        return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value11", "value12", "value13"));
    }

    @GetMapping("/dynamicFiltering")
    public MappingJacksonValue getSomeBeanDynamically() {

        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        //id:SomeBeanFilter value will be set to SomeBean
        SimpleFilterProvider someBeanFilter = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        mappingJacksonValue.setFilters(someBeanFilter);
        return mappingJacksonValue;
    }

    @GetMapping("/dynamicFilteringList")
    public MappingJacksonValue getSomeBeansDynamically() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value11", "value12", "value13"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3");
        //id:SomeBeanFilter value will be set to SomeBean
        SimpleFilterProvider someBeanFilter = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeans);
        mappingJacksonValue.setFilters(someBeanFilter);
        return mappingJacksonValue;
    }
}
