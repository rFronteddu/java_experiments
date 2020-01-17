package rfronteddu.java_experiments.structure.tree;

/**
 * a BST is a binary tree in symmetric order
 * A binary tree is either
 *      empty
 *      two disjoint binary trees (left and right)
 * Symmetric order: each node ha sa key, and every node's key is
 *      larger than all keys in its left subtree
 *      smaller than all keys in its right subtree
 *
 * Search can go left if less, right if greater, if equal, search hit.
 *
 * Insert: if less go left, if greater go right, if null insert
 *
 * Comparison:
 * --------------------------------------------------------------
 * --------------------------------------------------------------\
 * Implementation   ||  Guarantee       ||  Avg                 ||
 *                  || Search | Insert  || Search   | Insert    ||
 * -------------------------------------------------------------||
 * seq search       || N      |  N      || N/2      | N         ||
 * -------------------------------------------------------------||
 * binary search    || lg N   | N       || lgN      | N/2       ||
 * -------------------------------------------------------------||
 * BST              || N      | N       || 1.39lgN  | 1.39lgN   ||
 * --------------------------------------------------------------|
 * --------------------------------------------------------------
 */
public class BST <Key extends Comparable<Key>, Value>
{
    // < ----------------------------------------------- >
    private Node root;

    private class Node
    {
        private Key key;
        private Value val;
        private Node left, right;

        public Node (Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public Value get (Key key) {
        // Note that number of compares is equal to 1 + depth of node
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo (x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else if (cmp == 0) return x.val; // don't really need the compare
        }
        return null;
    }

    public void put(Key key, Value val) {
        root = put (root, key, val);
    }

    // #####
    private Node put (Node node, Key key, Value val) {
        // Note that number of compares is equal to 1 + depth of node
        // it is important to notice that the tree shape depends on order of insertion,
        // the worst case is a completely unbalanced tree (max depth)
        // As with quick-sort partitioning, if we insert the keys in random order the expected
        // number of compares for search/insert is ~2 ln N
        if (node == null) {
            return new Node (key, val);
        }
        int cmp = key.compareTo (node.key);
        if (cmp < 0) {
            node.left = put (node.left, key, val);
        } else if (cmp > 0) {
            node.right = put (node.right, key, val);
        } else {
            node.val = val;
        }
        return node;
    }
}
