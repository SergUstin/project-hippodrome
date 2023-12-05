import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    //test{Method}_Should{Do}_When{Condition}

    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10));
    }

    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsNameNull() {
        try {
            new Horse(null, 10);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    // Проверить все тоже для остальных полей
    // Для проверки isBlank, необходимо добавить @ParameterizedTest и @ValueSource
    // Изучить уровни 3 4 5
    // Проверить конструктор второго класса по аналогии

    @ParameterizedTest
    @ValueSource(strings = "")
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsNameBeBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10));

    }

    @ParameterizedTest
    @ValueSource(strings = "")
    void testConstructor_ShouldTrowExTextMessage_WhenArgsNameBeBlank(String name) {
        try {
            new Horse(name, 10);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -10}) // Если добавить 0 то не работает??
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsSpeedLessThenZero(Integer speed) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", speed));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -10})
    void testConstructor_ShouldTrowExTextMessage_WhenArgsSpeedLessThenZero(Integer speed) {
        try {
            new Horse("Horse", speed);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -10})
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsDistanceLessThenZero(Integer distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 10, distance));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -10})
    void testConstructor_ShouldTrowExTextMessage_WhenArgsDistanceLessThenZero(Integer distance) {
        try {
            new Horse("Horse", 10, distance);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

}