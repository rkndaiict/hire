package com.hire.ns.util;

import java.util.Map;

/**
 * The Class VelocityUtil.
 */
public final class VelocityUtil {

    /**
     * Instantiates a new velocity util.
     */
    private VelocityUtil() {

    }

    /**
     * Gets the processed template.
     * 
     * @param template the template
     * @param params the params
     * @return the processed template
     * @throws Exception the exception
     */
    public static String getProcessedTemplate(byte[] template, Map<String, Object> params) throws Exception {
        String templateString = new String(template, "UTF-8");
        templateString = FileUtil.velocityEval(templateString, params,null);
        return templateString;
    }
}
