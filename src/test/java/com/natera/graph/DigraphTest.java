package com.natera.graph;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <img src="bidirected-graph.png.png" />
 *
 * @author Oleg Cherednik
 * @since 03.05.2020
 */
@Test
public class DigraphTest {

    private Digraph<Integer> graph;

    @BeforeMethod
    public void createGraph() {
        graph = new Digraph<>();

        for (int i = 0; i <= 12; i++)
            graph.addVertex(i);

        graph.addEdge(0, 5);
        graph.addEdge(0, 1);

        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        graph.addEdge(3, 5);
        graph.addEdge(3, 2);

        graph.addEdge(4, 3);
        graph.addEdge(4, 2);

        graph.addEdge(5, 4);

        graph.addEdge(6, 9);
        graph.addEdge(6, 4);
        graph.addEdge(6, 0);

        graph.addEdge(7, 6);
        graph.addEdge(7, 8);

        graph.addEdge(8, 7);
        graph.addEdge(8, 9);

        graph.addEdge(9, 11);
        graph.addEdge(9, 10);

        graph.addEdge(10, 12);

        graph.addEdge(11, 4);
        graph.addEdge(11, 12);
        graph.addEdge(12, 9);
    }

    public void shouldRetrieveNotEmptyPathWhenPathExists() {
        List<Integer> path = graph.getPath(10, 1);
        assertThat(path).isNotEmpty().hasSize(9);
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
        assertThatThrownBy(() -> graph.addEdge(6, 0)).isInstanceOf(Exception.class);
    }

    public void shouldNotAddBidirectionalEdgeWhenAddEdge() {
        assertThatCode(() -> graph.addEdge(8, 10)).doesNotThrowAnyException();
        assertThatCode(() -> graph.addEdge(10, 8)).doesNotThrowAnyException();
    }

}
