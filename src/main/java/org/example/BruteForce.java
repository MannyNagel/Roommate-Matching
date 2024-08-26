package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BruteForce {

    private int[][] requests;
    private int N;
    int[] rooms;
    public BruteForce(int N, int[][] requests, int[] rooms){
        this.requests = requests;
        this.N = N;
        this.rooms = rooms;
    }

    public List<List<Integer>> findOptimalSolution(){
        //Find all combinations
        List<List<List<Integer>>> allCombinations = findCombinations();

        //Score all Combinations, keeping the highest
        List<List<Integer>> optimal = null;
        int highScore = 0;

        for(List<List<Integer>> combo: allCombinations){
            int value = score(combo);
            optimal = highScore >= value ? optimal:combo;
        }

        return optimal;
    }

    public List<List<List<Integer>>> findCombinations(){
        List<List<List<Integer>>> result = new ArrayList<>();
        List<List<Integer>> currentCombination = new ArrayList<>();
        for (int size : rooms) {
            currentCombination.add(new ArrayList<>());
        }
        backtrack(result, currentCombination,0,0);
        return result;
    }

    //copied and edited from chatGPT
    private void backtrack(List<List<List<Integer>>> result, List<List<Integer>> currentCombination, int index, int roomIndex) {
        if (index == N) {
            result.add(cloneCombination(currentCombination));
            return;
        }

        for (int i = roomIndex; i < rooms.length; i++) {
            if (currentCombination.get(i).size() < rooms[i]) {
                currentCombination.get(i).add(index);
                backtrack(result, currentCombination, index + 1, i);
                currentCombination.get(i).remove(currentCombination.get(i).size() - 1);
            }
        }
    }

    private static List<List<Integer>> cloneCombination(List<List<Integer>> combination) {
        List<List<Integer>> newCombination = new ArrayList<>();
        for (List<Integer> group : combination) {
            newCombination.add(new ArrayList<>(group));
        }
        return newCombination;
    }

    private int score(List<List<Integer>> assignment){
        int value = 0;
        for(List<Integer> room : assignment)
            for(int res : room)
                for(int other: room)
                    if(res!=other && (requests[res][0] == other || requests[res][1] == other
                            || requests[res][2] == other)) value++;
        return value;
    }
}
