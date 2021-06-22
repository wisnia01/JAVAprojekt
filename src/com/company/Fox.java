package com.company;

import javax.swing.*;

public class Fox extends Animal{
    private final char symbol ='F';
    private final ImageIcon texture = new ImageIcon("imagesGame/fox.jpg");
    public Fox()
    {

    }
    public Fox(Vector2 coordinates, World world)
    {
        this.initiative = 7;
        this.moveLength = 1;
        this.age = 0;
        this.strength = 3;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
        System.out.println("Sheep appears at ..."+coordinates.GetX()+","+coordinates.GetY());
    }
    public Fox(int x, int y, World world, int strength, int age)
    {
        this.initiative = 7;
        this.moveLength = 1;
        this.age = age;
        this.strength = strength;
        this.world = world;
        this.coordinates.SetX(x);
        this.coordinates.SetY(y);
    }

    @Override
    public void Move(int moveLength)
    {
        world.SetField(null, coordinates.GetX(), coordinates.GetY());

        int[]newCoordX = new int[4];
        int[] newCoordY = new int[4];
        int whereGo = 0;
        int possibleMoves = 0;
        if (coordinates.GetX() + moveLength < world.GetMapSize().GetX())
        {
            if (world.GetField(coordinates.GetX() + moveLength, coordinates.GetY()) == null || world.GetField(coordinates.GetX() + moveLength, coordinates.GetY()).GetStrength() <= strength)
            {
                newCoordX[possibleMoves] = coordinates.GetX() + moveLength;
                newCoordY[possibleMoves] = coordinates.GetY();
                possibleMoves++;
            }

        }
        if (coordinates.GetX() - moveLength >= 0)
        {
            if (world.GetField(coordinates.GetX() - moveLength, coordinates.GetY()) == null || world.GetField(coordinates.GetX() - moveLength, coordinates.GetY()).GetStrength() <= strength)
            {
                newCoordX[possibleMoves] = coordinates.GetX() - moveLength;
                newCoordY[possibleMoves] = coordinates.GetY();
                possibleMoves++;
            }
        }
        if (coordinates.GetY() + moveLength < world.GetMapSize().GetY())
        {
            if (world.GetField(coordinates.GetX(), coordinates.GetY() + moveLength) == null || world.GetField(coordinates.GetX(), coordinates.GetY() + moveLength).GetStrength() <= strength)
            {
                newCoordY[possibleMoves] = coordinates.GetY() + moveLength;
                newCoordX[possibleMoves] = coordinates.GetX();
                possibleMoves++;
            }
        }
        if (coordinates.GetY() - moveLength >= 0)
        {
            if (world.GetField(coordinates.GetX() , coordinates.GetY() - moveLength) == null || world.GetField(coordinates.GetX(), coordinates.GetY() - moveLength).GetStrength() <= strength)
            {
                newCoordY[possibleMoves] = coordinates.GetY() - moveLength;
                newCoordX[possibleMoves] = coordinates.GetX();
                possibleMoves++;
            }
        }
        if (possibleMoves != 0)
        {
            whereGo = (int)((Math.random() * possibleMoves)%possibleMoves);
            coordinates.SetX(newCoordX[whereGo]);
            coordinates.SetY(newCoordY[whereGo]);
        }

    }
    @Override
    public void Breed() {
        int[] newCoordX = new int[4];
        int[] newCoordY = new int[4];
        int whereGo = 0;
        int possibleMoves = 0;
        if (coordinates.GetX() + 1 < world.GetMapSize().GetX() && world.GetField(coordinates.GetX() + 1, coordinates.GetY()) == null)
        {
            newCoordX[possibleMoves] = coordinates.GetX() + 1;
            newCoordY[possibleMoves] = coordinates.GetY();
            possibleMoves++;
        }
        if (coordinates.GetX() - 1 >= 0 && world.GetField(coordinates.GetX() - 1, coordinates.GetY()) == null)
        {
            newCoordX[possibleMoves] = coordinates.GetX() - 1;
            newCoordY[possibleMoves] = coordinates.GetY();
            possibleMoves++;
        }
        if (coordinates.GetY() + 1 < world.GetMapSize().GetY() && world.GetField(coordinates.GetX(), coordinates.GetY() + 1) == null)
        {
            newCoordY[possibleMoves] = coordinates.GetY() + 1;
            newCoordX[possibleMoves] = coordinates.GetX();
            possibleMoves++;
        }
        if (coordinates.GetY() - 1 >= 0 && world.GetField(coordinates.GetX(), coordinates.GetY() - 1) == null)
        {
            newCoordY[possibleMoves] = coordinates.GetY() - 1;
            newCoordX[possibleMoves] = coordinates.GetX();
            possibleMoves++;
        }
        if (possibleMoves != 0)
        {
            whereGo = (int)((Math.random() * possibleMoves)%possibleMoves);
            Vector2 newCoordinates = new Vector2();
            newCoordinates.SetX(newCoordX[whereGo]);
            newCoordinates.SetY(newCoordY[whereGo]);
            Organism newOrganism = new Fox(newCoordinates, world);
            world.SetField(newOrganism, newOrganism.GetCoordinates().GetX(), newOrganism.GetCoordinates().GetY());
            world.AddOrganism(newOrganism);
        }
    }
    @Override
    public char GetSymbol()
    {
        return symbol;
    }
    @Override
    public ImageIcon GetTexture()
    {
        return texture;
    }
}
