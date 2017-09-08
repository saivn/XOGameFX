package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 21.03.17
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
public class Field {
    public static final int SIZE_X = 3;
    public static final int SIZE_Y = 3;
    public static final String NULL_CELL = "-1";
    public static ArrayList<TreeMap<String, String>> VisitingRules;

    public Field() {
        initVisitingRules();
    }

    public void initVisitingRules() {
        VisitingRules = new ArrayList<TreeMap<String, String>>();
        for (int i = 0; i < SIZE_X; i++) {
            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            for (int j = 0; j < SIZE_Y; j++) {
                String key = String.valueOf(i) + String.valueOf(j);
                treeMap.put(key, NULL_CELL);
            }
            VisitingRules.add(treeMap); //1-3 - 3 вертикали key 00 01 02 10 11 12 20 21 22
        }

        for (int i = 0; i < SIZE_X; i++) {
            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            for (int j = 0; j < SIZE_Y; j++) {
                String key = String.valueOf(j) + String.valueOf(i);
                treeMap.put(key, NULL_CELL);
            }
            VisitingRules.add(treeMap);    //4-6 - 3 горизонтали
        }

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("02", NULL_CELL);
        treeMap.put("11", NULL_CELL);
        treeMap.put("20", NULL_CELL);
        VisitingRules.add(treeMap);        //7 диагональ

        treeMap = new TreeMap<String, String>();
        treeMap.put("00", NULL_CELL);
        treeMap.put("11", NULL_CELL);
        treeMap.put("22", NULL_CELL);
        VisitingRules.add(treeMap);        //8 диагональ
    }

    public static void showVisiting() {
        for (TreeMap<String, String> treeMap : VisitingRules) { //7 shtuk
            for (Map.Entry entry : treeMap.entrySet()) {      //po 3 v kagdoy
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    public static void setPosition(String value, String key) {
        for (TreeMap<String, String> treeMap : VisitingRules) {
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                }
            }
        }
    }

    public static boolean checkPositionEmpty(String key) {
        boolean flagCheck = true;
        for (TreeMap<String, String> treeMap : VisitingRules) {
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                if (entry.getKey().equals(key)) {
                    if (!entry.getValue().equals(NULL_CELL)) {
                        return flagCheck = false;
                    }
                }
            }
        }
        return flagCheck;
    }

    public static int getPositionFullVictory(String value) {
        int pos = 0;
        for (TreeMap<String, String> treeMap : Field.VisitingRules) {
            int sum = 0;
            pos++;
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                if (entry.getValue().equals(value)) {
                    sum++;
                    if (sum == 3) {
                        String str = treeMap.firstKey();
                        return pos - 1;
                    }
                }
            }
        }
        return 0;
    }

    // --------------- moves -------------------
    public static boolean checkVictoryHuman() {
        int checkSumVictory;
        for (TreeMap<String, String> treeMap : VisitingRules) { //7 shtuk
            checkSumVictory = 0;
            for (Map.Entry obj : treeMap.entrySet()) {      //po 3 v kagdoy
                if (obj.getValue().equals("Human")) checkSumVictory++;
            }

            if (checkSumVictory == Field.SIZE_X) {//if dva uge est
                return true;
            }
        }
        return false;
    }

    public static String calculateMoveVictoryPK() {
        int checkSumVictory = 0;
        int checkSumNull = 0;
        String keyMovePosition = "-1";

        for (TreeMap<String, String> treeMap : VisitingRules) { //8 shtuk
            checkSumVictory = 0;
            checkSumNull = 0;
            for (Map.Entry obj : treeMap.entrySet()) {      //po 3 v kagdoy
                if (obj.getValue().equals("PK")) checkSumVictory++;
                if (obj.getValue().equals(Field.NULL_CELL)) checkSumNull++;
            }

            if ((checkSumVictory == Field.SIZE_X - 1) && (checkSumNull > 0)) {//if dva uge est
                for (Map.Entry entry : treeMap.entrySet()) { //to postavim v tretiy
                    if (entry.getValue().equals(Field.NULL_CELL)) {
                        keyMovePosition = String.valueOf(entry.getKey());
                        entry.setValue("PK");                //"-1" -> "PK" setVisitingRules
                    }
                }
                return keyMovePosition;
            }
        }
        return "-1";
    }

    public static String setPositionRandomMovePK() {
        ArrayList<String> tempCells = new ArrayList<String>();  //temp array for "-1"
        int countZero = 0;
        String randomPosition = "-1";
        for (TreeMap<String, String> treeMap : VisitingRules) {
            for (Map.Entry entry : treeMap.entrySet()) {
                if (entry.getValue().equals("-1")) {
                    tempCells.add((String) entry.getKey());  //формируем только где -1
                    countZero++;                             //из них потом выберем случайно
                }
            }
        }
        if (countZero > 0) {
            int random;
            random = new Random().nextInt(tempCells.size());
            randomPosition = tempCells.get(random);

            setPosition("PK", randomPosition);
        }
        return randomPosition;
    }

//----------------- end moves---------------

}
