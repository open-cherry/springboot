package com.example.valid;


import com.example.util.AjaxUtils;
import com.example.vo.OperateResult;
import com.example.vo.ValidFailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.*;

/**
 * 统一处理验证失败异常
 * 使用此切片后@Valid注解验证的参数后不用再加Errors或Bindingesult
 */
@RestControllerAdvice
public class ValidateControllerAdvice {

    Logger logger = LoggerFactory.getLogger(ValidateControllerAdvice.class);

    /**
     * bean校验未通过异常
     *
     * @see javax.validation.Valid
     * @see org.springframework.validation.Validator
     * @see org.springframework.validation.DataBinder
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e, WebRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            logger.error(error.getField() + ":" + error.getDefaultMessage());
        }
        request.setAttribute("fieldErrors", fieldErrors, WebRequest.SCOPE_REQUEST);
        if (AjaxUtils.isAjaxRequest(request)) {
            Map<String, Object> attrMap = new HashMap<>();
            String[] atrNames = request.getAttributeNames(WebRequest.SCOPE_REQUEST);
            for (String attr : atrNames) {
                Object value = request.getAttribute(attr, WebRequest.SCOPE_REQUEST);
                if (value instanceof Serializable) {
                    attrMap.put(attr, value);
                }
            }
            return getInvalidArgumentsMessage(e.getBindingResult());
        }
        return "/validError";
    }



    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleServiceException(ConstraintViolationException e, WebRequest request) {
        logger.error("数据库字段验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();


        List<ValidFailVo> invalidList = new ArrayList<>();
        //遍历错误字段获取错误消息
        for (ConstraintViolation violation : violations) {
            //获取错误信息
            ValidFailVo vo = new ValidFailVo();
            vo.setErrorMsg(violation.getMessage());
            vo.setFieldName(violation.getPropertyPath().toString());
            vo.setInvalidValue(violation.getInvalidValue());
            vo.setObjectName(violation.getRootBeanClass().getSimpleName());
            invalidList.add(vo);
        }
        request.setAttribute("fieldErrors", invalidList, WebRequest.SCOPE_REQUEST);
        if (AjaxUtils.isAjaxRequest(request)) {
            return new OperateResult<>(OperateResult.DEFAULT_FAIL,1002,invalidList);
        }
        return "/validError";
    }



    private OperateResult<List<ValidFailVo>> getInvalidArgumentsMessage(BindingResult result){
        //获取错误字段集合
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ValidFailVo> invalidArguments = new ArrayList<>();
        //遍历错误字段获取错误消息
        for (FieldError fieldError : fieldErrors) {
            //获取错误信息
            ValidFailVo invalidArgument = new ValidFailVo();
            invalidArgument.setErrorMsg(fieldError.getDefaultMessage());
            invalidArgument.setFieldName(fieldError.getField());
            invalidArgument.setRejectedValue(fieldError.getRejectedValue());
            invalidArgument.setObjectName(fieldError.getObjectName());
            invalidArguments.add(invalidArgument);

        }
        return new OperateResult<>(OperateResult.DEFAULT_FAIL,1001,invalidArguments);
    }
}
