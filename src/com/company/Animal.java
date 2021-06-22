package com.company;

import javax.swing.*;

abstract public class Animal extends Organism {
    public Animal() {

    }

    @Override
    public void Action(){
        Vector2 tmp = new Vector2();
        tmp.SetX(this.GetCoordinates().GetX());
        tmp.SetY(this.GetCoordinates().GetY());
        this.Move(this.GetMoveLength());
        if (tmp.GetX() == this.GetCoordinates().GetX() && tmp.GetY() == this.GetCoordinates().GetY()) {
            world.SetField(this, coordinates.GetX(), coordinates.GetY());
        }
	    else
        {

             if (world.GetField(coordinates.GetX(), coordinates.GetY()) != null)
             {
                  world.GetField(coordinates.GetX(), coordinates.GetY()).Collision(this, tmp);
              }
              else
              {
                world.SetField(this, coordinates.GetX(), coordinates.GetY());
                }
          }
     }


    public void Move(int moveLength)
    {
        world.SetField(null, coordinates.GetX(), coordinates.GetY());

        int[]newCoordX = new int[4];
        int[] newCoordY = new int[4];
        int whereGo = 0;
        int possibleMoves = 0;
        if (coordinates.GetX() + moveLength < world.GetMapSize().GetX())
        {
            newCoordX[possibleMoves] = coordinates.GetX() + moveLength;
            newCoordY[possibleMoves] = coordinates.GetY();
            possibleMoves++;
        }
        if (coordinates.GetX() - moveLength >= 0)
        {
            newCoordX[possibleMoves] = coordinates.GetX() - moveLength;
            newCoordY[possibleMoves] = coordinates.GetY();
            possibleMoves++;
        }
        if (coordinates.GetY() + moveLength < world.GetMapSize().GetY())
        {
            newCoordY[possibleMoves] = coordinates.GetY() + moveLength;
            newCoordX[possibleMoves] = coordinates.GetX();
            possibleMoves++;
        }
        if (coordinates.GetY() - moveLength >= 0)
        {
            newCoordY[possibleMoves] = coordinates.GetY() - moveLength;
            newCoordX[possibleMoves] = coordinates.GetX();
            possibleMoves++;
        }
        whereGo = (int)((Math.random() * possibleMoves)%possibleMoves);
        coordinates.SetX(newCoordX[whereGo]);
        coordinates.SetY(newCoordY[whereGo]);
    }
    @Override
    public void Collision(Organism attacker, Vector2 tmp){
        if (this.getClass().getName()== attacker.getClass().getName())
        {
            attacker.SetCoordinates(tmp);
            world.SetField(attacker, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
            if(this.age>=5)
            Breed();
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
    @Override
    abstract public void Breed();
    @Override
    abstract public char GetSymbol();
    @Override
    abstract public ImageIcon GetTexture();

}
