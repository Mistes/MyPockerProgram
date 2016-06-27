package com.mistes.database;

import javax.swing.*;

/**
 * Created by Администратор on 27.06.2016.
 */
public class Pocker {
        private String name;
        private int count;
        private int gameNumber;
        private int id;
    public Pocker(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return   id
                + ", username = " + name
                + ", gamenumber = " + gameNumber
                + ", your score now is " + count;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public Pocker(String name, int count, int gameNumber, int id, JCheckBox checkBox, JTextField textField) {
        this.name = name;
        this.count = count;
        this.gameNumber = gameNumber;
        this.id = id;
        this.checkBox = checkBox;
        this.textField = textField;
    }

        JCheckBox checkBox;
        JTextField textField;

    }

