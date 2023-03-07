package com.yautumn.remote.fallback;

import com.yautumn.common.utils.ResultUtil;
import com.yautumn.remote.IMarketInfoService;
import org.springframework.stereotype.Component;

@Component
public class ServiceFallback implements IMarketInfoService {
    @Override
    public ResultUtil findById(Integer marketId) {
        return ResultUtil.error("服务调用失败");
    }
}
