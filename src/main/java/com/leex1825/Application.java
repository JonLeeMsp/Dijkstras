package com.leex1825;

import com.leex1825.algorithm.Dijkstras;
import com.leex1825.store.Graph;
import com.leex1825.store.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    private Graph graph;

    @Autowired
    private Dijkstras dijkstras;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void testDijkstras() {
        Node startNode = graph.getNodeById(1);
        Node endNode = graph.getNodeById(12);

        List<Node> shortestPath = dijkstras.search(graph, startNode, endNode);

        shortestPath.stream().forEach(n-> {
            log.info("node:" + n.getId());
        });
    }
}
