package pers.xuchen.module.database.table.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.xuchen.module.base.model.ex.BusiException;
import pers.xuchen.module.database.table.dao.SysUserDao;
import pers.xuchen.module.database.table.entity.SysUser;
import pers.xuchen.module.database.table.service.SysUserService;


@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Override
    public void create(SysUser user) {
        SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("user_name", user.getUserName()));
        if (sysUser != null){
            throw new BusiException("用户名已存在！");
        }
        String newPwd = SecureUtil.md5(user.getPassword());
        user.setPassword(newPwd);
        save(user);
    }
}
