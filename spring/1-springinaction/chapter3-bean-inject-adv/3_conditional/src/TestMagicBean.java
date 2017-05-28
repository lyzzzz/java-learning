import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lyzzzz on 2016-11-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MagicBeanConfig.class)
public class TestMagicBean {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        MagicBean bean = applicationContext.getBean(MagicBean.class);
        Assert.assertNotNull(bean);
    }
}
