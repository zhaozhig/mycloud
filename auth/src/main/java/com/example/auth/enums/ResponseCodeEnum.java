package com.example.auth.enums;


import com.example.auth.constant.HttpCode;
import com.example.auth.model.ResponseCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


@Slf4j
public enum ResponseCodeEnum {
    /** 操作成功,返回code=1 **/
    SUCCESS("1", ""),
    /** 操作成功,返回code=0 **/
    FAILURE("0", "失败"),
    request_error("400", "请求数据错误"),
    no_login("401", "没有登录"),
    no_permission("403", "没有权限"),
    no_found("404", "找不到资源"),

    no_accept("406", "请求无响应"),

    sys_error("500", "系统异常"),

    fallback("410", "fallback"),

    /** 类型转换错误 **/
    class_type_error("9001", "系统异常"),
    redis_timeout("9002", "redis超时"),

    file_template_error("file_template_error", "导入模板标题错误或模板标题格式错误"),
    file_template_empty("file_template_empty", "导入文件内没有数据"),
    empty_datasource("empty_datasource", "数据源未设置"),
    validate_code_error("validate_code_error", "验证码错误"),
    illegal_req("illegal_req", "非法请求"),
    file_import_num_error("file_import_num_error","附件上传超出最大导入数量"),
    datainuse_nodel("datainuse_nodel", "数据在使用中"),

    service_error("service_error", "服务请求失败"),



    ;


    /** ========================================= **/
    @Getter
    private String code;
    @Getter
    private String dec;

    ResponseCodeEnum(String code, String dec) {
        this.code = code;
        this.dec = dec;
    }

    public ResponseCode info(){
        ResponseCode responseCode = new ResponseCode(code, dec);
        return responseCode;
    }

    public ResponseCode info(String dec){
        ResponseCode responseCode = new ResponseCode(this.code, dec);
        return responseCode;
    }

    /**
     * 通过code 获取 http 返回码
     * 仅处理特殊返回码
     * @param respCode
     * @return
     */
    public static int getHttpStatus(String respCode){
        int codeInt = HttpCode.success;

        if(StringUtils.isNotBlank(respCode)){
            try {
                codeInt = Integer.parseInt(respCode);
            } catch (NumberFormatException e) {
                log.info("errorCode : " + respCode);
            }

            switch(codeInt){
                case HttpCode.success:
                case HttpCode.redirect:
                case HttpCode.no_login:
                case HttpCode.no_permission:
                case HttpCode.no_found:
                case HttpCode.sys_error:
                    break;
                default:
                    codeInt = HttpCode.success;
                    break;
            }
        }

        return codeInt;
    }
}
