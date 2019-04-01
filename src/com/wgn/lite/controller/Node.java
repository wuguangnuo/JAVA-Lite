package com.wgn.lite.controller;

import java.util.List;

public class Node {
    int val;
    List<Node> children;

    public Node(int _val, List<Node> _children) {
        this.val = _val;
        this.children = _children;
    }
}
