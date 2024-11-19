import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BattelShip {

    public record Coordinate(int column, int row) {
    }

    public enum Field {
        FREE, SHIP, SHIP_HIT, WATER_HIT
    }

    static final int SIZE = 10;
    static final int ALL_HIT = 14;

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
        List<Coordinate> validEndCoordinates = new ArrayList<>();
        if(isCoordinate(new Coordinate(start.column - distance, start.row))){
            validEndCoordinates.add(new Coordinate(start.column - distance, start.row));
        }
        if(isCoordinate(new Coordinate(start.column + distance, start.row))){
            validEndCoordinates.add(new Coordinate(start.column + distance, start.row));
        }if(isCoordinate(new Coordinate(start.column, start.row-distance))){
            validEndCoordinates.add(new Coordinate(start.column, start.row-distance));
        }if(isCoordinate(new Coordinate(start.column, start.row+distance))){
            validEndCoordinates.add(new Coordinate(start.column, start.row+distance));
        }
        return validEndCoordinates.get(Utility.getRandomInt(validEndCoordinates.size()));

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
            case WATER_HIT:
              System.out.println("x");
              break;
            case SHIP_HIT:
              System.out.println("*");
              break;
            case FREE:
            default:
               System.out.println(" ");
          }
        
    }

    static int max(final int[] array){
        if(array.length<1){
            throw new IllegalArgumentException("Array muss mindestens ein Element enthalten");
        }
        int highestNumber = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            if(array[i]>highestNumber){
                highestNumber = array[i];
            }
        }           
        return highestNumber;
    }

    static void placeShip(final Coordinate start, final Coordinate end, final Field[][] field){
        for(int i=0; i < SIZE; i++){
            for(int j = 0; j<SIZE;j++){
                if(i >= start.column && i<= end.column ||  j >= start.row && j <= end.row){
                    field[i][j] = Field.SHIP;                
                }
                
            }
        }

    }
    static void showRow(final int row, final Field[][] ownField, final Field[][] otherField){
        String startString;
        if(row <10){
            startString=" "+row+"|";
        }else{
            startString=row+"|";
  
        }
        System.out.print(startString);
        for(int i = 0; i> ownField[row].length; i++){
            showField(ownField[row][i], true);
        }
        System.out.println("   "+startString);
        for(int i = 0; i> ownField[row].length; i++){
            showField(otherField[row][i], false);
        }
        System.out.print("\n");

    }
    static void showFields(final Field[][] ownField, final Field[][] otherField){
        System.out.println("    A B C D E F G H I J        A B C D E F G H I J");
        showSeperatorLine();
        for(int i = 0; i>SIZE; i++){
            showRow(i,ownField,otherField);
            showSeperatorLine();
        }
        System.out.print("\n");

    }
    static ArrayList<Coordinate> getShipCoordinates(final Coordinate coordinate, final Field[][] field){
        ArrayList<Coordinate> cordsList = new ArrayList<Coordinate>(); 
        ArrayList<Coordinate> zuPruefen = new ArrayList<Coordinate>(); 
        cordsList.add(coordinate);
        zuPruefen.add(coordinate);


        while(!zuPruefen.isEmpty()){
            Coordinate aktCord = zuPruefen.get(0);
            if(field[aktCord.row][aktCord.column-1] == Field.SHIP||field[aktCord.row][aktCord.column-1]==Field.SHIP_HIT && !isInList(new Coordinate(aktCord.row, aktCord.column -1),cordsList)){
                cordsList.add(new Coordinate(aktCord.row, aktCord.column -1));
                zuPruefen.add(new Coordinate(aktCord.row, aktCord.column -1));

            }
            if(field[aktCord.row-1][aktCord.column] == Field.SHIP|| field[aktCord.row-1][aktCord.column] == Field.SHIP_HIT&& !isInList(new Coordinate(aktCord.row-1, aktCord.column),cordsList)){
                cordsList.add(new Coordinate(aktCord.row-1, aktCord.column));
                zuPruefen.add(new Coordinate(aktCord.row-1, aktCord.column));

            }
            if(field[aktCord.row][aktCord.column+1] == Field.SHIP||field[aktCord.row][aktCord.column+1] == Field.SHIP_HIT&& !isInList(new Coordinate(aktCord.row, aktCord.column +1),cordsList)){
                cordsList.add(new Coordinate(aktCord.row, aktCord.column +1));
                zuPruefen.add(new Coordinate(aktCord.row, aktCord.column +1));

            }
            if(field[aktCord.row+1][aktCord.column] == Field.SHIP||field[aktCord.row+1][aktCord.column] == Field.SHIP&& !isInList(new Coordinate(aktCord.row+1, aktCord.column),cordsList)){
                cordsList.add(new Coordinate(aktCord.row+1, aktCord.column));
                zuPruefen.add(new Coordinate(aktCord.row+1, aktCord.column));

            }
            zuPruefen.remove(0);

        }

        return cordsList;
    }

    static boolean isInList(final Coordinate coordinate,final ArrayList<Coordinate> coordinateList){
        for(int i =0; i< coordinateList.size();i++){
            if(coordinate.row == coordinateList.get(i).row &&coordinate.column == coordinateList.get(i).column)
            return true;
        }
        return false;
    }
    static boolean shipSunk(final Coordinate shot, final Field[][] field){
        Boolean returnState = false;
        ArrayList<Coordinate> coordinateList = getShipCoordinates(shot, field);
        ArrayList<Field> fieldList = new ArrayList<Field>();

        while(!coordinateList.isEmpty()){
            fieldList.add(field[coordinateList.get(0).row][coordinateList.get(0).column]);
            coordinateList.remove(0);
        }
        
    
        for(int i=0; i<fieldList.size();i++){
            if(fieldList.get(0) == Field.SHIP){
                return false;
            }
            else{returnState = true;}

        }
        return returnState;
        

        
    }
    /*  Aufgabe2a)
    int i = 0;
    while(i<bediungung){
        i++;
    }

    
    while(true){


        if(Abbruchbedungung){
            break;
        }
    }
    */

    static void setAllFree(final Field[][]field){
        int rows = field.length;
        int column = field[0].length;
        for(int i = 0; i< rows;i++){
            for(int j = 0; j< column;j++){

                field[i][j] = Field.FREE;
            }
        }
    
    }
    static int countHits(final Field[][]field){
        int rows = field.length;
        int column = field[0].length;
        int hits =0;
        for(int i = 0; i< rows;i++){
            for(int j = 0; j< column;j++){
                if(field[i][j] == Field.SHIP_HIT){
                    hits ++;
                }
            }
        }
        return hits;
    
    }

    static void fillWaterHits(final Coordinate shot, final Field[][] field){
        ArrayList<Coordinate> coordinateList = getShipCoordinates(shot, field);
        for(int i=0; i< coordinateList.size(); i++){
            if(field[coordinateList.get(i).row+1][coordinateList.get(i).column] != Field.SHIP_HIT){
                field[coordinateList.get(i).row+1][coordinateList.get(i).column] = Field.WATER_HIT;
            }
            if(field[coordinateList.get(i).row][coordinateList.get(i).column+1] != Field.SHIP_HIT){
                field[coordinateList.get(i).row][coordinateList.get(i).column+1] = Field.WATER_HIT;
            }
            if(field[coordinateList.get(i).row-1][coordinateList.get(i).column] != Field.SHIP_HIT){
                field[coordinateList.get(i).row-1][coordinateList.get(i).column] = Field.WATER_HIT;
            }
            if(field[coordinateList.get(i).row][coordinateList.get(i).column-1] != Field.SHIP_HIT){
                field[coordinateList.get(i).row][coordinateList.get(i).column-1] = Field.WATER_HIT;
            }
        }

    }
    static ArrayList<Coordinate> inBetween(final Coordinate start,final Coordinate end){

        ArrayList<Coordinate> cords = new ArrayList<Coordinate>();
        cords.add(start);
        cords.add(end);
        if(start.row == end.row){
            for(int i=1; i<Math.abs(start.row-end.row);i++){
                int min = Math.min(start.row, end.row);
                cords.add(new Coordinate(start.column, min+i));
            }
        }
        else if(start.column == end.column){
            for(int i=1; i<Math.abs(start.column-end.column);i++){
                int min = Math.min(start.row, end.column);
                cords.add(new Coordinate(min+i,start.row));
            }

        }

        return cords;
    }

    static boolean noConflict(final Coordinate start, final Coordinate end, final Field[][]field){
        ArrayList<Coordinate> cords = inBetween(start, end);
        Boolean returnState = false;
        while(!cords.isEmpty()){
            Coordinate currentCord = cords.get(0);
            if(!isInList(new Coordinate(currentCord.column,currentCord.row-1), cords)){
                if(field[currentCord.column][currentCord.row-1] != Field.SHIP){
                    returnState = true;

                }
                else{return false;}
            }
            if(!isInList(new Coordinate(currentCord.column,currentCord.row+1), cords)){
                if(field[currentCord.column][currentCord.row+1] != Field.SHIP){
                    returnState = true;

                }
                else{return false;}
            }
            if(!isInList(new Coordinate(currentCord.column-1,currentCord.row), cords)){
                if(field[currentCord.column-1][currentCord.row] != Field.SHIP){
                    returnState = true;

                }
                else{return false;}
            }
            if(!isInList(new Coordinate(currentCord.column+1,currentCord.row), cords)){
                if(field[currentCord.column+1][currentCord.row] != Field.SHIP){
                    returnState = true;

                }
                else{return false;}
            }
            else{return false;};
            cords.remove(0);
        }

        return returnState;
    }

    static Coordinate readCoordinate(final String prompt){
        System.out.print(prompt);
        String input ="";
        while(!isValidCoordinate(input)  || input =="exit"){
            try{
                input =  Utility.readStringFromConsole();

            }

            catch(IOException e){
                

            }
        }
        if(input == "exit"){
            System.exit(0);
        }
        
        return toCoordinate(input);

    }


    static Coordinate getRandomUnshotCoordinate(final Field[][] field) throws Exception{
        ArrayList<Coordinate> cordsListe = new ArrayList<Coordinate>();
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[0].length; j++){
                if(field[i][j]!= Field.SHIP_HIT ||field[i][j]!= Field.WATER_HIT){
                    cordsListe.add(new Coordinate(i,j));
                }

            }
        }
        if(cordsListe.isEmpty()){
            throw new IllegalStateException("keine Coordinate mehr vorhanden");

        }
        return cordsListe.get(Utility.getRandomInt(cordsListe.size()));
    }

    static Coordinate readEndCoordinate(final int length){
        return readCoordinate(getEndCoordinatePrompt(length));

    }
    static Coordinate readStartCoordinate(final int length){
        return readCoordinate(getStartCoordinatePrompt(length));
        

    }

    static boolean allHit(final Field [][] field){
        int count = 0;
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[0].length; j++){
                if(field[i][j]!= Field.SHIP_HIT){
                    count ++;
                }

            }
        }
        if(count == ALL_HIT){
            return true;
        }
        else{return false;}
    }
    static boolean endCondition(final Field[][] ownField, final Field[][]otherField){
        if(allHit(ownField)||allHit(otherField)){
            return true;
        }
        else return false;
    }
    static boolean validPosition(final Coordinate start, final Coordinate end, final int length, final Field[][]field){

        if(Math.abs(start.column-end.column)!= length ||Math.abs(start.row-end.row) != length){
            throw new RuntimeException("falsche Lenth oder Coordinaten uebergeben");
        }
        return noConflict(start, end, field);
    }


    static void shot(final Coordinate shot, Field[][] field){
        switch (field[shot.column][shot.row]) {
            case SHIP:
                field[shot.column][shot.row] = Field.SHIP_HIT;
                if(shipSunk(shot, field)){
                    fillWaterHits(shot, field);
                }
            break;
            case FREE:
                field[shot.column][shot.row] = Field.WATER_HIT;
                break;
        
            default:
                break;
        }
    }
    static void turn(final Field[][] ownField, final Field[][] otherField){
        showFields(ownField,otherField);
        shot(readCoordinate("Feld treffen:"),otherField);
        Coordinate cord = getRandomCoordinate();
        while(ownField[cord.row][cord.column] !=Field.FREE||ownField[cord.row][cord.column] !=Field.SHIP){
            cord = getRandomCoordinate();
        }
        shot(cord,ownField);
    }
    static void outputWinner(final Field[][] ownField, final Field[][] otherField){
        if(allHit(ownField)){
            System.out.println("Du hast gewonnen!!");
        }
        if(allHit(otherField)){
            System.out.println("Der Computer hat gewonnen!!");
        }
    }

    static Field[][] intiOtherField(){
        Field [][] field = new Field[SIZE][SIZE];
        for(int i =0;i<4;i++){
            Coordinate startCord = getRandomCoordinate();
            Coordinate endCord = getRandomEndCoordinate(startCord,i);
            while (!noConflict(startCord,endCord,field)) {
                startCord = getRandomCoordinate();
                endCord = getRandomEndCoordinate(startCord,i); 
            }
            placeShip(startCord, endCord, field);
        }
        return field;
    }

    
    static Field[][] intiOwnField(){
        Field [][] field = new Field[SIZE][SIZE];
        for(int i =0;i<4;i++){
            Coordinate startCord = readStartCoordinate(i);
            Coordinate endCord =readEndCoordinate(i);            
            while (!noConflict(startCord,endCord,field)) {
                startCord = readStartCoordinate(i);
                endCord =readEndCoordinate(i);  
            }
            placeShip(startCord, endCord, field);
        }
        return field;
    }





    public static void main(String[]args){
        System.out.println(" ");
        Field[][] otherField = intiOtherField();
        Field[][] ownField = intiOwnField();
        showFields(otherField,ownField);
    
        

        
    }
    

}
