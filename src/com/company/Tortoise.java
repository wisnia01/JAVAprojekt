package com.company;

import javax.swing.*;

public class Tortoise extends Animal{
    private final char symbol ='T';
    private final ImageIcon texture = new ImageIcon("imagesGame/tortoise.jpg");
    private boolean IsDeflected(Organism attacker)
    {
        if(attacker.GetStrength() < 5)
            return true;
        else
            return false;
    }
    public Tortoise()
    {

    }
    public Tortoise(Vector2 coordinates, World world)
    {
        this.initiative = 1;
        this.moveLength = 1;
        this.age = 0;
        this.strength = 2;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
        System.out.println("Tortoise appears at ...");
    }
    public Tortoise(int x, int y, World world, int strength, int age)
    {
        this.initiative = 1;
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
            Organism newOrganism = new Tortoise(newCoordinates, world);
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
            if (IsDeflected(attacker))
            {
                attacker.SetCoordinates(tmp);
                world.SetField(attacker, tmp.GetX(), tmp.GetY());
                System.out.println("Turtoise deflecting attack\n");
                return;
            }
			else
            {
                if (attacker.GetStrength() > this.GetStrength())
                {
                    world.DelOrganism(this); //tu zamienilem kolejnoscia
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
    public void Move(int moveLength)
    {
        int IamLazy = (int)(Math.random()*4);
        if(IamLazy==0) {
            world.SetField(null, coordinates.GetX(), coordinates.GetY());

            int[] newCoordX = new int[4];
            int[] newCoordY = new int[4];
            int whereGo = 0;
            int possibleMoves = 0;
            if (coordinates.GetX() + moveLength < world.GetMapSize().GetX()) {
                newCoordX[possibleMoves] = coordinates.GetX() + moveLength;
                newCoordY[possibleMoves] = coordinates.GetY();
                possibleMoves++;
            }
            if (coordinates.GetX() - moveLength >= 0) {
                newCoordX[possibleMoves] = coordinates.GetX() - moveLength;
                newCoordY[possibleMoves] = coordinates.GetY();
                possibleMoves++;
            }
            if (coordinates.GetY() + moveLength < world.GetMapSize().GetY()) {
                newCoordY[possibleMoves] = coordinates.GetY() + moveLength;
                newCoordX[possibleMoves] = coordinates.GetX();
                possibleMoves++;
            }
            if (coordinates.GetY() - moveLength >= 0) {
                newCoordY[possibleMoves] = coordinates.GetY() - moveLength;
                newCoordX[possibleMoves] = coordinates.GetX();
                possibleMoves++;
            }
            whereGo = (int) ((Math.random() * possibleMoves) % possibleMoves);
            coordinates.SetX(newCoordX[whereGo]);
            coordinates.SetY(newCoordY[whereGo]);
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
