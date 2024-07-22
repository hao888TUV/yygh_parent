package com.atguigu.yygh.common.anno;


import com.atguigu.yygh.common.config.Common2UtilAutoConfigration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(Common2UtilAutoConfigration.class)
public @interface EnableCommon2Util {
}
