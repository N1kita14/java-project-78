package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected boolean required;
    protected Map<String, Predicate<T>> addValidation = new LinkedHashMap<>();

    public BaseSchema() {
        addValidation.put("checkNull", Objects::nonNull);
    }

    public boolean isValid(T value) {
        if (required) {
            var checkNull = addValidation.get("checkNull");
            if (checkNull != null && !checkNull.test(value)) {
                return false;
            }
        } else {
            var checkNull = addValidation.get("checkNull");
            if (checkNull != null && !checkNull.test(value)) {
                return true;
            }
        }

        for (var entry : addValidation.entrySet()) {
            if (!entry.getKey().equals("checkNull")) {
                if (!entry.getValue().test(value)) {
                    return false;
                }
            }
        }

        return true;
    }
}
