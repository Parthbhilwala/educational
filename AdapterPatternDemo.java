// Target Interface
interface MediaPlayer {
    void play(String audioType);
}

// Adaptee
class OldMediaPlayer {
    public void playOldFormat(String oldFormat) {
        System.out.println("Playing " + oldFormat + " format.");
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    private OldMediaPlayer oldMediaPlayer;

    public MediaAdapter(OldMediaPlayer oldMediaPlayer) {
        this.oldMediaPlayer = oldMediaPlayer;
    }

    @Override
    public void play(String audioType) {
        oldMediaPlayer.playOldFormat(audioType);
    }
}

// Demo
public class AdapterPatternDemo {
    public static void main(String[] args) {
        MediaPlayer player = new MediaAdapter(new OldMediaPlayer());
        player.play("MP3");
    }
}

