package com.example.vo;

/**
 * Created by cgc on 18/1/9.
 */
public class ValidFailVo {
    private String fieldName;
    private String objectName;
    private String errorMsg;
    private Object invalidValue;
    private  Object rejectedValue;

    public ValidFailVo(){

    }

    public ValidFailVo(String fieldName, String objectName, String errorMsg) {
        this.fieldName = fieldName;
        this.objectName = objectName;
        this.errorMsg = errorMsg;
    }



    public ValidFailVo(String fieldName, String objectName, String errorMsg, Object invalidValue) {
        this.fieldName = fieldName;
        this.objectName = objectName;
        this.errorMsg = errorMsg;
        this.invalidValue = invalidValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
