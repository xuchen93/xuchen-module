package com.github.xuchen93.util;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CommonUtil {

    /**
     * 控制台输出model对象的mybatis resultMap配置
     * 约定：
     * 驼峰映射
     * 只包含简单字段和List两种字段类型
     */
    public static void soutModelXml(Class cl) {
        List<String> list = generateModelXml(cl);
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 控制台输出mybatis addList的insert
     */
    public static void soutAddListEntity(Class cl) {
        Set<String> set = new HashSet<>();
        set.add("serialVersionUID");
        Field[] fields = ReflectUtil.getFields(cl);
        System.out.println("\t<insert id =\"addList\" parameterType=\"java.util.List\" >");
        System.out.println("\t\tinsert into XXX");
        System.out.print("\t\t(");
        StringBuilder sb = new StringBuilder(100);
        for (Field field : fields) {
            if (set.contains(field.getName())) {
                continue;
            }
            sb.append(StrUtil.toSymbolCase(field.getName(), '_') + ",");
        }
        System.out.print(sb.deleteCharAt(sb.length() - 1));
        System.out.println(")");
        System.out.println("\t\tvalues");
        System.out.println("\t\t<foreach collection =\"list\" item=\"item\" index= \"index\" separator =\",\">");
        System.out.print("\t\t(");
        sb = new StringBuilder(300);
        for (Field field : fields) {
            if (set.contains(field.getName())) {
                continue;
            }
            if ("version".equals(field.getName())) {
                sb.append("0,");
            } else {
                sb.append(String.format("#{item.%s},", field.getName()));
            }
        }
        System.out.print(sb.deleteCharAt(sb.length() - 1));
        System.out.println(")");
        System.out.println("\t\t</foreach>");
        System.out.println("\t</insert>");
    }

    @SneakyThrows
    private static List<String> generateModelXml(Class cl) {
        List<String> result = new ArrayList<>(100);
        Field[] fields = ReflectUtil.getFields(cl);
        List<Class> fieldClassList = new ArrayList<>(10);
        List<String> tempList = new ArrayList<>(10);
        result.add(StrUtil.format("\t<resultMap id=\"{}\" type=\"{}\">", StrUtil.lowerFirst(cl.getSimpleName()), cl.getCanonicalName()));
        for (Field field : fields) {
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }
            if (List.class.isAssignableFrom(field.getType())) {
                String className = StrUtil.subBetween(field.getGenericType().toString(), "<", ">");
                List<String> list = StrUtil.split(className, '.');
                fieldClassList.add(Class.forName(className));
                tempList.add(StrUtil.format("\t\t<collection  property=\"{}\" resultMap=\"{}\"/>", field.getName(), StrUtil.lowerFirst(list.get(list.size() - 1))));
            } else {
                result.add(StrUtil.format("\t\t<result column=\"{}\" property=\"{}\"/>", StrUtil.toUnderlineCase(field.getName()), field.getName()));
            }
        }
        result.addAll(tempList);
        result.add("\t</resultMap>");
        result.add("");
        for (Class aClass : fieldClassList) {
            result.addAll(generateModelXml(aClass));
        }
        return result;
    }
}
