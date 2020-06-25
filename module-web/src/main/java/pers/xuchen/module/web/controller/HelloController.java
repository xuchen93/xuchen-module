package pers.xuchen.module.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xuchen.module.base.model.R;

@RestController
@RequestMapping("xuchen")
public class HelloController {

    @GetMapping("hello")
    public R hello(){
        return R.success("hello xuchen");
    }
}
