/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Jason Kalili
 *  Date          :  1-24-2019
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
     if (isLeapYear(year) && month == 2) {
       return 29;
     }
      return (days[(int)month - 1]);
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return (month1==month2 && day1==day2 && year1==year2);
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     if (year1 == year2 && month1 == month2 && day1 == day2) {
     } else if (year1 > year2) {
       return 1;
     } else if (year2 > year1){
       return -1;
     } else if (month1 > month2){
       return 1;
     } else if (month2 > month1){
       return -1;
     } else if (day1 > day2){
       return 1;
     } else if (day2 > day1){
       return -1;
     }
   return 0;
}
  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      if (month >= 1 && month <= 12) {
          if(year >= 1 && year <= 9999) {
               if (day <= daysInMonth(month, year) && day > 0)
          return true;
       }
      }
    return false;
  }



  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
     String stringMonth;
      switch( month - 1 ) {
        case 0: stringMonth ="January";
          break;
        case 1: stringMonth ="February";
          break;
        case 2: stringMonth ="March";
          break;
        case 3: stringMonth ="April";
          break;
        case 4: stringMonth ="May";
          break;
        case 5: stringMonth ="June";
          break;
        case 6: stringMonth ="July";
          break;
        case 7: stringMonth ="August";
          break;
        case 8: stringMonth ="September";
          break;
        case 9: stringMonth ="October";
          break;
        case 10: stringMonth ="November";
          break;
        case 11: stringMonth ="December";
          break;
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
      return stringMonth;
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
    String stringDay;
      switch( day - 1 ) {
        case 0: stringDay ="Sunday";
          break;
        case 1: stringDay ="Monday";
          break;
        case 2: stringDay ="Tuesday";
          break;
        case 3: stringDay ="Wednesday";
          break;
        case 4: stringDay ="Thursday";
          break;
        case 5: stringDay ="Friday";
          break;
        case 6: stringDay ="Saturday";
          break;
     default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
      return stringDay;
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
//make year two long
     long y2 = year2;
     long dayCount = 0;
     long subbot = 0;
     long subtop = 0;
     long monthsbot = month1-1;
     long monthstop = 12;

     for (long i=0;i<(year2-year1)+1;i++){
         if (isLeapYear(y2) == true){
             dayCount = dayCount + 366;
         } else {
             dayCount = dayCount + 365;
           }
         y2 = y2 - 1;
     }

     if (month1 == 1){
         subbot = day1-1;
     } else {
         subbot = day1-1;
         for (long i=0; i<(month1-1); i++){
             subbot = subbot +  daysInMonth(monthsbot,year1);
             monthsbot = monthsbot - 1;
         }
     }

     if (month2 == 12){
         subtop = (daysInMonth(month2,year2)-day2);
     }else {
         subtop = (daysInMonth(month2,year2)-day2);
         for(long i=0; i<(12-month2); i++){
             subtop = subtop + daysInMonth(monthstop,year2);
             monthstop = monthstop - 1;
         }
     }

     dayCount = dayCount - (subtop + subbot)-1;
     if (dayCount < 0){
     	dayCount = dayCount * (-1);
     }
     	  return dayCount;
        }
     }
