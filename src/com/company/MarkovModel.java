package com.company;

import java.util.ArrayList;

// This class creates text based on N-characters
public class MarkovModel extends AbstractMarkovModel{
    private int n;

    public MarkovModel(int nChars) {
        n = nChars;
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);

        for(int i = 0; i < numChars-n; i++){
            ArrayList<String> follows = getFollows(key);

            if(follows.size() == 0){
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
