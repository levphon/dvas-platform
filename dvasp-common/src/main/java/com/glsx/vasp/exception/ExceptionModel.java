package com.glsx.vasp.exception;

import com.alibaba.fastjson.JSONObject;
import com.glsx.vasp.enums.ExceptionLevel;
import com.glsx.vasp.utils.IpUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class ExceptionModel {
    private String id;

    // 模块
    private String modul;
    // 异常发生时间
    private Date createTime;
    // 当前操作人
    private Integer operator;
    // 异常名称
    private String exceptionName;
    // 异常简短信息
    private String message;
    // 异常堆栈
    private List<String> stackTrace = new ArrayList<>();
    // 异常级别
    private ExceptionLevel exceptionLevel;
    //导致异常
    private List<CauseModel> causes = new ArrayList<CauseModel>();

    //存储请求信息
    private List<BasicNameValuePair> headers = new ArrayList<>();
    private String body;

    //记录远程 ip 地址
    private String ip;

    //记录异常发生的主机地址
    private String broker;

    public ExceptionModel() {
        this.createTime = new Date();
        this.broker = IpUtils.ipv4();
    }

    public ExceptionModel(String modul, ExceptionLevel exceptionLevel) {
        this();
        this.modul = modul;
        this.exceptionLevel = exceptionLevel;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }

    public ExceptionLevel getExceptionLevel() {
        return exceptionLevel;
    }

    public void setExceptionLevel(ExceptionLevel exceptionLevel) {
        this.exceptionLevel = exceptionLevel;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    /**
     * 设置异常信息
     *
     * @param throwable
     * @param packagePrefix
     */
    public void setException(Throwable throwable, String packagePrefix) {
        this.exceptionName = throwable.getClass().getName();
        this.message = throwable.getMessage();
        StackTraceElement[] stackTrace = throwable.getStackTrace();

        //记录主异常
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            if (className.startsWith(packagePrefix)) {
                String trackElement = stackTraceElement.toString();
                this.stackTrace.add(trackElement);
            }
        }

        //记录导致异常
        Throwable cause = throwable.getCause();
        while (cause != null) {
            try {
                String message = cause.getMessage();
                String exceptionName = cause.getClass().getName();
                CauseModel causeModel = new CauseModel(exceptionName,message);
                this.causes.add(causeModel);

                StackTraceElement[] causeStackTrace = cause.getStackTrace();
                for (StackTraceElement stackTraceElement : causeStackTrace) {
                    String className = stackTraceElement.getClassName();
                    if (className.startsWith(packagePrefix)) {
                        causeModel.add(stackTraceElement.toString());
                    }
                }
            } finally {
                cause = cause.getCause();
            }
        }
    }

    /**
     * 记录请求信息
     *
     * @param request
     * @throws IOException
     */
    public void setRequest(HttpServletRequest request) throws IOException {
        //记录 ip
        this.ip = request.getRemoteAddr();

        final String [] headerIgnore = {"host","connection","pragma","cache-control","accept","referer","accept-encoding","accept-language","cookie","x-requested-with","content-length","origin"};

        //获取请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if(ArrayUtils.contains(headerIgnore,headerName)){
                continue;
            }
            String headerValue = request.getHeader(headerName);
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(headerName, headerValue);
            headers.add(basicNameValuePair);
        }

        //获取请求体
        String method = request.getMethod();
        String contentType = request.getContentType();
        if(StringUtils.isBlank(contentType)){
            Map<String, String[]> parameterMap = request.getParameterMap();
            this.body = JSONObject.toJSONString(parameterMap);
        }else {
            if ("get".equalsIgnoreCase(method) || (contentType.startsWith("application/x-www-form-urlencoded"))) {
                Map<String, String[]> parameterMap = request.getParameterMap();
                this.body = JSONObject.toJSONString(parameterMap);
            } else if (contentType.startsWith("application/json")) {
                String jsonString = IOUtils.toString(request.getInputStream(), "utf-8");
                this.body = jsonString;
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 异常导致模型
     */
    final class CauseModel {
        private String exceptionName;
        private String message;
        private List<String> stackTrace = new ArrayList<>();

        public CauseModel() {
        }

        public CauseModel(String exceptionName, String message) {
            this.exceptionName = exceptionName;
            this.message = message;
        }

        public String getExceptionName() {
            return exceptionName;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getStackTrace() {
            return stackTrace;
        }

        public void add(String stackTrack) {
            this.stackTrace.add(stackTrack);
        }
    }


}
