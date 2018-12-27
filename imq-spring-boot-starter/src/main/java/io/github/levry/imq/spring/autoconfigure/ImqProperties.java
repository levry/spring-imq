package io.github.levry.imq.spring.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for OpenMQ
 *
 * @author levry
 */
@Data
@ConfigurationProperties("imq")
public class ImqProperties {

    /**
     * Hostname of OpenMQ broker
     */
    private String host;

    /**
     * Port number of OpenMQ broker
     */
    private Integer port = 7676;
    private String username;
    private String password;

}
