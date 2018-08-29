package com.example.vo;

/**
 * Created by cgc on 18/1/8.
 */
public class OperateResult<T> {
    public static final int DEFAULT_FAIL = 0;
    public static final int DEFAULT_SUC = 1;

    private int result = DEFAULT_FAIL;// 0 失败 1成功
    private Integer errorCode;
    private T data;//返回数据

    public OperateResult(){

    }

    public OperateResult(int result, T data) {
        this.result = result;
        this.data = data;
    }

    public OperateResult(int result, Integer errorCode, T data) {
        this.result = result;
        this.errorCode = errorCode;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
