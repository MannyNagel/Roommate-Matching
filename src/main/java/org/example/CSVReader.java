package org.example;
import org.example.SedgWayne.Bag;
import org.example.SedgWayne.Digraph;
import org.example.SedgWayne.In;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CSVReader {
    String csvFile;
    ArrayList<String[]> array;
    Digraph graph;
    In input;
    public CSVReader(String csvFile){
        this.csvFile = csvFile;
        System.out.println("Converting Data");
        array = new ArrayList<>();
        convertTo2DArray();
        convertToIn();
    }

    private void convertToIn(){

    }
    private void convertTo2DArray(){
        String line;
        boolean firstLine = true;
        String csvDelimiter = ","; // Modify this if your CSV file uses a different delimiter

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if(!firstLine) {
                    String[] data = line.split(csvDelimiter);
                    array.add(data);
                    System.out.println("Processing Data...");
                    // Process the data in each row
                    for (String value : data) {
                        System.out.print(value + " ");
                    }
                    System.out.println(); // Move to the next line
                }
                else{
                    firstLine = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bag<Person> makeListOfPeople(){
        HashMap<String,Person> allPeople = new HashMap();
        Bag<Person> people = new Bag<>();
        for(int i = 0; i < array.size(); i++){
            String name = array.get(i)[0];
            Person p = new Person(name, 0); //TODO: This is broken
            allPeople.put(name,p);
            people.add(p);
        }

        for(int i = 0; i < array.size(); i++){
            Bag<Person> requests = new Bag<>();
            for(int j = 1; j < array.get(i).length; j++){
                requests.add(allPeople.get(array.get(i)[j]));
            }
            //people. (i).setRequests(requests);
        }
        return people;
    }

    
    public static void main(String[] args) {
        String csvFile = "C:/Users/manny_uayvx1y/OneDrive - Yeshiva University/CS/DS/Marcus_Nagel_800752883/DataStructures/project/RoomMatching/example.csv";
        /*String line;
        String csvDelimiter = ","; // Modify this if your CSV file uses a different delimiter

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvDelimiter);

                // Process the data in each row
                for (String value : data) {
                    System.out.print(value + " ");
                }
                System.out.println(); // Move to the next line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        CSVReader reader = new CSVReader(csvFile);
        System.out.println();
        System.out.println("FINAL LIST");
        for(int i = 0; i < reader.array.size(); i++){
            for(int j = 0; j < reader.array.get(i).length; j++){
                System.out.print(reader.array.get(i)[j] + ",");
            }
            System.out.println();
        }

    }
}
