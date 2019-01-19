package com.sunshine.basepro;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
//一般需要放在顶级目录下，以为他默认扫得是当前包或者子包
@SpringBootApplication
//集成fastjson  method one   需要继承   重写 configureMessageConverters
//method  two   @Bean进行注入  返回  HttpMessageConverters
//extends WebMvcConfigurerAdapter
@MapperScan("com.sunshine.*")//扫描：该包下相应的class,主要是MyBatis的持久化类.
public class BaseproApplication  {
//    /**
//     * 1、需要先定义一个convert转换消息的对象
//     * 2、添加fastjson的配置信息，是否需要进行格式化
//     * 3、将convert添加配置信息
//     * 4、将convert添加到converters中
//     * @param converters
//     */
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		super.configureMessageConverters(converters);
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig=new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastJsonHttpMessageConverter);
//	}

	/**
	 * //method  two   @Bean进行注入  返回  HttpMessageConverters
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters(){
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter=fastJsonHttpMessageConverter;
        return new HttpMessageConverters(converter);
	}

//  war包
//	extends SpringBootServletInitializer
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Application.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(BaseproApplication.class, args);
	}

}

