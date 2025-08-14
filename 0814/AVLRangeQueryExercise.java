import java.util.*;

public class AVLRangeQueryExercise {

    static class Node {
        int key;
        int height;
        Node left, right;
        Node(int k) { key = k; height = 1; }
    }

    private Node root;

    public void insert(int key) { root = insert(root, key); }

    private Node insert(Node n, int key) {
        if (n == null) return new Node(key);
        if (key < n.key) n.left = insert(n.left, key);
        else if (key > n.key) n.right = insert(n.right, key);
        else return n;
        updateHeight(n);
        return rebalance(n);
    }

    private int h(Node n) { return n == null ? 0 : n.height; }

    private void updateHeight(Node n) { n.height = Math.max(h(n.left), h(n.right)) + 1; }

    private int bf(Node n) { return h(n.left) - h(n.right); }

    private Node rotateRight(Node y) {
        Node x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        updateHeight(y); updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        updateHeight(x); updateHeight(y);
        return y;
    }

    private Node rebalance(Node n) {
        int b = bf(n);
        if (b > 1) {
            if (bf(n.left) < 0) n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if (b < -1) {
            if (bf(n.right) > 0) n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> res = new ArrayList<>();
        rangeQuery(root, min, max, res);
        return res;
    }

    private void rangeQuery(Node n, int min, int max, List<Integer> out) {
        if (n == null) return;
        if (n.key > min) rangeQuery(n.left, min, max, out);
        if (n.key >= min && n.key <= max) out.add(n.key);
        if (n.key < max) rangeQuery(n.right, min, max, out);
    }

    public void printTree() { printTree(root, 0); }

    private void printTree(Node n, int depth) {
        if (n == null) return;
        printTree(n.right, depth + 1);
        System.out.println("  ".repeat(depth) + n.key);
        printTree(n.left, depth + 1);
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise avl = new AVLRangeQueryExercise();
        int[] nums = {20, 4, 26, 3, 9, 15, 30, 2, 7, 11, 18};
        for (int x : nums) avl.insert(x);

        avl.printTree();

        List<Integer> ans = avl.rangeQuery(7, 18);
        System.out.println("range [7, 18] -> " + ans);
    }
}
