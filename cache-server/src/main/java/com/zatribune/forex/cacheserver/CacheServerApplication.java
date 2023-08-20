package com.zatribune.forex.cacheserver;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.ConfigurableCacheFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CacheServerApplication {

    public static void main(String[] args) {
        /*
         * Usually when testing Coherence locally, where the whole Coherence cluster runs on e.g. a laptop,
         * restricting the cluster formation to loopback (127.0.0.1) is often useful. The same properties are also
         * set by the Maven pom.xml for the JUnit tests.
         */

        System.setProperty("coherence.ttl", "0");
        System.setProperty("java.net.preferIPv4Stack", "true");

        SpringApplication.run(CacheServerApplication.class, args);
    }

    /**
     * Spring bean that ensures that the Coherence instance is available.
     * @return the ConfigurableCacheFactory
     */
    @Bean(destroyMethod = "dispose")
    public ConfigurableCacheFactory coherenceServer() {
        CacheFactory.ensureCluster();

        final ConfigurableCacheFactory factory = CacheFactory.getCacheFactoryBuilder()
                .getConfigurableCacheFactory("coherence-cache-config.xml", getClass().getClassLoader());

        factory.activate();
        return factory;
    }

}
