/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.validators;

import dev.kalenchukov.fieldvaluevalidator.Violating;
import dev.kalenchukov.fieldvaluevalidator.constraints.Size;
import dev.kalenchukov.fieldvaluevalidator.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.fieldvaluevalidator.Violation;
import dev.kalenchukov.stringformat.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс проверяющего для ограничения {@link Size}.
 */
public final class SizeValidator extends AbstractValidator
{
	/**
	 * @see AbstractValidator#AbstractValidator(Locale)
	 */
	public SizeValidator(@NotNull final Locale locale) {super(locale);}

	/**
	 * @see Validating#valid(Field, Object)
	 */
	@Nullable
	@Override
	public Violating valid(@NotNull final Field field, @Nullable final Object value)
	{
		Size constraint = field.getDeclaredAnnotation(Size.class);

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
	private boolean isValid(@NotNull final Field field, @NotNull final Size constraint, @Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		if (value.getClass().isArray())
		{
			return this.isValidArray(constraint, (Object) value);
		}
		else if (value instanceof Collection<?>)
		{
			return this.isValidCollection(constraint, (Collection<?>) value);
		}
		else if (value instanceof Map<?, ?>)
		{
			return this.isValidMap(constraint, (Map<?, ?>) value);
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
	 * Проверка значения поля класса типа {@code Map}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidMap(@NotNull final Size constraint, @NotNull final Map<?, ?> value)
	{
		return this.isValidAbstract(constraint, value.size());
	}

	/**
	 * Проверка значения поля класса типа {@code Collection}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidCollection(@NotNull final Size constraint, @NotNull final Collection<?> value)
	{
		return this.isValidAbstract(constraint, value.size());
	}

	/**
	 * Проверка значения поля класса типа массив.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidArray(@NotNull final Size constraint, @NotNull final Object value)
	{
		return this.isValidAbstract(constraint, Array.getLength(value));
	}

	/**
	 * Проверка значения поля класса абстрактного типа.
	 * В качестве абстракции используется количество элементов.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param length Количество элементов значения поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidAbstract(@NotNull final Size constraint, @NotNull final Integer length)
	{
		if (length < constraint.min())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90005")
			));

			return false;
		}

		if (length > constraint.max())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90006")
			));

			return false;
		}

		return true;
	}
}
