/**  
* <p>Title: AjaxWebConfig.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年8月29日  
* @version 1.0  
*/
package com.zhejiang.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhanghaow
 *<p>Title: AjaxWebConfig</p> 
 *
 *<p>Description: 配置跨域请求</p> 
 *
 * 2018年8月29日 下午4:15:35
 */
@Configuration
public class AjaxWebConfig implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(5000);
    }
}
