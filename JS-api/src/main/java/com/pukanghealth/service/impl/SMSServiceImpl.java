package com.pukanghealth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pukanghealth.common.exception.RRException;
import com.pukanghealth.common.utils.HttpClientUtils;
import com.pukanghealth.common.utils.JsonUtils;
import com.pukanghealth.common.utils.XXTEAUtil;
import com.pukanghealth.service.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:LiWang
 * @Description:
 * @Date:Created in 18:02 2019/3/19
 * @Modified By:
 */
@Service
public class SMSServiceImpl implements SMSService{
    private Logger logger = LoggerFactory.getLogger(SMSServiceImpl.class);

    private static ObjectMapper objectMapper = new ObjectMapper();
    @Value("${pukang.captcha.url}")
    private String PKCaptchaServerURL;

    @Value("${pukang.sms.url}")
    private String PKSMSServerURL;

    @Value("${pukang.sms.token}")
    private String PKSMSServerVerifyTokenSecKey;

    /**
     * 通过手号获取验证码
     * @param mobile
     * @return
     */
    @Override
    public String getCaptchaByMobile(String mobile) {
        Map<String, String> param = new HashMap<>();
        param.put("userCode", mobile);
        String resStr = HttpClientUtils.doPost(PKCaptchaServerURL.concat("/captcha/doCaptcha"), param);
        if (resStr == null || "".equals(resStr)) {
            logger.error("SysCaptchaServiceImpl/getCaptchaByMobileOrCardCode,网络连接错误，请稍后重试,mobile=" + mobile +  ",resStr=" + resStr);
            throw new RRException("网络连接错误，请稍后重试");
        }
        JSONObject resJo = JSONObject.parseObject(resStr);

        if ((Integer)resJo.get("errorCode") == 0) {
            return resJo.getString("captcha");
        } else {
            logger.error("SysCaptchaServiceImpl/getCaptchaByMobileOrCardCode," + resJo.getString("errorMessage") + ",mobile=" + mobile +  ",resStr=" + resStr);
            throw new RRException(resJo.getString("errorMessage"));
        }
    }

    /**
     * 通过手机号校验验证码
     * @param
     * @param captcha
     * @return
     */
    @Override
    public boolean validateCaptcha(String tel, String captcha) {
        // declare
        // param check
        if (tel == null) {
            throw new RRException("参数错误,电话号码(userCode)不能为空");
        }

        if (captcha == null) {
            throw new RRException( "参数错误,验证码(captcha)不能为空");
        }

        // process
        try {
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("userCode", tel);
            paramMap.put("userInput", captcha);
            String responseString;
            responseString = HttpClientUtils.doPost(PKCaptchaServerURL.concat("/captcha/doVerify"), paramMap);
            Map<String, Object> resultMap = objectMapper.readValue(responseString, Map.class);
            logger.info("validateCaptcha(),resultMap = " + resultMap.toString());
            Integer errorCode = (Integer) resultMap.get("errorCode");
            String errorMessage = (String) resultMap.get("errorMessage");
            if (errorCode != 0) {
                throw new RRException(errorMessage);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    /**
     * 发送验证码
     * @param mobile
     * @param captcha
     */
    @Override
    public void sendCaptcha(String mobile, String captcha) {
        // param check
        if (mobile == null) {
            throw new RRException(1, "参数错误,手机号码(mobile)不能为空.");
        }

        if (captcha == null) {
            throw new RRException(2, "参数错误,短信验证码(sms)不能为空.");
        }

        // process
        try {
            Map<String, String> paramMap = new HashMap<String, String>();
            Map<String, Object> encodingMap = new HashMap<String, Object>();
            encodingMap.put("mobile", mobile);
            encodingMap.put("captcha", captcha);
            encodingMap.put("messageType", 0);
            String jsonStr = JsonUtils.toJson(encodingMap);
            String encodeJsonStr = XXTEAUtil.encrypt(jsonStr, PKSMSServerVerifyTokenSecKey);
            paramMap.put("encodedMessage", encodeJsonStr);
            String responseString = HttpClientUtils.doPost(PKSMSServerURL.concat("/sms/sendEncodedMessage"), paramMap);
            Map<String, Object> resultMap = JsonUtils.json2map(responseString);
            logger.info("sendCaptcha(),resultMap = " + resultMap.toString());
            Integer errorCode = (Integer) resultMap.get("errorCode");
            String errorMessage = (String) resultMap.get("errorMessage");
            if (errorCode != 0) {
                throw new RRException(errorCode, errorMessage);
            }
        } catch (RRException ce) {
            throw ce;
        } catch (Exception e) {
            throw new RRException(9, e.getLocalizedMessage());
        }
    }
    @Override
    public void sendResumeCaptcha(String mobile, String captcha, Integer sendMessageType) {
        // param check
        if (mobile == null) {
            throw new RRException(1, "参数错误,手机编号(mobile)不能为空.");
        }

        if (captcha == null) {
            throw new RRException(2, "参数错误,短信(sms)不能为空.");
        }

        // process
        try {
            Map<String, String> paramMap = new HashMap<String, String>();
            Map<String, Object> encodingMap = new HashMap<String, Object>();
            encodingMap.put("mobile", mobile);
            encodingMap.put("captcha", captcha);
            encodingMap.put("messageType", sendMessageType);
            String jsonStr = JsonUtils.toJson(encodingMap);
            String encodeJsonStr = XXTEAUtil.encrypt(jsonStr, PKSMSServerVerifyTokenSecKey);
            paramMap.put("encodedMessage", encodeJsonStr);
            String responseString = HttpClientUtils.doPost(PKSMSServerURL.concat("/sms/sendEncodedMessage"), paramMap);
            Map<String, Object> resultMap = JsonUtils.json2map(responseString);
            logger.info("sendCaptcha(),resultMap = " + resultMap.toString());
            Integer errorCode = (Integer) resultMap.get("errorCode");
            String errorMessage = (String) resultMap.get("errorMessage");
            if (errorCode != 0) {
                throw new RRException(errorCode, errorMessage);
            }
        } catch (RRException ce) {
            throw ce;
        } catch (Exception e) {
            throw new RRException(9, e.getLocalizedMessage());
        }
    }
}
