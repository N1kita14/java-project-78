### Hexlet tests and linter status:
[![Actions Status](https://github.com/N1kita14/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/N1kita14/java-project-78/actions)
[![SonarQube](https://github.com/Nikita14/java-project-78/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/Nikita14/java-project-78/actions/workflows/build.yml)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=N1kita14_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=N1kita14_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=N1kita14_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=N1kita14_java-project-78)

## Описание
Валидатор данных – библиотека, с помощью которой можно проверять корректность данных.

#### Пример использования
```java
Validator v = new Validator();

// Строки
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false

// Числа
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// Объект Map с поддержкой проверки структуры
Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("firstName", "Anna");
human2.put("lastName", "B");
schema.isValid(human2); // false
