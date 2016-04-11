/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superkey.keychain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ico
 */
public class KeyEntryTest {
    private KeyEntry entryA, entryEmpty;
    
    public KeyEntryTest() {
    }
    
    @Before
    public void setUp() {
        entryA = new KeyEntry();
        entryA.setApplicationName("appx");
        entryA.setUsername("xx");
        entryA.setPassword("secret@@@");    
    }
    
   
    @Test( expected = IllegalArgumentException.class)
    public void testSetApplicationNameWithNull() {
        entryA.setApplicationName(null);
    }

  
    @Test
    public void testingMethodGetPassword(){
        assertEquals("secret@@@",entryA.getPassword());
    }
    
    @Test
    public void testingMethodSetPassword(){
         entryA.setPassword("test");
         assertEquals("test", entryA.getPassword());
    }
    
    @Test
    public void testingMethodGetUsername(){
       assertEquals("xx",entryA.getUsername()); 
    }
            
     @Test
    public void testingMethodSetUsername(){
        entryA.setUsername("test");
         assertEquals("test", entryA.getUsername());
         
    }
    
    @Test
    public void testingMethodGetApplicationName(){
        assertEquals("appx",entryA.getApplicationName());
    }
    
    @Test
    public void testingMethodSetApplicationName(){
         entryA.setApplicationName("test");
         assertEquals("test", entryA.getApplicationName());
    }
    
     @Test
    public void testKey() {
        // the key is the application name
        assertEquals("failed to get existing key field", entryA.getApplicationName(), "appx");
    }

    @Test
    public void testFormatAsCsv() {
        String expects = "appx" + KeyEntry.FIELDS_DELIMITER + "xx" + KeyEntry.FIELDS_DELIMITER + "secret@@@";
        assertEquals("failed to format entry to delimited string", entryA.formatAsCsv(), expects);
                   
    }

    @Test
    public void testToString() {
        String valuesString= "appx\txx\tsecret@@@";
        assertEquals(valuesString,entryA.toString());
        
    }

    @Test
    public void testParse() {
        String valueTest= "hoje;esta;sol";
        entryA=KeyEntry.parse(valueTest);
        KeyEntry key= new KeyEntry(); 
        key.setApplicationName("hoje");
        key.setUsername("esta");
        key.setPassword("sol");
        
        assertEquals(key,entryA); 
        
        
    }
}
