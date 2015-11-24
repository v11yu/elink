package org.elink.database.mysql;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("org.elink.database")
@Import({MysqlConfiguration.class})
public class MySqlRootConfiguration {

}
