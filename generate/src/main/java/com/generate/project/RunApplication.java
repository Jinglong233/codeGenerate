package com.generate.project;


import com.generate.project.bean.TableInfo;
import com.generate.project.builder.*;

import java.util.List;

public class RunApplication {
    public static void main(String[] args) {
        List<TableInfo> tables = BuildTable.getTables();
        BuildBase.execute();
        for (TableInfo table : tables) {
            BuildPo.execute(table);
            BuildQuery.execute(table);
            BuildMapper.execute(table);
            BuildMapperXml.execute(table);
            BuilderService.execute(table);
            BuilderServiceImpl.execute(table);
            BuilderController.execute(table);
        }

    }
}
