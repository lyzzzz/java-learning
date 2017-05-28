/**
 * Created by lyzzzz on 2016-11-12.
 */
public class Disc {
    private String artist;
    private String title;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Disc() {
    }

    public Disc(String artist, String title) {

        this.artist = artist;
        this.title = title;
    }
}
