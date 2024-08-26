package org.example;

import org.example.SedgWayne.Bag;
import org.example.SedgWayne.Digraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Matcher {

    private List<String> people;
    private int[][] requests;
    private int N;
    private int numOfRooms;
    private int capacity;
    private int[] rooms;
    private Room[] assignments;
    private Map<String, Integer> nameToNum = new HashMap<>();
    private Map<Integer, String> numToName  = new HashMap<>();
    private Digraph g;
    private int ID = 0;

    public Matcher(List<String> people, int numOfRooms, int capacity){
        this.people = people;
        this.numOfRooms = numOfRooms;
        this.capacity = capacity;
        this.assignments = new Room[numOfRooms];
        for(int i = 0; i < numOfRooms; i++){
            assignments[i] = new Room(capacity);
        }
    }

    public Matcher(List<String> people, int numOfRooms1, int capacity1, int numOfRooms2, int capacity2){
        this.people = people;
        this.numOfRooms = numOfRooms1 + numOfRooms2;
        this.assignments = new Room[numOfRooms];
        for(int i = 0; i < numOfRooms; i++){
            if(i < numOfRooms1) assignments[i] = new Room(capacity1);
            else{ assignments[i] = new Room(capacity2);}
        }
    }

    public Matcher(String csvFile){
        if (csvFile == null) throw new IllegalArgumentException("argument is null");

        this.people = new ArrayList<>();
        System.out.println("Converting Data");
        String line, csvDelimiter = ","; // Modify this if your CSV file uses a different delimiter

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            //Process First Line
            line = br.readLine();
            this.N = Integer.parseInt(line);
            g = new Digraph(N); requests = new int[N][3];
            if (N < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be non-negative");

            //Process Second Line
            line = br.readLine();
            String[] data = line.split(csvDelimiter);
            rooms = new int[data.length];
            assignments = new Room[data.length];
            for(int i = 0; i < data.length; i++) {
                int c = Integer.parseInt(data[i]);
                rooms[i] = c;
                assignments[i] = new Room(c);
            }

            for(int i = 0; i < N; i++){
                line = br.readLine();
                data = line.split(csvDelimiter);
                for(int j = 0; j < data.length; j++) if(!nameToNum.containsKey(data[j])) {
                    //people.add(new Person(data[j]));
                    nameToNum.put(data[j], ID);
                    numToName.put(ID++, data[j]);
                }
                int person = nameToNum.get(data[0]);
                for(int j = 1; j < data.length; j++) {
                    int roomate = nameToNum.get(data[j]);
                    g.addEdge(person, roomate);
                    requests[person][j-1] = roomate;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bruteForceSolution(){
        /* Test all possible solutions and choose the correct one
         */
        BruteForce bf = new BruteForce(N, requests, rooms);
        List<List<Integer>> solution = bf.findOptimalSolution();
        System.out.println(displaySolution(solution));
    }



    /**
     * OBJECTIVE: Algorithm designed to try and maximize the number of requests fulfilled.
     * METRIC: A room is valued by a point sysem. Each request fulfilled amongst the group in the room is 1 point.
     * IMPLEMENTATION:
     *      Add every person to a queue
     *      Look to see if there first request is in a room, if they deserve to be added -> add them
     *          If there is space in the room -> add them
     *          If the room is full -> remove the most undeserving person
     *      Continue through the rest of the
     */
    /*public String maximizeRequests(){
        Queue<String> queue = new LinkedList<>();
        for(String p : people){
            queue.add(p);
        }
        while(!queue.isEmpty()){
            Person p = queue.remove();
            System.out.println("trying to queue " + p + " " + queue.size());
            boolean added = false;
            Room placeHere = null;
            int highestScore = 0;
            for(Person requested : p.getRequests()) {
                if (requested != null) {
                    System.out.println("" + p + " requested " + requested);
                    Room room = requested.getRoom();
                    if (room != null) {
                        //try to place him in this room
                        if (!room.isFull()) {
                            room.addPerson(p);
                            added = true;
                        } else {
                            Person kickedOut = room.deservesPlacement(p);
                            System.out.println("kicking out " + kickedOut);
                            if (kickedOut != p) {
                                queue.add(kickedOut);
                                added = true;
                                break;
                            }
                        }
                    } else {
                        System.out.println("" + requested + " is not in a room");
                    }
                }
            }

            if(!added) {
                System.out.println("Adding to empty spot" + p);
                addToNextEmptySpot(p);
            }
            added = false;
            System.out.println(displayRooms());
        }

        return displayRooms();

    }

    /*
    * OBJECTIVE: Algorithm designed to try and maximize the number of people who get atleast one request.
    * IMPLEMENTATION:
    *       use a back-tracing algorithm to progressively develop permutations until one is reached where
    *       every person receives atleast one request
    * */
    public String atleastOneRequestFulfilled(){
        Queue<String> queue = createQueue();
        return null;
    }

    public boolean addToNextEmptySpot(Person p){
        for(Room room: assignments){
            if(!room.isFull()){
                room.addPerson(p);
                System.out.print("added to this room");
                for(Person pson: room.residents) System.out.print(pson + ", ");
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public String displayRooms(){
        StringBuilder sb = new StringBuilder();
        sb.append("ROOM ASSIGNMENTS: ");
        int count = 1;
        for(Room room : assignments){
            sb.append("\n\tRoom " + count);
            for(Person p : room.residents) sb.append("\n\t\t" + p);
            count++;
        }
        return sb.toString();
    }

    public String displaySolution(List<List<Integer>> solution){
        StringBuilder sb = new StringBuilder();
        for(List<Integer> room : solution){
            System.out.println(room.size());
            for(int p: room) System.out.println(numToName.get(p));
            System.out.println();
        }
        return sb.toString();
    }
    public void displayRequests(){
        System.out.println(g.toString());
    }

    private Queue<String> createQueue(){
        Queue<String> queue = new LinkedList<>();
        for(String p : people){
            queue.add(p);
        }
        return queue;
    }



}
