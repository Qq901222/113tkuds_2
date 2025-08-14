public class AVLDeleteExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    Node root;

    int height(Node n) {
        return n == null ? 0 : n.height;
    }

    int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        else return node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key < node.left.key) return rotateRight(node);
        if (balance < -1 && key > node.right.key) return rotateLeft(node);
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    Node delete(Node root, int key) {
        if (root == null) return root;
        if (key < root.key) root.left = delete(root.left, key);
        else if (key > root.key) root.right = delete(root.right, key);
        else {
            if (root.left == null || root.right == null) {
                Node temp = (root.left != null) ? root.left : root.right;
                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }
        if (root == null) return root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0) return rotateRight(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) return rotateLeft(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        return root;
    }

    void printTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.key);
            printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();
        int[] nums = {10, 20, 30, 40, 50, 25};
        for (int num : nums) {
            tree.root = tree.insert(tree.root, num);
        }
        System.out.println("初始樹:");
        tree.printTree(tree.root, "", false);

        tree.root = tree.delete(tree.root, 25);
        System.out.println("刪除葉子節點 25:");
        tree.printTree(tree.root, "", false);

        tree.root = tree.delete(tree.root, 40);
        System.out.println("刪除只有一個子節點的節點 40:");
        tree.printTree(tree.root, "", false);

        tree.root = tree.delete(tree.root, 30);
        System.out.println("刪除有兩個子節點的節點 30:");
        tree.printTree(tree.root, "", false);
    }
}
