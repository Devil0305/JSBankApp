package com.pukanghealth;

import com.alibaba.fastjson.JSONObject;
import com.pukanghealth.ao.AppNetworkAO;
import io.swagger.annotations.ApiOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);
    //模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化
    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    //在测试开始前初始化工作
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    /**
     * 查询公告接口测试
     *
     * @throws Exception
     */
    @Test
    public void announcementControllerTest() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("announcementType", 0);
        params.put("page", "0");
        params.put("limit", "5");
        String s = JSONObject.toJSONString(params);
        System.out.println("================" + s);
        /*
        MvcResult result = mockMvc.perform(post("/api/appannouncement/list?" + params).content(JSONObject.toJSONString(params)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))//预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();//返回执行请求的结果

                */


        MvcResult result = mockMvc.perform(post("/api/appannouncement/list").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(params)))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果


/*
        MvcResult result = mockMvc.perform(post("/api/appannouncement/list").param("page", "0").param("limit", "5").param("announcementType", "0"))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

*/
        String p = "";
        Map<String, String[]> parameterMap = result.getRequest().getParameterMap();
        for (String[] value : parameterMap.values()) {
            p = Arrays.toString(value);

        }

        logger.debug("返回前端的内容：" + result.getResponse().getContentAsString() + "\n请求参数：" + p + "\n请求路径" + result.getRequest().getRequestURI());

    }

    @Test
    public void appNetworkSbControllerTest() throws Exception {

        AppNetworkAO appNetworkAO = new AppNetworkAO();
        //appNetworkAO.setNetworkAddress("上海");
        appNetworkAO.setNetworkName("维乐口腔南京东路医院");
       // appNetworkAO.setNetworkLatitude(BigDecimal.valueOf(1));
        //appNetworkAO.setNetworkLongitude(BigDecimal.valueOf(2));
        /*
        MvcResult result = mockMvc.perform(post("/api/appnetwork/list").param("page", "0").param("limit", "5").param("appNetworkAO", appNetworkAO.getNetworkAddress(),appNetworkAO.getNetworkName()))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果
        */
        Map<String, Object> params = new HashMap<>();
        params.put("appNetworkForm", appNetworkAO);
        params.put("page", "0");
        params.put("limit", "5");

        MvcResult result = mockMvc.perform(post("/api/appnetwork/list").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(params)))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果


        String p = "";
        Map<String, String[]> parameterMap = result.getRequest().getParameterMap();
        for (String[] value : parameterMap.values()) {
            p = Arrays.toString(value);
        }
        logger.debug("返回前端的内容：" + result.getResponse().getContentAsString() + "\n请求参数：" + p + "\n请求路径" + result.getRequest().getRequestURI());
    }

    @Test
    @ApiOperation("建行网点查询 测试")
    public void appNetworkJhControllerTest() throws Exception {
        AppNetworkAO appNetworkAO = new AppNetworkAO();
        appNetworkAO.setNetworkName("维乐口腔南京东路医院");
        appNetworkAO.setCurrPageNo(1);
        appNetworkAO.setPageSize(5);
       // appNetworkAO.setNetworkLatitude(BigDecimal.valueOf(1));
       // appNetworkAO.setNetworkLongitude(BigDecimal.valueOf(2));

       // Map<String, Object> params = new HashMap<>();
//        params.put("appNetworkForm", appNetworkVo);
//        params.put("networkName", "维乐口腔南京东路医院");
//        params.put("currPage", "0");
//        params.put("pageSize", "5");
//        String s = JSONObject.toJSONString(params);
        System.out.println("================" + JSONObject.toJSONString(appNetworkAO));

        MvcResult result = mockMvc.perform(post("/api/appnetwork/CCBList").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(appNetworkAO)))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果


        String p = "";
        Map<String, String[]> parameterMap = result.getRequest().getParameterMap();
        for (String[] value : parameterMap.values()) {
            p = Arrays.toString(value);
        }
        logger.debug("返回前端的内容：" + result.getResponse().getContentAsString() + "\n请求参数：" + p + "\n请求路径" + result.getRequest().getRequestURI());

    }


}
