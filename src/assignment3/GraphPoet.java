/* EE422C Assignment #3 submission by
 * Colby Janecka
 * CDJ2326
 */

package assignment3;

import java.io.*;
import java.util.*;

public class GraphPoet {

    /** wordList is a String List that contains the poems words. */
    private List<String> wordList;

    /** graph is an AffinityGraph object which stores the given corpus mapped with respective edges. */
    private AffinityGraph graph;

    /**
     *  Reads in the corpus fle and creates new Affinity Graph to populate.
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {

        wordList = wordsInCorpusFile(corpus);
        graph = new AffinityGraph();
        createAffinityGraph(wordList);
    }

    /**
     * Retrieves words from corpus file
     * @param corpus File from which to read corpus data from
     * @return an ArrayList containing words in file
     */
    private List<String> wordsInCorpusFile(File corpus) throws IOException {

        List<String> wordListTemp = new ArrayList<>();


        try {
            Scanner s = new Scanner(corpus);

            while(s.hasNext()){
                wordListTemp.add(s.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot access input file: " + corpus.getName());
        }

        return(wordListTemp);

    }

    /**
     * creates a graph, and populates it with the words in corpus
     * @param corpusWords is a List of Strings which make up corpus file.
     */
    void createAffinityGraph(List<String> corpusWords){

        for(int i=0; i<corpusWords.size();i++){

            String word = corpusWords.get(i);
            Vertex v = new Vertex(word);

            if(!graph.containsWord(word)){
                graph.addVertex(word, v);
            }
            else
            {
                v = graph.getVertex(word);
            }

            if(i+1 < corpusWords.size()){

                String edge = corpusWords.get(i+1);

                v.addEdgeWeight(edge);
                graph.setVertex(word,v);

            }
        }


    }

    /**
     * Generate a poem using corpus affinity graph.
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {

        List<String> poemWords = wordsInPoemFile(input);

        List<String> poemWordsModified = wordsInPoemFile(input);

        int indexOffset = 0;

        for(int i = 0; i < poemWords.size(); i++) {

            String currentWord = poemWords.get(i);
            if (graph.containsWord(currentWord)) {

                Vertex vertex = graph.getVertex(currentWord);
                String nextWord = poemWords.get(i+1);

                String bridgeWord = vertex.getBridgeWord(nextWord, graph);

                if (bridgeWord != null) {
                    poemWordsModified.add(i+1+indexOffset, bridgeWord);
                    indexOffset++;
                }

            }

        }

        String output = capitalizeFirstLetter(poemAsString(poemWordsModified));

        return output;
    }

    /**
     * capitalizes the first letter of the poem once it has been modified.
     * @param poem is a String which needs to have first word capitalized
     * @return a String of the poem with first letter capitalized.
     */
    private String capitalizeFirstLetter(String poem){
        String capitalizedPoem = poem.substring(0, 1).toUpperCase() + poem.substring(1);
        return capitalizedPoem;
    }

    /**
     * Converts a String List object into a single String.
     * @param poem is a String List object containing the words which make up the poem.
     * @return poemString is the given poem as a String object.
     */
    private String poemAsString(List<String> poem){
        StringBuilder poemStringBuilder = new StringBuilder();
        for(String word : poem){
            poemStringBuilder.append(" ");
            poemStringBuilder.append(word);
        }
        String poemString = poemStringBuilder.toString().trim();
        return poemString;
    }

    /**
     * Reads an input file using a Scanner object, and creates a String List containing the words within.
     * @param input is a File object which points to the given poem input file.
     * @return a String List that contains all the words in the given file.
     * @throws IOException if the corpus file cannot be found or read
     */
    private List<String> wordsInPoemFile(File input) throws IOException {

        List<String> stringList = new ArrayList<>();

        try {
            Scanner s = new Scanner(input);

            while(s.hasNext()){
                String word = s.next().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                stringList.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot access input file: " + input.getName());
        }

        return(stringList);

    }

}
