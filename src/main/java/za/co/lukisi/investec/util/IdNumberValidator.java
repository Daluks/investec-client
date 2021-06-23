package za.co.lukisi.investec.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdNumberValidator {

  /**
   * The format of a normal South-African ID number is as follows
   * {YYMMDD}{G}{SSS}{C}{A}{Z}
   * YYMMDD: Date of birth
   * G : Gender. 0-4 Female; 5-9 Male.
   * SSS : Sequence No. for DOB/G combination.
   * C : Citizenship. 0 SA; 1 Other.
   * A : Usually 8, or 9 (can be other values)
   * Z : Control digit.
   * The following logic explains how the control digit works:
   * <p>
   * For this explanation I am going to use ID number 860506 5 397 08 3
   * a) Add all the digits of the ID number in the odd positions (except for the last number, which is the control digit):
   * 8+0+0+5+9+0 = 22
   * b) Take all the even digits as one number and multiply that by 2:
   * 656378 * 2 = 1312756
   * c) Add the digits of this number together (in b)
   * 1+3+1+2+7+5+6 = 25
   * d) Add the answer of C to the answer of A
   * 22+25 = 47
   * e) Subtract the second character from D from 10, this number should now equal the control character
   * 10-7 = 3 = control character (3)
   *
   * @return
   */
  public boolean isValidIdNumber(String idNumber) {

    List<Integer> odds = new ArrayList<>();
    String evens = "";
    // Validate length
    if (idNumber.length() != 13) {
      return false;
    } else {
      for (int x = 1; x < idNumber.length(); x++) {

        if (x % 2 != 0) {
          odds.add(Integer.parseInt(idNumber.charAt(x - 1) + ""));
        } else {
          evens += idNumber.charAt(x - 1);
        }
      }
    }

    // Add all ODD numbers
    int sumOfOdds = odds.stream().mapToInt(Integer::intValue).sum();

    // Take all the even digits as one number and multiply that by 2
    int evensMultiplied = Integer.parseInt(evens) * 2;

    String[] evensMultipliedArray = (evensMultiplied + "").split("");
    int evensMultipliedAdded = Arrays.asList(evensMultipliedArray)
        .stream().mapToInt(val -> Integer.parseInt(val)).sum();

    //Sum of sumOfOdds + evensMultipliedAdded
    String resultA = (evensMultipliedAdded + sumOfOdds) + "";
    int secondCharacter = Integer.parseInt((resultA).substring(resultA.length() - 1));

    int checker = 10 - secondCharacter;

    if (Integer.parseInt(String.valueOf(idNumber.charAt(idNumber.length() - 1))) == checker) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    new IdNumberValidator().isValidIdNumber("9111160215089");
  }

}
