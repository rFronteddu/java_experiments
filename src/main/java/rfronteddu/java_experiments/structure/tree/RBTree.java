package rfronteddu.java_experiments.structure.tree;

/**
 * RB tree are a generalization of 2-3 trees that are a generalization of binary trees.
 * In binary tree we ad a key and then nodes on the left were smaller and nodes on the
 * right bigger. Now we can have two keys k1 and k2.
 * On the left of k1 we have nodes smaller than k1
 * On the right of k2 we have nodes that are bigger than k2
 * On nodes between k1 and k2 we have nodes that are between k1 and k2
 * <p>
 * A 2-3 tree has either 1 or 2 keys per node ->
 * 2-node: one key, two children
 * 3-node: two keys, three children
 * Inorder traversal yields keys in ascending order.
 * <p>
 * We have perfect balance if every path from root to null link has same length.
 * <p>
 * These trees guarantee logarithmic performance for search and insert but they are
 * rather complex to implement!
 * <p>
 * It was found that it is possible to represent 2-3 tree as a BST by using the internal
 * left-leaning links as glue for 3-nodes
 * <p>
 * 2-3 Node
 * a           b
 * /        |        \
 * less     between        Greater than b
 * than a    a and b
 * <p>
 * BST equivalent
 * b
 * /  \
 * a    greater than b
 * /  \
 * Less than a. Between a and b
 * <p>
 * Red links glue nodes with a 3-node, black links connect 2-nodes and 3-nodes
 * <p>
 * A Red/Black tree has also the following properties:
 * No node has two red links connected to it
 * Every path from root to null link has the same number of black links
 * Red links lean left
 *
 * Height of tree is <= 2 lg N in the worst case. This happens because every path from root
 * to null link has same number of black links. So there are never two red links in a row.
 *
 *
 */
public class RBTree <Key extends Comparable<Key>, Value>
{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    // < ----------------------------------------------- >
    private Node root;

    private class Node
    {
        private Key key;
        private Value val;
        private Node left, right;
        boolean color; // color of parent link

        public Node (Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    /**
     * Same as BST tree but runs faster (Ignore color).
     * Most other operations are also identical
     */
    public Value get (Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo (x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else if (cmp == 0) {
                return x.val;
            }
        }
        return null;
    }

    /**
     * Insert by applying elementary BST operations.
     * Case 1: Insert into a 2-node at the bottom:
     * Do standard BST insert; color new link red. If new red link is a right link, rotate left.
     * Case 2: Insert into a 3-node at the bottom:
     * Do standard BST insert; color new link red.
     * Rotate to balance the 4-node (if needed)
     * Flip colors to pass red link up one level
     * Rotate to make lean left (if needed)
     * Repeat case 1 or case 2 up the tree if needed
     * -> Same code for all cases:
     * Right child red, left child black: rotate left
     * Left child, left-left grandchild red: rotate right
     * Both children red: flip colors
     *
     * @param h
     * @return
     */
    public Node insert (Node h, Key key, Value val) {
        if (h == null) {
            // insert at bottom and color red
            return new Node (key, val, RED);
        }
        int cmp = key.compareTo (h.key);
        if (cmp < 0) {
            h.left = insert (h.left, key, val);
        } else if (cmp > 0) {
            h.right = insert (h.right, key, val);
        } else if (cmp == 0) {
            h.val = val;
        }

        if (isRed (h.right) && !isRed (h.left)) {
            // lean left
            h = rotateLeft (h);
        }
        if (isRed (h.left) && isRed (h.left.left)) {
            // balance 4-node
            h = rotateRight (h);
        }
        if (isRed (h.left) && isRed (h.right))
        // split 4-node
        {
            flipColors (h);
        }
        return h;
    }

    // ############################################
    private boolean isRed (Node x) {
        if (x == null) {
            return false; // null nodes are red
        }
        return x.color == RED;
    }

    /**
     * Before:
     * E
     * /  \
     * less than E     \
     * S
     * /  \
     * Between E and S   Greater than S
     * <p>
     * <p>
     * After
     * S
     * /  \
     * E    greater than S
     * /  \
     * Less than E. Between E and S
     * <p>
     * Orient a (temporarily) right leaning red link to lean left
     * Must maintain symmetric order and perfect black balance
     *
     * @param h root to rotate
     * @return pointer to new root
     */
    private Node rotateLeft (Node h) {
        assert isRed (h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }


    /**
     * /**
     * Before:
     * S
     * /  \
     * E    greater than S
     * /  \
     * Less than E. Between E and S
     * <p>
     * After
     * E
     * /  \
     * less than E     \
     * S
     * /  \
     * Between E and S   Greater than S
     * <p>
     * <p>
     * Temporarily orient a left-leaning red link to lean right
     *
     * @param h
     * @return
     */
    private Node rotateRight (Node h) {
        assert isRed (h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /**
     * Recolor to split a temporary 4-node
     *
     * @param h root of the 4-node to recolor
     */
    private void flipColors (Node h) {
        assert !isRed (h);
        assert isRed (h.left);
        assert isRed (h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
}
