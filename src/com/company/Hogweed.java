package com.company;

import javax.swing.*;

public class Hogweed extends Plant{
    private final char symbol = 'H';
    private final ImageIcon texture = new ImageIcon("imagesGame/hogweed.jpg");
    public Hogweed()
    {

    }
    public Hogweed(Vector2 coordinates, World world)
    {
        this.initiative = 0;
        this.age = 0;
        this.strength = 10;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
        System.out.println("DeadlyNightshade appears");
    }
    public Hogweed(int x, int y, World world, int strength, int age)
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
            int guaranaTry = (int)(Math.random()*100);
            if (guaranaTry <  20)
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
                    Organism newOrganism = new Hogweed(newCoordinates, world);
                    world.SetField(newOrganism, newOrganism.GetCoordinates().GetX(), newOrganism.GetCoordinates().GetY());
                    world.AddOrganism(newOrganism);
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
        if(attacker instanceof Cybersheep)
        {
            world.DelOrganism(this);
            attacker.GetWorld().SetField(attacker, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
        }
        else {
            attacker.GetWorld().DelOrganism(this);
            attacker.GetWorld().SetField(null, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
            attacker.GetWorld().DelOrganism(attacker);
        }
    }
    @Override
    public void Action()
    {
        if (age > 2)
            Breed();

        if (coordinates.GetX() + 1 < world.GetMapSize().GetX() && world.GetField(coordinates.GetX() + 1, coordinates.GetY()) != null)
        {
            if (world.GetField(coordinates.GetX() + 1, coordinates.GetY()).GetInitiative() != 0 && !(world.GetField(coordinates.GetX() + 1, coordinates.GetY()) instanceof Cybersheep))
            {
                world.DelOrganism(world.GetField(coordinates.GetX() + 1, coordinates.GetY()));
                world.SetField(null, coordinates.GetX() + 1, coordinates.GetY());

            }
        }
        if (coordinates.GetX() - 1 >= 0 && world.GetField(coordinates.GetX() - 1, coordinates.GetY()) != null)
        {
            if (world.GetField(coordinates.GetX() - 1, coordinates.GetY()).GetInitiative() != 0 && !(world.GetField(coordinates.GetX() - 1, coordinates.GetY()) instanceof Cybersheep))
            {
                world.DelOrganism(world.GetField(coordinates.GetX() - 1, coordinates.GetY()));
                world.SetField(null, coordinates.GetX() - 1, coordinates.GetY());

            }
        }
        if (coordinates.GetY() + 1 < world.GetMapSize().GetY() && world.GetField(coordinates.GetX(), coordinates.GetY() + 1) != null)
        {
            if (world.GetField(coordinates.GetX(), coordinates.GetY() + 1).GetInitiative() != 0 && !(world.GetField(coordinates.GetX(), coordinates.GetY()+1) instanceof Cybersheep))
            {
                world.DelOrganism(world.GetField(coordinates.GetX(), coordinates.GetY() + 1));
                world.SetField(null, coordinates.GetX(), coordinates.GetY() + 1);

            }
        }
        if (coordinates.GetY() - 1 >= 0 && world.GetField(coordinates.GetX(), coordinates.GetY() - 1) != null)
        {
            if (world.GetField(coordinates.GetX(), coordinates.GetY() - 1).GetInitiative() != 0 && !(world.GetField(coordinates.GetX(), coordinates.GetY()-1) instanceof Cybersheep))
            {
                world.DelOrganism(world.GetField(coordinates.GetX(), coordinates.GetY() - 1));
                world.SetField(null, coordinates.GetX(), coordinates.GetY() - 1);

            }
        }
    }
}
