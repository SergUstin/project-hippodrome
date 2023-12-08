import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {
    //test{Method}_Should{Do}_When{Condition}

    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsHorsesNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsHorsesNull() {
        try {
            new Hippodrome(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }

    }
    // Переписать
    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsHorsesEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
    }
    // Переписать
    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsHorsesEmpty() {
        try {
            new Hippodrome(List.of());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }
    // Переписать
    @Test
    void testGet() {
        List<Horse> horses = IntStream.range(0, 30).mapToObj(i -> new Horse("Pegas" + i, i, i)).toList();
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }
    // Переписать
    @Test
    void testMove() {
        List<Horse> horses = IntStream.range(0, 50).mapToObj(i -> mock(Horse.class)).toList();
        new Hippodrome(horses).move();

        horses.forEach(horse -> verify(horse, times(1)).move());
    }
    // Переписать
    @Test
    void testGetWinner() {
        Horse horse1 = new Horse("1", 5, 3);
        Horse horse2 = new Horse("2", 6, 10);
        Horse horse3 = new Horse("3", 1, 2);

        List<Horse> horses = List.of(horse1, horse2, horse3);

        Hippodrome hippodrome = new Hippodrome(horses);

        assertSame(horse2, hippodrome.getWinner());
    }

}