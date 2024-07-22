/**
 * Created by hao
 * 2024/7/11 23:41
 */
package com.atguigu.yygh.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(Swagger2Config.class)
@Configuration
public class CommonServiceUtilAutoConfiguration {
}
