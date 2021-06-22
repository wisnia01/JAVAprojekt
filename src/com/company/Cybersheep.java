package com.company;

import javax.swing.*;

public class Cybersheep extends Animal{
    private final char symbol ='C';
    private final ImageIcon texture = new ImageIcon("imagesGame/cybersheep.jpg");
    public Cybersheep()
    {

    }
    @Override
    public void Move(int moveLength)
    {
        world.SetField(null, coordinates.GetX(), coordinates.GetY());
        boolean isHogweed = false;
        int closestX=999, closestY=999;
        for (int i = 0; i < world.GetMapSize().GetY(); i++)
        {
            for (int j = 0; j < world.GetMapSize().GetX(); j++) {
                if (world.GetField(i,j) instanceof Hogweed) {
                    isHogweed = true;

                    if(Math.abs(i-this.coordinates.GetX())+Math.abs(j-this.coordinates.GetY())<Math.abs(closestX-this.coordinates.GetX())+Math.abs(closestY-this.coordinates.GetY())) {
                        closestX = i;
                        closestY = j;
                    }
                }
            } }


        if(isHogweed)
        {
            if(this.coordinates.GetX()<closestX)
            {
                coordinates.SetX(coordinates.GetX()+1);
                coordinates.SetY(coordinates.GetY());
            }
            else if(this.coordinates.GetX()>closestX)
            {
                coordinates.SetX(coordinates.GetX()-1);
                coordinates.SetY(coordinates.GetY());
            }
            else if(this.coordinates.GetY()<closestY)
            {
                coordinates.SetX(coordinates.GetX());
                coordinates.SetY(coordinates.GetY()+1);
            }
            else if(this.coordinates.GetY()>closestY)
            {
                coordinates.SetX(coordinates.GetX());
                coordinates.SetY(coordinates.GetY()-1);
            }

        }else {


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
    public Cybersheep(Vector2 coordinates, World world)
    {
        this.initiative = 4;
        this.moveLength = 1;
        this.age = 0;
        this.strength = 11;
        this.world = world;
        this.coordinates.SetX(coordinates.GetX());
        this.coordinates.SetY(coordinates.GetY());
        System.out.println("Cybersheep appears at ..."+coordinates.GetX()+","+coordinates.GetY());
    }
    public Cybersheep(int x, int y, World world, int strength, int age)
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
            Organism newOrganism = new Cybersheep(newCoordinates, world);
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
