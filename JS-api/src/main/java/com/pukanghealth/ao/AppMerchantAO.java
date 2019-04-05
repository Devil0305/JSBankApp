package com.pukanghealth.ao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pukanghealth.common.utils.PageUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Liukang
 * 查询合作商户
 */
@Data
@ApiModel(value = "查询合作商户")
public class AppMerchantAO  {
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "经度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "商户城市Id")
    private Integer merchantCityId;

    @ApiModelProperty(value = "商户省份Id")
    private Integer merchantProvinceId;

    @ApiModelProperty(value = "商户区域Id")
    private Integer merchantDistrictId;

    @ApiModelProperty(value = "筛选条件")
    private List<Integer> optionIds;

    @ApiModelProperty(value = "与商户的关联ID")
    private List<Integer> moduleIds;

    @ApiModelProperty(value = "当前条数")
    private int currPageNo;

    @ApiModelProperty(value = "页面容量")
    private int pageSize;

    @ApiModelProperty(value = "分页")
    private int limit;

    public int getLimit() {
        return this.currPageNo + this.pageSize;
    }
}
