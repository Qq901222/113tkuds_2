import java.util.*;

public class PersistentAVLExercise {

    public static final class Node {
        public final int key;
        public final int height;
        public final Node left, right;

        private Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = Math.max(h(left), h(right)) + 1;
        }
    }

    private static int h(Node n) { return n == null ? 0 : n.height; }
    private static int bf(Node n) { return (n == null) ? 0 : h(n.left) - h(n.right); }
    private static Node mk(int key, Node l, Node r) { return new Node(key, l, r); }

    private static Node rotateLeft(Node a) {
        Node y = a.right;
        Node t2 = y.left;
        Node newA = mk(a.key, a.left, t2);
        Node newY = mk(y.key, newA, y.right);
        return newY;
    }

    private static Node rotateRight(Node a) {
        Node x = a.left;
        Node t2 = x.right;
        Node newA = mk(a.key, t2, a.right);
        Node newX = mk(x.key, x.left, newA);
        return newX;
    }

    private static Node rebalance(Node n) {
        int b = bf(n);
        if (b > 1) {
            if (bf(n.left) < 0) {
                Node newLeft = rotateLeft(n.left);
                n = mk(n.key, newLeft, n.right);
            }
            return rotateRight(n);
        }
        if (b < -1) {
            if (bf(n.right) > 0) {
                Node newRight = rotateRight(n.right);
                n = mk(n.key, n.left, newRight);
            }
            return rotateLeft(n);
        }
        return n;
    }

    private static Node insert(Node root, int key) {
        if (root == null) return mk(key, null, null);
        if (key < root.key) {
            Node nl = insert(root.left, key);
            if (nl == root.left) return root;
            return rebalance(mk(root.key, nl, root.right));
        } else if (key > root.key) {
            Node nr = insert(root.right, key);
            if (nr == root.right) return root;
            return rebalance(mk(root.key, root.left, nr));
        } else {
            return root;
        }
    }

    private static boolean contains(Node root, int key) {
        Node n = root;
        while (n != null) {
            if (key < n.key) n = n.left;
            else if (key > n.key) n = n.right;
            else return true;
        }
        return false;
    }

    private static void inorder(Node n, List<Integer> out) {
        if (n == null) return;
        inorder(n.left, out);
        out.add(n.key);
        inorder(n.right, out);
    }

    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null);
    }

    public int versionCount() { return versions.size(); }

    public Node rootOf(int version) {
        if (version < 0 || version >= versions.size()) throw new IllegalArgumentException("bad version");
        return versions.get(version);
    }

    public int insertNewVersion(int baseVersion, int key) {
        Node base = rootOf(baseVersion);
        Node nr = insert(base, key);
        versions.add(nr);
        return versions.size() - 1;
    }

    public boolean contains(int version, int key) { return contains(rootOf(version), key); }

    public List<Integer> inorderKeys(int version) {
        List<Integer> res = new ArrayList<>();
        inorder(rootOf(version), res);
        return res;
    }

    public void printTree(int version) { printTree(rootOf(version), 0); }

    private void printTree(Node n, int depth) {
        if (n == null) return;
        printTree(n.right, depth + 1);
        System.out.println("  ".repeat(depth) + n.key);
        printTree(n.left, depth + 1);
    }

    public static void main(String[] args) {
        PersistentAVLExercise p = new PersistentAVLExercise();

        int v0 = 0;
        int v1 = p.insertNewVersion(v0, 20);
        int v2 = p.insertNewVersion(v1, 4);
        int v3 = p.insertNewVersion(v2, 26);
        int v4 = p.insertNewVersion(v3, 3);
        int v5 = p.insertNewVersion(v4, 9);
        int v6 = p.insertNewVersion(v5, 15);
        int v7 = p.insertNewVersion(v6, 30);

        System.out.println("v7:");
        p.printTree(v7);
        System.out.println("inorder v7: " + p.inorderKeys(v7));

        System.out.println("\nold v4:");
        p.printTree(v4);
        System.out.println("inorder v4: " + p.inorderKeys(v4));

        System.out.println("\nfrom v4 insert 7 -> v8:");
        int v8 = p.insertNewVersion(v4, 7);
        p.printTree(v8);
        System.out.println("inorder v8: " + p.inorderKeys(v8));
    }
}
