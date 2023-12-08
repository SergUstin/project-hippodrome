import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.invocation.MockitoMethod;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
class HorseTest {
    //test{Method}_Should{Do}_When{Condition}

    final String name = "Zephyr";
    final int speed = 10;
    final double distance = 10;

    Horse horse;

    @BeforeEach
    private void init() {
        horse = new Horse(name, speed, distance);
    }

    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
    }

    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsNameNull() {
        String exceptionMessage = "Name cannot be null.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsNameBeBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void testConstructor_ShouldTrowExTextMessage_WhenArgsNameBeBlank(String name) {
        String exceptionMessage = "Name cannot be blank.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
        assertEquals(exceptionMessage, exception.getMessage());
    }


    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsSpeedLessThenZero() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed));
    }

    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsSpeedLessThenZero() {
        String exceptionMessage = "Speed cannot be negative.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed));
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void testConstructor_ShouldThrowIllegalArgsEx_WhenArgsDistanceLessThenZero() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
    }

    @Test
    void testConstructor_ShouldTrowExTextMessage_WhenArgsDistanceLessThenZero() {
        String exceptionMessage = "Distance cannot be negative.";

        var exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, speed, -distance));
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void testConstructor_ShouldReturnDistance_WhenArgsConstructorIsTwoArgs() {
        assertEquals(0, new Horse(name, speed).getDistance());
    }

    @Test
    @SneakyThrows
    void testConstructor_ShouldReturnDistance_WhenArgsConstructorIsTwoArgsUseReflection() {
        double expectedDistance = 0;
        Horse horse = new Horse(name, speed);
        Field field = horse.getClass().getDeclaredField("distance");
        field.setAccessible(true);
        double actualDistance = (double) field.get(horse);

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void testGetName_ShouldReturnName_WhenArgsConstructorIsName() {
        assertEquals("Zephyr", horse.getName());
    }

    @Test
    @SneakyThrows
    void testGetName_ShouldReturnName_WhenArgsConstructorIsNameUseReflection() {
        String expectedName = "Zephyr";

        Field field = horse.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String actualName = (String) field.get(horse);

        assertEquals(expectedName, actualName);
    }

    @Test
    void testGetSpeed_ShouldReturnSpeed_WhenArgsConstructorIsSpeed() {
        assertEquals(10, horse.getSpeed());

    }

    @Test
    @SneakyThrows
    void testGetSpeed_ShouldReturnSpeed_WhenArgsConstructorIsSpeedUseReflection() {
        double expectedSpeed = 10;

        Field field = horse.getClass().getDeclaredField("speed");
        field.setAccessible(true);
        double actualSpeed = (double) field.get(horse);

        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void testGetDistance_ShouldReturnDistance_WhenArgsConstructorIsDistance() {
        assertEquals(10, horse.getDistance());
    }

    @Test
    @SneakyThrows
    void testGetDistance_ShouldReturnDistance_WhenArgsConstructorIsDistanceUseReflection() {
        double expectedDistance = 10;

        Field field = horse.getClass().getDeclaredField("distance");
        field.setAccessible(true);
        double actualDistance = (double) field.get(horse);

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void testMove() { // написать нормальное название теста
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(eq(0.2), eq(0.9)));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.1, 0.7, 4, 8, 100})
    void testMove1(Double fakeValue) { // написать нормальное название теста
        double expectedDistance = horse.getDistance() + horse.getSpeed() * fakeValue;

        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(fakeValue);
            horse.move();
            double actualDistance = horse.getDistance();

            assertEquals(expectedDistance, actualDistance);
        }
    }
}