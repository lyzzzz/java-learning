import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lyzzzz on 2016-11-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:TestBean.xml")
public class TestScopeByXml {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testSingle() {
        UniqueThing bean = applicationContext.getBean(UniqueThing.class);
        UniqueThing bean2 = applicationContext.getBean(UniqueThing.class);
        Assert.assertEquals(bean, bean2);
    }
}
