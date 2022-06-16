/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Violating;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Интерфейс для реализации проверяющего.
 */
public interface Validating
{
	/**
	 * Проверяет корректность значения поля класса.
	 *
	 * @param field Поле класса.
	 * @param value Значение поля класса.
	 * @return Нарушение, или {@code null} если значение поля класса корректно.
	 */
	@Nullable Violating valid(@NotNull Field field, @Nullable Object value);

	/**
	 * Возвращает параметры нарушения.
	 *
	 * @return Коллекцию параметров нарушения.
	 */
	@NotNull
	Map<@NotNull String, @NotNull String> getParams();

	/**
	 * Устанавливает параметр нарушения.
	 *
	 * @param key Название параметра.
	 * @param value Значение параметра.
	 */
	void setParam(@NotNull String key, @NotNull String value);

	/**
	 * Возвращает сообщение нарушения.
	 *
	 * @return Сообщение нарушения.
	 */
	@NotNull
	String getMessage();

	/**
	 * Устанавливает сообщение нарушения.
	 *
	 * @param message Сообщение нарушения.
	 */
	void setMessage(@NotNull String message);
}
