/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Number;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.Violation;
import dev.kalenchukov.stringformat.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link Number}.
 */
public final class NumberValidator extends AbstractValidator
{
	/**
	 * @see AbstractValidator#AbstractValidator(Locale)
	 */
	public NumberValidator(@NotNull final Locale locale)
	{
		super(Objects.requireNonNull(locale));
	}

	/**
	 * @see Validating#valid(Field, Object)
	 */
	@Nullable
	@Override
	public Violating valid(@NotNull final Field field, @Nullable final Object value)
	{
		Objects.requireNonNull(field);

		Number constraint = field.getDeclaredAnnotation(Number.class);

		boolean valid = this.isValid(field, constraint, value);

		if (!valid)
		{
			this.setParam("FIELD", field.getName());
			this.setParam("MIN", String.valueOf(constraint.min()));
			this.setParam("MAX", String.valueOf(constraint.max()));

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
	private boolean isValid(@NotNull final Field field, @NotNull final Number constraint, @Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		if (value.getClass().equals(Integer.class))
		{
			return this.isValidInteger(constraint, (Integer) value);
		}
		else if (value.getClass().equals(Short.class))
		{
			return this.isValidShort(constraint, (Short) value);
		}
		else if (value.getClass().equals(Byte.class))
		{
			return this.isValidByte(constraint, (Byte) value);
		}
		else if (value.getClass().equals(Long.class))
		{
			return this.isValidLong(constraint, (Long) value);
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
	 * Проверка значения поля класса типа {@code Integer}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidInteger(@NotNull final Number constraint, @NotNull final Integer value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, Long.valueOf(value));
	}

	/**
	 * Проверка значения поля класса типа {@code Short}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidShort(@NotNull final Number constraint, @NotNull final Short value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, Long.valueOf(value));
	}

	/**
	 * Проверка значения поля класса типа {@code Byte}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidByte(@NotNull final Number constraint, @NotNull final Byte value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, Long.valueOf(value));
	}

	/**
	 * Проверка значения поля класса типа {@code Long}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidLong(@NotNull final Number constraint, @NotNull final Long value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, value);
	}

	/**
	 * Проверка значения поля класса абстрактного типа.
	 * В качестве абстракции используется тип {@code Long}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidAbstract(@NotNull final Number constraint, @NotNull final Long value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		if (value < constraint.min())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90008")
			));

			return false;
		}

		if (value > constraint.max())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90009")
			));

			return false;
		}

		return true;
	}
}
