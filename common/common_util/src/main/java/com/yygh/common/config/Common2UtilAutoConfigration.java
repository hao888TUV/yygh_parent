/**
 * Created by hao
 * 2024/7/14 21:22
 */
package com.yygh.common.config;

import com.yygh.common.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GlobalExceptionHandler.class)
public class Common2UtilAutoConfigration {
}
