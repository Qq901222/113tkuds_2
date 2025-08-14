import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        String id;
        int score;
        int height;
        int size;
        Node left, right;
        Node(String id, int score) {
            this.id = id;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;
    private final Map<String, Integer> idToScore = new HashMap<>();

    private int h(Node n) { return n == null ? 0 : n.height; }
    private int sz(Node n) { return n == null ? 0 : n.size; }
    private void pull(Node n) {
        if (n != null) {
            n.height = Math.max(h(n.left), h(n.right)) + 1;
            n.size = sz(n.left) + sz(n.right) + 1;
        }
    }

    private int cmp(String id, int score, Node n) {
        if (score != n.score) return Integer.compare(n.score, score);
        return id.compareTo(n.id);
    }

    private Node rotateRight(Node y) {
        Node x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        pull(y); pull(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        pull(x); pull(y);
        return y;
    }

    private int bf(Node n) { return h(n.left) - h(n.right); }

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

    private Node insert(Node n, String id, int score) {
        if (n == null) return new Node(id, score);
        int c = cmp(id, score, n);
        if (c < 0) n.left = insert(n.left, id, score);
        else if (c > 0) n.right = insert(n.right, id, score);
        else { n.id = id; n.score = score; }
        pull(n);
        return rebalance(n);
    }

    private Node minNode(Node n) { while (n.left != null) n = n.left; return n; }

    private Node delete(Node n, String id, int score) {
        if (n == null) return null;
        int c = cmp(id, score, n);
        if (c < 0) n.left = delete(n.left, id, score);
        else if (c > 0) n.right = delete(n.right, id, score);
        else {
            if (n.left == null || n.right == null) {
                n = (n.left != null) ? n.left : n.right;
            } else {
                Node s = minNode(n.right);
                n.id = s.id; n.score = s.score;
                n.right = delete(n.right, s.id, s.score);
            }
        }
        if (n == null) return null;
        pull(n);
        return rebalance(n);
    }

    public void addPlayer(String id, int score) {
        if (idToScore.containsKey(id)) return;
        root = insert(root, id, score);
        idToScore.put(id, score);
    }

    public void setScore(String id, int newScore) {
        if (idToScore.containsKey(id)) {
            int old = idToScore.get(id);
            root = delete(root, id, old);
        }
        root = insert(root, id, newScore);
        idToScore.put(id, newScore);
    }

    public void addScore(String id, int delta) {
        int cur = idToScore.getOrDefault(id, 0);
        setScore(id, cur + delta);
    }

    public int getRank(String id) {
        Integer sc = idToScore.get(id);
        if (sc == null) return -1;
        return rank(root, id, sc);
    }

    private int rank(Node n, String id, int score) {
        if (n == null) return 0;
        int c = cmp(id, score, n);
        if (c < 0) return rank(n.left, id, score);
        if (c > 0) return sz(n.left) + 1 + rank(n.right, id, score);
        return sz(n.left) + 1;
    }

    public List<Player> topK(int k) {
        k = Math.min(k, sz(root));
        List<Player> res = new ArrayList<>(k);
        for (int i = 1; i <= k; i++) {
            Node n = select(root, i);
            res.add(new Player(n.id, n.score));
        }
        return res;
    }

    private Node select(Node n, int k) {
        int L = sz(n.left) + 1;
        if (k == L) return n;
        if (k < L) return select(n.left, k);
        return select(n.right, k - L);
    }

    public void printTree() { printTree(root, 0); }

    private void printTree(Node n, int depth) {
        if (n == null) return;
        printTree(n.right, depth + 1);
        System.out.println("  ".repeat(depth) + n.score + ":" + n.id);
        printTree(n.left, depth + 1);
    }

    public static class Player {
        public final String id;
        public final int score;
        public Player(String id, int score) { this.id = id; this.score = score; }
        @Override public String toString() { return id + "(" + score + ")"; }
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addPlayer("alice", 1200);
        lb.addPlayer("bob", 1500);
        lb.addPlayer("cathy", 1500);
        lb.addPlayer("dave", 900);
        lb.setScore("alice", 1600);
        lb.addScore("dave", 800);

        lb.printTree();

        System.out.println("rank(alice) = " + lb.getRank("alice"));
        System.out.println("rank(bob)   = " + lb.getRank("bob"));
        System.out.println("top3 = " + lb.topK(3));
    }
}
