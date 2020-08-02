package com.saletax.controller;

import com.saletax.model.Commodities;
import com.saletax.model.STResponseObject;
import com.saletax.service.InputParserNCheckCategory;
import com.saletax.service.STService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class STController {
    @Autowired
    STService stService;

    //mapping to end point calculate Tax cross origin since two server are there frontend and backend
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST,value="/calculateTax")
    public STResponseObject calculateTax(@RequestBody String[] body){
        return stService.getCommodities(body);
    }
}
