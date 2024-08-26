package org.example;

import org.example.SedgWayne.Bag;

import java.util.List;

public class Person {
    private String name;
    private Bag<Person> requests;
    int ID = -1;
    private Room room;

    public Person(String name, Bag<Person> requests){
        this.name = name;
        this.requests = requests;
    }

    public Person(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public void setRequests(Bag<Person> requests){
        this.requests = requests;
    }

    public Bag<Person> getRequests(){
        return this.requests;
    }

    public String getName(){
        return this.name;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public Room getRoom(){
        return this.room;
    }

    public boolean requested(Person p){
        for(Person request: requests){
            if(p.equals(request)) return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
