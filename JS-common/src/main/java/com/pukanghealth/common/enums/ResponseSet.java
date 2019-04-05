package com.pukanghealth.common.enums;

/**
 * @Author:LiWang
 * @Description:
 * @Date:Created in 10:33 2019/3/20
 * @Modified By:
 */
public class ResponseSet {
    public interface ResponseInfo{
        Integer getResponseCode();
        String getResponseMessage();
    }
    public enum  ResponseEnum implements ResponseInfo {


        SUCCESS(0,"请求成功!"),
        ERROR_DEFEAT(9999,"请求失败!");

        private Integer responseCode;

        private String responseMessage;

        ResponseEnum(Integer responseCode, String responseMessage) {
            this.responseCode = responseCode;
            this.responseMessage = responseMessage;
        }
        @Override
        public Integer getResponseCode() {
            return responseCode;
        }

        @Override
        public String getResponseMessage() {
            return responseMessage;
        }
    }
}
