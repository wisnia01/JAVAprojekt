package com.company;

import javax.swing.*;

abstract public class Organism {
    protected int initiative;
    protected int moveLength;
    protected ImageIcon texture;
    protected int age;
    protected int strength;
    protected World world;
    protected Vector2 coordinates = new Vector2();
    protected char symbol;

    public Organism()
    {

    }
    public Organism(World world)
    {
        this.world = world;
    }
    public Organism(World world, Vector2 coordinates)
    {
        this.world = world;
        this.coordinates = coordinates;
    }
    public Organism(char symbol, int strength, int initiative, Vector2 coordinates, World world)
    {

    }
    public final Vector2 GetCoordinates()
    {
        return coordinates;
    }
    public final void SetCoordinates(Vector2 newCoordinates)
    {
        this.coordinates = newCoordinates;
    }
    abstract public void Action();
    abstract public void Collision(Organism attacker, Vector2 tmp);
    abstract public void Breed();
    abstract public char GetSymbol();
    abstract public ImageIcon GetTexture();

    public final int GetStrength()
    {
        return strength;
    }
    public final void SetStrength(int newStrength)
    {
        this.strength = newStrength;
    }
    public final World GetWorld()
    {
        return world;
    }
    public final int GetAge()
    {
        return age;
    }
    public final void SetAge(int newAge)
    {
        this.age = newAge;
    }
    public final int GetMoveLength()
    {
        return moveLength;
    }
    public final int GetInitiative()
    {
        return initiative;
    }

}

