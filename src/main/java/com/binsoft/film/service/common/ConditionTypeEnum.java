package com.binsoft.film.service.common;

/**
 * 查询条件枚举
 */
public enum ConditionTypeEnum {

    BRAND("brand"),AREA("area"),HALLTYPE("hallType");

    private String type;

    public String getType(){
        return this.type;
    }
    ConditionTypeEnum(String type){
        this.type = type;
    }

}
