package com.github.xuchen93.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.xuchen93.model.R;

@RestController
@RequestMapping("xuchen")
public class HelloController {

    @GetMapping("hello")
    public R hello(){
        return R.success("hello xuchen");
    }
}
