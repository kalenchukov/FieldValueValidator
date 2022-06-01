/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.validators;

import dev.kalenchukov.fieldvaluevalidator.Violating;
import dev.kalenchukov.fieldvaluevalidator.constraints.Pattern;
import dev.kalenchukov.fieldvaluevalidator.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.fieldvaluevalidator.Violation;
import dev.kalenchukov.stringformat.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link Pattern}.
 */
public final class PatternValidator extends AbstractValidator
{
	/**
	 * @see AbstractValidator#AbstractValidator(Locale)
	 */
	public PatternValidator(@NotNull final Locale locale) {super(locale);}

	/**
	 * @see Validating#valid(Field, Object)
	 */
	@Nullable
	@Override
	public Violating valid(@NotNull final Field field, @Nullable final Object value)
	{
		Pattern constraint = field.getDeclaredAnnotation(Pattern.class);

		boolean valid = this.isValid(field, constraint, value);

		if (!valid)
		{
			this.setParam("FIELD", field.getName());
			this.setParam("REGEXP", constraint.regexp());

			return new Violation(
				field.getName(),
				this.getMessage(),
				this.getParams()
			);
		}

		return null;
	}


	/**
	 * Проверяет корректность значения поля класса.
	 *
	 * @param field Поле класса.
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля класса корректно, иначе {@code false}.
	 * @throws UnsupportedFieldTypeException Если тип поля класса не поддерживается данным ограничением.
	 */
	private boolean isValid(@NotNull final Field field, @NotNull final Pattern constraint, @Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		if (value.getClass().equals(String.class))
		{
			return this.isValidString(constraint, (String) value);
		}
		else if (value.getClass().equals(Character.class))
		{
			return this.isValidCharacter(constraint, (Character) value);
		}
		else
		{
			throw new UnsupportedFieldTypeException(String.format(
				this.localeExceptions.getString("20001"),
				constraint.getClass().getSimpleName()
			));
		}
	}

	/**
	 * Проверка значения поля класса типа {@code Character}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidCharacter(@NotNull final Pattern constraint, @NotNull final Character value)
	{
		return this.isValidAbstract(constraint, String.valueOf(value));
	}

	/**
	 * Проверка значения поля класса типа {@code String}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidString(@NotNull final Pattern constraint, @NotNull final String value)
	{
		return this.isValidAbstract(constraint, value);
	}

	/**
	 * Проверка значения поля класса абстрактного типа.
	 * В качестве абстракции используется тип {@code String}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidAbstract(@NotNull final Pattern constraint, @NotNull final String value)
	{
		if (!value.matches(constraint.regexp()))
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90010")
			));

			return false;
		}

		return true;
	}
}
