package com.template.ryan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ryan on 2018/11/13.
 */
@RestController
public class TestController {

    @RequestMapping(value = "/aa", method = RequestMethod.POST)
    public String hello() {
        return "Hello! Docker!";
    }

}
