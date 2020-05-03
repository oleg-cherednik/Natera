package com.natera.graph;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <img src="undirected-graph.png" />
 *
 * @author Oleg Cherednik
 * @since 03.05.2020
 */
@Test
public class GraphTest {

    private Graph<Integer> graph;

    @BeforeMethod
    public void createGraph() {
        graph = new Graph<>();

        for (int i = 0; i <= 12; i++)
            graph.addVertex(i);

        graph.addEdge(0, 6);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);

        graph.addEdge(3, 5);
        graph.addEdge(3, 4);

        graph.addEdge(4, 5);
        graph.addEdge(4, 6);

        graph.addEdge(7, 8);

        graph.addEdge(9, 11);
        graph.addEdge(9, 10);
        graph.addEdge(9, 12);

        graph.addEdge(11, 12);
    }

    public void shouldRetrieveNotEmptyPathWhenPathExists() {
        List<Integer> path = graph.getPath(0, 3);
        assertThat(path).isNotEmpty().hasSize(3);
    }

    public void shouldRetrieveEmptyPathWhenPathNotExists() {
        List<Integer> path = graph.getPath(0, 12);
        assertThat(path).isEmpty();
    }

    public void shouldThrowExceptionWhenGetPathForUnknownVertices() {
        assertThatThrownBy(() -> graph.getPath(0, 100)).isInstanceOf(Exception.class);
        assertThatThrownBy(() -> graph.getPath(100, 0)).isInstanceOf(Exception.class);
    }

    public void shouldThrowExceptionWhenAddEdgeForUnknownVertices() {
        assertThatThrownBy(() -> graph.addEdge(0, 100)).isInstanceOf(Exception.class);
        assertThatThrownBy(() -> graph.addEdge(100, 0)).isInstanceOf(Exception.class);
    }

    public void shouldThrowExceptionWhenAddDuplicateEdge() {
        assertThatThrownBy(() -> graph.addEdge(0, 6)).isInstanceOf(Exception.class);
        assertThatThrownBy(() -> graph.addEdge(6, 0)).isInstanceOf(Exception.class);
    }

    public void shouldAddBidirectionalEdgeWhenAddEdge() {
        assertThatCode(() -> graph.addEdge(8, 10)).doesNotThrowAnyException();
        assertThatThrownBy(() -> graph.addEdge(10, 8)).isInstanceOf(Exception.class);
    }

}
