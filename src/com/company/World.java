package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class World{
    private MyFrame frame;
    private ImageIcon groundTexture= new ImageIcon("imagesGame/ground.jpg");
    private ImageIcon fogTexture= new ImageIcon("imagesGame/fog.jpg");

    private int moveCount;
    private int numberOfOrganisms;
    private int humanChoice;
    private int deletedOrganism;
    private int howLongLastPowerSet;
    private Vector2 mapSize;
    private boolean isPowerSet;
    private boolean isHumanAlive;
    private boolean isHumanAliveTmp;
    private Organism[] organisms;
    private Organism field[][];
    private String[] comments;
    private int numberOfComments;


    private Organism human;



    public World(Vector2 mapSize, MyFrame frame)
    {
        numberOfComments=0;
        comments = new String[mapSize.GetX()* mapSize.GetY()];
        this.frame = frame;
        isHumanAlive = true;
        howLongLastPowerSet = 99;
        deletedOrganism = -1;
        moveCount = 0;
        this.mapSize = mapSize;
        field = new Organism[this.mapSize.GetY()][this.mapSize.GetY()];
        for (int i = 0; i < this.mapSize.GetY(); i++)
        {
            for (int j = 0; j < this.mapSize.GetY(); j++) {
                field[i][j] = null;
            }
        }
        organisms = new Organism[this.mapSize.GetY() * this.mapSize.GetX()];
        for (int i = 0; i < this.mapSize.GetY() * this.mapSize.GetX(); i++)
        {
            organisms[i] = null;
        }
        numberOfOrganisms = 0;
    }

    public World(Vector2 mapSize)
    {
        numberOfComments = 0;
        comments = new String[mapSize.GetX()* mapSize.GetY()];
        isHumanAlive = true;
        howLongLastPowerSet = 99;
        deletedOrganism = -1;
        moveCount = 0;
        this.mapSize = mapSize;
        field = new Organism[this.mapSize.GetY()][this.mapSize.GetY()];
        for (int i = 0; i < this.mapSize.GetY(); i++)
        {
            for (int j = 0; j < this.mapSize.GetY(); j++) {
                field[i][j] = null;
            }
        }
        organisms = new Organism[this.mapSize.GetY() * this.mapSize.GetX()];
        for (int i = 0; i < this.mapSize.GetY() * this.mapSize.GetX(); i++)
        {
            organisms[i] = null;
        }
        numberOfOrganisms = 0;
    }
    public void DrawWorld()
    {
        for(int i =0; i<numberOfOrganisms; i++)
        {
            Vector2 coords = organisms[i].GetCoordinates();
            field[coords.GetY()][coords.GetX()] = organisms[i];
        }

        isHumanAliveTmp = false;
        for (int i = 0; i < mapSize.GetY(); i++)
        {
            for (int j = 0; j < mapSize.GetX(); j++)
            {
                if(field[i][j] instanceof Human) /// j and i swapped
                {
                   this.human = field[i][j]; //
                    isHumanAliveTmp = true;

                    break;
                }
            }
        }
        if(!isHumanAliveTmp)
            isHumanAlive = false;

        for (int i = 0; i < mapSize.GetY(); i++)
        {
            for (int j = 0; j < mapSize.GetX(); j++)
            {
                if(isHumanAlive) {
                    if (Math.abs(j - human.coordinates.GetX()) < 3 && Math.abs(i - human.coordinates.GetY()) < 3) {
                        if (field[i][j] == null) /// j and i swapped
                        {
                            frame.getFields()[i][j].setIcon(groundTexture); //
                            frame.getFields()[i][j].setText("");
                        } else {
                            frame.getFields()[i][j].setIcon(field[i][j].GetTexture()); //
                            frame.getFields()[i][j].setText(String.valueOf(field[i][j].GetStrength()));
                        }
                    } else {
                        frame.getFields()[i][j].setIcon(fogTexture);
                        frame.getFields()[i][j].setText("");
                    }
                }
                else
                {
                    if (field[i][j] == null) /// j and i swapped
                    {
                        frame.getFields()[i][j].setIcon(groundTexture); //
                        frame.getFields()[i][j].setText("");
                    } else {
                        frame.getFields()[i][j].setIcon(field[i][j].GetTexture()); //
                        frame.getFields()[i][j].setText(String.valueOf(field[i][j].GetStrength()));

                    }
                }

            }
        }
    }
    public Vector2 GetMapSize()
    {
        return mapSize;
    }
    public Organism GetField(int x, int y)
    {
        return field[y][x];
    }
    public void SetField(Organism newField, int x,int y)
    {
        field[y][x] = newField;
    }
    public void AddOrganism(Organism newOrganism)
    {
        if(numberOfOrganisms==this.mapSize.GetY()*this.mapSize.GetX())
        {
            System.out.println("MAX ORGANISMS");
        }
        organisms[numberOfOrganisms] = newOrganism;
        field[newOrganism.GetCoordinates().GetY()][newOrganism.GetCoordinates().GetX()] = newOrganism;
        comments[numberOfComments] = newOrganism.getClass().getSimpleName()+" appears at "+newOrganism.GetCoordinates().GetX()+","+newOrganism.GetCoordinates().GetY()+"\n";
        numberOfComments++;
        numberOfOrganisms++;
    }
    public void DelOrganism(Organism organismToDel)
    {
        boolean start = false;
        field[organismToDel.GetCoordinates().GetY()][organismToDel.GetCoordinates().GetX()] = null;
        for (int i = 0; i < this.mapSize.GetY() * this.mapSize.GetX(); i++)
        {
            if (organisms[i] == organismToDel)
            {
                deletedOrganism = i;
                organisms[i] = null;
                start = true;
            }
            if (start)
            {
                if (i != this.mapSize.GetY() * this.mapSize.GetX() - 1)
                organisms[i] = organisms[i + 1];

            }
        }
        organisms[this.mapSize.GetY() * this.mapSize.GetX() - 1] = null;
        comments[numberOfComments] = organismToDel.getClass().getSimpleName()+" dies at "+organismToDel.GetCoordinates().GetX()+","+organismToDel.GetCoordinates().GetY()+"\n";
        numberOfComments++;
        numberOfOrganisms--;
    }
    public void MakeMove()
    {
        numberOfComments = 0;
        comments = new String[mapSize.GetX()* mapSize.GetY()];
        Organism tmp;
        int i, j;
        for (i = 0; i < numberOfOrganisms - 1; i++)

            //	// Last i elements are already in place
            for (j = 0; j < numberOfOrganisms - i - 1; j++)
            {
                if (organisms[j].GetInitiative() < organisms[j + 1].GetInitiative())
                {
                    tmp = organisms[j];
                    organisms[j] = organisms[j + 1];
                    organisms[j + 1] = tmp;

                }
			else if (organisms[j].GetInitiative() == organisms[j + 1].GetInitiative())
                for (int k = 0; k + j < numberOfOrganisms - 1; k++)
                {
                    for (int l = 0; organisms[l+1].GetInitiative()> organisms[l].GetInitiative(); l++) ///here
                    {
                        if (organisms[l].GetAge() < organisms[l + 1].GetAge())
                        {
                            tmp = organisms[l];
                            organisms[l] = organisms[l + 1];
                            organisms[l + 1] = tmp;
                        }
                    }
                }
            }
        moveCount++;

        for (i = 0; i < numberOfOrganisms; i++)
        {

            if (organisms[i] != null)
            {
                organisms[i].SetAge(organisms[i].GetAge() + 1);
                if (organisms[i].GetAge() > 0)
                organisms[i].Action();

                //System.out.println(organisms[i].GetCoordinates().GetX()+","+organisms[i].GetCoordinates().GetY());
            }
            if (deletedOrganism != -1)
            {
                if(deletedOrganism<=i)
                    i--;
                deletedOrganism = -1;
            }
        }
        howLongLastPowerSet++;
        if (howLongLastPowerSet == 5)
        {
            isPowerSet = false;
            System.out.println("ABILITY DISABLED");
        }
        humanChoice = 0;


    }
    public boolean GetPower()
    {
        return isPowerSet;
    }
    public int GetHumanChoice()
    {
        return humanChoice;
    }
    public void SetHumanChoice(int humanChoice)
    {
        this.humanChoice = humanChoice;
    }
    public int GetMoveCount()
    {
        return moveCount;
    }
    public void SetPower(boolean isset)
    {
        this.isPowerSet = isset;
    }
    public void ResetHowLong()
    {
        howLongLastPowerSet = 0;
    }
    public void SetHowLong(int howLong)
    {
        this.howLongLastPowerSet = howLong;
    }
    public int GetHowLong()
    {
        return howLongLastPowerSet;
    }
    public void SetHumanAlive(boolean dead)
    {
        this.isHumanAlive = dead;
    }
    public boolean GetHumanAlive()
    {
        return isHumanAlive;
    }
    public int GetNumberOfOrganisms()
    {
        return numberOfOrganisms;
    }
    public Organism GetOrganism(int i)
    {
        return organisms[i];
    }
    public void SetMoveCount(int moveCount)
    {
        this.moveCount = moveCount;
    }

    public String[] GetComments() {
        return comments;
    }
    public int GetNumberOfComments()
    {
        return numberOfComments;
    }
}
