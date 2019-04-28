package com.qfedu.cloudapi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: 冯志立
 * @Date: 2019/3/27 11:55
 */
@FeignClient(value = "fenguserprovider") //提供者名称
public interface UserService {
}
