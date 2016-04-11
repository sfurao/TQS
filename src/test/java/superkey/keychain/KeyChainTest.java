/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superkey.keychain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class KeyChainTest {
    private KeyChain c, ck; 
    private KeyEntry key1, key2; 
    
    
    public KeyChainTest() {
    }
    
    
    @Before
    public void setUp() { 
        
        key1= new KeyEntry(); 
        key1.setApplicationName("Instagram");
        key1.setPassword("saraabril");
        key1.setUsername("sfuraoAbril");
        
        key2= new KeyEntry(); 
        key2.setApplicationName("WhatsUp");
        key2.setPassword("sarawathsup");
        key2.setUsername("sfuraowhats");
        
        
        try {
            c=KeyChain.from(new File("Keychain.txt"), new CipherTool("#wisper"));
            c.put(key1);
            c.put(key2);
            
        } catch (IOException ex) {
            Logger.getLogger(KeyChainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @After
    public void tearDown() throws FileNotFoundException{
        File f = new File("emptyfiletest.txt");
        PrintWriter pw = new PrintWriter(f);
        pw.println("");
        pw.close();
    }
    
    @Test  //verificar se o que foi inserido como parametros de entrada (facebook, sfurao, saraf94) é igual ao que se encontra la c.find(key.key()
    public void testingMethodPutandFind(){
        KeyEntry key= new KeyEntry();
        key.setApplicationName("facebook");
        key.setUsername("sfurao");
        key.setPassword("saraf94");
        
        c.put(key);
        assertEquals(key,c.find(key.key())); 
    }
   
    
    @Test //verificar se metodo find retorna null a uma chave que nao existe
    public void testingMethodFind(){
        assertEquals(null, c.find("facebookk")); 
         
    }
    
    
    
    @Test 
    public void testingMethodSave(){
        try { //garantir que a chain escreve num file vazio
            ck=KeyChain.from(new File("emptyfiletest.txt"), new CipherTool("#wisperck"));
            ck.put(key1);
            ck.save();
        } catch (IOException ex) {
            Logger.getLogger(KeyChainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            //verificar se le o file anterior
            KeyChain keyc=KeyChain.from(new File("emptyfiletest.txt"), new CipherTool("#wisperck"));
            assertEquals(keyc.find(key1.key()),key1);
            
        } catch (IOException ex) {
            Logger.getLogger(KeyChainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Test //ver se o map retorna as entradas todas 
    public void testingMethodallEntries(){
        Iterable <KeyEntry> i= c.allEntries();
        Iterator it= i.iterator();
        boolean key1true =false; 
        boolean key2true = false; 
        
        while(it.hasNext()){
            
            KeyEntry ke= (KeyEntry)it.next();
            if(ke==key1){
                key1true=true; 
            }
            
            if(ke==key2){
                key2true=true; 
            }
        }
        
        assertEquals(key1true, true);
        assertEquals(key2true, true);
        
    }
    
    @Test //ver se o map retorna as entradas todas ordenadas
     public void testingMethodsortedEntries(){
       Iterable<KeyEntry> isorted= c.sortedEntries();
        KeyEntry before = null;
        KeyEntryComparator keyComp = new KeyEntryComparator();
        boolean comp = true;
        for (KeyEntry elem : isorted) {
            if (before != null && keyComp.compare(before, elem) > 0) {
                comp = false;
            }
            before = elem;
        }

        assertEquals(comp, true);
   }
     
          
    @Test 
     public void testingMethodtoString(){
        System.err.println(c.toString());
         
         String outputTest= "pp\t++\tso5b2ur7pv05v91mui\n"
                 + "1\t1\tvv6p8s4kddncnna64k\n"
                 + "ua2\tico2\tpassx\n"
                 + "Instagram\tsfuraoAbril\tsaraabril\n"
                 + "WhatsUp\tsfuraowhats\tsarawathsup\n"
                 + "ua\tico\t6rb5rv3n267cjoe5od\n"
                 + "zbd\t123\tudhb30ebgtfppks9gb\n"
                 + "b1\tb1\tcnr4d9j3q2r1utth1d\n";
         
         assertEquals(outputTest, c.toString()); 
     }
    
    
  
}
