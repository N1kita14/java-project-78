package hexlet.code.schemas;

import java.util.Map;
//import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidation.put("sizeof", map -> map.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {

        for (var key : schemas.keySet()) {
            var schema = schemas.get(key);
            addValidation.put(key, map -> schema.isValid((T) map.get(key)));
        }
        return this;
    }
}
