
public class Rekursion {

    public record ListNode(int value, ListNode next){}
       
    public record SignlyLinkedList(ListNode root){}

    static SignlyLinkedList addFirst (final SignlyLinkedList list, final int value){
        ListNode node = new ListNode(value, list.root());

        return new SignlyLinkedList(node);

    }

    static String toString1(final SignlyLinkedList list){
        if(list.root.next() == null){
            return " "+Integer.toString(list.root.value()) + " ";
        }

        String retString = " "+Integer.toString(list.root.value());
        retString = retString + toString1(new SignlyLinkedList(list.root.next()));
        return retString;
    }
        
   
    static SignlyLinkedList addLast(final SignlyLinkedList list, final int value){
        if(list.root.next() == null){
            ListNode lastNode = new ListNode(value,null);
            ListNode beforeLastNode = new ListNode(list.root.value(),lastNode);
            return new SignlyLinkedList(beforeLastNode);
        }
        ListNode node = new ListNode (list.root.value(), addLast(new SignlyLinkedList(list.root.next()), value).root());
        return new SignlyLinkedList(node);

    }


    static SignlyLinkedList remove(final SignlyLinkedList list, final int value){
        if(list.root.value() == value){
            return new SignlyLinkedList(list.root.next());
        }
        ListNode node = new ListNode (list.root.value(), remove(new SignlyLinkedList(list.root.next()), value).root());
        return new SignlyLinkedList(node);

    }



        


         

    public static void main(String[]args){
        ListNode kn1 = new ListNode(10,null);
        ListNode kn2 = new ListNode(15,kn1);
        SignlyLinkedList sll = new SignlyLinkedList(kn2);
        sll = addLast(sll, 19);
        sll = addLast(sll, 17);
        sll = remove(sll, 19);


        System.out.print(toString1(sll));
        

    }
    
}
