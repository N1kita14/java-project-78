import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    private static Validator v;

    @BeforeAll
    static void beforeAll() {
        v = new Validator();
    }

    @Test
    void mapSchemaTest1() {
        var schema = v.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(Map.of("key", "value")));

        schema.sizeof(2);

        assertFalse(schema.isValid(Map.of("k1", "v1")));  // size 1
        assertTrue(schema.isValid(Map.of("k1", "v1", "k2", "v2")));  // size 2
    }

    @Test
    void mapSchemaTest2() {
        var schema = v.map();

        assertTrue(schema.isValid(null));

        schema.sizeof(2);
        assertFalse(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    void mapSchemaTest3() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        var schema = v.map()
                .sizeof(2)
                .shape(schemas);

        Map<String, String> validData = Map.of(
                "firstName", "John",
                "lastName", "Doe"
        );
        assertTrue(schema.isValid(validData));

        Map<String, String> invalidLastName = Map.of(
                "firstName", "John",
                "lastName", null
        );
        assertFalse(schema.isValid(invalidLastName));

        Map<String, String> shortLastName = Map.of(
                "firstName", "Anna",
                "lastName", "B"
        );
        assertFalse(schema.isValid(shortLastName));

        Map<String, String> missingKey = Map.of(
                "firstName", "Alice"
        );
        assertFalse(schema.isValid(missingKey));
    }
}