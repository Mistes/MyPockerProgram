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
      private static int numberGame;
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
            while (resultSet.next()){ //loading class
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
            ResultSet resultSet2 =  statement.executeQuery(query2);
            while (resultSet2.next()){
                numberGame = resultSet2.getInt(1);

            }
        }catch (SQLException e){e.printStackTrace();}
    }
        public void go(){
            JFrame theFrame = new JFrame("Pocker Helper");
            theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            BorderLayout layout = new BorderLayout();
            JPanel background = new JPanel(layout);
            background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            JPanel mainPanel = new JPanel();
            JPanel topPanel = new JPanel();
            JPanel downPanel = new JPanel();
           // mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

           // JTextArea testing = new JTextArea(15,50);
           // testing.setLineWrap(true);
           // testing.setWrapStyleWord(true);
           // testing.setEditable(true);






            JLabel label = new JLabel("Add new player");

            JLabel countLabel = new JLabel("Game number is " + numberGame);

          //  JScrollPane aScroller = new JScrollPane(testing);
           // aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
           // aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JButton startGame = new JButton("Start this game");
            startGame.addActionListener(e -> {
              countLabel.setText(startingNewGame(mainPanel));

            });

            JTextField newPlayer = new JTextField(20);
            JButton sendButton = new JButton("add");
            sendButton.addActionListener(e -> {try{
                Pocker pocker = new Pocker(newPlayer.getText(), 500, 1, pockerUsers.size()+1);
                pockerUsers.add(pocker);
              //  labelsLoad(mainPanel,pockerUsers,jLabels);
                JCheckBox checkBox = new JCheckBox();
                mainPanel.add(checkBox);
                JLabel j =  new JLabel();
                 mainPanel.add(j);
                j.setText(pocker.toString());
                JTextField Jek = new JTextField(4);
                JButton jeca = new JButton("Ins");

                mainPanel.add(Jek);
                mainPanel.add(jeca);



            }catch (Exception ed){ed.printStackTrace();}
                newPlayer.setText("");

            });

            topPanel.add(startGame);
            topPanel.add(countLabel);

        labelsLoad(mainPanel,pockerUsers,jLabels);       //zagr middle

           // mainPanel.add(aScroller);
            downPanel.add(label);
            downPanel.add(newPlayer);
            downPanel.add(sendButton);
            //  downPanel.setLayout(new BoxLayout(downPanel,BoxLayout.Y_AXIS));

            theFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
            theFrame.getContentPane().add(BorderLayout.NORTH, topPanel);
            theFrame.getContentPane().add(BorderLayout.SOUTH, downPanel);



            theFrame.setSize(600,400);
            theFrame.setVisible(true);
        }
    public static void labelsLoad(JPanel mainpanel,ArrayList<Pocker>list,ArrayList<JLabel>jlabellist ){
        for(int i = 0; i < pockerUsers.size(); i++) {
            JLabel c = new JLabel();
            c.setText(pockerUsers.get(i).toString());
            jLabels.add(c);
            mainpanel.add(pockerUsers.get(i).getCheckBox());
           /* pockerUsers.get(i).getButton().getActionListeners(e -> {try{

            pockerUsers.get(i)



            }catch (Exception ed){ed.printStackTrace();}

            });*/
            mainpanel.add(jLabels.get(i));
            mainpanel.add(pockerUsers.get(i).getTextField());
        }
       }
   public static String startingNewGame(JPanel mainPanel){
       try{

           DBWorker worker = new DBWorker();
           Statement statement = worker.getConnection().createStatement();
           statement.executeUpdate("UPDATE gameis SET gameis = gameis+1");


           for(int i = 0; i < pockerUsers.size(); i++){
             int tempcount = pockerUsers.get(i).getCount();
               pockerUsers.get(i).setCount(tempcount - 17);
           }
           labelsLoad(mainPanel,pockerUsers,jLabels);


       }catch (Exception ed){ed.printStackTrace();}
       return "Game number is " + ++numberGame;
    }


    }


