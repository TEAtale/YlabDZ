package org.example.Lesson5.sqlquerybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLQueryBuilderImpl implements SQLQueryBuilder{

    private final Config databaseConfig;

    @Autowired
    public SQLQueryBuilderImpl(Config databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public String queryForTable(String tableName) {
        String query = null;
        try {
            DatabaseMetaData metaData = databaseConfig.dataSource().getConnection().getMetaData();

            ResultSet tables = metaData.getTables(null, null, tableName, null);

            if (tables.next()) {//если таблица есть в БД
                ResultSet columns = metaData.getColumns(null, null, tableName, null);

                List<String> columnNames = new ArrayList<>();
                while (columns.next()) {//получаем имена колонок
                    columnNames.add(columns.getString("COLUMN_NAME"));
                }
                query = "SELECT " + String.join(", ", columnNames) + " FROM " + tableName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;
    }

    @Override
    public List<String> getTables() {
        List<String> tableNames = new ArrayList<>();
        try {
            DatabaseMetaData metaData = databaseConfig.dataSource().getConnection().getMetaData();

            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            while (tables.next()) {//получаем имена таблиц
                tableNames.add(tables.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames;
    }
}

