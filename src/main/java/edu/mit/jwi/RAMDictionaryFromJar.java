package edu.mit.jwi;

import edu.mit.jwi.data.ILoadPolicy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * Created by mssammon on 1/7/16.
 */
public class RAMDictionaryFromJar
{

    public RAMDictionary getRAMDictionaryFromJar( String pathInJar )
    {
        boolean isCompressed = false;
        URI uri = null;
        URL url = null;
        try {
            url = getClass().getClassLoader().getResource( pathInJar );
            uri = url.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            fail( e.getMessage() );
        }

        // needed to handle the jar compression. Changes behavior of uri/url downstream.
        FileSystem zipfs = null;
        Map<String,String> env = new HashMap<>();
        env.put( "create", "false" );
        env.put( "encoding", "UTF-8" );

        try {
            zipfs = FileSystems.newFileSystem(uri, env);
        } catch (IOException e) {
            e.printStackTrace();
            fail( e.getMessage() );
        }

        return new RAMDictionary( url, ILoadPolicy.IMMEDIATE_LOAD );
    }
}
