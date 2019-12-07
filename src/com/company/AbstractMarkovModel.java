package com.company;

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);

    // Create an array of letters that follow the 'key'-letter(s)
    protected ArrayList<String> getFollows(String key){
        int pos = 0;
        ArrayList<String> follows = new ArrayList<String>();

        while(pos < myText.length()){
            int start = myText.indexOf(key, pos);

            if(start == -1){
                break;
            }

            if(start + key.length() >= myText.length()){
                break;
            }

            String next = myText.substring(start+key.length(), start+key.length()+1);
            follows.add(next);
            pos = start + key.length();
        }
        return follows;
    }
}
