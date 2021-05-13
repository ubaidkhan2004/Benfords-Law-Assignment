/*
 * Name: Dennis and Ubaid
 * Date: May 11, 2021
 * Teacher: Mr.Ho
 * Description: This program determines if sales fraud is likely to be occured or not 
 */

//Importing necessary packages
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SalesAnalysisSystem extends JPanel {
    //Necessary global variables to display and make the graph
    private double[] values;
    private String[] names;
    private String title;
    public static void main(String[] args) {
        
        try{
            //Initialize scanner
            Scanner reader = new Scanner(System.in); 
            //Asking user for sales file location
            System.out.println("Enter sale file location:");
            String filePath = reader.nextLine();

            Scanner scan = new Scanner (new File(filePath));
            
            //Counts the amount of numbers in the file
            int lineCounters = 0;
            //Current line the scanner is on
            String currentLine;
            //What the first digit is
            char index;
            //Array to store the amount of times a certain digit occurs
            double[] digitCounters = new double[9];
            
            //While scanner is reading the file
            while(scan.hasNextLine()) {
                currentLine = scan.nextLine();
                //Find the first digit
                index = (firstDigit(currentLine));
                //Add the first digit to the counting array
                digitCounters = putArray(index, digitCounters);
                //Add one each time a line is read
                lineCounters = lineCounters + 1;
            }

            //Subtract initial line
            lineCounters = lineCounters - 1;
            //The percentage of each digit appearing in the data set
            double[] frequency = new double[9];
            //Calculating the percentage of each digit appearing in the data set
            calculateFrequency(frequency, digitCounters, lineCounters);
            //Outputting results after analyzing the data
            printResults(frequency);
            
            //Outputting the results of the first digits in a csv file
            resultsFile(frequency);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    /*
     *@Description:It will find the first digit of the sales
     *@Parameters: String currentLine
     *@Return: It will return an char
    */
    public static char firstDigit(String currentLine) {
        //Find element at index 4
        char index = currentLine.charAt(4);
        return index;
    }
    /*
     *@Description:It will populate the array accodingly to what the first digit is
     *@Parameters: char index, double[] digitCounters
     *@Return: It will return an array with how many times a first digit appeared 
    */
    public static double[] putArray(char index, double[] digitCounters) {
        //Deciding what the index is and accordingly adding it to the corresponding array index
        switch(index) {
            
            case '1':
            ++digitCounters[0];
            break;
        
            case '2':
            ++digitCounters[1];
            break;
		
            case '3':
            ++digitCounters[2];
            break;
		
            case '4':
            ++digitCounters[3];
		    break;
		
            case '5':
            ++digitCounters[4];
		    break;
		
            case '6':
            ++digitCounters[5];
		    break;
		
            case '7':
            ++digitCounters[6];
		    break;
		
            case '8':
            ++digitCounters[7];
		    break;
		
            case '9':
		    ++digitCounters[8];
		    break;
        } 
        
        return digitCounters;
    }
    /*
     *@Description:It will calculate the frequency of each first digit appearing and inputting it into an array
     *@Parameters: double[] frequency, double[] digitCounters, int lineCoutners
     *@Return: It will return the array which keeps the frequency of the first digits
    */
    public static double[] calculateFrequency(double[] frequency, double[] digitCounters,int lineCounters) {
        for(int i = 0; i < digitCounters.length; i++) {
            //Calculating frequency for each first digit
            frequency[i] = digitCounters[i]/lineCounters * 100;
            //Rounding frequency for each first digit
            frequency[i] = Math.round(frequency[i] * 10.0) / 10.0;
        }

        return frequency;
    }
    /*
     *@Description:This will output the csv file which contains the results of the data analysis
     *@Parameters: double[] frequency
     *@Return: It as no return as it is a void method
    */
    public static void resultsFile(double[] frequency) {
        //Initialize scanner
        Scanner reader = new Scanner(System.in);
        //Ask where the user wants to store the results file
        System.out.println("Where do you want to store the results file:");
        String fileLocation = reader.nextLine();
        
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation));
            //Writing the results in the csv file
            bw.write("First digit, frequency \n");
            for(int i = 0; i < frequency.length; i++) {
                bw.write( i + 1 + "," + frequency[i] + "% \n");
            }
            bw.close();
        }
        //Handle any errors which are experinced due to making the file
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    /*
     *@Description:This will display the results and tell the user if the data set passed the benford's law or not
     *@Parameters: double[] frequency
     *@Return: It as no return as it is a void method
    */
    public static void printResults(double[] frequency) {
        //printing visual repersentation of the reults
        System.out.println("\nFirst digit,frequency");
        
        for(int i = 0; i < frequency.length; i++) {
            System.out.println(i + 1 + " = " + frequency[i] + "%");
        }
         //Determining if sales fraud was likely to occur in the data set or not
        if(frequency[0] > 29 && frequency[0] < 32) {
            System.out.println("\nAccording to benford's law, there is no sign of sales fraud occuring.\n");
        }
        else{
            System.out.println("\nThe data dose not pass the benford's law and therefore it is likely that fraud occured.\n");
        }
    }
}