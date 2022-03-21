package com.gopi.rest.webservices.restfulwebservices.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
    //Simple way to declare (URI type)
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Gopinatha M B");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Gopinatha", "M B"));
    }

    //Using params and these param values are passed
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 getPersonByParamV1() {
        return new PersonV1("Gopinatha M B");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 getPersonByParam() {
        return new PersonV2(new Name("Gopinatha", "M B"));
    }

    //Using Headers
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonByHeaderV1() {
        return new PersonV1("Gopinatha M B");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonByHeaderV2() {
        return new PersonV2(new Name("Gopinatha", "M B"));
    }

    //Using produces/mime type
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonByProducesV1() {
        return new PersonV1("Gopinatha M B");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonByProducesV2() {
        return new PersonV2(new Name("Gopinatha", "M B"));
    }

}
