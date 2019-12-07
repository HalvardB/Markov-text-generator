package com.company;

import java.util.*;

// This class creates text based on the last character (1 char)
public class MarkovOne extends AbstractMarkovModel{

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        System.out.println(myText);
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);

        for(int i = 0; i < numChars-1; i++){
            ArrayList<String> follows = getFollows(key);

            if(follows.size() == 0){
                break;
            }

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }

    public String toString(){
        return "MarkovModel of order 1";
    }
}
