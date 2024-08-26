package org.example;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int capacity;
    List<Person> residents;

    public Room(int capacity){
        this.capacity = capacity;
        residents = new ArrayList<>();
    }

    public boolean isFull(){
        if(capacity == residents.size())System.out.println("Room is full");
        return capacity == residents.size();
    }

    private boolean inRoom(Person p){
        for(Person res : residents) if(p == res) return false;
        return true;
    }

    public boolean addPerson(Person p){
        assert(p.getRoom() == null);
        assert(!inRoom(p));
        if(p == null || residents.size() == capacity) {
            System.out.println("ADDED TO FULL ROOM");
            return false;
        }
        residents.add(p);
        p.setRoom(this);
        return true;
    }

    public boolean removePerson(Person p){
        residents.remove(p);
        p.setRoom(null);
        return true;
    }

    // What is the value of placing person p into this room
    // A high value means they should be in the room
    // Each person who requested them is one point and each person who they requested is one point
    private int calculateRoomValue(){
        int value = 0;
        for(Person res : residents){
            for(Person other: residents)
                if(res!=other && other.requested(res)) value++;
        }
        return value;
    }

    // Replace the person leaving with the person entering and calculate the room value.
    private int calculateSwap(Person entering, Person leaving){
//      this doesn't calculate value of friends that remain in the room
        int value = 0;
        Room temp = new Room(this.capacity);
        for(Person res : residents)
            if(!res.equals(leaving)) temp.addPerson(res);
        temp.addPerson(entering);
        value = temp.calculateRoomValue();
        return value;
    }

    // returns the person that is kicked out of the room
    public Person deservesPlacement(Person p){
        System.out.print("Current Room: ");
        for(Person pson: residents) System.out.print(pson + ", ");
        System.out.println();

        int currentValue = calculateRoomValue();
        Person removeThem = p;
        for(Person kickOut: residents){
            int newValue = calculateSwap(p,kickOut);
            if(newValue > currentValue){
                removeThem = kickOut;
                System.out.println("As of now, " + kickOut + " is getting kicked out");
                currentValue = newValue;
            }
        }
        //kick out person from room and add in new person
        if(removeThem != p){
            removePerson(removeThem);
            addPerson(p);
        }
        System.out.print("Current Room end: ");
        for(Person pson: residents) System.out.print(pson + ", ");
        System.out.println();
        return removeThem;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Room");
        for(Person p : this.residents) sb.append("\n\t" + p);
        return sb.toString();
    }



}
