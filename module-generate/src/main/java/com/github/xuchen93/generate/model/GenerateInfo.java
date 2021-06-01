package com.github.xuchen93.generate.model;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.util.List;

@Data
public class GenerateInfo {
	//-----------------global配置---------------------
	/**
	 * 作者
	 */
	private String author = "system";
	/**
	 * 模块目录，会自动更新项目路径
	 */
	private String module;
	/**
	 * 项目路径，默认当前根目录下的/src/main/java
	 */
	private String projectPath = System.getProperty("user.dir") + "/src/main/java";
	/**
	 * 包路径 如com.github.xuchen93.generate
	 */
	private String packageName;
	/**
	 * 开启swagger
	 */
	private boolean swagger = false;
	/**
	 * 时间类型
	 */
	private DateType dateType = DateType.TIME_PACK;
	/**
	 * entity父类
	 */
	private Class superEntityClass = null;
	//-----------------策略配置---------------------
	/**
	 * 开启lombok
	 */
	private boolean lombok = true;
	/**
	 * 表名
	 */
	private List<String> tableList;
	//-----------------datasource配置---------------------
	/**
	 * 数据库类型
	 */
	private DbType dbType = DbType.MYSQL;
	/**
	 * 数据库url
	 */
	private String dbUrl;
	/**
	 * 数据库driver
	 */
	private String dbDriverClass = "com.mysql.jdbc.Driver";
	/**
	 * 数据库username
	 */
	private String dbUserName;
	/**
	 * 数据库密码
	 */
	private String dbPassword;

	/**
	 * 多模块时用
	 *
	 * @param module 模块名
	 */
	public void setModule(String module) {
		this.module = module;
		this.projectPath = StrUtil.format(System.getProperty("user.dir") + StrUtil.format("/{}/src/main/java", module));
	}

	public void setDBProperties(DataSourceProperties dataSourceProperties) {
		dbUrl = dataSourceProperties.getUrl();
		dbDriverClass = dataSourceProperties.getDriverClassName();
		dbUserName = dataSourceProperties.getUsername();
		dbPassword = dataSourceProperties.getPassword();
	}
}
