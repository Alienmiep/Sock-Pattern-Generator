package sockpatterngenerator;

import static org.junit.Assert.*;

import org.javatuples.Pair;
import org.junit.Test;

public class SockTest {

    Sock sock = new Sock();



    @Test
    public void testSockDefaultValues(){
        Sock testSock = new Sock();
        assertEquals(60, testSock.getStitchNr());
        assertEquals(15, testSock.getCuffLength());
        assertEquals(50, testSock.getLegLength());
        assertEquals(43, testSock.getShoeSize());
        assertEquals(66, testSock.getFootLength());
        assertEquals("10 / 10 / 10", testSock.getHeelSectioning());
        assertEquals(4, testSock.getPly());
        assertEquals(14, testSock.getDecreaseRounds());
    }

    @Test
    public void testGetHeelStitchPair(){
        GUI gui = new GUI(sock);
        sock.setGUI(gui);
        sock.setHeelStitchPair(Pair.with(11,11));
        assertEquals(Pair.with(11,11), sock.getHeelStitchPair());
    }

    @Test
    public void testSetCuffLength(){
        sock.setCuffLength(10);
        assertEquals(10, sock.getCuffLength());
    }

    @Test
    public void testSetLegLength(){
        sock.setLegLength(44);
        assertEquals(44, sock.getLegLength());
    }

    @Test
    public void testSetShoeSize(){
        sock.setShoeSize(45);
        assertEquals(45, sock.getShoeSize());
        assertEquals(80, sock.getFootLength());
    }

    @Test
    public void testSetFootLength(){
        sock.setFootLength(77);
        assertEquals(77,sock.getFootLength());
    }

    @Test
    public void testShoeSizeToFootLength() {
        sock.setPly(4);
        assertEquals(20, sock.shoeSizeToFootLength(33));
        assertEquals(40, sock.shoeSizeToFootLength(37));
        assertEquals(50, sock.shoeSizeToFootLength(39));
        assertEquals(55, sock.shoeSizeToFootLength(40));
        assertEquals(65, sock.shoeSizeToFootLength(42));
        assertEquals(80, sock.shoeSizeToFootLength(45));

        sock.setPly(6);
        assertEquals(15, sock.shoeSizeToFootLength(33));
        assertEquals(30, sock.shoeSizeToFootLength(37));
        assertEquals(37, sock.shoeSizeToFootLength(39));
        assertEquals(41, sock.shoeSizeToFootLength(40));
        assertEquals(48, sock.shoeSizeToFootLength(42));
        assertEquals(60, sock.shoeSizeToFootLength(45));
    }

    @Test
    public void testGenerateHeelSectioning(){
        GUI gui = new GUI(sock);
        sock.setGUI(gui);

        sock.setStitchNr(44);
        assertEquals("7 / 8 / 7", sock.generateHeelSectioning());
        sock.setStitchNr(53);
        assertEquals("9 / 8 / 9", sock.generateHeelSectioning());
        sock.setStitchNr(60);
        assertEquals("10 / 10 / 10", sock.generateHeelSectioning());
        sock.setStitchNr(67);
        assertEquals("11 / 11 / 11", sock.generateHeelSectioning());
        sock.setStitchNr(75);
        assertEquals("12 / 13 / 12", sock.generateHeelSectioning());
    }

}
