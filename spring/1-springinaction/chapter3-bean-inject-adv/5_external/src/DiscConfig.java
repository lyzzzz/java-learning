import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by lyzzzz on 2016-11-12.
 */
@Configuration
@PropertySource("classpath:disc.properties")
public class DiscConfig {
    @Autowired
    private Environment environment;
    @Bean
    public Disc disc() {
        return new Disc(environment.getProperty("disc.artist"),
        environment.getProperty("disc.title"));
    }
}
