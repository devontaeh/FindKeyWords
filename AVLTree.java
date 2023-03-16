import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;

// An AVL tree implementation
public class AVLTree<Key extends Comparable<Key>, Value> {

    public Node root; // root of the AVL tree
    public Node left;
    public Node right; 

    // A node of the AVL tree
    private class Node {
        public Key key; // key of the node
        public Value value; // value of the node
        public Node left, right; // left and right subtrees of the node
        public int height; // height of the node

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    // public Node getRoot(){
    //     return root;
    // }

    // Returns the height of the node (or 0 if node is null)
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Returns the balance factor of the node
    private int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Rotates the node to the left
    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return y;
    }

    // Rotates the node to the right
    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return y;
    }

    // Balances the subtree rooted at the given node
    private Node balance(Node node) {
        if (node == null) {
            return null;
        }
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        } else {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
        return node;
    }

    // Returns the value associated with the given key
    public Value get(Key key) {
        Node node = get(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    // Returns the node associated with the given key
    private Node get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    // Inserts the key-value pair into the AVL tree
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    // Inserts the key-value pair into the subtree rooted at the given node
    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        node = balance(node);
        return node;
    }

    // public Hashtable<Key, Value> inOrderTraversal(Node node, Hashtable<Key,Value> hashtable) {
    //     // Hashtable<Value,Key> hashtable = new Hashtable<>();

    //     if (node != null) {
    //         inOrderTraversal(node.left, hashtable) ;
    //         System.out.println(node.key + " " + node.value);
    //         // System.out.println(node.value);
    //         // if
    //         hashtable.put(node.key,node.value);
    //         inOrderTraversal(node.right,hashtable);
            
    //         // inOrderTraversal(node.right);
    //     }
        
    //     // System.out.println(hashtable);

    //     return hashtable;
    // }

    
    public PriorityQueue<Map.Entry<Value,Key>> inOrderTraversal(Node node,PriorityQueue<Map.Entry<Value,Key>> hashtable) {
        // Hashtable<Value,Key> hashtable = new Hashtable<>();

        if (node != null) {

            
            inOrderTraversal(node.left, hashtable) ;
            System.out.println(node.key + " " + node.value);
            hashtable.add(new AbstractMap.SimpleEntry<>(node.value, node.key));

            inOrderTraversal(node.right,hashtable);
            // System.out.println(node.value);
            // if
           
      
            
            // inOrderTraversal(node.right);
        }
        
        // System.out.println(hashtable);

        return hashtable;
    }

    private void inOrderTraversal(Node node) {
 

        if (node != null) {
            inOrderTraversal(node.left) ;
            System.out.println(node.key + " " + node.value);
            // System.out.println(node.value);

            inOrderTraversal(node.right);
            
            // inOrderTraversal(node.right);
        }
        
        // System.out.println(hashtable);

        
    }


    // Call this method from main:
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    public static void main(String[] args) {
        AVLTree<Integer, String> tree = new AVLTree<>();

        tree.put(5, "apple");
        tree.put(2, "banana");
        tree.put(8, "orange");
        tree.put(1, "pear");
        tree.put(4, "grape");
        tree.put(7, "kiwi");
        tree.put(9, "pineapple");
        tree.put(3, "mango");
        tree.put(6, "peach");

        System.out.println("Inorder traversal:");
        tree.inOrderTraversal();

        System.out.println("Get value at key 4: " + tree.get(4));
    }
}