package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        addValidation.put("required", object -> object != null && !object.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addValidation.put("minLength", object -> object.length() >= length);
        return this;
    }

    public StringSchema contains(String string) {
        addValidation.put("contains", object -> object.contains(string));
        return this;
    }
}
