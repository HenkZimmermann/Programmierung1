import java.lang.reflect.Array;

public class BattelShip {

    public record Coordinate(int column, int row) {
    }

    public enum Field {
        FREE, SHIP, SHIP_HIT, WATER_HIT
    }

    static final int SIZE = 10;

    static int distance(final Coordinate start, final Coordinate end) {

        return Math.abs(start.column - end.column) + Math.abs(start.row - end.row);
    }

    static Coordinate getRandomCoordinate() {
        int row = Utility.getRandomInt(SIZE);
        int column = Utility.getRandomInt(SIZE);
        Coordinate returnCoordinate = new Coordinate(row, column);
        return returnCoordinate;
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end) {
        boolean status = false;
        if (start.column == end.column || start.row == end.row) {
            status = true;
        }
        return status;
    }

    static void showSeperatorLine() {
        System.out.println("   +-+-+-+-+-+-+-+-+-+-+      +-+-+-+-+-+-+-+-+-+-+");
    }

    static int getMaxSurroundingColumn(final Coordinate start, final Coordinate end) {
        int returnIndex = Math.max(start.column, end.column);
        if (returnIndex <= 8 && returnIndex > 0) {
            returnIndex++;
        }
        return returnIndex;
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end) {
        int returnIndex = Math.max(start.row, end.row);
        if (returnIndex <= 8 && returnIndex > 0) {
            returnIndex++;
        }
        return returnIndex;
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end) {
        int returnIndex = Math.min(start.column, end.column);
        if (returnIndex <= 8 && returnIndex > 0) {
            returnIndex--;
        }
        return returnIndex;

    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end) {
        int returnIndex = Math.min(start.row, end.row);
        if (returnIndex <= 8 && returnIndex > 0) {
            returnIndex--;
        }
        return returnIndex;
    }

    static Coordinate toCoordinate(final String input) {
        int x = (int) input.toUpperCase().charAt(0) - 65;
        return new Coordinate(x, Integer.parseInt(input.substring(1))-1);
    }

    static boolean isValidCoordinate(final String input){
        return input.matches("[a-jA-j](10|[1-9])");
    }
    static final String ENTER_SHIP_COORDINATE_PROMPT =  ("Geben Sie die %skoordinate für ein Schiff der Länge %d ein");


    static String getStartCoordinatePrompt(final int length){
        String returnString =String.format(ENTER_SHIP_COORDINATE_PROMPT,"Start", length);
        return returnString;
    }

    static String getEndCoordinatePrompt(final int length){
        String returnString =String.format(ENTER_SHIP_COORDINATE_PROMPT,"End", length);
        return returnString;
    }

    static void showRowNumber(final int row){
        if(row<9){
            System.out.print(" ");
        }
        System.out.print(row+1);

        
    }
    static String grade(final int points){
        if(points<=100 &&points>0){
            if(points<=49){
                return "5.0";
            }
            else if(points<=58 && points>49 ){
                return "4.0";
            }
            else if(points<=66 && points>=58 ){
                return "3.7";
            }
            else if(points<=71 && points>=67 ){
                return "3.3";
            }
            else if(points<=76 && points>=72 ){
                return "3.0";
            }
            else if(points<=80 && points>=77 ){
                return "2.7";
            }
            else if(points<=84 && points>=81 ){
                return "2.3";
            }
            else if(points<=88 && points>=85 ){
                return "2.0";
            }
            else if(points<=91 && points>=89 ){
                return "1.7";
            }
            else if(points<=96 && points>=92 ){
                return "1.3";
            }
            return "1.0";
            
        }
        else return "Ungültige Punktezahl";
    }

    static boolean isCoordinate(final Coordinate input){
        if(input.column()>=0 &&input.column()<=9 && input.row()>=0 &&input.row()<=9){
            return true;
        }
        else{
            return false;
        }
    }
    static Coordinate getRandomEndCoordinate(final Coordinate start, final int distance){

      
        int index = Utility.getRandomInt(4);

        Coordinate[] cord = new Coordinate[]{
            new Coordinate(start.column() - distance, start.row),
            new Coordinate(start.column() + distance, start.row),
            new Coordinate(start.column(), start.row()-distance),
            new Coordinate(start.column(), start.row()+distance)
        };


        while(!isCoordinate(cord[index])){
            index = Utility.getRandomInt(4);
        }
        return cord[index];


    }
    static Coordinate getRandomEndCoordinate1(final Coordinate start, final int distance){
        int index = -1;
        if(isCoordinate(new Coordinate(start.column() - distance, start.row))){
            index =+1;
        }
        if(isCoordinate(new Coordinate(start.column() + distance, start.row))){
            index =+1;
        }
        if(isCoordinate(new Coordinate(start.column(), start.row()-distance))){
            index =+1;
        }
        if(isCoordinate(new Coordinate(start.column(), start.row()+distance))){
            index =+1;
        }
        int random = Utility.getRandomInt(index);

        int number = (4-index) + random;
        if(number == 1){
            return new Coordinate(start.column() - distance, start.row);
        }
        else if(number == 2){
            return new Coordinate(start.column() + distance, start.row);
        }
        else if(number == 3){
            return new Coordinate(start.column(), start.row()-distance);
        }
        else if(number == 4){
            return new  Coordinate(start.column(), start.row()+distance);
        }
        else{return new  Coordinate(0,0);
        }


    }



    static void showField(final Field field, final boolean showShips){
        switch(field) {
            case SHIP:
                if(showShips){
                    System.out.print("0");

                }
                else{
                    System.out.print(" ");

                }
              break;
            case FREE:
               System.out.println(" ");
              break;
            case WATER_HIT:
              System.out.println("x");
              break;
            case SHIP_HIT:
              System.out.println("*");
              break;
          }
        
    }
    public static void main(String[]args){
        System.out.println(toCoordinate("A10"));
    }


}
