public class CountTheDays {
  public static void main (String[]args) {
    try {
      long month1 = Long.parseLong(args[0]);
      long day1 = Long.parseLong(args[1]);
      long year1 = Long.parseLong(args[2]);
      long month2 = Long.parseLong(args[3]);
      long day2 = Long.parseLong(args[4]);
      long year2 = Long.parseLong(args[5]);
        System.out.println(CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2) + " days");
    }
    catch (NumberFormatException nfe) {
      System.out.println("Input is not valid!");
    }
  }
}
