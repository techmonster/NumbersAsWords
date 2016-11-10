/**
 * Created by nathanielholloway on 11/3/16.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumbersAsWords {
    static Map<Integer, String> numberMap = new HashMap<>();

    {
        numberMap.put(0, "Zero");
        numberMap.put(1, "One ");
        numberMap.put(2, "Two ");
        numberMap.put(3, "Three ");
        numberMap.put(4, "Four ");
        numberMap.put(5, "Five ");
        numberMap.put(6, "Six ");
        numberMap.put(7, "Seven ");
        numberMap.put(8, "Eight ");
        numberMap.put(9, "Nine ");
        numberMap.put(10, "Ten ");
        numberMap.put(11, "Eleven ");
        numberMap.put(12, "Twelve ");
        numberMap.put(13, "Thirteen ");
        numberMap.put(14, "Fourteen ");
        numberMap.put(15, "Fifteen ");
        numberMap.put(16, "Sixteen ");
        numberMap.put(17, "Seventeen ");
        numberMap.put(18, "Eightteen ");
        numberMap.put(19, "Nineteen ");
        numberMap.put(20, "Twenty ");
        numberMap.put(30, "Thirty ");
        numberMap.put(40, "Forty ");
        numberMap.put(50, "Fifty ");
        numberMap.put(60, "Sixty ");
        numberMap.put(70, "Seventy ");
        numberMap.put(80, "Eighty ");
        numberMap.put(90, "Ninety ");
    }

    StringBuilder getOnes(int num, StringBuilder stringBuilder, int count){
        if(num >count-1){
            num = num / count;
        }
        if(num<20)
         stringBuilder.append(numberMap.get(num));

        return stringBuilder;
    }

    StringBuilder getTens(int num, StringBuilder stringBuilder,int count){
        int r =0;
        if(num>count){
            num = ((num/count));
            if(num<10) {
                r = num * 10;
            } else{
                r = num;
            }
        }

        if(r>0) {
            stringBuilder.append(numberMap.get(r));
        }

        return stringBuilder;
    }

    StringBuilder getHundreds(int num, StringBuilder stringBuilder, int count){
        int[] numArray = new int[3];
        numArray[0] = num / count;

        if(numArray[0]>0) {
            stringBuilder.append(numberMap.get(numArray[0]));
            stringBuilder.append("Hundred ");
        }

        return stringBuilder;
    }

    public static void main(String[] args) {
        String s = "192132001";
        String pattern = "(\\d{1,9})";
        Pattern myPattern = Pattern.compile(pattern);
        Matcher m = myPattern.matcher(s);
        StringBuilder sb = new StringBuilder();


        if(m.find()) {
            int count = (m.regionEnd());

            int number = Integer.parseInt(m.group());

            NumbersAsWords numbersAsWords = new NumbersAsWords();
            System.out.println("Number is:"+number);
            System.out.println("Count is:"+count);

                switch(count) {
                    case 9:
                        sb=numbersAsWords.getHundreds(number, sb, 100000000);
                        number -= (number-(number % 100000000));
                    case 8:
                        sb=numbersAsWords.getTens(number, sb, 10000000);
                        number -= (number-(number % 10000000));
                    case 7:
                        sb=numbersAsWords.getOnes(number, sb, 1000000);
                        number -= (number-(number % 1000000));
                        sb.append("Million ");
                    case 6:
                        sb=numbersAsWords.getHundreds(number, sb, 100000);
                        number -= (number-(number % 100000));
                    case 5:
                         sb=numbersAsWords.getTens(number, sb, 10000);
                        number -= (number-(number % 10000));
                    case 4:
                        sb=numbersAsWords.getOnes(number, sb, 1000);
                        number -= (number-(number % 1000));
                        sb.append("Thousand ");
                    case 3:
                        sb=numbersAsWords.getHundreds(number, sb, 100);
                        number -= (number-(number % 100));
                    case 2:
                        sb=numbersAsWords.getTens(number, sb, 10);
                        number -= (number-(number % 10));
                    case 1:
                        sb=numbersAsWords.getOnes(number, sb, 1);
                        break;

                }

            if(number == 1 && count==1){
                sb.append(" Dollar");
            }
            else{
                sb.append(" Dollars");
            }


        }



        System.out.println(sb.toString());




    }
}
