/*
 */
package tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yohan
 */
public class PathToolsTest {
    
    public PathToolsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of nettoyerPath method, of class PathTools.
     */
    @Test
    public void testNettoyerPath() {
        System.out.println("nettoyerPath");
        String path = "D:\\coucou\\je suis beau\\mon dossier";
        System.out.println(PathTools.nettoyerPath(path));
//        String expResult = "\\\"D:\\coucou\\\"je suis beau\"\\\"mon dossier\"\\";
//        String result = PathTools.nettoyerPath(path);
//        assertEquals(expResult, result);
        
    }
    
}
