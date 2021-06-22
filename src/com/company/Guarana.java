package com.company;

import javax.swing.*;

public class Guarana extends Plant{
    private final char symbol = 'G';
    private final ImageIcon texture = new ImageIcon("imagesGame/guarana.jpg");
    public Guarana()
    {

    }
    public Guarana(Vector2 coordinates, World world)
    {
        this.initiative = 0;
        this.age = 0;
        this.strength = 0;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
        System.out.println("Guarana appears");
    }
    public Guarana(int x, int y, World world, int strength, int age)
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
            int guaranaTry = (int)(Math.random()*100);
            if (guaranaTry < 7)
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
                    Organism newOrganism = new Guarana(newCoordinates, world);
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
        world.DelOrganism(this);
        attacker.SetStrength(attacker.GetStrength() + 3);
        attacker.GetWorld().SetField(attacker, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
    }
}
