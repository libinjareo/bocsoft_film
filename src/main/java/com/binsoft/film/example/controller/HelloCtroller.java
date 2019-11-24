package com.binsoft.film.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/demo/")
@Api("swaggerDemoController API")
public class HelloCtroller {

    @ApiOperation(value ="Test Swagger Value",notes = "Test Swagger value note")
    @ApiImplicitParam(name = "str",value = "inputParam",paramType = "query",required=true,dataType="string")
    @RequestMapping(value = "hello")
    public Object hello(String str){

        System.out.println("HelloController=" + str);

        Map<String,String> map = new HashMap<String,String>();
        map.put("v",str);

        return map;

    }
}
