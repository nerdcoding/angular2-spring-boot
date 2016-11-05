/*
 * EmbeddedMongoConfiguration.java
 *
 * Copyright (c) 2016, Tobias Koltsch. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/lgpl.txt>.
 */

package org.nerdcoding.sample.angular2.server.domain;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.nerdcoding.sample.angular2.server.domain.repository.RepositoryScanMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Spring Data configuration for an embedded MongoDB. Holds the all information to access the embedded database during
 * JUnit tests.
 *
 * @author Tobias Koltsch
 * @version 1.0.0
 */
@Configuration
@ComponentScan(basePackageClasses = RepositoryScanMarker.class)
@EnableMongoRepositories
public class EmbeddedMongoConfiguration extends AbstractMongoConfiguration {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private static final String EMBEDDED_MONGO_HOST = "127.0.0.1";
    private static final String TEST_DATABASE_NAME = "test";

    private int serverPort;

    @Override
    @Bean(destroyMethod = "close")
    @DependsOn("mongod")
    public Mongo mongo() throws IOException {
        return new MongoClient(EMBEDDED_MONGO_HOST, serverPort);
    }

    @Override
    protected String getDatabaseName() {
        return TEST_DATABASE_NAME;
    }

    @Bean(destroyMethod = "stop")
    @DependsOn("mongodExecutable")
    public MongodProcess mongod() throws IOException {
        return mongodExecutable().start();
    }

    @Bean(destroyMethod = "stop")
    public MongodExecutable mongodExecutable() throws IOException {
        return MongodStarter.getDefaultInstance().prepare(mongodConfig());
    }

    @Bean
    public IMongodConfig mongodConfig() throws IOException {
        serverPort = findFreePort();
        return new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(serverPort, Network.localhostIsIPv6()))
                .build();
    }

    private int findFreePort() {
        try (final ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            return socket.getLocalPort();
        } catch (final IOException ioe) {
            throw new IllegalStateException("Could not find a free TCP/IP port for embedded Mongo DB");
        }
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
