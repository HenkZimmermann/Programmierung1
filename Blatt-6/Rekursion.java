
public class Rekursion {

    public record ListNode(int value, ListNode next){}
       
    public record SignlyLinkedList(ListNode root){}

    static SignlyLinkedList addFirst (final SignlyLinkedList list, final int value){
        ListNode node = new ListNode(value, list.root());

        return new SignlyLinkedList(node);

    }

    static String toString1(final SignlyLinkedList list){
        if(list.root == null){
            return " ";
        }
        String retString = list.root().toString();
        retString = retString + toString1(new SignlyLinkedList(list.root.next()));
        return retString;
    }
    static SignlyLinkedList addLast(final SignlyLinkedList list, final int value){
        if(list.root == null){
            return new SignlyLinkedList(value, null)
        }

    }

    public static void main(String[]args){
        SignlyLinkedList sll = new SignlyLinkedList(new ListNode(10,null));
        addFirst(sll, 15);
        toString1(sll);
        

    }
    
}
