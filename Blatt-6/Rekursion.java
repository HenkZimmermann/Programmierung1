
public class Rekursion {

    public record ListNode(int value, ListNode next){}
       
    public record SignlyLinkedList(ListNode root){}

    static SignlyLinkedList addFirst (final SignlyLinkedList list, final int value){
        ListNode node = new ListNode(value, list.root());

        return new SignlyLinkedList(node);

    }

    static String toString1(final SignlyLinkedList list){
        if(list.root.next() == null){
            return " "+Integer.toString(list.root.value());
        }

        String retString = " "+Integer.toString(list.root.value());
        retString = retString + toString1(new SignlyLinkedList(list.root.next()));
        return retString;
    }
    /*
    static SignlyLinkedList addLast(final SignlyLinkedList list, final int value){
        if(list.root == null){
            return new SignlyLinkedList(value, null)
        }

    }
         */

    public static void main(String[]args){
        ListNode kn1 = new ListNode(10,null);
        ListNode kn2 = new ListNode(15,kn1);
        SignlyLinkedList sll = new SignlyLinkedList(kn2);

        System.out.print(toString1(sll));
        

    }
    
}
