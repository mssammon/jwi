package edu.mit.jwi;

import edu.mit.jwi.data.FileProvider;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.POS;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;


/**
 * Created by mssammon on 1/6/16.
 */
public class RAMDictionaryTest {

    /**
     * TODO: READ FROM CONFIG FILE
     */
    private static final String WORDNET_FILES_HOME = "/shared/corpora/ccgDependencies/WordNet-3.0/dict";


    @Test
    public void testInstantiationFromJar()
    {
        String jarPath = "wordnet-dict";

//        boolean isCompressed = false;
//        URI uri = null;
//        URL url = null;
//        try {
//            url = getClass().getClassLoader().getResource( jarPath );
//            uri = url.toURI();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            fail( e.getMessage() );
//        }
//
//        FileSystem zipfs = null;
//        Map<String,String> env = new HashMap<>();
//        env.put( "create", "false" );
//        env.put( "encoding", "UTF-8" );
//
//        try {
//            zipfs = FileSystems.newFileSystem(uri, env);
//        } catch (IOException e) {
//            e.printStackTrace();
//            fail( e.getMessage() );
//        }
//
//
////        File path = new File( jarPath );
//        FileProvider fp = null; //new FileProvider( url );
//        try {
//            fp = new FileProvider( uri.toURL() );
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            fail(e.getMessage());
//        }
//        try {
//            fp.open();
//        } catch (IOException e) {
//            e.printStackTrace();
//            fail( e.getMessage() );
//        }
//
//        fp.close();
//
//        RAMDictionary dict = new RAMDictionary( url, ILoadPolicy.IMMEDIATE_LOAD ); // path in ccg wordnet jar

        RAMDictionary dict = new RAMDictionaryFromJar().getRAMDictionaryFromJar( jarPath );
        try {
            dict.open();
        } catch (IOException e) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
        dict.load();
//        dict.load();

        IIndexWord result = dict.getIndexWord( "beat", POS.NOUN );

        System.out.println( "found tag sense count of: " + result.getTagSenseCount() );
    }

    @Test
    public void testInstantiationFromLocalFile()
    {
        File f = new File( WORDNET_FILES_HOME );

        RAMDictionary dict = new RAMDictionary( f, ILoadPolicy.IMMEDIATE_LOAD ); // path in ccg wordnet jar
        try {
            dict.open();
        } catch (IOException e) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
        dict.load();
//        dict.load();

        IIndexWord result = dict.getIndexWord( "beat", POS.NOUN );

        System.out.println( "found tag sense count of: " + result.getTagSenseCount() );
    }

}
