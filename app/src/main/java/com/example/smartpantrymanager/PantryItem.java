package com.example.smartpantrymanager;
public class PantryItem {
    public long id;
    public String name,unit,expiry;
    public double quantity;
    public PantryItem(long i,String n,double q,String u,String e){id=i;name=n;quantity=q;unit=u;expiry=e;}
}
