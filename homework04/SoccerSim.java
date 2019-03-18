/* balls xloc, yloc, xvel, yvel, radius
constructor()
move()
updateVel()
isInMotion()
getLoc()
getSpeed()
toString()
main()
isInBounds()

/*
Jason Kalili
SoccerSim.java
Homework04
3/19/2019
*/

   public class SoccerSim {

     public SoccerSim() {
       super();
     }

     public void handleArgs(String[] args, Field field) {
       System.out.println("\n     Welcome to SoccerSimulation \n");    //args as numbers
       int getArgs = args.length;           //If args are divisible by four make balls.
       double[] doubleArgs = new double[getArgs];
       try{
         for(int i = 0; i < args.length; i++) {
           doubleArgs[i] = field.validateArgs(args[i]);
         }
       }
       catch(IllegalArgumentException e){
           System.out.println(e);
           System.out.println("Shutting Down");
           System.exit( 0 );
       }
       if(args.length % 4 == 0) {
         for(int j = 0; j < args.length; j += 4) {
           field.CreateBall(doubleArgs[j], doubleArgs[j + 1], doubleArgs[j + 2], doubleArgs[j + 3]);
         }
       }
       else if((args.length - 1) % 4 == 0) {
         for(int j = 0; j < doubleArgs.length - 1; j += 4) {
           field.CreateBall(doubleArgs[j], doubleArgs[j + 1], doubleArgs[j + 2], doubleArgs[j + 3]);
         }
         field.timeSlice = field.validateTimeSliceArg(doubleArgs[doubleArgs.length - 1]);
       }
       else{
         System.out.println("Please input four arguments for each ball, and one more for (optional) time slice. \n");
         System.exit( 1 );
       }
     }

     public static void main(String[] args) throws InterruptedException{
       SoccerSim ss = new SoccerSim();
       Field simField = new Field();
       ss.handleArgs(args, simField);
       Field.formatNums();
       boolean programState = true;
       while(programState == true){
         simField.RunSim();
         programState = false;
       }
     }
   }
