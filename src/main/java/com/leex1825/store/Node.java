package com.leex1825.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    public Node(int id) {
        this.id = id;
    }

    private int id;

    private int distance;

    @ToString.Exclude
    private Node parentNode;

    @ToString.Exclude
    private List<Edge> neighboringEdges = new ArrayList<>();

}
