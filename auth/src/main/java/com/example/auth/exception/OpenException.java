package com.example.auth.exception;

import com.example.auth.enums.ResponseCodeEnum;
import com.example.auth.model.ResponseCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 自定义异常集合
 */
public class OpenException extends RuntimeException {
    @Getter
    @Setter
    private String errorCode;
    @Getter
    @Setter
    private String errorMsg;

    protected OpenException(String msg) {
        super(msg);
    }

    protected OpenException(Throwable te) {
        super(te);
    }

    protected void initException(ResponseCode error, String errorMsg){
        if(error == null){
            error = ResponseCodeEnum.sys_error.info();
        }

        this.errorCode = error.getCode();

        if(StringUtils.isBlank(errorMsg)){
            this.errorMsg = error.getDec();
        }else{
            this.errorMsg = errorMsg;
        }
    }

    /**
     * 创建捕获到的异常
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static OpenException createException(ResponseCode errorCode, String errorMsg){

        if(errorCode == null){
            errorCode = ResponseCodeEnum.sys_error.info();
        }

        if(errorMsg == null){
            errorMsg = errorCode.getDec();
        }

        OpenException oe = new OpenException(errorCode.getDec());

        oe.initException(errorCode, errorMsg);

        return oe;
    }

    /**
     * 创建捕获到的异常
     * @param errorCode
     * @return
     */
    public static OpenException createException(ResponseCode errorCode){
        OpenException oe = createException(errorCode, null);
        return oe;
    }

    /**
     * 创建捕获到的异常
     * @return
     */
    public static OpenException createException(){
        OpenException oe = createException(null, null);
        return oe;
    }

    public static OpenException fail(String msg){
        return OpenException.createException(ResponseCodeEnum.FAILURE.info(msg));
    }

    /**
     * 创建捕获到的异常
     * @param te
     * @return
     */
    public static OpenException createException(Throwable te){
        OpenException oe = null;

        if(te instanceof OpenException){
            oe = (OpenException)te;
        }else{
            oe = new OpenException(te);
            oe.initException(null, null);
        }

        return oe;
    }
    
}
