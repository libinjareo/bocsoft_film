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

    /**
     * 根据type 取得相应枚举
     * @param type
     * @return
     */
    public static ConditionTypeEnum getTypeEnum(String type){
        for(ConditionTypeEnum enums : ConditionTypeEnum.values()){
            if(enums.getType().equals(type)){
                return enums;
            }
        }
        return null;
    }
}
