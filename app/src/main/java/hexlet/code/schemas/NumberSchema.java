package hexlet.code.schemas;

//import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        addValidation.put("positive", object -> object > 0);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addValidation.put("range", object -> object >= begin && object <= end);
        return this;
    }
}
