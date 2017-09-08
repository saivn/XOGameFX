package model;

//import view.FieldImage;
//import view.VLine;


//import java.awt.*;

import javafx.scene.layout.Pane;
import view.FieldImage;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 21.03.17
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class PKPlayer extends Thread {
    private static int count = 0;


    private static int flagVictory = 0;
    Field field;


    public PKPlayer(Field field) {
        this.field = field;
    }


    public synchronized void run() {
        String movePos;
        int NumberMove = (Main.PervHod.equals("Human")) ? 4 : 5;
        flagVictory = 0;

        for (int i = 0; i < NumberMove; i++) {
            movePos = Field.calculateMoveVictoryPK(); //расчет выигрыша за один ход
            System.out.println("pk move " + movePos);
            if (movePos.equals("-1")) {                //расчет дал не выигрыш за один ход ?
                movePos = Field.setPositionRandomMovePK();       //ставим случайно
            } else {
                flagVictory = 1;                          //выигрыш

            }
            System.out.println("pos " + movePos);
            Pane paneMove = FieldImage.getCell(movePos);
            paneMove.setAccessibleText("PK");
            FieldImage.paintMovePlayer(paneMove);         //нарисовал

            if (flagVictory == 1) break;
            waitTread();                               //заснули, ждем хода соперника
        }
        if (flagVictory == 1) {
            count++;
            Pane paneCount = FieldImage.getPcCount();
            FieldImage.paintCountPlayer(paneCount, count);    //счет

            Pane paneVictory = FieldImage.getStatusVictory();
            FieldImage.paintStatusVictory(paneVictory, "pkv");    //счет

            Integer numberLineVictory = Field.getPositionFullVictory("PK"); //узнали какие ячейки выиграли
            Pane paneLineVictory = FieldImage.getLineVictory();
            FieldImage.paintLineVictory(paneLineVictory, numberLineVictory.toString()); //линия победы
            FieldImage.setPaneAnimate("PK");


            //FieldImage.setMovingPicture("PK", 260);
        } else {
            System.out.println("draw");
            Pane paneVictory = FieldImage.getStatusVictory();
            FieldImage.paintStatusVictory(paneVictory, "draw");    //счет
        }

    }

    synchronized void waitTread() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public static int getFlagVictory() {
        return flagVictory;
    }
    public static void setFlagVictory(int flagVictory) {
        PKPlayer.flagVictory = flagVictory;
    }

}

