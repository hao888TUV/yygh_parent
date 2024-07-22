/**
 * Created by hao
 * 2024/7/11 17:48
 */
package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.result.ResultCodeEnum;
import com.atguigu.yygh.common.utils.MD5;
import com.atguigu.yygh.hosp.mapper.HospitalSetMapper;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
@CrossOrigin
//@CrossOrigin(allowCredentials = "true",origins = "*")
@Slf4j
@Api(tags = "医院设置控制器")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation("获取所有医院设置")
    @GetMapping("/findAll")
    public Result<List<HospitalSet>> findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation("逻辑删除医院设置")
    @DeleteMapping("/{id}")
    public Result<Object> removeHospSet(@ApiParam(value = "医院id", example = "1") @PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();

    }


    // 3 条件查询带分页
    @ApiOperation("条件查询带分页")
    @PostMapping("/findPageHospSet/{current}/{limit}")
    public Result<Page<HospitalSet>> findPageHospSet(@PathVariable Long current, @PathVariable Long limit,
                                                     @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        Page<HospitalSet> page = new Page<>(current, limit);
        LambdaQueryWrapper<HospitalSet> queryWrapper = new LambdaQueryWrapper<>();
        if (hospitalSetQueryVo !=null) {
            queryWrapper.like(hospitalSetQueryVo.getHosname() != null, HospitalSet::getHosname, hospitalSetQueryVo.getHosname())
                    .eq(hospitalSetQueryVo.getHoscode() != null, HospitalSet::getHoscode, hospitalSetQueryVo.getHoscode());
        }
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, queryWrapper);
        List<HospitalSet> records = hospitalSetPage.getRecords();
        log.info("------------------------------");
        log.info(String.valueOf(records));
        return Result.ok(hospitalSetPage);
    }

    // 4 添加医院设置
    @ApiOperation("添加医院设置")
    @PostMapping("/saveHospitalSet")
    public Result<Object> saveHospSet(@RequestBody HospitalSet hospitalSet) {
        // 设置状态 1 使用 0 不能使用
        hospitalSet.setStatus(0);
        // 签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(String.valueOf(System.currentTimeMillis() + random.nextInt(100))));
        // 调用service
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return Result.ok(1);
        }
        return Result.fail();
    }

    // 5 根据id获取医院设置
    @ApiOperation("根据id获取医院设置")
    @GetMapping("/getHospSet/{id}")
    public Result<HospitalSet> getHospSet(@PathVariable Long id) {
//        try {
//            int a = 10 / 0;
//        }catch (ArithmeticException e){
//            throw new YyghException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
//        }

        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);

    }


    // 6 修改医院设置
    @ApiOperation("修改医院设置")
    @PutMapping("/updateHospitalSet")
    public Result<Object> updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }


    // 7 批量删除医院设置
    @ApiOperation("批量删除医院设置")
    @DeleteMapping("/batchRemove")
    public Result<Object> batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        boolean flag = hospitalSetService.removeByIds(idList);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }

    //8. 医院设置锁定和解锁
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result<Object> lockHospitalSet(@PathVariable Long id,
                                          @PathVariable Integer status) {
        // 根据id查询医院设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        // 设置状态
        hospitalSet.setStatus(status);
        // 调用service方法,真正更新值
        boolean b = hospitalSetService.updateById(hospitalSet);

        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }

    // 9 发送签名密钥
    @PostMapping("/sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        // TODO 发送短信
        return Result.ok();
    }


}
