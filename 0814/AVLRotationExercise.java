public class AVLRotationExercise {
    static class Node {
        int key, height;
        Node left, right;
        Node(int key) {
            this.key = key;
            height = 1;
        }
    }

    int height(Node n) {
        return n == null ? 0 : n.height;
    }

    void updateHeight(Node n) {
        if (n != null) n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    Node rotateLeftRight(Node z) {
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }

    Node rotateRightLeft(Node z) {
        z.right = rotateRight(z.right);
        return rotateLeft(z);
    }

    void printTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.key);
            printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void main(String[] args) {
        AVLRotationExercise avl = new AVLRotationExercise();

        Node root;

        root = new Node(10);
        root.right = new Node(20);
        root.right.right = new Node(30);
        avl.updateHeight(root.right);
        avl.updateHeight(root);
        System.out.println("左旋前:");
        avl.printTree(root, "", false);
        root = avl.rotateLeft(root);
        System.out.println("左旋後:");
        avl.printTree(root, "", false);

        root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(10);
        avl.updateHeight(root.left);
        avl.updateHeight(root);
        System.out.println("右旋前:");
        avl.printTree(root, "", false);
        root = avl.rotateRight(root);
        System.out.println("右旋後:");
        avl.printTree(root, "", false);

        root = new Node(30);
        root.left = new Node(10);
        root.left.right = new Node(20);
        avl.updateHeight(root.left);
        avl.updateHeight(root);
        System.out.println("左右旋前:");
        avl.printTree(root, "", false);
        root = avl.rotateLeftRight(root);
        System.out.println("左右旋後:");
        avl.printTree(root, "", false);

        root = new Node(10);
        root.right = new Node(30);
        root.right.left = new Node(20);
        avl.updateHeight(root.right);
        avl.updateHeight(root);
        System.out.println("右左旋前:");
        avl.printTree(root, "", false);
        root = avl.rotateRightLeft(root);
        System.out.println("右左旋後:");
        avl.printTree(root, "", false);
    }
}
