

package com.pukanghealth.common.validator;

import com.pukanghealth.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author wangli
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(500,message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(500,message);
        }
    }
}
