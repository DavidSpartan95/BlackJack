package Main;

public class CalculateScore {

   public int addPoints (String cardName){
      int points = 0;
      char checkCard = cardName.charAt(0);
      switch (checkCard){
         case '1', 'j', 'k', 'q' -> points +=10;
         case '2'-> points +=2;
         case '3'-> points +=3;
         case '4'-> points +=4;
         case '5'-> points +=5;
         case '6'-> points +=6;
         case '7'-> points +=7;
         case '8'-> points +=8;
         case '9'-> points +=9;
         case 'a'-> points +=11;
         default -> points +=0;

      }
      return points;
   }

}
