package com.company;

import javax.swing.*;

public class Human extends Animal {
    private final char symbol = '#';
    private final ImageIcon texture = new ImageIcon("imagesGame/human.jpg");
    public Human(){

    }
    public Human(Vector2 coordinates, World world)
    {
        this.initiative = 4;
        this.age = 0;
        this.moveLength = 1;
        this.strength = 5;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
    }
    public Human(int x, int y, World world, int strength, int age)
    {
        this.initiative = 4;
        this.age = age;
        this.moveLength = 1;
        this.strength = strength;
        this.world = world;
        this.coordinates.SetX(x);
        this.coordinates.SetY(y);
    }
    @Override
    public void Breed()
    {
        //human cannot breed
        System.out.println("How did it happen?");
        return;
    }
    @Override
    public char GetSymbol()
    {
        return symbol;
    }
    @Override
    public void Move(int moveLength)
    {
        world.SetField(null, coordinates.GetX(), coordinates.GetY());
        switch (world.GetHumanChoice())
        {
            case 38:
            {
                if (coordinates.GetY() - 1 >= 0)
                {
                    coordinates.SetY(coordinates.GetY() - 1);
                }
                break;
            }
            case 40:
            {
                if (coordinates.GetY() + 1 < world.GetMapSize().GetY())
                {
                    coordinates.SetY(coordinates.GetY() + 1);
                }
                break;
            }
            case 37:
            {
                if (coordinates.GetX() - 1 >= 0)
                {
                    coordinates.SetX(coordinates.GetX() - 1);
                }
                break;
            }
            case 39:
            {
                if (coordinates.GetX() + 1 < world.GetMapSize().GetX())
                {
                    coordinates.SetX(coordinates.GetX() + 1);
                }
                break;
            }
        }
    }
    @Override
    public void Collision(Organism attacker, Vector2 tmp)
    {
        if (world.GetPower())
        {
            attacker.SetCoordinates(tmp);
            world.SetField(attacker, tmp.GetX(), tmp.GetY());
            System.out.println("SUPER ABILITY");
        }
        else
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
        }
    }
    @Override
    public ImageIcon GetTexture() {
        return texture;

    }
}
