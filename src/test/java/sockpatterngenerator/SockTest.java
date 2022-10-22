package sockpatterngenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.javatuples.Pair;

class SockTest {

    Sock sock = new Sock();



    @Test
    void testSockDefaultValues(){
        Sock testSock = new Sock();
        Assertions.assertEquals(60, testSock.getStitchNr());
        Assertions.assertEquals(15, testSock.getCuffLength());
        Assertions.assertEquals(50, testSock.getLegLength());
        Assertions.assertEquals(43, testSock.getShoeSize());
        Assertions.assertEquals(66, testSock.getFootLength());
        Assertions.assertEquals("10 / 10 / 10", testSock.getHeelSectioning());
        Assertions.assertEquals(4, testSock.getPly());
        Assertions.assertEquals(14, testSock.getDecreaseRounds());
    }

    @Test
    void testGetHeelStitchPair(){
        GUI gui = new GUI(sock);
        sock.setGUI(gui);
        sock.setHeelStitchPair(Pair.with(11,11));
        Assertions.assertEquals(Pair.with(11,11), sock.getHeelStitchPair());
    }

    @Test
    void testSetCuffLength(){
        sock.setCuffLength(10);
        Assertions.assertEquals(10, sock.getCuffLength());
    }

    @Test
    void testSetLegLength(){
        sock.setLegLength(44);
        Assertions.assertEquals(44, sock.getLegLength());
    }

    @Test
    void testSetShoeSize(){
        sock.setShoeSize(45);
        Assertions.assertEquals(45, sock.getShoeSize());
        Assertions.assertEquals(80, sock.getFootLength());
    }

    @Test
    void testSetFootLength(){
        sock.setFootLength(77);
        Assertions.assertEquals(77,sock.getFootLength());
    }

    @Test
    void testShoeSizeToFootLength() {
        sock.setPly(4);
        Assertions.assertEquals(20, sock.shoeSizeToFootLength(33));
        Assertions.assertEquals(40, sock.shoeSizeToFootLength(37));
        Assertions.assertEquals(50, sock.shoeSizeToFootLength(39));
        Assertions.assertEquals(55, sock.shoeSizeToFootLength(40));
        Assertions.assertEquals(65, sock.shoeSizeToFootLength(42));
        Assertions.assertEquals(80, sock.shoeSizeToFootLength(45));

        sock.setPly(6);
        Assertions.assertEquals(15, sock.shoeSizeToFootLength(33));
        Assertions.assertEquals(30, sock.shoeSizeToFootLength(37));
        Assertions.assertEquals(37, sock.shoeSizeToFootLength(39));
        Assertions.assertEquals(41, sock.shoeSizeToFootLength(40));
        Assertions.assertEquals(48, sock.shoeSizeToFootLength(42));
        Assertions.assertEquals(60, sock.shoeSizeToFootLength(45));
    }

    @Test
    void testGenerateHeelSectioning(){
        GUI gui = new GUI(sock);
        sock.setGUI(gui);

        sock.setStitchNr(44);
        Assertions.assertEquals("7 / 8 / 7", sock.generateHeelSectioning());
        sock.setStitchNr(53);
        Assertions.assertEquals("9 / 8 / 9", sock.generateHeelSectioning());
        sock.setStitchNr(60);
        Assertions.assertEquals("10 / 10 / 10", sock.generateHeelSectioning());
        sock.setStitchNr(67);
        Assertions.assertEquals("11 / 11 / 11", sock.generateHeelSectioning());
        sock.setStitchNr(75);
        Assertions.assertEquals("12 / 13 / 12", sock.generateHeelSectioning());
    }

}
