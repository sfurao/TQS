/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superkey.keychain;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sarafurao
 */
public class CipherToolTest {
    private KeyEntry key1;
    public CipherToolTest() {
    }
    
      @Before
    public void setUp() { 
        
        key1= new KeyEntry(); 
        key1.setApplicationName("Instagram");
        key1.setPassword("saraabril");
        key1.setUsername("sfuraoAbril");
    }
    
    
    @Test
    public void verifyValidKey() throws IOException{
        KeyChain keyChain= KeyChain.from(new File("Keychain.txt"), new CipherTool("#wisper"));
        keyChain.put(key1);
        
        assertEquals(key1, keyChain.find(key1.key()));
    }
    
    @Test (expected=IOException.class)
    public void verifyInvalidKey() throws IOException{
        KeyChain keyChain= KeyChain.from(new File("Keychain.txt"), new CipherTool("#wisper2"));
        
    }
    
 
}
