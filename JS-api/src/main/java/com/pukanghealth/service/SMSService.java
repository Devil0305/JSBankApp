package com.pukanghealth.service;

/**
 * @Author:LiWang
 * @Description:
 * @Date:Created in 18:00 2019/3/19
 * @Modified By:
 */
public interface SMSService {
    String getCaptchaByMobile(String mobile) throws Exception;

    boolean validateCaptcha(String userCode, String captcha) throws Exception;

    void sendCaptcha(String mobile, String captcha) throws Exception;

    void sendResumeCaptcha(String mobile, String captcha, Integer sendMessageType);

}
