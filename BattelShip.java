public class BattelShip {

    public record Coordinate(int column, int row){}

    public enum Field {FREE, SHIP, SHIP_HIT,WATER_HIT}

    final int size = 10;

    static int distance(final Coordinate start, final Coordinate end){

        return Math.abs(start.column()-end.column())+ Math.abs(start.row()-end.row());
    }

    static Coordinate getRandomCoordinate(){
        int row = Utility.getRandomInt(10);
        int column = Utility.getRandomInt(10);
        Coordinate returnCoordinate = new Coordinate(row, column);
        return returnCoordinate;
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end){
        boolean status = false;
        if(start.column == end.column|| start.row == end.row){
            status= true;
        }
        return status;
    }
    static void showSeperatorLine(){
        System.out.println("   +-+-+-+-+-+-+-+-+-+-+      +-+-+-+-+-+-+-+-+-+-+");
    }

    static int getMaxSurroundingColumn(final Coordinate start, final Coordinate end){
        int returnIndex = Math.max(start.column, end.column);
        if(returnIndex<=8 && returnIndex>0){
            returnIndex ++;
        }
        return returnIndex;
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end){
        int returnIndex = Math.max(start.row, end.row);
        if(returnIndex<=8&& returnIndex>0){
            returnIndex ++;
        }
        return returnIndex;
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end){
        int returnIndex = Math.min(start.column, end.column);
        if(returnIndex<=8 && returnIndex>0){
            returnIndex --;
        }
        return returnIndex;

    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end){
        int returnIndex = Math.min(start.row, end.row);
        if(returnIndex<=8 && returnIndex>0){
            returnIndex --;
        }
        return returnIndex;
    }

}
