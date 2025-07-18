package tech.sneakybuzz.artifact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableAsync
@Configuration
public class AsyncConfig {
  @Bean
  public Executor taskExecutor() {
    return Executors.newCachedThreadPool();
  }
}