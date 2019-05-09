/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  To find the least amount of coins in inputted denominations to total an inputted amount.
 * @author    :  Jason Kalili
 * Date       :  2019-05-8
 * Description:  Program takes in a given set of coin denominations,seperated by commas, and finds the best possible combination
 *               of these coin denominations - the second input in array - to total inputted about of money.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker{
  private static final String usageMessage = "\n Please be sure that you are entering positive integers. " +
                                             "\n NOTE: Coin denominations are to be seperated by commas. " +
                                             "\n Usage: java DynamicChangeMaker <coin_denominations> <total_money>";
  private static final String BAD_DATA = "BAD DATA";
  private static final String IMPOSSIBLE = "IMPOSSIBLE";

  public static String[] coinDenominations;
  public static int[] validCoins;
  public static Tuple coins;
  public static Tuple[][] theTable;
  private static int[] data;
  private static Tuple temp;

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * Method that utilizes Dynamic Programming to find the least number of coins, from a given set
   * of coin values, that add up to the total target.
   *
   * @param denominations  int array containing an unordered set of coin values
   * @param target         int containing the target goal to add up to
   *
   * @return the Tuple containing amount of each coin (same order as denominations) to reach target
   *         in most optimal way
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

  public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target){
    // NOTE: Don't forget to reset Data to 0 after creating the respective Tuple for each Table location.


    if (target < 0) {
      System.out.println(BAD_DATA + "\n because target can't = 0");
      System.out.println(usageMessage);
      return Tuple.IMPOSSIBLE;
    }
    coins = new Tuple(denominations); // success //
    theTable = new Tuple[coins.length()][target + 1];
    data = new int[coins.length()];

    if (target == 0){
      return new Tuple(data);
    }
    for (int i = 0; i < denominations.length; i++) {
      if (denominations[i] < 0){
        System.out.println(BAD_DATA + "\n because input is negative");
        System.out.println(usageMessage);
        return Tuple.IMPOSSIBLE;
      }
      else if (denominations[i] == 0) {
        System.out.println(BAD_DATA + "\n because input can't contain 0");
        System.out.println(usageMessage);
        return Tuple.IMPOSSIBLE;
      }
      for (int j = 0; j < i; j++) {
        if (denominations[j] == denominations[i]) {
          System.out.println(BAD_DATA + "\n repeating args!");
          System.out.println(usageMessage);
          return Tuple.IMPOSSIBLE;
        }
      }
    }

    for (int i = 0; i < coins.length(); i++) {
      data[i] = 0;
    }

    for (int i = 0; i < coins.length(); i++) {
      for (int j = 0; j <= target; j++) {

        if (j == 0) {
          theTable[i][j] = new Tuple(data);
        }
        else if (j < coins.getElement(i)) {
          temp = Tuple.IMPOSSIBLE;
          if ( ( i > 0 ) && ( theTable[i-1][j].isImpossible() ) ){
            temp = temp;
          }
          else if ( ( i > 0 ) && ( ( temp.isImpossible() == true ) && ( theTable[i-1][j].isImpossible() == false ) ) ) {
            temp = theTable[i-1][j];
          }
          else if ( ( i > 0 ) && ( theTable[i-1][j].total() <= temp.total() ) ) {
            temp = theTable[i-1][j];
          }

          theTable[i][j] = temp;
        }
        else {
          data[i] = 1;
          temp = new Tuple(data);
          data[i] = 0;  // resets data to 0 for the next one

          if ( theTable[i][j - coins.getElement(i)].isImpossible() == true ){
            temp = Tuple.IMPOSSIBLE;
          }
          else{
            temp = temp.add( theTable[i][j - coins.getElement(i)] );
          }

          if ( ( i > 0 ) && ( theTable[i-1][j].isImpossible() ) ){
            temp = temp;
          }
          else if ( ( i > 0 ) && ( ( temp.isImpossible() == true ) && ( theTable[i-1][j].isImpossible() == false ) ) ) {
            temp = theTable[i-1][j];
          }
          else if ( ( i > 0 ) && ( theTable[i-1][j].total() <= temp.total() ) ) {
            temp = theTable[i-1][j];
          }

          theTable[i][j] = temp;

        }
      }
    }
    return theTable[coins.length()-1][target];
  }

//

  public static void main(String args[]) throws IllegalArgumentException{
    if (args.length > 2) {
      System.out.println(BAD_DATA + " \n too many args!");
      throw new IllegalArgumentException(usageMessage);
    }
    else if (args.length < 2){
      System.out.println(BAD_DATA + "\n not enought args!");
      throw new IllegalArgumentException(usageMessage);
    }
    else if (Integer.parseInt(args[1]) < 0) {
      System.out.println(BAD_DATA + "\n can't have total coin amount that is negative.");
      throw new IllegalArgumentException(usageMessage);
    }
    else if (Integer.parseInt(args[1]) == 0) {
      System.out.println(BAD_DATA + "\n can't have a total coin amount of 0.");
      throw new IllegalArgumentException(usageMessage);
    }

    coinDenominations = args[0].split(",");
    validCoins = new int[coinDenominations.length];

    for (int i = 0; i < coinDenominations.length; i++){
      if (Integer.parseInt(coinDenominations[i]) < 0){
        System.out.println(BAD_DATA + "\n because input arguments contain a negative number");
        System.out.println(usageMessage);
        System.exit(0);
      }
      else if (Integer.parseInt(coinDenominations[i]) == 0){
        System.out.println(BAD_DATA + "\n because input arguments contain a zero, if you have a coin, you don't have 0");
        throw new IllegalArgumentException(usageMessage);
      }

      validCoins[i] = Integer.parseInt(coinDenominations[i]);
    }


    Tuple result = makeChangeWithDynamicProgramming( validCoins, Integer.parseInt(args[1]) ) ;

    if (result.isImpossible() == true) {
      System.out.println("IMPOSSIBLE! \n Because you can't make " + args[1] + " cents with these coins");
    }
    else{
      System.out.println(args[1] + " cents can be made with " + Integer.toString( result.total() ) + " inputted coins in the most optimal way as follows: " );
      for (int i = 0; i < validCoins.length; i++) {
        System.out.println(  result.getElement(i)  + " x " + Integer.toString(validCoins[i]) + " cents");
      }
    }
    System.exit(0);
  }
}
