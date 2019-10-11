/* EE422C Assignment #3 submission by
 * Colby Janecka
 * CDJ2326
 */

package assignment3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Vertex<T> {

    /** name is a private variable of generic type T which represents the key of the object in the graph. */
    private T name;

    /** edges is a Map object with key of generic type T and value type Integer */
    private Map<T, Integer> edges;

    /** default constructor: sets word value, and creates HashMap to store edges */
    Vertex(T word) {
        name = word;
        edges = new HashMap< T, Integer>();
    }

    /**
     * returns the name of this Vertex as a String object
     * @return a String object containing the name, which is generic type T.
     */
    String getName(){
        return name.toString();
    }

    /**
     * checks to see if this Vertex object has an edge word supplied.
     * @param word is the key value for the object to check for in edges HashMap
     * @returns true if word is present in edges, and false otherwise.
     */
    boolean hasEdgeWord(T word){
        return edges.containsKey(word);
    }

    /**
     * gets the bridge word with the greatest weight between this Vertex and the next word in the poem.
     * @param nextInPoem is a word that follows this Vertex
     * @param graph is the graph object which maps the affinity graph of the corpus.
     */
    String getBridgeWord(T nextInPoem, AffinityGraph graph){
        Integer maxWeight = 0;
        String bestWord = null;
        for(Map.Entry<T, Integer> edge : this.edges.entrySet()){
            Vertex edgeWord = graph.getVertex(edge.getKey());
            if(edgeWord.hasEdgeWord(nextInPoem)){

                if(edgeWord.edgeWeightOfWord(nextInPoem) > maxWeight){
                    maxWeight = edgeWord.edgeWeightOfWord(nextInPoem);
                    bestWord = edge.getKey().toString();
                }

            }
        }
        return bestWord;
    }

    /**
     * finds the weight of an edge between this object and a given word.
     * @param word is the key of the edge that we need the weight of
     * @return an Integer value representing the weight of the connection.
     */
    Integer edgeWeightOfWord(T word){
        return edges.get(word);
    }


    /**
     * Increments the weight between this Vertex and a supplied edge
     * @param edge is the edge of this word which needs to have its weight incremented
     */
    void addEdgeWeight(T edge){
        if(edges.containsKey(edge)){
            Integer weight = edges.get(edge);
            edges.replace(edge, weight+1);
        }
        else
        {
            edges.put(edge, new Integer(1));
        }
    }

    /**
     *  Outputs the edge words and their weights to the console
     */
    void printEdges(){
        Set< Map.Entry< T, Integer> > st = edges.entrySet();

        for (Map.Entry< T, Integer> vertex : st){
            System.out.print(vertex.getKey() + ":");
            System.out.print(vertex.getValue() + ", ");
        }
        System.out.println("");
    }

}
