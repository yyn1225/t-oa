package com.jtech.toa.core;

import com.google.common.base.Throwables;

import com.baomidou.mybatisplus.toolkit.StringUtils;

import javax.servlet.http.HttpServletResponse;

import com.jtech.marble.error.BaseErrorCode;
import com.jtech.marble.error.ErrorCode;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Deprecated
public class ApiResponse<T> {


    private final T data;
    private final String throwable;
    private final int errorCode;
    private final boolean success;
    private final String errorMessage;
    private final int httpStatus;

    ApiResponse(T data, String throwable, int errorCode, boolean success, String errorMessage, int httpStatus) {
        this.data = data;
        this.throwable = throwable;
        this.errorCode = errorCode;
        this.success = success;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public static <E> ApiResponse<E> success(E data) {
        return new Builder<E>().setSuccess(true)
                .setErrorCode(HttpServletResponse.SC_OK)
                .setThrowable(StringUtils.EMPTY)
                .setErrorMessage(StringUtils.EMPTY)
                .setData(data)
                .setHttpStatus(HttpServletResponse.SC_OK)
                .create();
    }

    public static ApiResponse<String> success() {
        return new Builder<String>().setSuccess(true)
                .setErrorCode(HttpServletResponse.SC_OK)
                .setThrowable(StringUtils.EMPTY)
                .setErrorMessage(StringUtils.EMPTY)
                .setData(StringUtils.EMPTY)
                .setHttpStatus(HttpServletResponse.SC_OK)
                .create();
    }

    public static <E> ApiResponse<E> error(String errorMessage, ErrorCode errorCode) {
        return new Builder<E>().setSuccess(false)
                .setErrorCode(errorCode.getCode())
                .setThrowable(StringUtils.EMPTY)
                .setErrorMessage(errorMessage)
                .setData(null)
                .setHttpStatus(HttpServletResponse.SC_OK)
                .create();
    }

    public static <E> ApiResponse<E> error() {
        return new Builder<E>().setSuccess(false)
                .setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .setThrowable(StringUtils.EMPTY)
                .setErrorMessage("server error!")
                .setData(null)
                .setHttpStatus(HttpServletResponse.SC_OK)
                .create();
    }


    public static <E> ApiResponse<E> error(ErrorCode errorCode) {
        return new Builder<E>().setSuccess(false)
                .setErrorCode(errorCode.getCode())
                .setThrowable(StringUtils.EMPTY)
                .setErrorMessage("Server Error!")
                .setData(null)
                .setHttpStatus(HttpServletResponse.SC_OK)
                .create();
    }

    public static <E> ApiResponse<E> error(E data, Throwable e, ErrorCode code) {
        String exeption = Throwables.getStackTraceAsString(e);
        return new Builder<E>().setSuccess(false)
                .setErrorCode(code.getCode())
                .setThrowable(exeption)
                .setErrorMessage(e.getMessage())
                .setData(data)
                .setHttpStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .create();
    }

    public static <E> ApiResponse<E> error(E data, Throwable e, ErrorCode errorCode, int httpStatus) {
        String exeption = Throwables.getStackTraceAsString(e);
        return new Builder<E>().setSuccess(false)
                .setErrorCode(errorCode.getCode())
                .setThrowable(exeption)
                .setErrorMessage(e.getMessage())
                .setData(data)
                .setHttpStatus(httpStatus)
                .create();
    }

    public static <E> ApiResponse<E> error(E data, String errorMessage, ErrorCode errorCode) {
        return new Builder<E>().setSuccess(false)
                .setErrorCode(errorCode.getCode())
                .setThrowable(StringUtils.EMPTY)
                .setErrorMessage(errorMessage)
                .setData(data)
                .setHttpStatus(HttpServletResponse.SC_OK)
                .create();
    }

    public static <E> ApiResponse<E> error(E data, String errorMessage, ErrorCode errorCode, int httpStatus) {
        return new Builder<E>().setSuccess(false)
                .setErrorCode(errorCode.getCode())
                .setThrowable(StringUtils.EMPTY)
                .setErrorMessage(errorMessage)
                .setData(data)
                .setHttpStatus(httpStatus)
                .create();
    }

    public T getData() {
        return data;
    }

    public String getThrowable() {
        return throwable;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public static class Builder<E> {
        private E data;
        private String throwable;
        private int errorCode;
        private boolean success;
        private String errorMessage;
        private int httpStatus;

        public Builder<E> setData(E data) {
            this.data = data;
            return this;
        }

        public Builder<E> setThrowable(String throwable) {
            this.throwable = throwable;
            return this;
        }

        public Builder<E> setErrorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder<E> setSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<E> setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder<E> setHttpStatus(int httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public ApiResponse<E> create() {
            return new ApiResponse<>(data, throwable, errorCode, success, errorMessage, httpStatus);
        }
    }
}
