package com.wonder.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.wonder.core.cache.thread.PriceCacheThread;

@Configuration
public class BeanMainConfig {

	@Scope("prototype")
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public PriceCacheThread priceCacheThread() {
		return new PriceCacheThread();
	}
}
