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
        int x = (int) input.toUpperCase().charAt(0) - 65;
        if(x<=10 && x>0 && Integer.parseInt(input.substring(1))<=10){
            return true;

        }
        else return false;
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

    public static void main(String[]args){
        System.out.println(toCoordinate("A10"));
    }


}
