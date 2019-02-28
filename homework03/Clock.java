/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Jason Kalili
 *  Date written  :  2019-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.text.DecimalFormat;

  public class Clock{
    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
    private static final double INVALID_ARGUMENT_VALUE = -1.0;
    private static final double MAXIMUM_DEGREE_VALUE = 360.0;
    private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
    private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
    public double seconds = 0;
    public double minutes = 0;
    public double hours = 0;
    private double hourAngle = 0;
    private double minuteAngle = 0;
    public double totalSeconds = 0;
    public double slice = 0;
    public double angle = 0;

     /**
     *  Constructor goes here
     */
 	public Clock(double angle, double slice){
 		this.seconds = 0;
 		this.minutes = 0;
 		this.hours = 0;
 		this.slice = slice;
 		this.angle = angle;
 		this.totalSeconds = totalSeconds;
 		this.hourAngle = 0;
 		this.minuteAngle = 0;
 	}
 	/**
    *  Methods go here
    *
    *  Method to calculate the next tick from the time increment
    *  @return double-precision value of the current clock tick
    */
    public double tick() {
   	  this.totalSeconds += this.slice;
 	  return totalSeconds;
    }

   /**
    *  Method to validate the angle argument
    *  @param   argValue  String from the main programs args[0] input
    *  @return  double-precision value of the argument
    *  @throws  NumberFormatException
    */
    public double validateAngleArg( String argValue ) throws NumberFormatException {

 		 double validateAng = Double.parseDouble(argValue);
 		 if(validateAng < MAXIMUM_DEGREE_VALUE && validateAng > 0){
 			 return validateAng;
 		 }else{
 			 validateAng = INVALID_ARGUMENT_VALUE;
 		 }
 	return validateAng;
 	}

   /**
    *  Method to validate the optional time slice argument
    *  @param  argValue  String from the main programs args[1] input
    *  @return double-precision value of the argument or -1.0 if invalid
    *  note: if the main program determines there IS no optional argument supplied,
    *         I have elected to have it substitute the string "60.0" and call this
    *         method anyhow.  That makes the main program code more uniform, but
    *         this is a DESIGN DECISION, not a requirement!
    *  note: remember that the time slice, if it is small will cause the simulation
    *         to take a VERY LONG TIME to complete!
    */
    public double validateTimeSliceArg( String argValue ) {
       double validateSlice = Double.parseDouble(argValue);
 	  if(validateSlice > 0 && validateSlice < 1800){
 		  return validateSlice;
    }else{
 	   return INVALID_ARGUMENT_VALUE;
    }
    }

   /**
    *  Method to calculate and return the current position of the hour hand
    *  @return double-precision value of the hour hand location
    */
    public double getHourHand() {
       double hourAngle = (0.008333 * totalSeconds);
 	  if(hourAngle > 360){
 		  hourAngle = hourAngle % 360;
 		if(hourAngle > 180){
 		  hourAngle = (360 - hourAngle);
 		  }
 	  }
 	  return hourAngle;
    }
   /**
    *  Method to calculate and return the current position of the minute hand
    *  @return double-precision value of the minute hand location
    */
    public double getMinuteHand() {
       double minuteAngle = (totalSeconds * 0.1);
 	  if(minuteAngle > 360){
 		  minuteAngle = (minuteAngle % 360);
 		if(minuteAngle > 180){
 			minuteAngle = (360 - minuteAngle);
 		  }
 	  }
 	  return minuteAngle;
    }

   /**
    *  Method to calculate and return the angle between the hands
    *  @return double-precision value of the angle between the two hands
    */
    public double getHandAngle() {
        this.angle = Math.abs( getMinuteHand() - getHourHand() );
 	   if(this.angle > 180){
 		   this.angle = (360 - this.angle);
 	   }
 	   return this.angle;
    }


   /**
    *  Method to return a String representation of this clock
    *  @return String value of the current clock
    */
    public String toString() {
 //Dec Format df = "#0.0000"
 	  if(totalSeconds < 60){
 		  seconds = totalSeconds;
 	  }else{
 		  seconds = Math.floor(totalSeconds % 60);
 	  }
 	  if(totalSeconds < 3600){
 		  minutes = Math.floor(totalSeconds / 60);
 	  }else{
 		  minutes = Math.floor(totalSeconds % 60);
 	  }
 	  if(totalSeconds < 43200){
 		  hours = Math.floor(totalSeconds / 3600);
 	  }
       String timeString = Double.toString(hours)+":"+Double.toString(minutes)+":"+ Double.toString(seconds);
       return timeString;
    }

   /**
    *  The main program starts here
    *  remember the constraints from the project description
    *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
    *  be sure to make LOTS of tests!!
    *  remember you are trying to BREAK your code, not just prove it works!
   */

   // tests
    public static void main( String args[] ) {
      System.out.println( "\n" );
      System.out.println( "  Creating a new clock with angle 0 and ts 90: " );
         Clock clock1 = new Clock(0,90.0);
         System.out.println( "    New clock created: " + clock1.toString() );
         System.out.println( "    Testing validateAngleArg(0)....");
         System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
         try { System.out.println( (0 == clock1.validateAngleArg( "0" )) ? " - got   0.0" : " - no joy" ); }
         catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println( "    Testing validateTimeSliceArg(0.0)....");
         System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
         try { System.out.println( (90.0 == clock1.validateTimeSliceArg( "90.0" )) ? " - got   90.0" : " - no joy" ); }
         catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }


       System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                           "--------------------------\n" );
       System.out.println( "  Creating a new clock with angle 90 and ts 90: " );
       Clock clock = new Clock(90.0,90.0);
       System.out.println( "    New clock created: " + clock.toString() );
       System.out.println( "    Testing validateAngleArg(90.0)....");
       System.out.print( "      sending '  90.0 degrees', expecting double value   90.0" );
       try { System.out.println( (90.0 == clock.validateAngleArg( "90.0" )) ? " - got   90.0" : " - no joy" ); }
       catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
 	  System.out.println( "    Testing validateTimeSliceArg(90.0)....");
       System.out.print( "      sending '  90.0 degrees', expecting double value   90.0" );
       try { System.out.println( (90.0 == clock.validateTimeSliceArg( "90.0" )) ? " - got   90.0" : " - no joy" ); }
       catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }


 	  System.out.println( "\n" );
 	  System.out.println( "  Creating a new clock with angle 1000 and ts 1000 " );
       Clock clock2 = new Clock(1000,1000);
       System.out.println( "    New clock created: " + clock1.toString() );
       System.out.println( "    Testing validateAngleArg(1000)....");
       System.out.print( "      sending '  1000 degrees', expecting double value   1000" );
       try { System.out.println( (1000.00 == clock2.validateAngleArg( "1000" )) ? " - got   1000" : " - no joy" ); }
       catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
 	  System.out.println( "    Testing validateTimeSliceArg(1000)....");
       System.out.print( "      sending '  1000 degrees', expecting double value   1000" );
       try { System.out.println( (1000.00 == clock2.validateTimeSliceArg( "1000" )) ? " - got   1000" : " - no joy" ); }
       catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    }
 }
