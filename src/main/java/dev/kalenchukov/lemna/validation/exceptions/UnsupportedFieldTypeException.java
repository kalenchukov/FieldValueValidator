/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.exceptions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Исключение для неподдерживаемого типа поля класса.
 */
public class UnsupportedFieldTypeException extends RuntimeException
{
	/**
	 * Конструктор для {@code UnsupportedFieldTypeException}.
	 *
	 * @param message Сообщение.
	 */
	public UnsupportedFieldTypeException(@NotNull final String message)
	{
		super(Objects.requireNonNull(message));
	}
}
