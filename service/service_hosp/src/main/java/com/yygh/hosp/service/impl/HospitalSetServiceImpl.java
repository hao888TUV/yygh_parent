/**
 * Created by hao
 * 2024/7/11 17:30
 */
package com.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yygh.hosp.mapper.HospitalSetMapper;
import com.yygh.hosp.service.HospitalSetService;
import com.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HospitalSetServiceImpl  extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {


    @Override
    @Cacheable(value = "signKey",keyGenerator = "keyGenerator")
    public String getSignKey(String hoscode) {
        LambdaQueryWrapper<HospitalSet> hospitalSetLambdaQueryWrapper = new LambdaQueryWrapper<>();
        hospitalSetLambdaQueryWrapper.eq(HospitalSet::getHoscode,hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(hospitalSetLambdaQueryWrapper);
        String signKey = hospitalSet.getSignKey();
        return signKey;
    }
}
