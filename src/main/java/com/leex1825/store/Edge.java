package com.leex1825.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

    @JsonProperty("cost")
    private int cost;

    @JsonProperty("target_node")
    private Node targetNode;
}