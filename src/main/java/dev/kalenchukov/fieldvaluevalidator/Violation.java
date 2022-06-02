/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator;

import dev.kalenchukov.stringformat.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * Класс нарушения.
 */
public final class Violation implements Violating
{
	/**
	 * Название поля класса.
	 */
	@NotNull
	private final String field;

	/**
	 * Сообщение о нарушении.
	 */
	@NotNull
	private final String message;

	/**
	 * Параметры нарушения.
	 */
	@NotNull
	private final Map<@NotNull String, @NotNull String> params;

	/**
	 * Конструктор для {@code Violation}.
	 *
	 * @param field Название поля класса.
	 * @param message Сообщение о нарушении.
	 * @param params Параметры нарушения.
	 * <ul>
	 * 		<li><b>key</b> - название.</li>
	 * 		<li><b>value</b> - значение.</li>
	 * </ul>
	 */
	public Violation(@NotNull final String field,
					 @NotNull final String message,
					 @NotNull final Map<@NotNull String, @NotNull String> params)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(message);
		Objects.requireNonNull(params);

		this.field = field;
		this.message = message;
		this.params = params;
	}

	/**
	 * @see Violating#getField()
	 */
	@NotNull
	public String getField()
	{
		return this.field;
	}

	/**
	 * @see Violating#getMessage()
	 */
	@NotNull
	public String getMessage()
	{
		return this.message;
	}

	/**
	 * @see Violating#getParams()
	 */
	@UnmodifiableView
	@NotNull
	public Map<@NotNull String, @NotNull String> getParams()
	{
		return Collections.unmodifiableMap(this.params);
	}

	@NotNull
	@Override
	public String toString()
	{
		return "Violation{" + "field='" + field + '\'' + ", message='" + message + '\'' + ", params=" + params + '}';
	}
}
