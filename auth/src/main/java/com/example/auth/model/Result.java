package com.example.auth.model;

import com.example.auth.enums.ResponseCodeEnum;
import com.example.auth.exception.OpenException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 时振超
 * @version 1.0
 * @date 2018/11/28 17:45
 */
@Data
public class Result<T> implements Serializable {
    private String code;
    private String errCode;
    private String msg;
    private String service;
    private T content;

    @SuppressWarnings("unchecked")
    public void setAttribute(Object objKey, Object objValue) {
        if (content == null) {
            content = (T) new HashMap<>();
        } else {
            if (!(content instanceof Map)) {
                throw OpenException.createException(ResponseCodeEnum.class_type_error.info());
            }
        }

        ((Map) content).put(objKey, objValue);
    }

    /**
     * 返回成功
     */
    public static <T> Result<T> successResponse() {
        Result<T> resp = new Result<>();
        resp.setCode(ResponseCodeEnum.SUCCESS.getCode());
        return resp;
    }

    /**
     * 返回成功
     *
     * @param obj
     */
    public static <T> Result<T> successResponse(T obj) {
        Result<T> resp = new Result<>();

        resp.setCode(ResponseCodeEnum.SUCCESS.getCode());
        resp.setContent(obj);

        return resp;
    }

    /**
     * 返回成功
     *
     * @param keyObj
     * @param valueObj
     */
    public static <T> Result<T> responseAttribute(Object keyObj, Object valueObj) {
        Result<T> resp = successResponse();

        resp.setAttribute(keyObj, valueObj);

        return resp;
    }

    /**
     * 返回失败
     *
     * @param exce
     */
    public static <T> Result<T> failResponse(Exception exce) {

        OpenException customExc = OpenException.createException(exce);

        String errorCode = customExc.getErrorCode();
        String errorMsg = customExc.getErrorMsg();

        Result<T> resp = new Result<>();

        resp.setCode(ResponseCodeEnum.FAILURE.getCode());
        resp.setErrCode(errorCode);
        resp.setMsg(errorMsg);

        return resp;
    }

    /**
     * 返回失败
     */
    public static <T> Result<T> failResponse() {
        OpenException exce = OpenException.createException();
        return failResponse(exce);
    }

    /**
     * 返回失败
     */
    public static <T> Result<T> failResponse(ResponseCode responseCode) {
        OpenException exce = OpenException.createException(responseCode);
        return failResponse(exce);
    }

    /**
     * 校验返回结果
     *
     * @param resp
     * @return
     */
    public static boolean check(Result resp, boolean fastFail) {

        boolean check = false;

        ResponseCode errorCode = null;

        if (resp != null) {
            if (!resp.getCode().equals(ResponseCodeEnum.SUCCESS.getCode())) {
                errorCode = new ResponseCode(resp.getErrCode(), resp.getMsg());
            }
        } else {
            errorCode = ResponseCodeEnum.fallback.info();
        }
        if (errorCode == null) {
            check = true;

        } else if (fastFail) {
            throw OpenException.createException(errorCode);
        }

        return check;
    }

    /**
     * 获取并校验返回结果
     *
     * @param resp
     * @param <T>
     * @return
     */
    public static <T> T getResultContent(Result<T> resp, boolean fastFail) {
        boolean check = check(resp, fastFail);
        T result = null;
        if (check) {
            result = resp.getContent();
        }
        return result;
    }

    /**
     * 获取并校验返回结果 快速失败模式
     *
     * @param resp
     * @param <T>
     * @return
     */
    public static <T> T getResultContent(Result<T> resp) {
        return getResultContent(resp, true);
    }

    /**
     * 该方法已过时，请使用 static check(Result resp, boolean fastFaile) 方法代替
     *
     * @return
     */
    @Deprecated
    @JsonIgnore
    public boolean isRight() {
        return "1".equals(this.code);
    }

}
