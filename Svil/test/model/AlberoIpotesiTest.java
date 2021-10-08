/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giuseppe
 */
public class AlberoIpotesiTest {
    
    public AlberoIpotesiTest() {
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
     * Test of CambiaNodoIndice method, of class AlberoIpotesi.
     */
    @Test
    public void testCambiaNodoIndice() {
        System.out.println("CambiaNodoIndice");
        NodeIpotesi Nodo = new NodeIpotesi('a', 'b');
        AlberoIpotesi instance = new AlberoIpotesi();
        instance.AddNode(Nodo);
        instance.AddNewNode('b', 's');
        instance.CambiaNodoIndice(Nodo);
        assertTrue(Nodo.equals(instance.getIndex()));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of AddNewNode method, of class AlberoIpotesi.
     */
    @Test
    public void testAddNewNode() {
        System.out.println("AddNewNode");
        char a = 'a';
        char b = 'b';
        AlberoIpotesi instance = new AlberoIpotesi();
        instance.AddNewNode(a, b);
        
        assertTrue(instance.index.equals(new NodeIpotesi('a','b')));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of AddNode method, of class AlberoIpotesi.
     */
    @Test
    public void testAddNode() {
        System.out.println("AddNode");
        NodeIpotesi Nodo = new NodeIpotesi('a','b');
        AlberoIpotesi instance = new AlberoIpotesi();
        
        instance.AddNode(Nodo);
        assertTrue(instance.index.equals(Nodo));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of Undo method, of class AlberoIpotesi.
     */
    @Test
    public void testUndo() throws Exception {
        System.out.println("Undo");
        AlberoIpotesi instance = new AlberoIpotesi();
        NodeIpotesi Nodo = new NodeIpotesi('a', 'b');
        instance.AddNode(Nodo);
        instance.AddNewNode('b', 's');
        instance.Undo();
        assertTrue(Nodo.equals(instance.getIndex()));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of GetLista method, of class AlberoIpotesi.
     */
    @Test
    public void testGetLista() {
        System.out.println("GetLista");
        AlberoIpotesi instance = new AlberoIpotesi();
        instance.AddNewNode('a', 'b');
        ListaIpotesi expResult = new ListaIpotesi();
        expResult.AddNewNode(' ', ' ');
        expResult.AddNewNode('a', 'b');
        ListaIpotesi result = instance.GetLista();
        assertEquals(expResult.toString(), result.toString());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AlberoIpotesi.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AlberoIpotesi instance = new AlberoIpotesi();
        String expResult = "  =>  ";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
