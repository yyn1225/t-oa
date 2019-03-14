package com.jtech.toa.exception;

import com.google.common.base.Strings;

import com.jtech.marble.error.BaseErrorCode;
import com.jtech.marble.error.ErrorCode;
import com.jtech.marble.error.ErrorModel;
import com.jtech.marble.exception.BussinessException;
import com.jtech.marble.exception.DaoException;
import com.jtech.marble.exception.InternalServerErrorException;
import com.jtech.marble.exception.ParamCheckException;
import com.jtech.toa.core.service.Ii8nMessageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p> 全局异常处理 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final Ii8nMessageService messageService;

    @Autowired
    public GlobalExceptionHandler(Ii8nMessageService messageService) {
        this.messageService = messageService;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DaoException.class)
    @ResponseBody
    public ErrorModel daoException(DaoException e)
            throws Exception {
        logger.error("The dao handle exception!", e);
        final String message = e.getMessage();
        return ErrorModel.builder().setCode(BaseErrorCode.ERRROR)
                .setMessage(message).createErrorModel();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ParamCheckException.class)
    @ResponseBody
    public ErrorModel paramCheckException(ParamCheckException e)
            throws Exception {
        logger.error("The param check handle exception!", e);
        final String message = e.getMessage();
        return ErrorModel.builder().setCode(e.getCode())
                .setMessage(message).createErrorModel();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = InternalServerErrorException.class)
    @ResponseBody
    public ErrorModel paramCheckException(InternalServerErrorException e)
            throws Exception {
        logger.error("The param check handle exception!", e);
        return ErrorModel.builder().setCode(BaseErrorCode.ERRROR)
                .setMessage(e.getMessage())
                .createErrorModel();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorModel handleException(Exception e) {
        logger.error("The handle exception!", e);
        return ErrorModel.builder().setCode(BaseErrorCode.ERRROR)
                .setMessage(e.getMessage())
                .createErrorModel();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BussinessException.class)
    @ResponseBody
    public ErrorModel handleBussiness(BussinessException e) {
        logger.error("The bussiness has exception!", e);
        final ErrorCode errorCode = e.getErrorCode();
        final String message;
        if (Strings.isNullOrEmpty(e.getMessage())) {
            message = messageService.getMessage(errorCode);
        } else {
            message = e.getMessage();
        }
        return ErrorModel.builder().setCode(errorCode)
                .setMessage(message)
                .createErrorModel();
    }
}
