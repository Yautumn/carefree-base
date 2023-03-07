package com.yautumn.service.market;

import com.yautumn.common.entity.market.MarketInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.market.MarketParam;
import org.springframework.stereotype.Service;

@Service
public interface MarketInfoService {

    /**
     * 新增市场信息
     * @param marketParam
     * @return
     */
    String insert(MarketParam marketParam);

    /**
     * 更新市场信息
     * @param marketParam
     * @return
     */
    String update(MarketParam marketParam);

    /**
     * 根据条件删除市场信息
     * @param marketId
     * @return
     */
    String delete(Integer marketId);

    /**
     * 根据id查询市场信息
     * @param marketId
     * @return
     */
    MarketInfo findById(Integer marketId);

    /**
     * 分页查询市场信息列表
     * @param pageParam
     * @return
     */
    PageBeanUtil findMarketAll(PageParam pageParam);

    /**
     * 统计市场信息总数
     * @return
     */
    int countMarket();
}
