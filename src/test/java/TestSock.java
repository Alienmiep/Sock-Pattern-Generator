import static org.junit.Assert.*;

import org.junit.Test;

public class TestSock {

    Sock sock = new Sock();

    @Test
    public void testShoeSizeToFootLength() {
        assertEquals(20, sock.shoeSizeToFootLength(33));
        assertEquals(40, sock.shoeSizeToFootLength(37));
        assertEquals(50, sock.shoeSizeToFootLength(39));
        assertEquals(55, sock.shoeSizeToFootLength(40));
        assertEquals(65, sock.shoeSizeToFootLength(42));
        assertEquals(80, sock.shoeSizeToFootLength(45));
    }

    @Test
    public void testGenerateHeelSectioning(){
        sock.setStitchNr(44);
        assertEquals("7/8/7", sock.generateHeelSectioning());
        sock.setStitchNr(53);
        assertEquals("9/8/9", sock.generateHeelSectioning());
        sock.setStitchNr(60);
        assertEquals("10/10/10", sock.generateHeelSectioning());
        sock.setStitchNr(67);
        assertEquals("11/10/11", sock.generateHeelSectioning());
        sock.setStitchNr(75);
        assertEquals("12/12/12", sock.generateHeelSectioning());
    }

}
