/**
 * Created by hao
 * 2024/7/11 23:06
 */
package com.yygh.hosp.config;

import com.yygh.cmn.client.DictFeignClientService;
import com.yygh.common.anno.EnableCommon2Util;
import com.yygh.common.anno.EnableCommonServiceUtil;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;


@EnableCommon2Util
@EnableCommonServiceUtil
@Configuration
@EnableDiscoveryClient
@EnableFeignClients(clients = DictFeignClientService.class)
public class HospConfig {
}
