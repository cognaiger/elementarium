//package elementarium.utils.sound;
//
//
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//
//public class SoundUtil {
//    private SoundEffect selectSoundEffect;
//
//    private SoundEffect dropSoundEffect;
//
//    private SoundEffect canCombine;
//
//    private SoundEffect canNotCombine;
//
//    private SoundEffect newElement;
//
//    private SoundEffect backToListView;
//
//    public void playSelectSoundEffect() {
//        selectSoundEffect = new SoundEffect(new MediaPlayer(new Media(getClass().getResource("/sound/select.mp3").toExternalForm())));
//        selectSoundEffect.play();
//    }
//
//    public void playDropSoundEffect() {
//        dropSoundEffect = new SoundEffect(new MediaPlayer(new Media(getClass().getResource("/sound/drop.mp3").toExternalForm())));
//        dropSoundEffect.play();
//    }
//
//    public void playCanCombineSoundEffect() {
//        canCombine = new SoundEffect(new MediaPlayer(new Media(getClass().getResource("/sound/combine.mp3").toExternalForm())));
//        canCombine.play();
//    }
//
//    public void playCanNotCombineSoundEffect() {
//        canNotCombine = new SoundEffect(new MediaPlayer(new Media(getClass().getResource("/sound/cant_combine.mp3").toExternalForm())));
//        canNotCombine.play();
//    }
//
//    public void playNewElementSoundEffect() {
//        newElement = new SoundEffect(new MediaPlayer(new Media(getClass().getResource("/sound/new_element_pop_up.mp3").toExternalForm())));
//        newElement.play();
//    }
//
//    public void playBackToListViewSoundEffect() {
//        backToListView = new SoundEffect(new MediaPlayer(new Media(getClass().getResource("/sound/back_to_listview.mp3").toExternalForm())));
//        backToListView.play();
//    }
//
//
//}
