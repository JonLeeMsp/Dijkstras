package com.leex1825.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Graph {

    private List<Node> nodes;

    public Node getNodeById(int id) {

        Node returnNode = null;

        for (Node node : nodes) {
            if (node.getId() == id) {
                returnNode = node;
            }
        }

        return returnNode;
    }
}