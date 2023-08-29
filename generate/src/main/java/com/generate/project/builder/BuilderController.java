package com.generate.project.builder;

import com.generate.project.bean.Constants;
import com.generate.project.bean.FieldInfo;
import com.generate.project.bean.TableInfo;
import com.generate.project.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuilderController {
    private static final Logger logger = LoggerFactory.getLogger(BuilderController.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_CONTROLLER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String className = tableInfo.getBeanName() + "Controller";
        File serviceImplFile = new File(folder, className + ".java");
        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(serviceImplFile);
            outw = new OutputStreamWriter(out, "utf8");
            bw = new BufferedWriter(outw);
            bw.write("package " + Constants.PACKAGE_CONTROLLER + ";");
            bw.newLine();
            bw.newLine();

            String serviceName = tableInfo.getBeanName() + "Service";
            String serviceBeanName = StringUtils.lowerCaseFirstLetter(serviceName);
            // 导包
            bw.write("import " + Constants.PACKAGE_PO + "." + tableInfo.getBeanName() + ";");
            bw.newLine();
            bw.write("import " + Constants.PACKAGE_QUERY + "." + tableInfo.getBeanParamName() + ";");
            bw.newLine();
            bw.write("import " + Constants.PACKAGE_VO + ".ResponseVO;");
            bw.newLine();
            bw.write("import " + Constants.PACKAGE_SERVICE + "." + serviceName + ";");
            bw.newLine();
            bw.write("import org.springframework.web.bind.annotation.RequestBody;");
            bw.newLine();
            bw.write("import org.springframework.web.bind.annotation.RequestMapping;");
            bw.newLine();
            bw.write("import org.springframework.web.bind.annotation.RestController;");
            bw.newLine();
            bw.newLine();
            bw.write("import javax.annotation.Resource;");
            bw.newLine();
            bw.write("import java.util.List;");
            bw.newLine();
            bw.newLine();


            BuildComment.createClassComment(bw, tableInfo.getComment() + "Service");
            bw.write("@RestController");
            bw.newLine();
            bw.write("@RequestMapping(\"/" + StringUtils.lowerCaseFirstLetter(tableInfo.getBeanName()) + "\")");
            bw.newLine();
            bw.write("public class " + className + " extends ABaseController{");
            bw.newLine();
            bw.newLine();

            // 引入Mapper
            bw.write("\t@Resource");
            bw.newLine();
            bw.write("\tprivate " + serviceName + " " + StringUtils.lowerCaseFirstLetter(serviceName) + ";");
            bw.newLine();
            bw.newLine();

            BuildComment.createFieldComment(bw, "根据条件分页查询");
            bw.write("\t@RequestMapping(\"loadDataList\")");
            bw.newLine();
            bw.write("\tpublic ResponseVO loadDatalist(" + tableInfo.getBeanParamName() + " query) {");
            bw.newLine();
            bw.write("\t\treturn getSuccessResponseVO(" + serviceBeanName + ".findListByPage(query));");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();


            BuildComment.createFieldComment(bw, "新增");
            bw.write("\t@RequestMapping(\"add\")");
            bw.newLine();
            bw.write("\tpublic ResponseVO add(" + tableInfo.getBeanName() + " bean) {");
            bw.newLine();
            bw.write("\t\treturn getSuccessResponseVO(this." + serviceBeanName + ".add(bean));");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();

            BuildComment.createFieldComment(bw, "批量新增");
            bw.write("\t@RequestMapping(\"addBatch\")");
            bw.newLine();
            bw.write("\tpublic ResponseVO addBatch(@RequestBody List<" + tableInfo.getBeanName() + "> listBean) {");
            bw.newLine();
            bw.write("\t\treturn getSuccessResponseVO(this." + serviceBeanName + ".addBatch(listBean));");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();

            BuildComment.createFieldComment(bw, "批量新增或修改");
            bw.write("\t@RequestMapping(\"addOrUpdateBatch\")");
            bw.newLine();
            bw.write("\tpublic ResponseVO addOrUpdateBatch(@RequestBody List<" + tableInfo.getBeanName() + "> listBean) {");
            bw.newLine();
            bw.write("\t\treturn getSuccessResponseVO(this." + serviceBeanName + ".addOrUpdateBatch(listBean));");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();

            for (Map.Entry<String, List<FieldInfo>> entry : tableInfo.getKeyIndexMap().entrySet()) {
                List<FieldInfo> keyFieldInfoList = entry.getValue();
                Integer index = 0;
                StringBuilder methodName = new StringBuilder();
                StringBuilder methodParams = new StringBuilder();
                StringBuilder paramsBuffer = new StringBuilder();
                for (FieldInfo fieldInfo : keyFieldInfoList) {
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    if (index < keyFieldInfoList.size()) {
                        methodName.append("And");
                    }
                    methodParams.append(fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName());
                    paramsBuffer.append(fieldInfo.getPropertyName());
                    if (index < keyFieldInfoList.size()) {
                        methodParams.append(", ");
                        paramsBuffer.append(",");
                    }
                }
                // 生成查询相关
                BuildComment.createFieldComment(bw, "根据" + methodName + "查询");
                bw.newLine();
                String methodNameResult = "get" + tableInfo.getBeanName() + "By" + methodName;
                bw.write("\t@RequestMapping(\"" + methodNameResult + "\")");
                bw.newLine();
                bw.write("\tpublic ResponseVO " + methodNameResult + "(" + methodParams + ") {");
                bw.newLine();
                bw.write("\t\treturn getSuccessResponseVO(this." + serviceBeanName + ".get" + tableInfo.getBeanName() + "By" + methodName + "(" + paramsBuffer + "));");
                bw.newLine();
                bw.write("\t}");
                bw.newLine();
                bw.newLine();


                // 生成更新相关
                BuildComment.createFieldComment(bw, "根据" + methodName + "更新");
                methodNameResult = "update" + tableInfo.getBeanName() + "By" + methodName;
                bw.write("\t@RequestMapping(\"" + methodNameResult + "\")");
                bw.newLine();
                bw.write("\tpublic ResponseVO " + methodNameResult + "(" + tableInfo.getBeanName() + " bean, " + methodParams + ") {");
                bw.newLine();
                bw.write("\t\treturn getSuccessResponseVO(this." + serviceBeanName + ".update" + tableInfo.getBeanName() + "By" + methodName + "(bean, " + paramsBuffer + "));");
                bw.newLine();
                bw.write("\t}");
                bw.newLine();
                bw.newLine();

                // 生成删除相关
                BuildComment.createFieldComment(bw, "根据" + methodName + "删除");
                methodNameResult = "delete" + tableInfo.getBeanName() + "By" + methodName;
                bw.write("\t@RequestMapping(\"" + methodNameResult + "\")");
                bw.newLine();
                bw.write("\tpublic ResponseVO " + methodNameResult + "(" + methodParams + ") {");
                bw.newLine();
                bw.write("\t\tthis." + serviceBeanName + ".delete" + tableInfo.getBeanName() + "By" + methodName + "(" + paramsBuffer + ");");
                bw.newLine();
                bw.write("\t\treturn getSuccessResponseVO(null);");
                bw.newLine();
                bw.write("\t}");
                bw.newLine();
                bw.newLine();

            }


            bw.write("}");
            bw.flush();
        } catch (Exception e) {
            logger.info("创建serivce失败");
        } finally {
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
