/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
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
		this.params = new HashMap<>(params);
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

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		Violation violation = (Violation) obj;

		if (!Objects.equals(this.getField(), violation.getField())) {
			return false;
		}

		if (!Objects.equals(this.getMessage(), violation.getMessage())) {
			return false;
		}

		if (!Objects.equals(this.getParams(), violation.getParams())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = 0;

		result = 31 * result + this.getField().hashCode();
		result = 31 * result + this.getMessage().hashCode();
		result = 31 * result + this.getParams().hashCode();

		return result;
	}

	@NotNull
	@Override
	public String toString()
	{
		return "Violation{" + "field='" + field + '\'' + ", message='" + message + '\'' + ", params=" + params + '}';
	}
}
