import java.io.*;

public class AVLTree {

    Node root;
    String pre;

    int height(Node N) {
        if (N == null)
            return 0;

        return N.getHeight();
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }


    public Node right_rotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.setHeight(max(height(y.left), height(y.right)) + 1);
        x.setHeight(max(height(x.left), height(x.right)) + 1);

        return x;
    }

    public Node left_rotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.setHeight(max(height(x.left), height(x.right)) + 1);
        y.setHeight(max(height(y.left), height(y.right)) + 1);

        return y;
    }

    int balance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    public Node insert_node(Node node, int key) {

        if (node == null)
            return (new Node(key));

        if (key < node.getKey())
            node.left = insert_node(node.left, key);
        else if (key > node.getKey())
            node.right = insert_node(node.right, key);
        else
            return node;

        node.setHeight(1 + max(height(node.left), height(node.right)));

        int balance = balance(node);

        if (balance > 1 && key < node.left.getKey())
            return right_rotate(node);

        if (balance < -1 && key > node.right.getKey())
            return left_rotate(node);

        if (balance > 1 && key > node.left.getKey()) {
            node.left = left_rotate(node.left);
            return right_rotate(node);
        }

        if (balance < -1 && key < node.right.getKey()) {
            node.right = right_rotate(node.right);
            return left_rotate(node);
        }
        return node;
    }

    public Node min_value(Node node)
    {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }


    public Node delete_node(Node root, int key)
    {
        if (root == null)
            return root;

        if (key < root.getKey())
            root.left = delete_node(root.left, key);

        else if (key > root.getKey())
            root.right = delete_node(root.right, key);

        else
        {

            if ((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;

            }
            else
            {
                Node temp = min_value(root.right);
                root.setKey(temp.getKey());
                root.right = delete_node(root.right, temp.getKey());
            }
        }

        if (root == null)
            return root;

        root.setHeight(max(height(root.left), height(root.right)) + 1);

        int balance = balance(root);

        if (balance > 1 && balance(root.left) >= 0)
            return right_rotate(root);

        if (balance > 1 && balance(root.left) < 0)
        {
            root.left = left_rotate(root.left);
            return right_rotate(root);
        }

        if (balance < -1 && balance(root.right) <= 0)
            return left_rotate(root);

        if (balance < -1 && balance(root.right) > 0)
        {
            root.right = right_rotate(root.right);
            return left_rotate(root);
        }

        return root;
    }

    public void pre_order(Node node) {

        if (node != null) {
           //System.out.print(node.getKey() + ",");
            pre = node.getKey() + ",";
            System.out.print(pre);
            pre_order(node.left);
            pre_order(node.right);
            }

    }

    public void in_order(Node node) {

            if (node == null)
                return;
            in_order(node.left);
            System.out.print(node.getKey() + ",");
            in_order(node.right);

    }

    public void post_order(Node node) {
        if (node == null)
            return;
        post_order(node.left);
        post_order(node.right);
        System.out.print(node.getKey() + ",");
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        try
        {
            File file=new File("/Users/berilerken/Desktop/Java Projects/SENG383_Project_1/src/input.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reading the file
            BufferedReader br=new BufferedReader(fr);
            StringBuffer sb=new StringBuffer();
            String line;


           /* File outputFile=new File("/Users/berilerken/Desktop/Java Projects/SENG383_Project_1/src/output.txt");    //creates a new file instance
            FileWriter fw = new FileWriter(outputFile); */

            while((line=br.readLine())!=null)
            {
                sb.append(line);//appends line to string buffer

                if(line.contains("insert 78")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 78);
                }
                else if(line.contains("insert 49")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 49);
                }
                else if(line.contains("insert 12")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 12);
                }
                else if(line.contains("delete 44")){
                    avlTree.root = avlTree.delete_node(avlTree.root, 44);
                }
                else if(line.contains("insert 99")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 99);
                }
                else if(line.contains("show inorder")){
                    System.out.println("\n IN-ORDER: ");
                    avlTree.in_order(avlTree.root);
                    System.out.println("\n");
                }
                else if(line.contains("delete 99")){
                    avlTree.root = avlTree.delete_node(avlTree.root, 99);
                }
                else if(line.contains("insert 34")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 34);
                }
                else if(line.contains("insert 44")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 44);
                }
                else if(line.contains("insert 45")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 45);
                }
                else if(line.contains("insert 92")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 92);
                }
                else if(line.contains("insert 23")){
                    avlTree.root = avlTree.insert_node(avlTree.root, 23);
                }
                else if(line.contains("show preorder")){
                    System.out.println("\n PRE-ORDER: ");
                    avlTree.pre_order(avlTree.root);
                    System.out.println("\n");
                }
                else if(line.contains("delete 34")){
                    avlTree.root = avlTree.delete_node(avlTree.root, 34);
                }
                else if(line.contains("show postorder")){
                    System.out.println("\n POST-ORDER: ");
                    avlTree.post_order(avlTree.root);
                    System.out.println("\n");

                }

                sb.append("\n");
            }

            fr.close();
            System.out.println("Contents of File: ");
            System.out.println(sb.toString());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
