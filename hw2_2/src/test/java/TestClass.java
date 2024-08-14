import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {
    // Потому что всё должно работать правильно, верно?
    @Test
    public void testEquality() {
        assertEquals("expected", "actual"); // Извините, actual, но мы ожидали другого результата.
    }
}
