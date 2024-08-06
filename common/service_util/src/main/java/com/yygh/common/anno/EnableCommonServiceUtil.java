package com.yygh.common.anno;

import com.yygh.common.config.CommonServiceUtilAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CommonServiceUtilAutoConfiguration.class)
public @interface EnableCommonServiceUtil {

}
