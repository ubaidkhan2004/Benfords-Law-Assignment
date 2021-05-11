//Importing necessary packages
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
class SalesAnalysisSystem {
    public static void main(String[] args) {
        
        try{
            //Initialize scanner
            Scanner reader = new Scanner(System.in); 
            //Asking user for sales file location
            System.out.println("Enter file path:");
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
            //Outputting the results of the first digits in the data set
            resultsFile(frequency);
        }
        //Catch errors due to the data file
        catch(IOException e) {
            e.printStackTrace();

        }
        

    }

    public static char firstDigit(String currentLine) {
        char index = currentLine.charAt(4);
        return index;
    }

    public static double[] putArray(char index, double[] digitCounters) {
       
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
    
    public static double[] calculateFrequency(double[] frequency, double[] digitCounters,int lineCounters) {
        for(int i = 0; i < digitCounters.length; i++) {
            frequency[i] = digitCounters[i]/lineCounters * 100;
            frequency[i] = Math.round(frequency[i] * 10.0) / 10.0;
        }

        return frequency;
    }

    public static void resultsFile(double[] frequency) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Where do you want to store the results file:");
        String fileLocation = reader.nextLine();
        
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation));
            bw.write("First digit, frequency \n");
            for(int i = 0; i < frequency.length; i++) {
                bw.write( i + 1 + "," + frequency[i] + "% \n");
            }
            bw.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}