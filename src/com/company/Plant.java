package com.company;

import javax.swing.*;

abstract public class Plant extends Organism {
    public Plant()
    {

    }
    @Override
    public void Action()
    {
        if(age>2)
            Breed();
    }
    @Override
    public void Collision(Organism attacker, Vector2 tmp)
    {
        world.DelOrganism(this);
        attacker.GetWorld().SetField(attacker, attacker.GetCoordinates().GetX(), attacker.GetCoordinates().GetY());
    }
    @Override
    abstract public void Breed();

    @Override
    abstract public char GetSymbol();

    @Override
    abstract public ImageIcon GetTexture();

}
