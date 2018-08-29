package com.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by cgc on 17/12/29.
 */
public class AjaxUtils {

    private static Logger logger = LoggerFactory.getLogger(AjaxUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    private AjaxUtils() {
    }

    /**
     * 验证是否是ajax请求
     *
     * @param webRequest
     * @return
     */
    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    public static boolean isAjaxUploadRequest(WebRequest webRequest) {
        return webRequest.getParameter("ajaxUpload") != null;
    }

}
