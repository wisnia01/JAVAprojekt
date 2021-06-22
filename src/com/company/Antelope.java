package com.company;

import javax.swing.*;

public class Antelope extends Animal{
    private final char symbol ='A';
    private final ImageIcon texture = new ImageIcon("imagesGame/antelope.jpg");

    public Antelope()
    {

    }
    public Antelope(Vector2 coordinates, World world)
    {
        this.initiative = 4;
        this.moveLength = 2;
        this.age = 0;
        this.strength = 4;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
        System.out.println("Tortoise appears at ...");
    }
    public Antelope(int x, int y, World world, int strength, int age)
    {
        this.initiative = 4;
        this.moveLength = 2;
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
            Organism newOrganism = new Antelope(newCoordinates, world);
            world.SetField(newOrganism, newOrganism.GetCoordinates().GetX(), newOrganism.GetCoordinates().GetY());
            world.AddOrganism(newOrganism);
        }
    }
    @Override
    public void Collision(Organism attacker, Vector2 tmp)
    {
        if (this.getClass().getName()== attacker.getClass().getName())
        {
            attacker.SetCoordinates(tmp);
            world.SetField(attacker, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
            if (this.age >= 5)
            Breed();
        }
	else
        {
            int runaway = (int)(Math.random() *2);
            if (runaway == 0)
            {

                if (attacker.GetStrength() > this.GetStrength())
                {
                    world.DelOrganism(this);
                    attacker.GetWorld().SetField(attacker, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
                }
			else
                {
                    attacker.SetCoordinates(tmp);
                    world.DelOrganism(attacker);
                }
            }
            else
            {
                world.SetField(attacker, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
                int[] newCoordX = new int[4];
                int[] newCoordY = new int[4];
                int whereGo = 0;
                int possibleMoves = 0;
                if (coordinates.GetX() + 1 < world.GetMapSize().GetX())
                {
                    if (world.GetField(coordinates.GetX() + 1, coordinates.GetY()) == null)
                    {
                        newCoordX[possibleMoves] = coordinates.GetX() + 1;
                        newCoordY[possibleMoves] = coordinates.GetY();
                        possibleMoves++;
                    }

                }
                if (coordinates.GetX() - 1 >= 0)
                {
                    if (world.GetField(coordinates.GetX() - 1, coordinates.GetY()) == null)
                    {
                        newCoordX[possibleMoves] = coordinates.GetX() - 1;
                        newCoordY[possibleMoves] = coordinates.GetY();
                        possibleMoves++;
                    }
                }
                if (coordinates.GetY() + 1 < world.GetMapSize().GetY())
                {
                    if (world.GetField(coordinates.GetX(), coordinates.GetY() + 1) == null)
                    {
                        newCoordY[possibleMoves] = coordinates.GetY() + 1;
                        newCoordX[possibleMoves] = coordinates.GetX();
                        possibleMoves++;
                    }
                }
                if (coordinates.GetY() - 1 >= 0)
                {
                    if (world.GetField(coordinates.GetX(), coordinates.GetY() - 1) == null)
                    {
                        newCoordY[possibleMoves] = coordinates.GetY() - 1;
                        newCoordX[possibleMoves] = coordinates.GetX();
                        possibleMoves++;
                    }
                }
                if (possibleMoves != 0)
                {
                    whereGo = (int)((Math.random() * possibleMoves)%possibleMoves);
                    coordinates.SetX(newCoordX[whereGo]);
                    coordinates.SetY(newCoordY[whereGo]);
                    world.SetField(this, coordinates.GetX(), coordinates.GetY());
                }
                else
                    world.DelOrganism(this);


            }
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
