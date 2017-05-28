import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lyzzzz on 2016-11-12.
 */
@Configuration
public class MagicBeanConfig {
    @Bean
    @Conditional(MagicBeanCondition.class)
    public MagicBean magicBean() {
        return new MagicBean();
    }
}
