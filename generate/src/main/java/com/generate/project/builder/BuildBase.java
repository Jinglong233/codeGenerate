package com.generate.project.builder;

import com.generate.project.bean.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class BuildBase {

    private static final Logger logger = LoggerFactory.getLogger(BuildBase.class);

    public static void execute() {
        List<String> headerInfoList = new ArrayList<>();
        // 生成date Enum
        headerInfoList.add("package " + Constants.PACKAGE_ENUMS + ";");
        build(headerInfoList, "DateTimePatternEnum", Constants.PATH_ENUMS);

        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_UTILS + ";");
        build(headerInfoList, "DateUtils", Constants.PATH_UTILS);

        // 生成BaseMapper
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_MAPPERS + ";");
        build(headerInfoList, "BaseMapper", Constants.PATH_MAPPERS);

        // 生成pageSize枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_ENUMS + ";");
        build(headerInfoList, "PageSize", Constants.PATH_ENUMS);

        // 生成SimplePage枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_QUERY + ";");
        headerInfoList.add("import " + Constants.PACKAGE_ENUMS + ".PageSize" + ";");
        build(headerInfoList, "SimplePage", Constants.PATH_QUERY);

        // 生成BaseQuery枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_QUERY + ";");
        build(headerInfoList, "BaseQuery", Constants.PATH_QUERY);

        // 生成PaginationResultVo
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_VO + ";");
        build(headerInfoList, "PaginationResultVo", Constants.PATH_VO);

    }

    public static void build(List<String> headerInfoList, String fileName, String outPutPath) {
        File folder = new File(outPutPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File javaFile = new File(outPutPath, fileName + ".java");
        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;

        InputStream in = null;
        InputStreamReader inr = null;
        BufferedReader bf = null;

        try {
            out = new FileOutputStream(javaFile);
            outw = new OutputStreamWriter(out, "utf-8");
            bw = new BufferedWriter(outw);

            for (String head : headerInfoList) {
                bw.write(head);
                bw.newLine();
                bw.newLine();
            }

            String templatePath = BuildBase.class.getClassLoader().getResource("template/" + fileName + ".txt").getPath();
            // 解决读取中文路径乱码
            templatePath = URLDecoder.decode(templatePath, "utf-8");
            in = new FileInputStream(templatePath);
            inr = new InputStreamReader(in);
            bf = new BufferedReader(inr);
            String lineInfo = null;
            while ((lineInfo = bf.readLine()) != null) {
                bw.write(lineInfo);
                bw.newLine();
            }
            bw.flush();

        } catch (Exception e) {
            logger.info("生成基础类，{}失败:", fileName, e);
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (inr != null) {
                try {
                    inr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (outw != null) {
                try {
                    outw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }

}
