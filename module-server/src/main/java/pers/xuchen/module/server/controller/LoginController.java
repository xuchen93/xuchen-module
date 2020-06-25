package pers.xuchen.module.server.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xuchen.module.base.model.R;
import pers.xuchen.module.base.model.ex.BusiException;
import pers.xuchen.module.database.table.entity.SysUser;
import pers.xuchen.module.server.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("login")
    public R login(SysUser user) {
        if (StrUtil.hasBlank(user.getUserName(), user.getPassword())) {
            throw new BusiException("缺少用户名或者密码");
        }
        String token = loginService.login(user);
        return R.success(token);
    }
}
