package com.github.braisdom.objsql.example.oracle;

import com.github.braisdom.objsql.FieldValue;
import com.github.braisdom.objsql.TableRowAdapter;
import com.github.braisdom.objsql.transition.ColumnTransitional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;

public class JsonColumnTransitional implements ColumnTransitional {

    private Gson gson = new GsonBuilder().create();

    @Override
    public Object sinking(DatabaseMetaData databaseMetaData, Object object,
                          TableRowAdapter tableRowDescriptor, String fieldName, FieldValue fieldValue) {
        if(fieldValue.getValue() != null)
            return gson.toJson(fieldValue.getValue());
        return null;
    }

    @Override
    public Object rising(DatabaseMetaData databaseMetaData, ResultSetMetaData resultSetMetaData,
                         Object object, TableRowAdapter tableRowDescriptor, String fieldName, Object columnValue) {
        if(columnValue != null)
            return gson.fromJson(String.valueOf(columnValue), tableRowDescriptor.getFieldType(fieldName));
        return null;
    }
}