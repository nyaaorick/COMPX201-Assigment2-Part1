import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

//BST: define a class called StrBST in a file called StrBST.java. This class is to
//implement an unbalanced BST using self-referential nodes. Your solution should support
//the following public methods using recursion where applicable:

public class StrBST {
    
    
    
//DEFINE NODE CLASS
//have the following:
//A public member variable to hold the value of node as a string.
//A public member variable to hold a link to the left subtree.
//A public member variable to hold a link to the right subtree.
//A constructor that takes a value as a string argument and copies that value into
//the node’s member variable. The constructor should also initialize the left and
//right subtree links to null.
    // Node definition
    public static class Node {
        String value;
        Node left, right;

        Node(String value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


    // Root of the BST
    public Node root;

    // Public constructor
    public StrBST() {
        this.root = null;
    }

    // Public insert method
    public void insert(String s) {
        root = insertRec(root, s);
    }
    private Node insertRec(Node node, String s) {

        
        // If the current node is null, create a new node
        // and return it

        if (node == null) return new Node(s);

        //compare the value to be inserted with the current node's value
        // If the value is less than the current node's value,
        // insert it into the left subtree
        // If the value is greater than the current node's value,
        // insert it into the right subtree
        // If the value is equal, do nothing (no duplicates allowed)

        int cmp = s.compareTo(node.value);
        if (cmp < 0) node.left = insertRec(node.left, s);
        else if (cmp > 0) node.right = insertRec(node.right, s);
        
        
        return node;
    }

    // Constructor
    //int BF = getBF()

    //if BF > 1
    //print Left imbalance
    //else print left-right imbalance
    //apply rotate

    //if BF < -1
    //print Right imbalance
    //else print right-left imbalance
    //apply rotate
    //public method search

    public boolean search(String s) {
        return searchRec(root, s);
    }

    //searchseachseach
    private boolean searchRec(Node node, String s) {
        if (node == null) return false;
        int cmp = s.compareTo(node.value);
        if (cmp < 0) return searchRec(node.left, s);
        else if (cmp > 0) return searchRec(node.right, s);
        else return true; // Found
    }


    // Public remove method
    public void remove(String s) {
        root = removeRec(root, s);
    }

    //remove remove remove
    private Node removeRec(Node node, String s) {
        if (node == null) return null;

        int cmp = s.compareTo(node.value);
        if (cmp < 0) node.left = removeRec(node.left, s);
        else if (cmp > 0) node.right = removeRec(node.right, s);
        else { // Found
            // Node with only one child or no child
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            node.value = minValue(node.right);

            // Delete the inorder successor
            node.right = removeRec(node.right, node.value);
        }
        return node;
    }


    //private method to find the minimum value in a subtree
    // Find minimum value in subtree
    private String minValue(Node node) {
        while (node.left != null) node = node.left;
        return node.value;
    }



    // Public print method
    public void print() {
        printRec(root);
    }


    // Recursive in-order print
    private void printRec(Node node) {
        if (node != null) {
            printRec(node.left);
            System.out.println("Root: " + node.value +
                    " | Left: " + (node.left != null ? node.left.value : "null") +
                    " | Right: " + (node.right != null ? node.right.value : "null"));
            printRec(node.right);
        }
    }



    //prettyprint evo
    //print  
    // StrBSTPrinter as an inner class
    public static class StrBSTPrinter {

        public static void printNode(Node root) {
            int maxLevel = StrBSTPrinter.maxLevel(root);
            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        public static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || StrBSTPrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            StrBSTPrinter.printWhitespaces(firstSpaces);

            List<Node> newNodes = new ArrayList<>();
            for (Node node : nodes) {
                if (node != null) {
                    System.out.print(node.value);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                StrBSTPrinter.printWhitespaces(betweenSpaces);
            }

            System.out.println("");



            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    StrBSTPrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        StrBSTPrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        StrBSTPrinter.printWhitespaces(1);

                    StrBSTPrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        StrBSTPrinter.printWhitespaces(1);

                    StrBSTPrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }
            printNodeInternal(newNodes, level + 1, maxLevel);
        }
        public static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }
        public static int maxLevel(Node node) {
            if (node == null) return 0;
            return Math.max(StrBSTPrinter.maxLevel(node.left), StrBSTPrinter.maxLevel(node.right)) + 1;
        }
        
        public static boolean isAllElementsNull(List<Node> list) {
            for (Node node : list) {
                if (node != null) return false;
            }
            return true;
        }

    }

//
}


    //pretty print :(
    // Pretty print the BST sideways
    // i lay the tree down cuz it ez to print

   // Pretty print the BST vertically with visual links
   // prettyPrint(): 打印树的结构（横向）。
// 右子树在上，左子树在下，递归方式实现，
// 使用缩进（每层缩进3个空格）展示树的层级结构。

//stop using this part, using setbstprint instead
// public void prettyPrint() {
//     prettyPrintRec(root, 0);
// }

// private void prettyPrintRec(Node node, int depth) {
//     if (node == null) return;

//     // 递归打印右子树，增加缩进表示层级
//     prettyPrintRec(node.right, depth + 1);

//     // 打印当前节点，前面添加对应的缩进空格
//     for (int i = 0; i < depth; i++) {
//         System.out.print("   ");
//     }
//     System.out.println(node.value);

//     // 递归打印左子树
//     prettyPrintRec(node.left, depth + 1);
// }
//
// StrBSTPrinter as an inner class
  


//insert(String s) - add the value to the BST, maintain ordering
//alphabetically by using the compareTo method
//(see https://www.w3schools.com/java/ref_string_compareto.asp for more details)
//and assume that duplicates are not allowed.

//remove(String s) - remove the specified value from the BST.

//search(String s) - search the tree to find the specified value and return a
//boolean true if value is found, false otherwise.


//print() - a method which prints out the tree following an in-order traversal with
//each value on a separate line. For example:
//Root: A | Left: null | Right: null
//Root: B | Left: A | Right: null
//Root: C | Left: B | Right: E
//Root: D | Left: null | Right: null
//Root: E | Left: D | Right: null

    //print print print
// Public print method





