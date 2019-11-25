package com.binsoft.film.common.typehandler;

import org.apache.ibatis.type.LocalDateTimeTypeHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MyLocalDateTimeTypeHandler extends LocalDateTimeTypeHandler {
    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {

        Object object = rs.getObject(columnName);
        if(object instanceof  java.sql.Timestamp){
            return LocalDateTime.ofInstant(((Timestamp) object).toInstant(), ZoneOffset.ofHours(0));
        }
        return super.getResult(rs,columnName);
    }
}
