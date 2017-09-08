package model;

//import control.HController;
//import view.FieldImage;
//import view.VLine;

import Comtrol.HController;
import javafx.scene.layout.Pane;
import view.FieldImage;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 21.03.17
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */

public class HumanPlayer extends Thread {
    private static int count = 0;


    private static int flagVictory = 0;
    Field field;


    public HumanPlayer(Field field) {
        this.field = field;
    }

    public synchronized void run() {
        int NumberMove = (Main.PervHod.equals("Human")) ? 5 : 4;

        for (int i = 0; i < NumberMove; i++) {

            waitThread();                                   //заснул, разбудит pk
            if (HController.flagVictory == 1) {
                flagVictory = 1;
                HController.flagVictory = 0;
                break;
            }
        }

        if (flagVictory == 1) {
            count++;
            Pane paneCount = FieldImage.getHumanCount();
            FieldImage.paintCountPlayer(paneCount, count);    //счет

            Pane paneVictory = FieldImage.getStatusVictory();
            FieldImage.paintStatusVictory(paneVictory, "humanv");    //счет

            Integer numberLineVictory = Field.getPositionFullVictory("Human"); //узнали какие ячейки выиграли
            Pane paneLineVictory = FieldImage.getLineVictory();
            FieldImage.paintLineVictory(paneLineVictory, numberLineVictory.toString());
            FieldImage.setPaneAnimate("Human");
        } else {
            System.out.println("draw");

            Pane paneVictory = FieldImage.getStatusVictory();
            FieldImage.paintStatusVictory(paneVictory, "draw");    //счет
        }
    }

    synchronized void waitThread() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public static int getFlagVictory() {
        return flagVictory;
    }
    public static void setFlagVictory(int flagVictory) {
        HumanPlayer.flagVictory = flagVictory;
    }

}


