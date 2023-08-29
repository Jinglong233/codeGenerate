package com.generate.project.bean;

import com.generate.project.utils.PropertiesUtils;

/**
 * 常量
 */
public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;

    public static String SUFFIX_BEAN_QUERY;
    public static String Author;
    public static String SUFFIX_BEAN_QUERY_FUZZY;
    public static String SUFFIX_BEAN_QUERY_TIME_START;
    public static String SUFFIX_BEAN_QUERY_TIME_END;
    public static String SUFFIX_MAPPERS;

    // 需要忽略的属性
    public static String IGNORE_BEAN_TOJSON_FIELD;
    public static String IGNORE_BEAN_TOJSON_EXPRESSION;
    public static String IGNORE_BEAN_TOJSON_CLASS;

    // 日期格式序列化
    public static String BEAN_DATE_FORMAT_EXPRESSION;
    public static String BEAN_DATE_FORMAT_CLASS;

    // 日期格式反序列化
    public static String BEAN_DATE_UNFORMAT_EXPRESSION;
    public static String BEAN_DATE_UNFORMAT_CLASS;

    private static String PATH_JAVA = "java";
    private static String PATH_RESOURCES = "resources";

    public static String PACKAGE_BASE;
    public static String PACKAGE_PO;
    public static String PACKAGE_UTILS;
    public static String PACKAGE_ENUMS;
    public static String PACKAGE_EXCEPTION;
    public static String PACKAGE_QUERY;
    public static String PACKAGE_VO;
    public static String PACKAGE_MAPPERS;
    public static String PACKAGE_SERVICE;
    public static String PACKAGE_SERVICE_IMPL;
    public static String PACKAGE_CONTROLLER;


    public static String PATH_BASE;
    public static String PATH_PO;
    public static String PATH_UTILS;
    public static String PATH_ENUMS;
    public static String PATH_EXCEPTION;
    public static String PATH_QUERY;
    public static String PATH_VO;
    public static String PATH_MAPPERS;
    public static String PATH_MAPPERS_XMLS;
    public static String PATH_SERVICE;
    public static String PATH_SERVICE_IMPL;
    public static String PATH_CONTROLLER;


    static {
        Author = PropertiesUtils.getString("author.comment");
        // 需要忽略的属性
        IGNORE_BEAN_TOJSON_FIELD = PropertiesUtils.getString("ignore.bean.tojson.field");
        IGNORE_BEAN_TOJSON_EXPRESSION = PropertiesUtils.getString("ignore.bean.tojson.expression");
        IGNORE_BEAN_TOJSON_CLASS = PropertiesUtils.getString("ignore.bean.tojson.class");
        // 日期格式序列化
        BEAN_DATE_FORMAT_EXPRESSION = PropertiesUtils.getString("bean.date.format.expression");
        BEAN_DATE_FORMAT_CLASS = PropertiesUtils.getString("bean.date.format.class");
        // 日期格式反序列化
        BEAN_DATE_UNFORMAT_EXPRESSION = PropertiesUtils.getString("bean.date.unformat.expression");
        BEAN_DATE_UNFORMAT_CLASS = PropertiesUtils.getString("bean.date.unformat.class");

        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix"));
        SUFFIX_BEAN_QUERY = PropertiesUtils.getString("suffix.bean.param");
        SUFFIX_BEAN_QUERY_FUZZY = PropertiesUtils.getString("suffix.bean.query.fuzzy");
        SUFFIX_BEAN_QUERY_TIME_START = PropertiesUtils.getString("suffix.bean.query.time.start");
        SUFFIX_BEAN_QUERY_TIME_END = PropertiesUtils.getString("suffix.bean.query.time.end");
        SUFFIX_MAPPERS = PropertiesUtils.getString("suffix.mappers");

        PACKAGE_BASE = PropertiesUtils.getString("package.base");
        // PO
        PACKAGE_PO = PACKAGE_BASE + "." + PropertiesUtils.getString("package.po");
        // Utils
        PACKAGE_UTILS = PACKAGE_BASE + "." + PropertiesUtils.getString("package.utils");
        PACKAGE_ENUMS = PACKAGE_BASE + "." + PropertiesUtils.getString("package.enums");
        // Query
        PACKAGE_QUERY = PACKAGE_BASE + "." + PropertiesUtils.getString("package.query");
        // VO
        PACKAGE_VO = PACKAGE_BASE + "." + PropertiesUtils.getString("package.vo");
        // MAPPER
        PACKAGE_MAPPERS = PACKAGE_BASE + "." + PropertiesUtils.getString("package.mappers");
        // SERVICE
        PACKAGE_SERVICE = PACKAGE_BASE + "." + PropertiesUtils.getString("package.service");
        // SERVICE_IMPL
        PACKAGE_SERVICE_IMPL = PACKAGE_BASE + "." + PropertiesUtils.getString("package.service.impl");
        // EXCEPTION
        PACKAGE_EXCEPTION = PACKAGE_BASE + "." + PropertiesUtils.getString("package.exception");
        // CONTROLLER
        PACKAGE_CONTROLLER = PACKAGE_BASE + "." + PropertiesUtils.getString("package.controller");

        PATH_BASE = PropertiesUtils.getString("path.base");
        PATH_BASE = PATH_BASE + PATH_JAVA;

        PATH_PO = PATH_BASE + "/" + PACKAGE_PO.replace(".", "/");
        PATH_UTILS = PATH_BASE + "/" + PACKAGE_UTILS.replace(".", "/");
        PATH_ENUMS = PATH_BASE + "/" + PACKAGE_ENUMS.replace(".", "/");
        PATH_EXCEPTION = PATH_BASE + "/" + PACKAGE_EXCEPTION.replace(".", "/");
        PATH_QUERY = PATH_BASE + "/" + PACKAGE_QUERY.replace(".", "/");
        PATH_VO = PATH_BASE + "/" + PACKAGE_VO.replace(".", "/");
        PATH_MAPPERS = PATH_BASE + "/" + PACKAGE_MAPPERS.replace(".", "/");
        PATH_MAPPERS_XMLS = PropertiesUtils.getString("path.base") + PATH_RESOURCES + "/" + PACKAGE_MAPPERS.replace(".", "/");
        PATH_SERVICE = PATH_BASE + "/" + PACKAGE_SERVICE.replace(".", "/");
        PATH_SERVICE_IMPL = PATH_BASE + "/" + PACKAGE_SERVICE_IMPL.replace(".", "/");
        PATH_CONTROLLER = PATH_BASE + "/" + PACKAGE_CONTROLLER.replace(".", "/");
    }

    public static String[] SQL_DATE_TIME_TYPES = new String[]{"datetime", "timestamp"};
    public static String[] SQL_DATE_TYPES = new String[]{"date"};
    public static String[] SQL_DECIMAL_TYPE = new String[]{"decimal", "double", "float"};
    public static String[] SQL_STRING_TYPES = new String[]{"char", "varchar", "text", "mediumtext", "longtext"};
    public static String[] SQL_TIME_TYPES = new String[]{"time"};
    public static String[] SQL_INTEGER_TYPES = new String[]{"int", "tinyint"};
    public static String[] SQL_LONG_TYPES = new String[]{"bigint"};

    public static void main(String[] args) {

        System.out.println(PATH_MAPPERS_XMLS);
    }
}
