public class Matches {

    public static void main(String[] args) {
        System.out.println(matchesC(args[0]));
    }

    static boolean matchesA(String string){
        return string.matches("[A-Z][A-Z]");
    }

    static boolean matchesB(String string){
        return string.matches("Too*r!");
    }

    static boolean matchesC(String string){
        return string.matches("[a-z|A-Z|_][a-z|A-Z|_|0-9]*");
    }

    static boolean matchesD(String string){
        return string.matches("[1-9][0-9]*[w|m|d]");
    }
    
}
