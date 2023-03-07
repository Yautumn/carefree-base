package com.yautumn.param.request.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageParam {

    @ApiModelProperty(example = "1",value = "页码数")
    private int currentPage;

    @ApiModelProperty(example = "20",value = "单页条数")
    private int pageSize;

    @ApiModelProperty(example = "20",value = "最大条数")
    private int totalCount;

}
