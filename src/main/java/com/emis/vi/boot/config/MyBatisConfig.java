package com.emis.vi.boot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"com.emis.vi.boot.mbg.mapper","com.emis.vi.boot.dao"})
public class MyBatisConfig {
}
