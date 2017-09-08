package view;

import Comtrol.HController;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

/**
 * Created by sasha on 06.07.2017.
 */
public class FieldImage {
    private static HController hController;
    public static final int SIZE_FIELD_X = 3;
    public static final int SIZE_FIELD_Y = 3;


    public FieldImage(HController hController) {
        this.hController = hController;

    }

    public static void paintMovePlayer(Pane pane) {

        if (pane.getAccessibleText().equals("Human")) {
            pane.setStyle("-fx-background-image: url('view/img/krest.jpg');");
        } else {
            pane.setStyle("-fx-background-image: url('view/img/nolik.jpg');");
        }
    }

    public static void paintCountPlayer(Pane pane, int count) {
        pane.setStyle("-fx-background-image: url('view/img/" + count + ".gif');");
    }


    public static void paintStatusVictory(Pane pane, String status) {
        pane.setVisible(true);
        pane.setStyle("-fx-background-image: url('view/img/" + status + ".gif');");
    }

    public static void paintLineVictory(Pane pane, String position) {
        pane.setVisible(true);
        pane.setStyle("-fx-background-image: url('view/img/lineV" + position + ".jpg');");
    }


    public static Pane getCell(String numberCell) {
        switch (numberCell) {
            case "00":
                return hController.getCell_00();   //0
            case "10":
                return hController.getCell_10();  //3
            case "20":
                return hController.getCell_20();  //6
            case "01":
                return hController.getCell_01();   //1
            case "11":
                return hController.getCell_11();   //4
            case "21":
                return hController.getCell_21();  //7
            case "02":
                return hController.getCell_02();  //2
            case "12":
                return hController.getCell_12();  //5
            case "22":
                return hController.getCell_22();   //8

            default:
                break;
        }
        return null;
    }

    public static void clearField() {
        for (int i = 0; i < SIZE_FIELD_X; i++) {//00,01,02, 10,11,12, 20,21,22
            for (int y = 0; y < SIZE_FIELD_Y; y++) {
                getCell(((Integer) i).toString() + ((Integer) y).toString()).
                        setStyle("-fx-background-image: url('view/img/lineV.jpg');");
            }
        }
    }

    public static void setPaneAnimate(String player) {

        if (player.equals("Human")) {
            //запуск отдульного потока анимации, без потока exception checkFXUserThread
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    SpriteAnimation.setSpriteAnimationToPane(
                            "Human", hController.getImagePaneHuman());
                }
            });

        } else {
            Platform.runLater(new Runnable() { //запуск отдульного потока анимации
                @Override
                public void run() {
                    SpriteAnimation.setSpriteAnimationToPane(
                            "PK", hController.getImagePanePK());
                }
            });
        }
    }

    public static Pane getPcCount() {
        return hController.getPcCount();
    }

    public static Pane getHumanCount() {
        return hController.getHumanCount();
    }

    public static Pane getStatusVictory() {
        return hController.getStatusVictory();
    }

    public static Pane getLineVictory() { return hController.getLineVictory(); }
}
