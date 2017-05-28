import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by lyzzzz on 2016-11-12.
 */
public class MagicBeanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        int r = (int) (100 * Math.random());
        System.out.println(r);
        if(r > 50) {
            return true;
        } else {
            return false;
        }
    }
}
