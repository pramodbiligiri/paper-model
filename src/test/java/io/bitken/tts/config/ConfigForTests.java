package io.bitken.tts.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("io.bitken.tts.repo")
@EntityScan(basePackages = {"io.bitken.tts.model.entity"})
@ComponentScan(
    basePackages = {"io.bitken.tts"}
)
@EnableAutoConfiguration
public class ConfigForTests {
}
