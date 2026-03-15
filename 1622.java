class Node {
    Node left;              // Left child node
    Node right;             // Right child node
    int l;                  // Left boundary of the segment
    int r;                  // Right boundary of the segment
    int mid;                // Midpoint of the segment
    long value;             // Sum value stored in this node
    long lazyAdd;           // Lazy addition value to be propagated
    long lazyMultiply = 1;  // Lazy multiplication value to be propagated

    /**
     * Constructor for Node
     * @param l left boundary
     * @param r right boundary
     */
    public Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;  // Bit shift for efficient division by 2
    }
}

/**
 * Segment Tree implementation with lazy propagation
 * Supports range addition and multiplication operations
 */
class SegmentTree {
    private Node root = new Node(1, (int) 1e5 + 1);  // Root node covering range [1, 100001]
    private static final int MOD = (int) 1e9 + 7;    // Modulo value for overflow prevention

    public SegmentTree() {
    }

    /**
     * Public method to add a value to a range
     * @param l left boundary of range
     * @param r right boundary of range
     * @param inc increment value
     */
    public void modifyAdd(int l, int r, int inc) {
        modifyAdd(l, r, inc, root);
    }

    /**
     * Private recursive method to add a value to a range
     * @param l left boundary of range
     * @param r right boundary of range
     * @param inc increment value
     * @param node current node being processed
     */
    private void modifyAdd(int l, int r, int inc, Node node) {
        // Base case: invalid range
        if (l > r) {
            return;
        }
      
        // Current segment is completely within the update range
        if (node.l >= l && node.r <= r) {
            // Update node value and lazy propagation value
            node.value = (node.value + (long)(node.r - node.l + 1) * inc) % MOD;
            node.lazyAdd = (node.lazyAdd + inc) % MOD;
            return;
        }
      
        // Push down lazy values before recursing
        pushdown(node);
      
        // Recurse to left child if needed
        if (l <= node.mid) {
            modifyAdd(l, r, inc, node.left);
        }
      
        // Recurse to right child if needed
        if (r > node.mid) {
            modifyAdd(l, r, inc, node.right);
        }
      
        // Update current node value based on children
        pushup(node);
    }

    /**
     * Public method to multiply a range by a value
     * @param l left boundary of range
     * @param r right boundary of range
     * @param m multiplication factor
     */
    public void modifyMul(int l, int r, int m) {
        modifyMul(l, r, m, root);
    }

    /**
     * Private recursive method to multiply a range by a value
     * @param l left boundary of range
     * @param r right boundary of range
     * @param m multiplication factor
     * @param node current node being processed
     */
    private void modifyMul(int l, int r, int m, Node node) {
        // Base case: invalid range
        if (l > r) {
            return;
        }
      
        // Current segment is completely within the update range
        if (node.l >= l && node.r <= r) {
            // Update node value and lazy propagation values
            node.value = (node.value * m) % MOD;
            node.lazyAdd = (node.lazyAdd * m) % MOD;
            node.lazyMultiply = (node.lazyMultiply * m) % MOD;
            return;
        }
      
        // Push down lazy values before recursing
        pushdown(node);
      
        // Recurse to left child if needed
        if (l <= node.mid) {
            modifyMul(l, r, m, node.left);
        }
      
        // Recurse to right child if needed
        if (r > node.mid) {
            modifyMul(l, r, m, node.right);
        }
      
        // Update current node value based on children
        pushup(node);
    }

    /**
     * Public method to query sum of a range
     * @param l left boundary of range
     * @param r right boundary of range
     * @return sum of values in the range
     */
    public int query(int l, int r) {
        return query(l, r, root);
    }

    /**
     * Private recursive method to query sum of a range
     * @param l left boundary of range
     * @param r right boundary of range
     * @param node current node being processed
     * @return sum of values in the range
     */
    private int query(int l, int r, Node node) {
        // Base case: invalid range
        if (l > r) {
            return 0;
        }
      
        // Current segment is completely within the query range
        if (node.l >= l && node.r <= r) {
            return (int) node.value;
        }
      
        // Push down lazy values before recursing
        pushdown(node);
      
        int result = 0;
      
        // Query left child if needed
        if (l <= node.mid) {
            result = (result + query(l, r, node.left)) % MOD;
        }
      
        // Query right child if needed
        if (r > node.mid) {
            result = (result + query(l, r, node.right)) % MOD;
        }
      
        return result;
    }

    /**
     * Update parent node value based on children values
     * @param node parent node to update
     */
    private void pushup(Node node) {
        node.value = (node.left.value + node.right.value) % MOD;
    }

    /**
     * Push down lazy propagation values to children
     * @param node parent node with lazy values
     */
    private void pushdown(Node node) {
        // Create children nodes if they don't exist
        if (node.left == null) {
            node.left = new Node(node.l, node.mid);
        }
        if (node.right == null) {
            node.right = new Node(node.mid + 1, node.r);
        }
      
        // Apply lazy propagation if there are pending operations
        if (node.lazyAdd != 0 || node.lazyMultiply != 1) {
            Node left = node.left;
            Node right = node.right;
          
            // Apply multiplication first, then addition to left child
            left.value = (left.value * node.lazyMultiply + 
                         (long)(left.r - left.l + 1) * node.lazyAdd) % MOD;
            left.lazyAdd = (left.lazyAdd * node.lazyMultiply + node.lazyAdd) % MOD;
            left.lazyMultiply = (left.lazyMultiply * node.lazyMultiply) % MOD;
          
            // Apply multiplication first, then addition to right child
            right.value = (right.value * node.lazyMultiply + 
                          (long)(right.r - right.l + 1) * node.lazyAdd) % MOD;
            right.lazyAdd = (right.lazyAdd * node.lazyMultiply + node.lazyAdd) % MOD;
            right.lazyMultiply = (right.lazyMultiply * node.lazyMultiply) % MOD;
          
            // Reset lazy values after propagation
            node.lazyAdd = 0;
            node.lazyMultiply = 1;
        }
    }
}

/**
 * Fancy class that maintains a sequence of integers
 * Supports append, add to all, multiply all, and get operations
 */
class Fancy {
    private int sequenceLength;           // Current length of the sequence
    private SegmentTree segmentTree = new SegmentTree();  // Segment tree to manage operations

    public Fancy() {
    }

    /**
     * Append a value to the end of the sequence
     * @param val value to append
     */
    public void append(int val) {
        ++sequenceLength;
        // Add value at position sequenceLength (1-indexed)
        segmentTree.modifyAdd(sequenceLength, sequenceLength, val);
    }

    /**
     * Add increment to all existing elements in the sequence
     * @param inc increment value
     */
    public void addAll(int inc) {
        segmentTree.modifyAdd(1, sequenceLength, inc);
    }

    /**
     * Multiply all existing elements in the sequence by m
     * @param m multiplication factor
     */
    public void multAll(int m) {
        segmentTree.modifyMul(1, sequenceLength, m);
    }

    /**
     * Get the value at the given index
     * @param idx 0-indexed position
     * @return value at index, or -1 if index is out of bounds
     */
    public int getIndex(int idx) {
        // Check if index is valid
        if (idx >= sequenceLength) {
            return -1;
        }
        // Query at position idx+1 (convert to 1-indexed)
        return segmentTree.query(idx + 1, idx + 1);
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
