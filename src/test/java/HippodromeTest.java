import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @ParameterizedTest
    @EmptySource
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsHorsesEmpty(List<Horse> horses) {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));


    }

    @ParameterizedTest
    @EmptySource
    void testConstructor_ShouldTrowExTextMessage_WhenArgsHorsesEmpty(List<Horse> horses) {
        try {
            new Hippodrome(horses);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }

    }

}