package com.example.demo.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
	@Override
    public Executor getAsyncExecutor() {
        System.out.println("DemoApp.getAsyncExecutor");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(10);
	    executor.setQueueCapacity(50);
	    executor.setThreadNamePrefix("DemoAsync-");
        executor.initialize();

        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects)-> { 
        	System.out.println("Exception Caught in Thread - " + Thread.currentThread().getName());
        	throwable.printStackTrace();
        };
    }
}
