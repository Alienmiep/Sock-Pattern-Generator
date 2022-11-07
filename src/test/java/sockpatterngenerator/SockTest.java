package sockpatterngenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.javatuples.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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
    void testGetCuffRib(){
        GUI gui = new GUI(sock);
        sock.setGUI(gui);
        sock.setCuffRib(Pair.with(2,2));
        Assertions.assertEquals(Pair.with(2,2), sock.getCuffRib());
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

    @ParameterizedTest
    @MethodSource("provideShoeSizeInts")
    void testShoeSizeToFootLength(int input, int output4ply, int output6ply) {
        sock.setPly(4);
        Assertions.assertEquals(output4ply, sock.shoeSizeToFootLength(input));

        sock.setPly(6);
        Assertions.assertEquals(output6ply, sock.shoeSizeToFootLength(input));
    }

    private static Stream<Arguments> provideShoeSizeInts() {
        return Stream.of(
                Arguments.of(33, 20, 15),
                Arguments.of(37, 40, 30),
                Arguments.of(39, 50, 37),
                Arguments.of(40, 55, 41),
                Arguments.of(42, 65, 48),
                Arguments.of(45, 80, 60)
        );
    }

    @ParameterizedTest
    @MethodSource("provideHeelSectionings")
    void testGenerateHeelSectioning(int input, String expected){
        GUI gui = new GUI(sock);
        sock.setGUI(gui);

        sock.setStitchNr(input);
        Assertions.assertEquals(expected, sock.generateHeelSectioning());
    }

    private static Stream<Arguments> provideHeelSectionings(){
        return Stream.of(
                Arguments.of(44, "7 / 8 / 7"),
                Arguments.of(53, "9 / 8 / 9"),
                Arguments.of(60, "10 / 10 / 10"),
                Arguments.of(67, "11 / 11 / 11"),
                Arguments.of(75, "12 / 13 / 12")
        );
    }

}
