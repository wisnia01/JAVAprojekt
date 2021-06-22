package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyFrame extends JFrame implements ActionListener, KeyListener {

    private World world;
    private Generator generator;
    private JButton start, load;
    private int xFields, yFields;
    private JTextField xFieldsText, yFieldsText;
    private JLabel [][] fields;
    private JPanel lobby = new JPanel();//
    private JPanel gameArea = new JPanel();//
    private JPanel infoArea = new JPanel();//
    private ImageIcon groundTexture= new ImageIcon("imagesGame/ground.jpg");
    private Vector2 mapSize;
    private JTextArea text;

    MyFrame() { //constructor
        //elements
        load = new JButton("Load previous game");
        load.addActionListener(this);
        lobby.add(load);

        start = new JButton("Start");
        start.addActionListener(this);
        lobby.add(start);


        //
        lobby.setLayout(new GridLayout());
        this.add(lobby);

        //

        xFieldsText = new JTextField();
        xFieldsText.setPreferredSize(new Dimension(50,40));
        yFieldsText = new JTextField();
        yFieldsText.setPreferredSize(new Dimension(50,40));
        lobby.add(xFieldsText);
        lobby.add(yFieldsText);

        //window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("The Game - Menu");
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new GridLayout());
        this.pack();
        this.addKeyListener(this);
        this.setFocusable(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==start)
        {
            if(Integer.parseInt(yFieldsText.getText())*Integer.parseInt(xFieldsText.getText())>25) {

                yFields = Integer.parseInt(yFieldsText.getText());
                xFields = Integer.parseInt(xFieldsText.getText());
                this.remove(lobby);
                this.add(gameArea);
                this.add(infoArea);
                text = new JTextArea(20, 20);
                text.setEditable(false);
                text.setFocusable(false);
                infoArea.add(text);
                infoArea.setBackground(Color.red);
                infoArea.setBounds(xFields * 50, 0, 300, yFields * 50);
                gameArea.setLayout(new GridLayout(yFields, xFields));

                gameArea.setSize(xFields * 50, yFields * 50);
                this.setSize(xFields * 50 + 300, yFields * 50 + 70);
                this.setLayout(null);
                fields = new JLabel[yFields][xFields];
                for (int i = 0; i < yFields; i++) {
                    for (int j = 0; j < xFields; j++) {
                        fields[i][j] = new JLabel();
                        //fields[i][j].setText("1");
                        fields[i][j].setFont(new Font("MV Boli", Font.PLAIN, 20));
                        fields[i][j].setHorizontalTextPosition(JLabel.CENTER);
                        fields[i][j].setForeground(new Color(0xF00000));
                        fields[i][j].setIcon(groundTexture);
                        gameArea.add(fields[i][j]);
                    }
                }
                mapSize = new Vector2(xFields, yFields);
                world = new World(mapSize, this);


                generator = new Generator(world);
                generator.Generate();
                world.DrawWorld();
            }
        }
        else if(e.getSource()==load)
        {
            try {
                File myObj = new File("gameinfo.txt");
                Scanner myReader = new Scanner(myObj);
                String data = myReader.nextLine();
                String[] words = data.split("\\s");
                xFields = Integer.parseInt(words[0]);
                yFields = Integer.parseInt(words[1]);
                mapSize = new Vector2();
                mapSize.SetX(xFields);
                mapSize.SetY(yFields);
                world = new World(mapSize, this);
                world.SetMoveCount(Integer.parseInt(words[2]));
                world.SetHowLong(Integer.parseInt(words[3]));
                world.SetPower(Boolean.parseBoolean(words[4]));

                this.remove(lobby);
                this.add(gameArea);
                this.add(infoArea);
                text = new JTextArea(20,20);
                text.setEditable(false);
                text.setFocusable(false);
                infoArea.add(text);
                infoArea.setBackground(Color.red);
                infoArea.setBounds(xFields*50,0,300,yFields*50);
                gameArea.setLayout(new GridLayout(yFields, xFields));

                gameArea.setSize(xFields*50,yFields*50);
                this.setSize(xFields*50+300,yFields*50+70);
                this.setLayout(null);
                fields = new JLabel[yFields][xFields];
                for(int i =0; i<yFields; i++)
                {
                    for(int j =0; j<xFields; j++) {
                        fields[i][j] = new JLabel();
                        fields[i][j].setFont(new Font("MV Boli",Font.PLAIN, 20));
                        fields[i][j].setHorizontalTextPosition(JLabel.CENTER);
                        fields[i][j].setForeground(new Color(0xFF00000));
                        fields[i][j].setIcon(groundTexture);
                        gameArea.add(fields[i][j]);
                    }
                }

                generator = new Generator(world);
                generator.GenerateSet();
                world.DrawWorld();

            }
            catch(FileNotFoundException error)
            {
                System.out.println("An error occured while reading a file");
            }
        }
    }

    public JLabel[][] getFields() {
        return fields;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 80: //power
            {
                world.SetPower(true);
                world.ResetHowLong();
                text.append("SPECIAL POWER ACTIVATED\n");
                break;
            }
            case 83: //save
            {
                int tmp = world.GetPower() ? 1 : 0;
                try {
                   FileWriter myWriter = new FileWriter("gameinfo.txt");
                    myWriter.write(xFields+" "+yFields+" "+world.GetMoveCount()+" "+world.GetHowLong()+" "+tmp+"\n");
                    for (int i = 0; i < world.GetNumberOfOrganisms(); i++)
                    {
                        myWriter.write(world.GetOrganism(i).GetSymbol()+" "+world.GetOrganism(i).GetCoordinates().GetX()+" "+world.GetOrganism(i).GetCoordinates().GetY()+" "+world.GetOrganism(i).GetStrength()+" "+world.GetOrganism(i).GetAge()+"\n");
                    }

                   myWriter.close();
                    text.append("GAME STATUS SAVED\n");
                }
                catch(IOException error)
                {
                    System.out.println("An error occurred with writing a file.");
                }
                break;
            }
            case 37, 38, 39, 40:

                    world.SetHumanChoice(e.getKeyCode());
                    world.MakeMove();
                    world.DrawWorld();
                    text.setText(null);
                    for (int i = 0; i < world.GetNumberOfComments(); i++)
                        text.append(world.GetComments()[i]);


                break;
        }
    }
}

