# FieldValueValidator
Проверяющий значения полей класса.

## Аннотации
Данными аннотациями необходимо помечать поля классов.

### Аннотация "@NoNull"
Значение поля класса аннотированное данной аннотацией не должно быть null.

#### Параметры
|     Параметр      | Описание                   |  Обязательный  |
|:-----------------:|:---------------------------|:--------------:|
|      message      | Задаёт сообщение нарушения |      Нет       |

#### Поддерживаемые типы полей
Поддерживаются любые типы полей.

```java
public class Experimental
{
    @NoNull
    private Long id = 1L;
}
```

### Аннотация "@NoEmpty"
Значение поля класса аннотированное данной аннотацией не должно быть пустым.

#### Параметры
|  Параметр  | Описание                     | Обязательный |
|:----------:|:-----------------------------|:------------:|
|  message   | Задаёт сообщение нарушения   |     Нет      |
| allowSpace | Разрешить пробельные символы |     Нет      |

#### Поддерживаемые типы полей
* String

```java
public class Experimental
{
    @NoEmpty
    private String message = "сообщение";
}
```

### Аннотация "@Length"
Значение поля класса аннотированное данной аннотацией ограничивается количеством символов.

#### Параметры
| Параметр | Описание                         | Обязательный |
|:--------:|:---------------------------------|:------------:|
| message  | Задаёт сообщение нарушения       |     Нет      |
|   min    | Минимальное количество символов  |     Нет      |
|   max    | Максимальное количество символов |      Да      |

#### Поддерживаемые типы полей
* String

```java
public class Experimental
{
    @Length(min = 3, max = 13)
    private String comment = "значение";
}
```

### Аннотация "@Size"
Значение поля класса аннотированное данной аннотацией ограничивается количеством элементов.

#### Параметры
| Параметр | Описание                          | Обязательный |
|:--------:|:----------------------------------|:------------:|
| message  | Задаёт сообщение нарушения        |     Нет      |
|   min    | Минимальное количество элементов  |     Нет      |
|   max    | Максимальное количество элементов |      Да      |

#### Поддерживаемые типы полей
* Array
* Collection
* Map

```java
public class Experimental
{
    @Size(min = 2, max = 4)
    private Integer[] days = {1, 2, 3};
}
```

### Аннотация "@Number"
Значение поля класса аннотированное данной аннотацией ограничивается целым числом.

#### Параметры
| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|   min    | Минимальное значение       |     Нет      |
|   max    | Максимальное значение      |     Нет      |

#### Поддерживаемые типы полей
* Byte
* Short
* Integer
* Long

```java
public class Experimental
{
    @Number(min = 0, max = 1000)
    private Integer sum = 785;
}
```

### Аннотация "@NumberFloat"
Значение поля класса аннотированное данной аннотацией ограничивается дробным числом.

#### Параметры
| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|   min    | Минимальное значение       |     Нет      |
|   max    | Максимальное значение      |     Нет      |

#### Поддерживаемые типы полей
* Float
* Double

```java
public class Experimental
{
    @NumberFloat(min = 0.0, max = 1000.1000)
    private Double sum = 785.785;
}
```

### Аннотация "@Pattern"
Значение поля класса аннотированное данной аннотацией ограничивается шаблоном регулярного выражения.

#### Параметры
| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|  regexp  | Регулярное выражение       |      Да      |

#### Поддерживаемые типы полей
* String
* Character

```java
public class Experimental
{
    @Pattern(regexp = "[0-9A-F]+")
    private String hexadecimal = "08A6D9";
}
```

### Аннотация "@Exist"
Значение поля класса аннотированное данной аннотацией ограничивается собственной реализацией проверки существования.
Данную аннотацию можно применять несколько раз для одного поля класса.

#### Параметры
| Параметр  | Описание                     | Обязательный |
|:---------:|:-----------------------------|:------------:|
|  message  | Задаёт сообщение нарушения   |     Нет      |
| existence | Класс проверки существования |      Да      |

#### Создание собственного класса проверки существования
Создать собственный класс проверки существования можно создав класс реализующий интерфейс "Existable".

```java
import Existable;

public class GenderExistence implements Existable<String>
{
	@Override
	public boolean exists(@NotNull String value)
	{
		return value.equals("MALE") || value.equals("FEMALE");
	}
}
```

```java
public class Experimental
{
    @Exist(existence = GenderExistence.class)
    private String gender = "MALE";
}
```

### Аннотация "@Valid"
Значение поля класса аннотированное данной аннотацией ограничивается собственной реализацией проверки корректности.
Данную аннотацию можно применять несколько раз для одного поля класса.

#### Параметры
| Параметр  | Описание                    | Обязательный |
|:---------:|:----------------------------|:------------:|
|  message  | Задаёт сообщение нарушения  |     Нет      |
| validator | Класс проверки корректности |      Да      |

#### Создание собственного класса проверки корректности
Создать собственный класс проверки корректности можно создав класс реализующий интерфейс "Validable".

```java
import Validable;

public class BrowserValidator implements Validable<String>
{
	@Override
	public boolean valid(@NotNull final String value)
	{
		return value.equals("FIREFOX") || value.equals("CHROME");
	}
}
```

```java
public class Experimental
{
    @Valid(validator = BrowserValidator.class)
    private String browser = "FIREFOX";
}
```

## Проверка значений полей класса
Создадим класс поля которого необходимо проверить:
```java
public class Experimental
{
    @NoNull
    private Long id = null;
	
	@NoEmpty
	private String message = "";
	
	@Length(min = 10, max = 13)
	private String comment = "значение";
	
	@Size(min = 5, max = 10)
	private Integer[] days = {1, 2, 3};
}
```

Проверка всех полей класса и возврат нарушений:
```java
FieldValueValidating fieldValueValidating = new FieldValueValidator();
List<Violating> violations = fieldValueValidating.validate(new Experimental());

/*
Результат выполнения:
[
    Violation {
        field='id', 
        message='Значение поля 'id' не должно быть null', 
        params={FIELD=id}
    },
    Violation {
        field='message', 
        message='Значение поля 'message' не должно быть пустым', 
        params={FIELD=message}
    }, 
    Violation {
        field='comment', 
        message='Количество символов значения поля 'comment' должно быть больше 10', 
        params={MIN=10, MAX=13, FIELD=comment}
    }, 
    Violation {
        field='days', 
        message='Количество элементов поля 'days' должны быть больше 5', 
        params={MIN=5, MAX=10, FIELD=days}
    }
]
*/
```

Проверка всех полей класса и возврат первого найденного нарушения:
```java
FieldValueValidating fieldValueValidating = new FieldValueValidator();
fieldValueValidating.setPushy(false);
List<Violating> violations = fieldValueValidating.validate(new Experimental());

/*
Результат выполнения:
[
    Violation {
        field='id', 
        message='Значение поля 'id' не должно быть null', 
        params={FIELD=id}
    }
]
*/
```