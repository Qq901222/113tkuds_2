public class AVLBasicExercise {
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

    boolean search(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        return key < node.key ? search(node.left, key) : search(node.right, key);
    }

    int calcHeight(Node node) {
        return height(node);
    }

    boolean isAVL(Node node) {
        if (node == null) return true;
        int balance = getBalance(node);
        if (balance < -1 || balance > 1) return false;
        return isAVL(node.left) && isAVL(node.right);
    }

    void printTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.key);
            printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();
        int[] nums = {10, 20, 30, 40, 50, 25};
        for (int num : nums) {
            tree.root = tree.insert(tree.root, num);
        }
        System.out.println("直立顯示 AVL 樹:");
        tree.printTree(tree.root, "", false);
        System.out.println("搜尋 25: " + tree.search(tree.root, 25));
        System.out.println("搜尋 60: " + tree.search(tree.root, 60));
        System.out.println("高度: " + tree.calcHeight(tree.root));
        System.out.println("是否為有效 AVL 樹: " + tree.isAVL(tree.root));
    }
}
