package corejavapractice.collections;

import java.util.ArrayList;
import java.util.List;

public class Nodes {
    public int val;
    public List<Nodes> neighbors;

    // Constructor to initialize the node with a value
    public Nodes(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }

}
