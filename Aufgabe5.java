public class Aufgabe5 {

    public static void main(String[]args){
        warning();

    }
    
    static int product(int x, int y){
        return(int)x*y;
    }
    static int squaresum(int x, int y){
        return(x*x)+(y*y);
    }
    static void output(String content){
        System.out.println(content);
    }
    static void warning(){
        System.out.println("WARNUNG!");
    }
}

