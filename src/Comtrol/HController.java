package Comtrol;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Field;
import model.HumanPlayer;
import model.Main;
import model.PKPlayer;
import view.FieldImage;


public class HController {
    public static int flagVictory = 0;
    @FXML
    private Pane statusVictory;
    @FXML
    private Pane lineVictory;
    @FXML
    private Pane imagePaneHuman;
    @FXML
    private Pane imagePanePK;
    @FXML
    private Pane humanCount;
    @FXML
    private Pane pcCount;
    @FXML
    private Pane cell_00, cell_10, cell_20,
            cell_01, cell_11, cell_21,
            cell_02, cell_12, cell_22;

    @FXML
    private void initialize() {
        new FieldImage(this);
    }

    public void onMouseCliked(MouseEvent mouseEvent) {
        Pane paneCell = (Pane) mouseEvent.getSource();
        String[] positionCliked = paneCell.getId().split("_", 2);
        paneCell.setAccessibleText("Human");

       if (Field.checkPositionEmpty(positionCliked[1]) &&
               (HumanPlayer.getFlagVictory() == 0) && (PKPlayer.getFlagVictory() == 0)
               ) {//защита чтоб не показывал

            FieldImage.paintMovePlayer(paneCell);
            Field.setPosition("Human", positionCliked[1]);             //маркируем visitRules

            if (Field.checkVictoryHuman()) {                  //pobeda?
                flagVictory = 1;
            }
            notifyHumanAndPK();                          //будим потоки
      }
    }

    @FXML
    public void onRestartAction(MouseEvent mouseEvent) {
        System.out.println("Restart");
        FieldImage.clearField();
        lineVictory.setVisible(false);
        statusVictory.setVisible(false);
        HumanPlayer.setFlagVictory(0);
        PKPlayer.setFlagVictory(0);
        Main.activationGame();
    }

    //------------------------synxr-------------------
    public synchronized void notifyHumanAndPK() {
        if (flagVictory == 0) {                   //когда победа Human тогда PK не будим
            synchronized (Main.pkPlayer) {
                Main.pkPlayer.notify();           //пробуждаем поток
            }
        }

        synchronized (Main.humanPlayer) {
            Main.humanPlayer.notify();
        }
    }
    //------------------------------------------------

    public Pane getHumanCount() {
        return humanCount;
    }

    public Pane getPcCount() {
        return pcCount;
    }

    public Pane getCell_00() {
        return cell_00;
    }

    public Pane getCell_10() {
        return cell_10;
    }

    public Pane getCell_20() {
        return cell_20;
    }

    public Pane getCell_01() {
        return cell_01;
    }

    public Pane getCell_11() {
        return cell_11;
    }

    public Pane getCell_21() {
        return cell_21;
    }

    public Pane getCell_02() {
        return cell_02;
    }

    public Pane getCell_12() {
        return cell_12;
    }

    public Pane getCell_22() {
        return cell_22;
    }

    public Pane getStatusVictory() {
        return statusVictory;
    }

    public Pane getLineVictory() {
        return lineVictory;
    }

    public Pane getImagePaneHuman() { return imagePaneHuman; }

    public Pane getImagePanePK() { return imagePanePK; }

}
