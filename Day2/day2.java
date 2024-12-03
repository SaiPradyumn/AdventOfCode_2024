import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class day2 {
    public static int checkIfSafe(ArrayList<Integer> report) {
        boolean incline = report.get(0) < report.get(1)?true:false;
        for(int i = 0; i < report.size()-1; i++){
            if(incline){
                if(report.get(i)<report.get(i+1) && (Math.abs(report.get(i)-report.get(i+1)))<=3){
                    continue;
                }else{
                    return 0;
                }
            }else{
                if(report.get(i)>report.get(i+1) && (Math.abs(report.get(i)-report.get(i+1)))<=3){
                    continue;
                }else{
                    return 0;
                }
            }
        }
        return 1;
    }

    public static int problemDampner(ArrayList<Integer> report){
        for(int i = 0; i < report.size();i++){
            ArrayList<Integer> temp = new ArrayList<>(report);
            //System.out.println(Arrays.asList(temp));
            temp.remove(i);
            int isSafe = checkIfSafe(temp);
            if(isSafe == 1){
                return 1;
            }
            //temp.clear();
        }
        return 0;
    }
    
    public static void main(String[] args) {
        int countSafe = 0;

        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
            String line; 
            int reportNo = 1;
            while((line = br.readLine())!= null){
                ArrayList<Integer> report = new ArrayList<>();
                String[] splitReport = line.split(" ");
                for(String s : splitReport){
                    //System.out.println(s);
                    report.add(Integer.parseInt(s));
                }
                int isSafe = checkIfSafe(report);
                //System.out.println("Firstcheck: " + isSafe);
                if(isSafe == 0){
                    isSafe = problemDampner(report);
                }
                //System.out.println("Report "+ reportNo++ + " is " + isSafe);
                countSafe += isSafe;
                //break;
            }

        }catch(IOException io){
            io.printStackTrace();
        }
        System.out.println(countSafe);
    }
}
