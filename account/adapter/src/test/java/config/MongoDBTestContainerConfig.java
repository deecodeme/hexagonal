package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.testcontainers.containers.MongoDBContainer;

@Configuration
@EnableMongoRepositories(basePackages = "com.deecodeme.hexagonal.account.adapter.out.persistence")
public class MongoDBTestContainerConfig {
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.3")
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        System.setProperty("mongodb.container.port", String.valueOf(mongoDBContainer.getMappedPort(27017)));
    }
}

