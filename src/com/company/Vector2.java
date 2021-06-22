package com.company;

public class Vector2 {
    private int x;
    private int y;
    public void SetX(int x)
    {
        this.x = x;
    }
    public void SetY(int y)
    {
        this.y = y;
    }
    public int GetX()
    {
        return x;
    }
    public int GetY()
    {
        return y;
    }
    Vector2()
    {

    }
    Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
