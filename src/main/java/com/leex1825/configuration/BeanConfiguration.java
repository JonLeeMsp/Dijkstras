package com.leex1825.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leex1825.store.Edge;
import com.leex1825.store.Graph;
import com.leex1825.store.Node;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class BeanConfiguration {

    @Value("${graph.node-file-name}")
    private String nodesFileName;

    @Value("${graph.edges-file-name}")
    private String edgesFileName;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Graph graph() throws IOException {

        Map<Integer, Node> nodesMap = new HashMap<>();

        for (FileNode fileNode : readNodesFromFile()) {
            nodesMap.put(fileNode.getId(), new Node(fileNode.getId()));
        }

        Map<String, List<FileEdge>> configuredEdges = readEdgesFromFile();

        for (String sourceNodeId : readEdgesFromFile().keySet()) {
            Node sourceNode = nodesMap.get(Integer.valueOf(sourceNodeId));

            for (FileEdge fileEdge : configuredEdges.get(sourceNodeId)) {
                Node targetNode = nodesMap.get(Integer.valueOf(fileEdge.getTargetNodeId()));

                Edge edge = new Edge(fileEdge.getCost(), targetNode);
                sourceNode.getNeighboringEdges().add(edge);
            }
        }

        return new Graph(nodesMap.values().stream().collect(Collectors.toList()));
    }

    private List<FileNode> readNodesFromFile() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(nodesFileName);
        InputStreamReader nodesInputStream = new InputStreamReader(is, StandardCharsets.UTF_8);
        return objectMapper().readValue(nodesInputStream, new TypeReference<List<FileNode>>(){});

    }

    private Map<String, List<FileEdge>> readEdgesFromFile() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(edgesFileName);
        InputStreamReader edgesInputStream = new InputStreamReader(is, StandardCharsets.UTF_8);
        return
                objectMapper().readValue(edgesInputStream, new TypeReference<HashMap<String, List<FileEdge>>>(){});

    }

}

@ToString
@Data
class FileNode {

    @JsonProperty("id")
    private int id;

    @JsonProperty("hValue")
    private int hValue;
}

@ToString
@Data
class FileEdge {
    @JsonProperty("targetNodeId")
    private int targetNodeId;

    @JsonProperty("cost")
    private int cost;
}