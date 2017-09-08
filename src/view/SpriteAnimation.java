package view;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;

/**
 * Created by sasha on 11.07.2017.
 */
public class SpriteAnimation extends Transition {
    private static final Duration D_MILLIS = Duration.millis(1000); //частота вывода спрайта
    private static final int WITH_HUMAN = 63;
    private static final int HIGHT_HUMAN = 100;
    private static final int WITH_PK = 80;
    private static final int HIGHT_PK = 80;
    private static final int COUNT_ANIMATION = 2; //2 цикла анимации(цикл- 5 кадров)
    URL url_human = ClassLoader.getSystemClassLoader().getResource("view/img/human_sprite.jpg");
    URL url_pk = ClassLoader.getSystemClassLoader().getResource("view/img/pk_sprite.jpg");

    private final Image IMAGE_HUMAN = new Image(String.valueOf(url_human));
    private final Image IMAGE_PK = new Image(String.valueOf(url_pk));

    private static ImageView imageView;  //изо - нарезка спрайтов матрица
    private static final int count = 5;            //кол-во спрайтов
    private static int columns = 5;
    private static int offsetX = 0;     //нач смещение по X
    private static int offsetY = 0;     //нач смещ по Y, если матрица не в одну строку
    private static int width;       //ширина спрайта
    private static int hight;       //высота спрайта


    public SpriteAnimation( String imagePlayer, int width, int hight){
        this.width = width;
        this.hight = hight;
        Image image = (imagePlayer.equals("Human"))? IMAGE_HUMAN: IMAGE_PK;
        ImageView imageView = new ImageView(image);
        this.imageView = imageView;
        setCycleDuration(D_MILLIS);
    }

    @Override
    protected void interpolate(double fract) {
        final int index = Math.min((int) Math.floor(fract*count), count-1);
        final int x = (index % columns) * width + offsetX; //получаем координаты кратные ширине спрайта
        final int y = (index / columns) * hight + offsetY; //y=0 if imageview матрица спрайтов линейная
        imageView.setViewport(new Rectangle2D(x, y, width, hight)); //вытаскиваем нужный спрайт
    }


    public static void setSpriteAnimationToPane(String imagePlayer, Pane pane){
        Animation animation= null;

        if (imagePlayer.equals("Human")) {
            animation = new SpriteAnimation(imagePlayer, WITH_HUMAN, HIGHT_HUMAN);
        } else{
            animation = new SpriteAnimation(imagePlayer, WITH_PK, HIGHT_PK);
        }
        animation.setCycleCount(COUNT_ANIMATION);
        animation.play();
        pane.getChildren().addAll(imageView);
    }
}
