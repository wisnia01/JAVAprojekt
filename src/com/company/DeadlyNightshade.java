package com.company;

import javax.swing.*;

public class DeadlyNightshade extends Plant{
    private final char symbol = 'N';
    private final ImageIcon texture = new ImageIcon("imagesGame/deadlynightshade.jpg");
    public DeadlyNightshade()
    {

    }
    public DeadlyNightshade(Vector2 coordinates, World world)
    {
        this.initiative = 0;
        this.age = 0;
        this.strength = 99;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
        System.out.println("DeadlyNightshade appears");
    }
    public DeadlyNightshade(int x, int y, World world, int strength, int age)
    {
        this.initiative = 0;
        this.age = age;
        this.strength = strength;
        this.world = world;
        this.coordinates.SetX(x);
        this.coordinates.SetY(y);
    }
    @Override
    public void Breed()
    {
        for (int i = 0; i < 3; i++)
        {
            int wolfTry = (int)(Math.random()*100);
            if (wolfTry < 5)
            {
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
                    Organism newOrganism = new DeadlyNightshade(newCoordinates, world);
                    world.SetField(newOrganism, newOrganism.GetCoordinates().GetX(), newOrganism.GetCoordinates().GetY());
                    world.AddOrganism(newOrganism);
                }
            }
        }
    }
    @Override
    public char GetSymbol()
    {
        return symbol;
    }

    @Override
    public ImageIcon GetTexture() {
        return texture;

    }
    @Override
    public void Collision(Organism attacker, Vector2 tmp)
    {
        attacker.GetWorld().DelOrganism(this);
        attacker.GetWorld().SetField(null, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
        attacker.GetWorld().DelOrganism(attacker);
    }
}
