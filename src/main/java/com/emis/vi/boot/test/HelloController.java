package com.emis.vi.boot.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller，Spring MVC里的一个普通的控制器
 */
@Api(tags = "HelloController", description = "测试Controller")
@RestController //是spring4里的新注解，是@ResponseBody和@Controller的缩写
@RequestMapping("/test")
public class HelloController {

    @ApiOperation("测试")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello Spring Boot!";
    }

}