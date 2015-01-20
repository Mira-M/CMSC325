/*
 * Project: Homework 1 
 * CMSC 325 - Spring 2015
 * Shamireya Mollar 
 * Professor Wiseman
 * Due: January 18, 2015
 * 
 * Purpose:
 * 
 * The FileManager Class takes the toss results and saves 
 * the data to a file. 
 * The FileManager Class also reads the saved data from the 
 * file for future usage.
 */
package mollar_homework1_cmsc325;

import java.io.*;
import java.util.*;

public class AutoFileManager implements Serializable {
    final String resultStringFile = "resultData.ser";
    final String resultIntFile = "resultIntData.ser";
    ArrayList<String> resultDataString;
    ArrayList<Integer> resultDataInt;
    
    public AutoFileManager ()
    {
    }//Constructor FileManager()
    
    
    public void saveRandom(ArrayList<String> resultDataString) throws IOException 
    {
        FileOutputStream fileOutput = new FileOutputStream(resultStringFile);
        this.resultDataString = resultDataString;
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(resultDataString);
        objectOutput.close();
        fileOutput.close();    
    }// save()
    
    public void saveProb(ArrayList<Integer> resultDataInt) throws IOException 
    {
        FileOutputStream fileOutput = new FileOutputStream(resultIntFile);
        this.resultDataInt = resultDataInt;
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
        objectOutput.writeObject(resultDataInt);
        objectOutput.close();
        fileOutput.close();    
    }// save()
    
    public void readRandom() throws IOException, ClassNotFoundException
    {
        FileInputStream fileInput = new FileInputStream(resultStringFile);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        resultDataString = (ArrayList <String>)objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }
    
    public void readProb() throws IOException, ClassNotFoundException
    {
        FileInputStream fileInput = new FileInputStream(resultIntFile);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        resultDataInt = (ArrayList <Integer>)objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }
    
    public ArrayList<String> getDataRandom()
    {
        return resultDataString;
    }//getData()
    
    public ArrayList<Integer> getDataProb()
    {
        return resultDataInt;
    }
  
}//Class FileManager
