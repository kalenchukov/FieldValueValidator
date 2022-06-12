/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.validators;

import dev.kalenchukov.fieldvaluevalidator.Violating;
import dev.kalenchukov.fieldvaluevalidator.constraints.Valid;
import dev.kalenchukov.fieldvaluevalidator.exceptions.InvalidExistenceClassException;
import dev.kalenchukov.fieldvaluevalidator.exceptions.InvalidValidationClassException;
import dev.kalenchukov.fieldvaluevalidator.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.fieldvaluevalidator.interfaces.Validable;
import dev.kalenchukov.fieldvaluevalidator.Violation;
import dev.kalenchukov.stringformat.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Objects;
/**
 * Класс проверяющего для ограничения {@link Valid}.
 */
public final class ValidValidator extends AbstractValidator
{
	/**
	 * @see AbstractValidator#AbstractValidator(Locale)
	 */
	public ValidValidator(@NotNull final Locale locale)
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

		Valid[] constraints = field.getDeclaredAnnotationsByType(Valid.class);

		for (Valid constraint : constraints)
		{
			boolean valid = this.isValid(field, constraint, value);

			if (!valid)
			{
				this.setParam("FIELD", field.getName());

				return new Violation(
					field.getName(),
					this.getMessage(),
					this.getParams()
				);
			}
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
	 * @throws InvalidExistenceClassException Если класс проверки существования некорректный.
	 */
	private boolean isValid(@NotNull final Field field, @NotNull final Valid constraint, @Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		Class<? extends Validable<?>> validator = constraint.validator();

		try
		{
			Method method = validator.getMethod("valid", field.getType());

			boolean valid = (boolean) method.invoke(
				validator.getConstructor().newInstance(),
				value
			);

			if (!valid)
			{
				this.setMessage(StringFormat.format(
					constraint.message(),
					"DEFAULT_MESSAGE",
					this.localeViolations.getString("90002")
				));

				return false;
			}
		}
		catch (NoSuchMethodException exception)
		{
			throw new UnsupportedFieldTypeException(String.format(
				this.localeExceptions.getString("20005"),
				validator.getName()
			));
		}
		catch (Exception exception)
		{
			throw new InvalidValidationClassException(String.format(
				this.localeExceptions.getString("20003"),
				validator.getName()
			));
		}

		return true;
	}
}
