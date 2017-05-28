import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lyzzzz on 2016-11-12.
 */
public class TestExternal {
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = DiscConfig.class)
    public static class TestExternalByJava {
        @Autowired
        private Disc disc;

        @Test
        public void test() {
            System.out.println(disc.getArtist());
            System.out.println(disc.getTitle());
        }
    }
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = "classpath:Disc.xml")
    public static class TestExternalByXml {
        @Autowired
        private Disc disc;

        @Test
        public void test() {
            System.out.println(disc.getArtist());
            System.out.println(disc.getTitle());
        }
    }
}
