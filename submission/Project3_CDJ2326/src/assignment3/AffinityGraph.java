/* EE422C Assignment #3 submission by
 * Colby Janecka
 * CDJ2326
 */

package assignment3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class AffinityGraph<T> {

    /** vertices is a Map object with key of generic type T, and value of type Vertex */
    Map<T, Vertex> vertices;

    /**
     * Default AffinityGraph constructor
     *  Creates new empty HashMap for vertices.
     */
    AffinityGraph(){

        vertices = new HashMap<T, Vertex>();
    }

    /**
     * creates a new entry in the vertices Map object.
     * @param word is an object of generic type T and is the key for the new Map entry
     * @param vertex is a Vertex object to be stored as the value for the new Map entry
     */
    void addVertex(T word, Vertex vertex){
        vertices.put(word, vertex);
    }


    /**
     * creates a new entry in the vertices Map object.
     * @param word is the key value for the object to find in vertices HashMap
     * @return the Vertex in the HashMap for the given word.
     */
    Vertex getVertex(T word){

        Vertex vertex = vertices.get(word);
        return vertex;
    }

    /**
     * Replaces the value of a graph entry with a supplied Vertex object.
     * @param word is the key value for the object to replace in the graph.
     */
    void setVertex(T word, Vertex v){
        vertices.replace(word, v);
    }

    /**
     * Finds whether or not there is a Vertex for
     * @param word is the key value for the object to find in vertices HashMap
     * @return the Vertex in the HashMap for the given word.
     */
    boolean containsWord(T word){
        return vertices.containsKey(word);
    }

    /**
     * Used for testing purposes to output the objects contained in the AffinityGraph.
     */
    void printAffinityGraph(){
        Set< Map.Entry< T, Vertex> > st = vertices.entrySet();

        for (Map.Entry< T, Vertex> foo : st){
            System.out.print(foo.getKey() + " - ");
            foo.getValue().printEdges();
        }
        System.out.println("");
    }

}
