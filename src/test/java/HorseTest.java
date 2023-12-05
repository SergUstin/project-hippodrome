import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.invocation.MockitoMethod;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class HorseTest {
    //test{Method}_Should{Do}_When{Condition}

    String name = "Zephyr";
    int speed = 10;
    double distance = 10;

    String exceptionMessageWhenNameIsNull = "Name cannot be null.";
    String exceptionMessageWhenNameIsBlank = "Name cannot be blank.";
    String exceptionMessageWhenSpeedIsNegative = "Speed cannot be negative.";
    String exceptionMessageWhenDistanceIsNegative = "Distance cannot be negative.";

    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
    }

    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsNameNull() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
        assertEquals(exceptionMessageWhenNameIsNull, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsNameBeBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void testConstructor_ShouldTrowExTextMessage_WhenArgsNameBeBlank(String name) {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
        assertEquals(exceptionMessageWhenNameIsBlank, exception.getMessage());
    }


    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsSpeedLessThenZero() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed));
    }

    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsSpeedLessThenZero() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed));
        assertEquals(exceptionMessageWhenSpeedIsNegative, exception.getMessage());
    }

    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsDistanceLessThenZero() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
    }

    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsDistanceLessThenZero() {
        var exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, speed, -distance));
        assertEquals(exceptionMessageWhenDistanceIsNegative, exception.getMessage());
    }

    @Test
    void testGetName_ShouldReturnName_WhenArgsConstructorIsName() {
        assertEquals("Zephyr", new Horse(name, speed).getName());
    }

    @Test
    @SneakyThrows
    void testGetName_ShouldReturnName_WhenArgsConstructorIsNameUseReflection() {
        String expectedName = "Zephyr";

        Horse horse = new Horse(name, speed);
        Field field = horse.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String actualName = (String) field.get(horse);

        assertEquals(expectedName, actualName);
    }

    // Дописать тесты на геттеры!
    // На getDistance проверить когда в конструктор передано два параметра
    // Проверить, что конструктор с двумя параметрами на третий возвращает 0
    // Более подробное ТЗ в телеге!

    // Проверить, что метод вызывает внутри метод getRandomDouble() с параметрами 0.2 и 0.9.
    // Для этого нужно использовать MockedStatic и его метод verify();
    // Проверить, что метод присваивает дистанции значение высчитанное по формуле:
    // distance + speed * getRandomDouble(0.2, 0.9). Для этого нужно замокать getRandomDouble(),
    // чтобы он возвращал определенные значения, которые нужно задать параметризовав тест.

    @Test
    void testMove() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse(name, speed, distance).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }


}