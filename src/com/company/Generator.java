package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Generator {
    private World world;
    public Generator(World world){
        this.world = world;

    }
    public void Generate()
    {
        int additionalOrganisms = (world.GetMapSize().GetX() * world.GetMapSize().GetY())/40;
        int numberOfAllOrganisms = 1 + (2+additionalOrganisms) * 6 + (2+additionalOrganisms)*5;
        int eachRace = additionalOrganisms +2;
        int newX, newY;
        Vector2 coordinates = new Vector2();
        Organism newOrganism;

        for(int i = 0; i<numberOfAllOrganisms; i++)
        {
            newX = (int) ((Math.random() * world.GetMapSize().GetX()) % world.GetMapSize().GetX());
            newY = (int) ((Math.random() * world.GetMapSize().GetY()) % world.GetMapSize().GetY());
            coordinates.SetX(newX);
            coordinates.SetY(newY);
            if (i < eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Antelope(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= eachRace && i < 2 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Fox(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 2*eachRace && i < 3 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Sheep(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 3*eachRace && i < 4 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Tortoise(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 4*eachRace && i < 5 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Wolf(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 5*eachRace && i < 6 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Dandelion(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 6*eachRace && i < 7 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new DeadlyNightshade(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 7*eachRace && i < 8* eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Grass(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 8*eachRace && i < 9 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Guarana(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 9*eachRace && i < 10 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Hogweed(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i >= 10*eachRace && i < 11 * eachRace)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Cybersheep(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }
            if (i == numberOfAllOrganisms - 1)
            {
                if (world.GetField(newX, newY) == null)
                {
                    newOrganism = new Human(coordinates, world);
                    world.AddOrganism(newOrganism);
                }
                else
                    i--;
            }

        }



    }
    public void GenerateSet()
    {
        Organism newOrganism;
        try
        {
            File myObj = new File("gameinfo.txt");
            Scanner myReader = new Scanner(myObj);
            String cache = myReader.nextLine();
            String data;
            while(myReader.hasNextLine()) {
                data = myReader.nextLine();
                String[] words = data.split("\\s");
                System.out.println(words[0]);
                if(words[0].equals("A"))
                {
                    System.out.println("added an");
                    newOrganism = new Antelope(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("F"))
                {
                    newOrganism = new Fox(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("S"))
                {
                    newOrganism = new Sheep(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("T"))
                {
                    newOrganism = new Tortoise(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("W"))
                {
                    newOrganism = new Wolf(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("D"))
                {
                    newOrganism = new Dandelion(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("N"))
                {
                    newOrganism = new DeadlyNightshade(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("L"))
                {
                    newOrganism = new Grass(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("G"))
                {
                    newOrganism = new Guarana(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("H"))
                {
                    newOrganism = new Hogweed(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("#"))
                {
                    newOrganism = new Human(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }
                else if(words[0].equals("C"))
                {
                    newOrganism = new Cybersheep(Integer.parseInt(words[1]),Integer.parseInt(words[2]), world, Integer.parseInt(words[3]),Integer.parseInt(words[4]));
                    world.AddOrganism(newOrganism);
                }

            }
        }
        catch(FileNotFoundException error)
        {
            System.out.println("AN ERROR OCCURED WHILE READING A FILE");
        }
    }
}
