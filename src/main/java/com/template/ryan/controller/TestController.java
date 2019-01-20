package com.template.ryan.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ryan on 2018/11/13.
 */
@Log
@RestController
public class TestController {

    @RequestMapping(value = "/aa", method = RequestMethod.POST)
    public String hello() {
        return "Hello! Docker!";
    }

}
