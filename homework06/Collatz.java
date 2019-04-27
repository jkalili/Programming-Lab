//if even divide by two
//if odd then
import java.util.ArrayList;
import java.util.Arrays;


//I wrote this code and it does not work with my program but I confirmed with
//other people and its supposed to be working. It JUST DOESNT ADD! Im pulling my hair out
//about this because adding works otherwise but just not here. I wrote this code
//and it works... im sure of it, but just not here for some reason. UGHH Out of time:(
public class Collatz{
   public static void main (String[] args) {
     int count = 0;
     BrobInt value = new BrobInt(args[0]);
     while (value.compareTo(BrobInt.ONE) == 1) {
       System.out.println("Value:  " + value);
       if (value.remainder(BrobInt.TWO) != BrobInt.ZERO) {
         //I cant figure out why it doesnt let me use add here!
         value = (value.multiply(BrobInt.THREE)).add(BrobInt.ONE);
       }else{
         value = value.divide(BrobInt.TWO);
       }
       count++;
       }
       System.out.println("Steps:  " + count);
     }
  }
