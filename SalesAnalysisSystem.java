import java.util.Scanner;
import java.io.File;
import java.io.IOException;
class SalesAnalysisSystem {
    public static void main(String[] args) {

        try{
            Scanner reader = new Scanner(System.in);
            
            System.out.println("Enter file address");
            String filePath = reader.nextLine();
            
            Scanner scan = new Scanner (new File(filePath));
            
            int lineCounters = 0;
            String input;
            char index;
            double[] digitCounters = new double[9];
            
            while(scan.hasNextLine()) {
                input = scan.nextLine();
                index = (firstDigit(input));
                digitCounters = putArray(index, digitCounters);
                lineCounters = lineCounters + 1;
            }
            
            lineCounters = lineCounters - 1;
            
            double[] frequency = new double[9];
            calculateFrequency(frequency, digitCounters, lineCounters);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static char firstDigit(String input) {
        char index = input.charAt(4);
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
}