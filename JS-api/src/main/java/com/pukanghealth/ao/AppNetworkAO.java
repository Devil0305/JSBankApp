package com.pukanghealth.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 查询网点
 *
 * @author liukang
 */
@Data
@ApiModel(value = "查询网点")
public class AppNetworkAO {
    @ApiModelProperty(value = "网点名称")
    private String networkName;

    @ApiModelProperty(value = "经度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "网点城市Id")
    private Integer networkCityId;

    @ApiModelProperty(value = "网点省份Id")
    private Integer networkProvinceId;

    @ApiModelProperty(value = "网点区域Id")
    private Integer networkDistrictId;

    @ApiModelProperty(value = "筛选条件")
    private List<Integer> optionIds;

    @ApiModelProperty(value = "与商户的关联ID")
    private List<Integer> moduleIds;

    @ApiModelProperty(value = "当前条数")
    private int currPageNo;

    @ApiModelProperty(value = "页面容量")
    private int pageSize;

    private int limit;

    public int getLimit() {
        return this.currPageNo + this.pageSize;
    }
}
