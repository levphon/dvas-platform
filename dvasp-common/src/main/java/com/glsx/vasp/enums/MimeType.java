package com.glsx.vasp.enums;

import com.glsx.vasp.exception.SystemMessage;
import org.apache.commons.lang.StringUtils;

public enum MimeType {
    AUTO("自动获取", ""),
    STREAM("application/octet-stream", "jpg"),

    PDF("application/pdf", "pdf"),
    ZIP("application/zip", "zip"),
    RAR("application/zip", "rar"),
    EXCEL2003("application/vnd.ms-excel", "xls"),
    EXCEL2007("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx"),
    EXE("application/octet-stream", "exe"),

    TXT("text/plain", "txt"),
    JAVA("text/plain", "java"),
    PYTHON("text/plain", "py"),
    JAVASCRIPT("text/plain", "js"),
    CSS("text/plain", "css"),

    JPG("application/x-jpg", "jpg"),
    JPEG("image/jpeg", "jpg"),
    GIF("image/gif", "gif"),
    PNG("application/x-png", "png");
    private String contentType;
    private String suffix;

    public String getContentType() {
        return contentType + ";charset=utf-8";
    }

    private MimeType(String contentType, String suffix) {
        this.contentType = contentType;
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public static MimeType parseMIME(String fileType) {
        if (StringUtils.isBlank(fileType)) {
            throw SystemMessage.NOT_SUPPORT_MIME.exception(fileType);
        }
        MimeType[] values = MimeType.values();
        for (MimeType mimeType : values) {
            if (mimeType.getSuffix().equalsIgnoreCase(fileType)) {
                return mimeType;
            }
        }
        throw SystemMessage.NOT_SUPPORT_MIME.exception(fileType);
    }
}