package org.elink.mysql;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("org.elink.mysql")
@Import({MysqlConfiguration.class})
public class MySqlRootConfiguration {

}
