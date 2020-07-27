package com.github.xuchen93.generate.util;


import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.github.xuchen93.database.base.BaseEntity;
import com.github.xuchen93.generate.model.GenerateInfo;

public class GenerateUtil {

    public static void generate(GenerateInfo generateInfo) {
        GlobalConfig config = new GlobalConfig()
                .setSwagger2(generateInfo.isSwagger())
                .setActiveRecord(false)
                .setAuthor(generateInfo.getAuthor())
                .setOutputDir(generateInfo.getProjectPath())
                .setFileOverride(false)
                .setEnableCache(false)
                .setOpen(false)
                .setDateType(generateInfo.getDateType())
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setEntityName("%s")
                .setMapperName("%sDao")
                .setXmlName("%sMapper");
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setDbType(generateInfo.getDbType())
                .setUrl(generateInfo.getDbUrl())
                .setUsername(generateInfo.getDbUserName())
                .setPassword(generateInfo.getDbPassword())
                .setDriverName(generateInfo.getDbDriverClass());
        StrategyConfig strategyConfig = new StrategyConfig()
                .setEntityLombokModel(generateInfo.isLombok())
                .setCapitalMode(true)
                .setSkipView(true)
                .setSuperEntityClass(BaseEntity.class)
                .setSuperEntityColumns("id", "version", "created_user", "created_time", "updated_user", "updated_time")
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(ArrayUtil.toArray(generateInfo.getTableList(), String.class));
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(generateInfo.getPackageName())
                                .setMapper("dao")
                                .setXml("mapper")
                )
                .execute();
    }
}
