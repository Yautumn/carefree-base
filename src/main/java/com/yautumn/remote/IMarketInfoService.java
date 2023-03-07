package com.yautumn.remote;

import com.yautumn.common.utils.ResultUtil;
import com.yautumn.remote.fallback.ServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "market",fallback = ServiceFallback.class)
public interface IMarketInfoService {

    @RequestMapping(value = "find/id",method = RequestMethod.GET)
    ResultUtil findById(Integer marketId);

}
