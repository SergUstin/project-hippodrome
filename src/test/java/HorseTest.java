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

}