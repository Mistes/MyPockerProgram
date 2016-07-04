package com.mistes.database;
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MainHere {
    private static int numberGame;
    static ArrayList<Pocker> pockerUsers = new ArrayList<>();

    static ArrayList<JLabel> jLabels = new ArrayList<>();

    public static void main(String[] args) {
        MainHere king = new MainHere();
        king.queryWorker();
        king.go();
    }

    public void queryWorker() {
        DBWorker worker = new DBWorker();
        String query = "select * from pocker";
        String query2 = "select * from gameis";
        try {

            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { //loading class
                Pocker pocker = new Pocker();
                JTextField textField = new JTextField(4);
                JCheckBox checkBox = new JCheckBox();
                JButton button = new JButton("impl");
                pocker.setId(resultSet.getInt(1));
                pocker.setName(resultSet.getString(3));
                pocker.setCount(resultSet.getInt(2));
                pocker.setGameNumber(resultSet.getInt(4));
                checkBox.setSelected(resultSet.getBoolean(5));
                pocker.setCheckBox(checkBox);
                pocker.setTextField(textField);
                pocker.setButton(button);
                pockerUsers.add(pocker);
                //  pocker.setCheckBox(new JCheckBox().setSelected(resultSet.getBoolean(5)));
                //  s = pocker.toString();
            }
            ResultSet resultSet2 = statement.executeQuery(query2);
            while (resultSet2.next()) {
                numberGame = resultSet2.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void go() {
        JFrame theFrame = new JFrame("Pocker Helper");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel downPanel = new JPanel();
        // mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // JTextArea testing = new JTextArea(15,50);
        // testing.setLineWrap(true);
        // testing.setWrapStyleWord(true);
        // testing.setEditable(true);


        JLabel label = new JLabel("Add new player");

        JLabel countLabel = new JLabel("Game number is " + numberGame); //here

        //  JScrollPane aScroller = new JScrollPane(testing);
        // aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton startGame = new JButton("Start this game");
        startGame.addActionListener(e -> {
            countLabel.setText(startingNewGame());

        });

        JTextField newPlayer = new JTextField(20);
        JButton sendButton = new JButton("add");      //Adding new player
        sendButton.addActionListener(e -> {
            addingNewPlayer(mainPanel, newPlayer);
        });

        topPanel.add(startGame);
        topPanel.add(countLabel);

        labelsLoad(mainPanel);       //zagr middle

        // mainPanel.add(aScroller);
        downPanel.add(label);
        downPanel.add(newPlayer);
        downPanel.add(sendButton);

        theFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        theFrame.getContentPane().add(BorderLayout.NORTH, topPanel);
        theFrame.getContentPane().add(BorderLayout.SOUTH, downPanel);

        theFrame.setSize(600, 400);
        theFrame.setVisible(true);
    }

    public static void labelsLoad(JPanel mainpanel) {
        for (int i = 0; i < pockerUsers.size(); i++) {
            JLabel c = new JLabel();
            c.setText(pockerUsers.get(i).toString());
            jLabels.add(c);
            mainpanel.add(pockerUsers.get(i).getCheckBox());
            mainpanel.add(jLabels.get(i));
            mainpanel.add(pockerUsers.get(i).getTextField());
            mainpanel.add(pockerUsers.get(i).getButton());
            buttonGo(pockerUsers.get(i), jLabels.get(i));


        }
    }

    public static String startingNewGame() {
        try {

            DBWorker worker = new DBWorker();
            Statement statement = worker.getConnection().createStatement();
            statement.executeUpdate("UPDATE gameis SET gameis = gameis+1");


            for (int i = 0; i < pockerUsers.size(); i++) {
                if (pockerUsers.get(i).getCheckBox().isSelected()) {
                    int tempcount = (pockerUsers.get(i).getCount()) - 300;
                    pockerUsers.get(i).setCount(tempcount);
                    pockerUsers.get(i).setGameNumber(pockerUsers.get(i).getGameNumber() + 1);
                    jLabels.get(i).setText(pockerUsers.get(i).toString());
                    PreparedStatement preparedStatement = worker.getConnection().prepareStatement("UPDATE pocker SET count = ?, gameNumber = ?,checkbox = ? WHERE id =?");
                    preparedStatement.setInt(1,pockerUsers.get(i).getCount());
                    preparedStatement.setInt(2,pockerUsers.get(i).getGameNumber());
                    preparedStatement.setBoolean(3, true);
                    preparedStatement.setInt(4,pockerUsers.get(i).getId());
                    preparedStatement.execute();

                }else if(!pockerUsers.get(i).getCheckBox().isSelected()){
                    PreparedStatement preparedStatement = worker.getConnection().prepareStatement("UPDATE pocker SET checkbox = ? WHERE id =?");
                    preparedStatement.setBoolean(1, false);
                    preparedStatement.setInt(2,pockerUsers.get(i).getId());
                    preparedStatement.execute();
                }
            }


        } catch (Exception ed) {
            ed.printStackTrace();
        }
        return "Game number is " + ++numberGame;
    }

    public static void addingNewPlayer(JPanel mainPanel, JTextField newPlayer) {
        try {
            JCheckBox checkBox = new JCheckBox();
            checkBox.isSelected();
            if (newPlayer.getText().isEmpty()) {
                throw new NullPointerException();
            }

            mainPanel.add(checkBox);
            JLabel j = new JLabel();
            mainPanel.add(j);
            jLabels.add(j);

            JTextField JtextField = new JTextField(4);
            JButton ins = new JButton("Ins");

            mainPanel.add(JtextField);
            mainPanel.add(ins);

            Pocker playerOfMyDreams = new Pocker(newPlayer.getText(), 500, 1, pockerUsers.size() + 1, checkBox, JtextField, ins);
            DBWorker worker = new DBWorker();
            PreparedStatement preparedStatement = worker.getConnection().prepareStatement("INSERT INTO pocker VALUES(?,?,?,?,?)");
            preparedStatement.setInt(1,pockerUsers.size()+1);
            preparedStatement.setInt(2,500);
            preparedStatement.setString(3,playerOfMyDreams.getName());
            preparedStatement.setInt(4,0);
            preparedStatement.setBoolean(5,true);
            preparedStatement.execute();
            pockerUsers.add(playerOfMyDreams);
            j.setText(playerOfMyDreams.toString());
            ins.addActionListener(e -> {
                int temps = Integer.parseInt(JtextField.getText());
                playerOfMyDreams.setCount(playerOfMyDreams.getCount() + temps);
                j.setText(playerOfMyDreams.toString());
                JtextField.setText("");
            });


        } catch (Exception ed) {
            ed.printStackTrace();
        }
        newPlayer.setText("");


    }

    public static void buttonGo(Pocker pocker, JLabel c) {
        pocker.getButton().addActionListener(e -> {
            int temps = 0;

             temps = Integer.parseInt(pocker.getTextField().getText());
            pocker.setCount(pocker.getCount() + temps);
            c.setText(pocker.toString());
            pocker.getTextField().setText("");

            try{
            DBWorker worker = new DBWorker();

                PreparedStatement preparedStatement = worker.getConnection().prepareStatement("UPDATE pocker SET count = ? WHERE id =?");
                preparedStatement.setInt(1, pocker.getCount());
                preparedStatement.setInt(2, pocker.getId());
                preparedStatement.execute();}catch (SQLException egs){
                System.out.println(egs);
            }
        });
    }

}


