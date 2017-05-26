package soundsystem;

import org.springframework.stereotype.Component;

/**
 * Created by lyzzzz on 2016-11-09.
 */
@Component
public class SgtPeppers implements CompactDisc {
    private String title = "sgt. peppers";
    private String artist = "The beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
