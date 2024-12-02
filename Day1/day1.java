import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class day1 {

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        HashMap<Integer,Integer> frequencyL2 = new HashMap<>();
        
        //Read the file and add the values to arrayLists.
        try(BufferedReader bufferReader = new BufferedReader(new FileReader("input1.txt"))){
            String line;
            while((line = bufferReader.readLine()) != null){
                //System.out.println(line);
                String[] splitedLine = line.split("   ");
                list1.add(Integer.parseInt(splitedLine[0]));
                list2.add(Integer.parseInt(splitedLine[1]));
                frequencyL2.computeIfPresent(Integer.parseInt(splitedLine[1]),(key, value) -> value +1);
                frequencyL2.putIfAbsent(Integer.parseInt(splitedLine[1]), 1);

            }

        }catch(IOException io){
            io.printStackTrace();
        }

        //sort the lists.
        Collections.sort(list1);
        Collections.sort(list2);

        int sum = 0;
        for(int i = 0; i < list1.size(); i++){
            int dist = Math.abs(list1.get(i)-list2.get(i));
            sum +=dist;
        }
        //Difference
        System.out.println(sum);

        int similarityScore = 0;
        for(int i = 0; i < list1.size(); i++){
                similarityScore += (list1.get(i) * frequencyL2.getOrDefault(list1.get(i),0));
        }
        //Similarity
        System.out.println(similarityScore);
    }
}