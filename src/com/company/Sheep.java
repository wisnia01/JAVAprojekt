package com.company;

import javax.swing.*;

public class Sheep extends Animal{
    private final char symbol ='S';
    private final ImageIcon texture = new ImageIcon("imagesGame/sheep.jpg");
    public Sheep()
    {

    }
    public Sheep(Vector2 coordinates, World world)
    {
        this.initiative = 4;
        this.moveLength = 1;
        this.age = 0;
        this.strength = 4;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
        System.out.println("Sheep appears at ..."+coordinates.GetX()+","+coordinates.GetY());
    }
    public Sheep(int x, int y, World world, int strength, int age)
    {
        this.initiative = 4;
        this.moveLength = 1;
        this.age = age;
        this.strength = strength;
        this.world = world;
        this.coordinates.SetX(x);
        this.coordinates.SetY(y);
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
            Organism newOrganism = new Sheep(newCoordinates, world);
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
