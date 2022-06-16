/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.exceptions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Исключение для некорректного класса проверки корректности.
 */
public class InvalidValidationClassException extends RuntimeException
{
	/**
	 * Конструктор для {@code InvalidValidationClassException}.
	 *
	 * @param message Сообщение.
	 */
	public InvalidValidationClassException(@NotNull final String message)
	{
		super(Objects.requireNonNull(message));
	}
}
