/**
 * Created by hao
 * 2024/7/11 17:29
 */
package com.yygh.hosp.service;

import com.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.IService;

public interface HospitalSetService extends IService<HospitalSet> {

    String getSignKey(String hoscode);
}
