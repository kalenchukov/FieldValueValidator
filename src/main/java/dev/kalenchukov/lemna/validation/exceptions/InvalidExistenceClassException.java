/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.exceptions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Исключение для некорректного класса проверки существования.
 */
public class InvalidExistenceClassException extends RuntimeException
{
	/**
	 * Конструктор для {@code InvalidExistenceClassException}.
	 *
	 * @param message Сообщение.
	 */
	public InvalidExistenceClassException(@NotNull final String message)
	{
		super(Objects.requireNonNull(message));
	}
}
