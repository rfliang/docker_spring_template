package com.template.ryan.controller;

import com.template.ryan.service.GetDateSevice;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Log
@RestController
public class GetDataController {

    @Autowired
    private GetDateSevice getDateSevice;

    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String getDate(@RequestParam(required=false) String date) throws ParseException {
        return getDateSevice.getSum(date);
    }
}
