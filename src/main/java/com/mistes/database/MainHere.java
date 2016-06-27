package com.mistes.database;

import javafx.concurrent.Worker;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Администратор on 27.06.2016.
 */
public class MainHere {
      private int numberGame;
        // public static MainHere basic;
    private static String s;
    static ArrayList<Pocker> pockerUsers = new ArrayList<>();

    static ArrayList<JLabel> jLabels = new ArrayList<>();




        public static void main(String[] args)
        {
            MainHere king = new MainHere();
            king.queryWorker();
            king.go();
        }
    public void queryWorker(){
        DBWorker worker = new DBWorker();
        String query = "select * from pocker";
        String query2 = "select * from gameis";
        try{

            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet =  statement.executeQuery(query);
            while (resultSet.next()){
                Pocker pocker = new Pocker();
                pocker.setId(resultSet.getInt(1));
                pocker.setName(resultSet.getString(3));
                pocker.setCount(resultSet.getInt(2));
                pocker.setGameNumber(resultSet.getInt(4));
                pockerUsers.add(pocker);
                //  pocker.setCheckBox(new JCheckBox().setSelected(resultSet.getBoolean(5)));
                //  s = pocker.toString();
            }
            ResultSet resultSet2 =  statement.executeQuery(query2);
            while (resultSet2.next()){
                numberGame = resultSet2.getInt(1);

            }
        }catch (SQLException e){e.printStackTrace();}
    }
        public void go(){
            JFrame theFrame = new JFrame("Cyber BeatBox");
            theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            BorderLayout layout = new BorderLayout();
            JPanel background = new JPanel(layout);
            background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            JPanel mainPanel = new JPanel();
            JPanel topPanel = new JPanel();
            JPanel downPanel = new JPanel();

           // JTextArea testing = new JTextArea(15,50);
           // testing.setLineWrap(true);
           // testing.setWrapStyleWord(true);
           // testing.setEditable(true);

            for(int i = 0; i < pockerUsers.size(); i++) {
                JLabel c = new JLabel();
                c.setText(pockerUsers.get(i).toString());
                jLabels.add(c);
            }


            JLabel label = new JLabel("Add new player");

            JLabel countLabel = new JLabel("Game number is " + numberGame);

          //  JScrollPane aScroller = new JScrollPane(testing);
           // aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
           // aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JButton startGame = new JButton("Start this game");
            startGame.addActionListener(e -> {try{


               DBWorker worker = new DBWorker();
                Statement statement = worker.getConnection().createStatement();
                statement.executeUpdate("UPDATE gameis SET gameis = gameis+1");
                countLabel.setText("Game number is " + ++numberGame);

            }catch (Exception ed){ed.printStackTrace();}

            });

            JTextField newPlayer = new JTextField(20);
            JButton sendButton = new JButton("add");
            sendButton.addActionListener(e -> {try{
               newPlayer.getText();

            }catch (Exception ed){ed.printStackTrace();}
                newPlayer.setText("");

            });

            topPanel.add(startGame);
            topPanel.add(countLabel);
            for(int i = 0; i <jLabels.size(); i++){
                mainPanel.add(jLabels.get(i));
            }
           // mainPanel.add(aScroller);
            downPanel.add(label);
            downPanel.add(newPlayer);
            //  downPanel.setLayout(new BoxLayout(downPanel,BoxLayout.Y_AXIS));

            theFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
            theFrame.getContentPane().add(BorderLayout.NORTH, topPanel);
            theFrame.getContentPane().add(BorderLayout.SOUTH, downPanel);



            theFrame.setSize(700,400);
            theFrame.setVisible(true);
        }


    }


