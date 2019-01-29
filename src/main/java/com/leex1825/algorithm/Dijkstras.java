package com.leex1825.algorithm;

import com.leex1825.store.Edge;
import com.leex1825.store.Graph;
import com.leex1825.store.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
@Service
public class Dijkstras {

    public List<Node> search(Graph graph, Node startNode, Node endNode) {

        // Reset all distances in the graph to be infinity
        for (Node node : graph.getNodes()) {
            node.setDistance(Integer.MAX_VALUE);
        }

        Set<Integer> visitedNodeIds = new HashSet<>();

        startNode.setDistance(0);

        Queue<Node> nodeQueue = new LinkedBlockingDeque<>();
        nodeQueue.add(startNode);

        visitedNodeIds.add(startNode.getId());

        while (!nodeQueue.isEmpty()) {

            Node node = nodeQueue.poll();

            for (Edge neighboringEdges : node.getNeighboringEdges() ) {

                Node targetNode = neighboringEdges.getTargetNode();

                if (node.getDistance() + neighboringEdges.getCost() < targetNode.getDistance() ) {
                    targetNode.setDistance(node.getDistance() + neighboringEdges.getCost());
                    targetNode.setParentNode(node);
                }

                if (!visitedNodeIds.contains(targetNode.getId())){
                    nodeQueue.add(targetNode);
                }

                visitedNodeIds.add(targetNode.getId());
            }
        }

        return backTrackPath(endNode);
    }

    public List<Node> backTrackPath(Node endNode){
        List<Node> shortestPath = new ArrayList<Node>();

        log.error("Found a path. back tracking");

        for(Node node = endNode; node!=null; node = node.getParentNode()){
            shortestPath.add(node);
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
