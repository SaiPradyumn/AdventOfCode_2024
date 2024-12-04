import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3 {

    static class Pair{
        int key;
        String value;
        Pair(int key, String value){
            this.key = key;
            this.value = value;
        }
        public int getKey(){
            return this.key;
        }
        public String getValue(){
            return this.value;
        }
    }


    public static void main(String[] args) {
        String regex_mul = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        String regex_do = "do\\(\\)";
        String regex_dont = "don't\\(\\)";
        Pattern pattern_mul = Pattern.compile(regex_mul);
        Pattern pattern_do = Pattern.compile(regex_do);
        Pattern pattern_dont = Pattern.compile(regex_dont);
        long total = 0; 
        boolean enable = true;
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                // Compare based on the Integer value (first element of the pair)
                return Integer.compare(p1.getKey(), p2.getKey());
            }
        });

        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
            String line;
            //manipulated the input to be in a single line. need to change the logic to let it use multiple lines.  
            while((line = br.readLine())!= null){
              Matcher matcher_mul = pattern_mul.matcher(line);
              Matcher matcher_do = pattern_do.matcher(line);
              Matcher matcher_dont = pattern_dont.matcher(line);
              while(matcher_mul.find()){
                //total += Integer.parseInt(matcher_mul.group(1)) * Integer.parseInt(matcher_mul.group(2));// PART_1
                minHeap.add(new Pair(matcher_mul.start(), matcher_mul.group()));
              }
              while (matcher_do.find()) {
                minHeap.add(new Pair(matcher_do.start(), matcher_do.group()));
              }
              while (matcher_dont.find()) {
                minHeap.add(new Pair(matcher_dont.start(), matcher_dont.group()));
              }
            }
        }catch(IOException io){
            io.printStackTrace();
        }
        //System.out.println(total);

        while (!minHeap.isEmpty()) {
            Pair pair = minHeap.poll();
            if(pair.getValue().contains("don")){
                enable = false;
            }else if(pair.getValue().contains("do")){
                enable = true;
            }else if(pair.getValue().contains("mul") && enable){
                Matcher mul = pattern_mul.matcher(pair.getValue());
                while (mul.find()) {
                    total += Integer.parseInt(mul.group(1)) * Integer.parseInt(mul.group(2));
                }
            }else{
                continue;
            }
        }
        System.out.println(total);
    }
}
