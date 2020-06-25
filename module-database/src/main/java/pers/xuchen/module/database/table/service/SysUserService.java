package pers.xuchen.module.database.table.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xuchen.module.database.table.entity.SysUser;

public interface SysUserService extends IService<SysUser> {

    void create(SysUser user);
}
