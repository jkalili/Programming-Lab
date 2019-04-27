/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* File name  :  BrobInt.java
* @author    :  Jason Kalili
* Date       :  2019-04-17
* Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
* Notes      :  None
* Warnings   :  None
*
*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Revision History
* ================
*   Ver      Date     Modified by:  Reason for change or modification
*  -----  ----------  ------------  ---------------------------------------------------------------------
*  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
*  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
*                                     validateDigits, two reversers, and valueOf methods; revamped equals
*                                     and compareTo methods to use the Java String methods; ready to
*                                     start work on subtractByte and subtractInt methods
*  1.2.0  2019-04-18  B.J. Johnson  Fixed bug in add() method that was causing errors in Collatz
*                                     sequence.  Added some tests into the main() method at the bottom
*                                     of the file to test construction.  Also added two tests there to
*                                     test multiplication by three and times-3-plus-1 operations
*
*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

/* Brobdingnagian Integer
need add
     subtract
     multiply
     divide
     remainder
     equals
     compare
     toString (put out representation of what the number is)
     valueOf(long)*/

     import java.util.Arrays;
     import java.lang.StringBuffer;

     public class BrobInt {
//constants for their given numbers
        public static final BrobInt ZERO = new BrobInt(  "0" );
        public static final BrobInt ONE = new BrobInt(  "1" );
        public static final BrobInt TWO = new BrobInt(  "2" );
        public static final BrobInt THREE = new BrobInt(  "3" );
        public static final BrobInt FOUR = new BrobInt(  "4" );
        public static final BrobInt FIVE = new BrobInt(  "5" );
        public static final BrobInt SIX = new BrobInt(  "6" );
        public static final BrobInt SEVEN = new BrobInt(  "7" );
        public static final BrobInt EIGHT = new BrobInt(  "8" );
        public static final BrobInt NINE = new BrobInt(  "9" );
        public static final BrobInt TEN = new BrobInt( "10" );
//types
        public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
        public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
        public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
        public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );
        public char[] NumChar = {'0','1','2','3','4','5','6','7','8','9'};
//internals
        private String internalValue = "";
        private int    sign          = 0;
        private String reversed      = "";
        private int[]  intVersion    = null;
        private int[]  intReversed   = null;
        private int    valueLength   = 0;

       /**
        *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
        *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
        *   for later use
        *  @param  value  String value to make into a BrobInt
        */
        public BrobInt ( String value ) {
         validateDigits( value );
         setReverse();
         setIntVersion();
        }

        /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      *  Method to validate that all the characters in the value are valid decimal digits
      *  @return  boolean  true if all digits are good
      *  @throws  IllegalArgumentException if something is hinky
      *  note that there is no return false, because of throwing the exception
      *  note also that this must check for the '+' and '-' sign digits
      *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public boolean validateDigits( String value ) {
         for ( int i = 0; i < value.length(); i++ ) {
           boolean check = false;
           for ( int j = 0; j < NumChar.length; j++) {
             if ( value.charAt(i) == NumChar[j] ) {
               check = true;
               break;
             } else if ( i == 0 && (value.charAt(i) == '+' || value.charAt(i) == '-') ) {
               check = true;
               break;
             }
           }
           if (!check) {
             throw new IllegalArgumentException("\n Enter a valid big integer");
           }
         }
          if ( value.charAt(0) == '-' ) {
           sign = 1;
          } else {
           sign = 0;
          }
        internalValue = value.substring( 0, value.length() );
        valueLength = internalValue.length();
          return true;
            }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to reverse the value of this BrobInt
        *  @return BrobInt that is the reverse of the value of this BrobInt
        *  NOTE: you can use this or not, as you see fit; explanation was given in class
        *  @see StringBuffer API page for an easy way to do this
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public String setReverse() {
         StringBuffer rev = new StringBuffer(internalValue).reverse();
         return reversed = rev.toString();
        }
        //8 for value length
        public int[] setIntVersion() {
         intVersion = new int[ (valueLength/8) + 1 ];
         for (int i = 0; (i * 8) < valueLength; i++ ) {
           if ( ((i + 1) * 8) > valueLength ) {
             intVersion[i] = Integer.parseInt( internalValue.substring( 0, valueLength - (i * 8) ) );
           } else {
             intVersion[i] = Integer.parseInt( internalValue.substring( valueLength - ((i + 1) * 8) , valueLength - (i * 8) ) );
           }
         }
         return intVersion;
        }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to reverse the value of a BrobIntk passed as argument
        *  Note: static method
        *  @param  bint         BrobInt to reverse its value
        *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
        *  NOTE: you can use this or not, as you see fit; explanation was given in class
        *  @see StringBuffer API page for an easy way to do this
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public int[] reverser( BrobInt bint ) {
         intReversed = new int[ (valueLength/8) + 1 ];
         for (int i = 0; (i * 8) < valueLength; i++ ) {
           if ( ((i + 1) * 8) > valueLength ) {
             intReversed[i] = Integer.parseInt( internalValue.substring( (i *8), ( (i + 1) * 8) ) );
           } else {
             intReversed[i] = Integer.parseInt( internalValue.substring( (i * 8) ) );
           }
         }
         return intReversed;
        }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
        *  @param  bint         BrobInt to add to this
        *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public BrobInt add( BrobInt bint ) {
         BrobInt output = new BrobInt("0");
         int carry = 0;
         if ( this.sign == 0 && bint.sign == 1 ) {
           sub(bint);
         } else if ( this.sign > bint.sign ) {
           bint.sub(this);
         }
           output.sign = this.sign;

         if ( this.intVersion.length == bint.intVersion.length ) {
           output.intVersion = new int[this.intVersion.length];
           for ( int i = 0; i < intVersion.length; i++) {
             output.intVersion[i] = this.intVersion[i] + bint.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] >= 100000000 ) {
               carry = output.intVersion[i] / 100000000;
               output.intVersion[i] = output.intVersion[i] % 100000000;
             }
           }

           for ( int i = 0; i < output.intVersion.length; i++) {
             if ( i == 0 ) {
               output.internalValue = Integer.toString( output.intVersion[i] );
             } else if ( output.intVersion[i] > 0 ) {
               output.internalValue = output.intVersion[i] + output.internalValue;
             }
           }

           return output;
         }

         if (this.internalValue.length() > bint.internalValue.length() ) {
           output.intVersion = new int[this.intVersion.length];

           for ( int i = 0; i < bint.intVersion.length; i++) {
             output.intVersion[i] = this.intVersion[i] + bint.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] >= 100000000 ) {
               carry = output.intVersion[i] / 100000000;
               output.intVersion[i] = output.intVersion[i] % 100000000;
             }

           }

           for ( int i = bint.intVersion.length; i < this.intVersion.length; i++ ) {
             output.intVersion[i] = this.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] >= 100000000 ) {
               carry = output.intVersion[i] / 100000000;
               output.intVersion[i] = output.intVersion[i] % 100000000;
             }
           }


           for ( int i = 0; i < output.intVersion.length; i++) {
             if ( i == 0 ) {
               output.internalValue = Integer.toString(output.intVersion[i]);
             } else if ( output.intVersion[i] > 0 ) {
               output.internalValue = output.intVersion[i] + output.internalValue;
             }
           }
          return output;
         }

         if (this.internalValue.length() < bint.internalValue.length() ) {
           output.intVersion = new int[bint.intVersion.length];

           for ( int i = 0; i < this.intVersion.length; i++) {
             output.intVersion[i] = this.intVersion[i] + bint.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] >= 100000000 ) {
               carry = output.intVersion[i] / 100000000;
               output.intVersion[i] = output.intVersion[i] % 100000000;
             }
           }

           for ( int i = this.intVersion.length; i < bint.intVersion.length; i++ ) {
             output.intVersion[i] = bint.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] >= 100000000 ) {
               carry = output.intVersion[i] / 100000000;
               output.intVersion[i] = output.intVersion[i] % 100000000;
             }
           }

           for ( int i = 0; i < output.intVersion.length; i++) {
             if ( i == 0 ) {
               output.internalValue = Integer.toString( output.intVersion[i] );
             } else if ( output.intVersion[i] > 0 ) {
               output.internalValue = output.intVersion[i] + output.internalValue;
             }
           }
          return output;
         }
          throw new RuntimeException( "\n         Something went wrong in adding." );
         }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
        *  @param  bint         BrobInt to subtract from this
        *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public BrobInt sub( BrobInt bint ) {
         BrobInt output = new BrobInt("0");
         int carry = 0;
         if ( bint.sign > this.sign ) {
           bint.sign = 0;
           add(bint);
         } else if ( bint.sign < this.sign ) {
           this.sign = 0;
           add(bint);
         }

         output.sign = this.sign;
         if ( this.intVersion.length == bint.intVersion.length ) {
           output.intVersion = new int[this.intVersion.length];
           if ( this.sign == 0 ) {
             for ( int i = 0; i < this.intVersion.length; i++ ) {
               output.intVersion[i] = this.intVersion[i] - bint.intVersion[i] + carry;
               carry = 0;
               if ( output.intVersion[i] < 0 ) {
                 output.intVersion[i] *= -1;
                 carry = -1;
               }
             }

             if ( carry == -1 ) {
               output.sign = 1;
             } else {
               output.sign = 0;
             }

             for ( int i = 0; i < output.intVersion.length; i++) {
               if ( i == 0 ) {
                 output.internalValue = Integer.toString( output.intVersion[i] );
               } else if ( output.intVersion[i] > 0 ) {
                 output.internalValue = output.intVersion[i] + output.internalValue;
               }
             }

             if ( output.sign == 1 ) {
               output.internalValue = "-" + output.internalValue;
             }

             return output;
           }

           if ( this.sign == 1 ) {
             for ( int i = 0; i < this.intVersion.length; i++ ) {
               output.intVersion[i] = bint.intVersion[i] - this.intVersion[i] + carry;
               carry = 0;
               if ( output.intVersion[i] < 0 ) {
                 output.intVersion[i] *= -1;
                 carry = -1;
               }
             }

             if ( carry == -1 ) {
               output.sign = 0;
             } else {
               output.sign = 1;
             }

             for ( int i = 0; i < output.intVersion.length; i++) {
               if ( i == 0 ) {
                 output.internalValue = Integer.toString( output.intVersion[i] );
               } else if ( output.intVersion[i] > 0 ) {
                 output.internalValue = output.intVersion[i] + output.internalValue;
               }
             }

             if ( output.sign == 1 ) {
               output.internalValue = "-" + output.internalValue;
             }
             return output;
           }
         }
     // this method is only applied when this.intVersion is longer than bint.intVersion
         if ( this.intVersion.length > bint.intVersion.length ) {
           output.intVersion = new int[this.intVersion.length];
           if ( this.sign == 0 ) {
             for ( int i = 0; i < bint.intVersion.length; i++ ) {
               output.intVersion[i] = this.intVersion[i] - bint.intVersion[i] + carry;
               carry = 0;
               if ( output.intVersion[i] < 0 ) {
                 output.intVersion[i] *= -1;
                 carry = -1;
               }
             }

             for ( int i = bint.intVersion.length; i < this.intVersion.length; i++ ) {
               output.intVersion[i] = this.intVersion[i] + carry;
               carry = 0;
               if ( output.intVersion[i] < 0 ) {
                 output.intVersion[i] *= -1;
                 carry = -1;
               }
             }

             if ( carry == -1 ) {
               output.sign = 1;
             } else {
               output.sign = 0;
             }

             for ( int i = 0; i < output.intVersion.length; i++) {
               if ( i == 0 ) {
                 output.internalValue = Integer.toString( output.intVersion[i] );
               } else if ( output.intVersion[i] > 0 ) {
                 output.internalValue = output.intVersion[i] + output.internalValue;
               }
             }

             if ( output.sign == 1 ) {
               output.internalValue = "-" + output.internalValue;
             }

             return output;
           }

           if ( this.sign == 1 ) {
             for ( int i = 0; i < bint.intVersion.length; i++ ) {
               output.intVersion[i] = bint.intVersion[i] - this.intVersion[i] + carry;
               carry = 0;
               if ( output.intVersion[i] < 0 ) {
                 output.intVersion[i] *= -1;
                 carry = -1;
               }
             }

             for ( int i = bint.intVersion.length; i < this.intVersion.length; i++ ) {
               output.intVersion[i] = - this.intVersion[i] + carry;
               carry = 0;
               if ( output.intVersion[i] < 0 ) {
                 output.intVersion[i] *= -1;
                 carry = -1;
               }
             }

             if ( carry == -1 ) {
               output.sign = 0;
             } else {
               output.sign = 1;
             }

             for ( int i = 0; i < output.intVersion.length; i++) {
               if ( i == 0 ) {
                 output.internalValue = Integer.toString( output.intVersion[i] );
               } else if ( output.intVersion[i] > 0 ) {
                 output.internalValue = output.intVersion[i] + output.internalValue;
               }
             }

             if ( output.sign == 1 ) {
               output.internalValue = "-" + output.internalValue;
             }
             return output;
           }
         }

       if ( this.intVersion.length < bint.intVersion.length ) {
         output.intVersion = new int[bint.intVersion.length];
         if ( this.sign == 0 ) {
           for ( int i = 0; i < this.intVersion.length; i++ ) {
             output.intVersion[i] = bint.intVersion[i] - this.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] < 0 ) {
               output.intVersion[i] *= -1;
               carry = -1;
             }
           }

           for ( int i = this.intVersion.length; i < bint.intVersion.length; i++ ) {
             output.intVersion[i] = bint.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] < 0 ) {
               output.intVersion[i] *= -1;
               carry = -1;
             }
           }

           if ( carry == -1 ) {
             output.sign = 1;
           } else {
             output.sign = 0;
           }

           for ( int i = 0; i < output.intVersion.length; i++) {
             if ( i == 0 ) {
               output.internalValue = Integer.toString( output.intVersion[i] );
             } else if ( output.intVersion[i] > 0 ) {
               output.internalValue = output.intVersion[i] + output.internalValue;
             }
           }

           if ( output.sign == 1 ) {
             output.internalValue = "-" + output.internalValue;
           }

           return output;
         }

         if ( this.sign == 1 ) {
           for ( int i = 0; i < this.intVersion.length; i++ ) {
             output.intVersion[i] = this.intVersion[i] - bint.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] < 0 ) {
               output.intVersion[i] *= -1;
               carry = -1;
             }
           }

           for ( int i = this.intVersion.length; i < bint.intVersion.length; i++ ) {
             output.intVersion[i] = - bint.intVersion[i] + carry;
             carry = 0;
             if ( output.intVersion[i] < 0 ) {
               output.intVersion[i] *= -1;
               carry = -1;
             }
           }

           if ( carry == -1 ) {
             output.sign = 0;
           } else {
             output.sign = 1;
           }

           for (int i = 0; i < output.intVersion.length; i++) {
             if (i == 0) {
               output.internalValue = Integer.toString( output.intVersion[i] );
             } else if ( output.intVersion[i] > 0 ) {
               output.internalValue = output.intVersion[i] + output.internalValue;
             }
           }

           if (output.sign == 1) {
             output.internalValue = "-" + output.internalValue;
           }
           return output;
         }
       }

       throw new RuntimeException( "\n         Something went wrong when subtracting." );
       }


       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
        *  @param  bint         BrobInt to multiply by this
        *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public BrobInt multiply( BrobInt bint ) {
         BrobInt output = new BrobInt( "0" );
         int carry = 0;

         if ( this.sign == 1 && bint.sign == 1 ) {
           output.sign = 0;
         } else if ((this.sign > bint.sign) || (this.sign < bint.sign)){
           output.sign = 1;
         }

         output.intVersion = new int[ this.intVersion.length + bint.intVersion.length + 1];
         for (int i = 0; i < output.intVersion.length; i++) {
           output.intVersion[i] = 0;
         }
         for ( int i = 0; i < this.intVersion.length; i++ ) {
           for ( int j = 0; j < bint.intVersion.length; j++) {
             output.intVersion[ i + j ] += ((this.intVersion[i] * bint.intVersion[i]) + carry);
             carry = 0;
             if ( output.intVersion[ i + j ] < 0 ) {
               output.intVersion[ i + j ] *= -1;
             }
             if ( output.intVersion[ i + j ] >= 100000000 ) {
               carry = output.intVersion[ i + j ] / 100000000;
               output.intVersion[i] = output.intVersion[ i + j ] % 100000000;
             }
           }
         }
         for ( int i = 0; i < output.intVersion.length; i++) {
           if (i == 0) {
             output.internalValue = Integer.toString(output.intVersion[i]);
           } else if (output.intVersion[i] > 0) {
             output.internalValue = output.intVersion[i] + output.internalValue;
           }

         if (output.sign == 1) {
           output.internalValue = "-" + output.internalValue;
         }
         return output;
         }


         throw new RuntimeException("\n            Something went wrong when multiplying." );
        }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
        *  @param  bint         BrobInt to divide this by
        *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public BrobInt divide( BrobInt bint ) {
         BrobInt output = new BrobInt( "0" );
         if ( bint.compareTo(ZERO) == 0 ) {
           throw new IllegalArgumentException("\n enter a valid divisor.");
         } else if (this.compareTo(ZERO) == 0 || this.compareTo(bint) < 0) {
           return output;
         } else if (this.compareTo(bint) == 0) {
           return output = new BrobInt( "1" );
         }

         int n = bint.valueLength;
         BrobInt div = new BrobInt( this.internalValue.substring(0, n));
         if (div.compareTo(bint) == -1) {
           div.internalValue = this.internalValue.substring(0, n + 1);
         }

         while ( n < this.internalValue.length() ) {
           while (div.compareTo(bint) == 1) {

             div = div.sub(bint);
             output = output.add(ONE);
           }

           if (n == this.internalValue.length()) {
             break;
           }
           n++;

           BrobInt plus = new BrobInt(this.internalValue.substring(n -1, n));
           div = div.multiply(TEN);

           output = output.multiply(TEN);
           div = div.add(plus);
         }
        return output;
        }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to get the remainder of division of this BrobInt by the one passed as argument
        *  @param  bint         BrobInt to divide this one by
        *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public BrobInt remainder(BrobInt bint) {
         BrobInt output = new BrobInt("0");
         output = this.sub(bint.multiply(this.divide(bint)));
         return output;
        }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to compare a BrobInt passed as argument to this BrobInt
        *  @param  bint  BrobInt to add to this
        *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
        *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
        *        THAT was easy.....
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public int compareTo(BrobInt bint) {
          if( 1 == sign && 0 == bint.sign ) {
              return -1;
           } else if(0 == sign && 1 == bint.sign) {
              return 1;
           }

           if( internalValue.length() > bint.internalValue.length() ) {
              return 1;
           } else if( internalValue.length() < bint.internalValue.length() ) {
              return (-1);

           } else {
              for(int i = 0; i < internalValue.length(); i++) {
                 Character a = Character.valueOf( internalValue.charAt(i) );
                 Character b = Character.valueOf( bint.internalValue.charAt(i) );
                 if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
                    return 1;
                 } else if( Character.valueOf(a).compareTo(Character.valueOf(b)) < 0 ) {
                    return (-1);
                 }
              }
           }
           return 0;
     }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to check if a BrobInt passed as argument is equal to this BrobInt
        *  @param  bint     BrobInt to compare to this
        *  @return boolean  that is true if they are equal and false otherwise
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public boolean equals(BrobInt bint) {
           return (internalValue.equals(bint.toString()));
        }
       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to return a BrobInt given a long value passed as argument
        *  @param  value         long type number to make into a BrobInt
        *  @return BrobInt  which is the BrobInt representation of the long
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public static BrobInt valueOf(long value) throws NumberFormatException {
           BrobInt bi = null;
           try {
              bi = new BrobInt(Long.valueOf(value).toString());
           }
           catch(NumberFormatException nfe) {
              System.out.println( "\n  Sorry, the value must be numeric of type long." );
           }
           return bi;
        }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to return a String representation of this BrobInt
        *  @return String  which is the String representation of this BrobInt
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public String toString() {
           String output = "";
           output += internalValue;
           return output;
        }

       /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *  Method to display an Array representation of this BrobInt as its bytes
        *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        public void toArray(int[] d) {
           System.out.println( Arrays.toString(d));
        }
        /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
           *  Method to remove leading zeros from a BrobInt passed as argument
           *  @param  bint     BrobInt to remove zeros from
           *  @return BrobInt that is the argument BrobInt with leading zeros removed
           *  Note that the sign is preserved if it exists
           *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
           public BrobInt removeLeadingZeros(BrobInt bint) {
              Character sign = null;
              String returnString = bint.toString();
              int index = 0;

              if( allZeroDetect(bint) ) {
                 return bint;
              }
              if( ('-' == returnString.charAt(index)) || ('+' == returnString.charAt(index)) ) {
                 sign = returnString.charAt(index);
                 index++;
              }
              if( returnString.charAt(index) != '0' ) {
                 return bint;
              }

              while( returnString.charAt(index) == '0' ) {
                 index++;
              }
              returnString = bint.toString().substring( index, bint.toString().length() );
              if(sign != null) {
                 returnString = sign.toString() + returnString;
              }
              return new BrobInt( returnString );

           }

          /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
           *  Method to return a boolean if a BrobInt is all zeros
           *  @param  bint     BrobInt to compare to this
           *  @return boolean  that is true if the BrobInt passed is all zeros, false otherwise
           *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
           public boolean allZeroDetect( BrobInt bint ) {
              for( int i = 0; i < bint.toString().length(); i++ ) {
                 if( bint.toString().charAt(i) != '0' ) {
                    return false;
                 }
              }
              return true;
           }

          /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
           *  Method to display an Array representation of this BrobInt as its bytes
           *  @param   d  byte array from which to display the contents
           *  NOTE: may be changed to int[] or some other type based on requirements in code above
           *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
           public void toArray( byte[] d ) {
              System.out.println( "Array contents: " + Arrays.toString(d));
           }
       //the Enter prompt one kept giving me 2 compiling errors and I couldnt figure it out in time so i do it manual

       //tests
        public static void main(String[] args) {
           System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
           System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

     /*BrobInt b1 = null;;
     try { System.out.println( "   Making a new BrobInt: " ); b1 = new BrobInt( "147258369789456123" ); }
     catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
     try { System.out.println( "   expecting: 147258369789456123\n     and got: " + b1.toString() ); }
     catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
     System.out.println( "\n    Multiplying 82832833 by 3: " );
     try { System.out.println( "      expecting: 248498499\n        and got: " + new BrobInt("82832833").multiply( BrobInt.THREE ) ); }
     catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }
// OOB beacuse of 0 at 0
     System.out.println( "\n    Multiplying 3 by 82832833 and adding 1: " );
     try { System.out.println( "      expecting: 248498500\n        and got: " + BrobInt.THREE.multiply( new BrobInt( "82832833" ) ).add( BrobInt.ONE ) ); }
     catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }
     System.exit( 0 );*/

        }
     }
