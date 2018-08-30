/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Junit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kyle
 */
public class JunitTests {
    
    String URL = "http://healeytestbucketblue.s3-website.us-east-2.amazonaws.com/auto.html";
    String testText = "Automation for the people";
    
    public JunitTests() {
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
    
    @Test
    public void autoTest(){
        
        
        try{
            URL oracle = new URL(URL);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

            String inputLine;
            inputLine = in.readLine();
            in.close();
            assertEquals(testText, inputLine);

        }
        catch(Exception e){
            
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
