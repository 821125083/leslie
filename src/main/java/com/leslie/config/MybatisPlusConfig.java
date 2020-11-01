package com.leslie.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    //自动填充

    //乐观锁
//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
//
//        return new OptimisticLockerInterceptor();
//    }
//    //分页
//    @Bean
//    public PaginationInterceptor paginationInterceptor(){
//        return new PaginationInterceptor();
//    }
//
//    //软删除
//    @Bean
//    public ISqlInjector iSqlInjector(){
//
//        return null;
//    }

    }


