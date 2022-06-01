/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.validators;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Класс абстрактного проверяющего.
 */
public abstract class AbstractValidator implements Validating
{
	/**
	 * Локализация.
	 */
	@NotNull
	protected Locale locale = new Locale("ru", "RU");

	/**
	 * Локализованные тексты логирования.
	 */
	@NotNull
	protected ResourceBundle localeLogs = ResourceBundle.getBundle("localizations/logs", this.locale);

	/**
	 * Локализованные тексты нарушений.
	 */
	@NotNull
	protected ResourceBundle localeViolations = ResourceBundle.getBundle("localizations/violations", this.locale);

	/**
	 * Локализованные тексты исключений.
	 */
	@NotNull
	protected ResourceBundle localeExceptions = ResourceBundle.getBundle("localizations/exceptions", this.locale);

	/**
	 * Сообщение нарушения.
	 */
	@NotNull
	protected String message = "";

	/**
	 * Параметры нарушения.
	 */
	@NotNull
	protected Map<@NotNull String, @NotNull String> params = new HashMap<>();

	/**
	 * Конструктор для {@code AbstractValidator}.
	 *
	 * @param locale Локализация.
	 */
	public AbstractValidator(@NotNull final Locale locale)
	{
		Objects.requireNonNull(locale);

		if (!this.locale.equals(locale))
		{
			this.locale = locale;

			this.localeLogs = ResourceBundle.getBundle("localizations/logs", this.locale);
			this.localeViolations = ResourceBundle.getBundle("localizations/violations", this.locale);
		}
	}

	/**
	 * @see Validating#getParams()
	 */
	@NotNull
	@Override
	public Map<@NotNull String, @NotNull String> getParams()
	{
		return this.params;
	}

	/**
	 * @see Validating#setParam(String, String)
	 */
	@Override
	public void setParam(@NotNull final String key, @NotNull final String value)
	{
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);

		this.params.put(key, value);
	}

	/**
	 * @see Validating#getMessage()
	 */
	@NotNull
	@Override
	public String getMessage()
	{
		return this.message;
	}

	/**
	 * @see Validating#setMessage(String)
	 */
	@Override
	public void setMessage(@NotNull final String message)
	{
		Objects.requireNonNull(message);

		this.message = message;
	}
}
