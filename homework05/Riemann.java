/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Riemann.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Jason Kalili
 *  Date written  :  2019-04-04
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 2.  Includes the following
 *  Notes: Hi professor, I cant get my numbers to output the wrong thing and I ran out of time to keep going.
 *         currently very frusterated and cant get the right answers so just a forewarning.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.lang.Math;
 import java.util.Arrays;

 class Riemann{
   public String functionArg;
   public double[] coefficients;
   public double lowerBound;
   public double upperBound;
   public double percentage;

 public void validateArguments(String[] args) {
   if(args.length < 3){
     System.out.println("Need more Arguments!");
   if(lowerBound>upperBound){
     System.out.println("Upper Bound must have greater value!");
     }
   }
 }

 public void CheckPercent(String[] args){

   int in = args.length-1;
   if(args[in].contains("%")){
     lowerBound = Double.parseDouble(args[args.length - 3]);
     upperBound = Double.parseDouble(args[args.length - 2]);
     this.coefficients = new double [args.length - 3];
     for(int j = 1; j < args.length - 3; j++){

       this.coefficients[j] = Double.parseDouble(args[j]);
       System.out.println("Coefficient: " + this.coefficients[j]);
     }
     percentage = Double.parseDouble(args[in].substring(0,args[in].length() -1));
       System.out.println("Percentage: " + percentage);
     }
   else{
     lowerBound = Double.parseDouble(args[args.length - 2]);
     upperBound = Double.parseDouble(args[args.length - 1]);
     this.coefficients = new double [args.length - 2];

     for(int j = 1; j < args.length - 2; j++){

       this.coefficients[j] = Double.parseDouble(args[j]);
       System.out.println("Coefficient: " + this.coefficients[j]);
     }
     percentage = 0.01;
     System.out.println("Percentage: " + percentage);
   }
 }

 public double function(String [] args){
   double area = 0;
   double sumOfArea = 0;
   double oldSumOfArea = -1.0;
   double newSumofArea = -5.0;
   int n = 1;
   double width = (upperBound - lowerBound)/n;
   double yVal = 0.0;
   double midpoint = 0.0;

   switch(args[0]){
   case "poly":
   do {
     for(int i = 0; i <= n; i++){
       midpoint = (width / 2.0) + lowerBound + (width * i);
       yVal = 0.0;
       for(int j = 0; j < coefficients.length-1; j++){
         yVal += coefficients[j] * Math.pow (midpoint, j);
        }
       area = width * yVal;
       System.out.println("Area: "+ area);
       System.out.println("Y Value: "+yVal);
       System.out.println("Midpoint: " + midpoint);
       System.out.println("Number of Blocks: " + n );
       sumOfArea += area;
      }
     oldSumOfArea = newSumofArea;
     System.out.println("Old Sum of Area: " + oldSumOfArea);
     newSumofArea = sumOfArea;
     System.out.println("New Sum of Area: " + newSumofArea);
     System.out.println("Sum of Area: " + sumOfArea);
     n++;
   } while (1 - (Math.abs(newSumofArea/oldSumOfArea))>percentage);
 break;

   /*
   case "sin":
   do{
     for(int i = 0; i<= n; i++){
       midpoint = (width/2.0) + lowerBound + (width*i);
       yVal = 0;
       for(int j = 0; j< coefficients.length -1; j++){
         yVal +=
       }
     }
   }

   break;
   case "cos":

   break;

   case "tan":
   break;
   */

   default:
   throw new IllegalArgumentException("Equation type is Invalid");
 }
   System.out.println("Final Sum of Area: " + sumOfArea);
   return sumOfArea;
 }


 public static void main(String [] args){
   Riemann r1 = new Riemann();


   r1.validateArguments(args);
   r1.CheckPercent(args);
   r1.function(args);
   }
 }
