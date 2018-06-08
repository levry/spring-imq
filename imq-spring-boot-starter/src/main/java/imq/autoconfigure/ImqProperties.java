package imq.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for OpenMQ
 * @author levry
 */
@Data
@ConfigurationProperties("imq")
public class ImqProperties {

    private String host;
    private Integer port = 7676;
    private String username;
    private String password;

}
