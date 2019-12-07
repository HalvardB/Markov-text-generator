package com.company;

import java.util.ArrayList;
import java.util.HashMap;

// A more efficient class where each character combination is stored in a HashMap for quicker processing.
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int n;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int nChars) {
        n = nChars;
    }

    public void setTraining(String s, int n) {
        myText = s.trim();
        buildMap(n);
    }

    public void buildMap(int n){
        map = new HashMap<String, ArrayList<String>>();
        // System.out.println(myText);

        for(int i = 0; i < myText.length() - (n - 1); i++){
            String key = myText.substring(i, i + n);

            if(!map.containsKey(key)){
                ArrayList<String> follows = getFollows(key);
                map.put(key, follows);
            }
        }
        // System.out.println("Text size " + myText.length());
        // System.out.println("Map size: " + map.size());
    }

    // Get random text from N-characters
    public String getRandomText(int numChars){

        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);

        for(int i = 0; i < numChars-n; i++){
            ArrayList<String> follows = map.get(key);

            if(follows == null || follows.size() == 0){
                break;
            }

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }

    public String toString(){
        return "MarkovModel of order " + n;
    }
}
