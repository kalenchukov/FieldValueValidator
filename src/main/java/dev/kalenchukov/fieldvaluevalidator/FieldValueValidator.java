/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator;

import dev.kalenchukov.fieldvaluevalidator.constraints.Number;
import dev.kalenchukov.fieldvaluevalidator.validators.Validating;
import dev.kalenchukov.fieldvaluevalidator.constraints.*;
import dev.kalenchukov.fieldvaluevalidator.validators.*;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс проверки корректности значений полей класса.
 */
public class FieldValueValidator implements FieldValueValidating
{
	/**
	 * Локализация.
	 */
	@NotNull
	private Locale locale = new Locale("ru", "RU");

	/**
	 * Настырность проверки.
	 */
	@NotNull
	private Boolean pushy = true;

	/**
	 * Логгер для данного класса.
	 */
	@NotNull
	private static final Logger LOG = Logger.getLogger(FieldValueValidator.class);

	/**
	 * Локализованные тексты логирования.
	 */
	@NotNull
	private ResourceBundle localeLogs = ResourceBundle.getBundle("localizations/logs", this.locale);

	/**
	 * @see FieldValueValidating#setLocale(Locale)
	 */
	@Override
	public void setLocale(@NotNull final Locale locale)
	{
		Objects.requireNonNull(locale);

		if (!this.locale.equals(locale))
		{
			this.locale = locale;

			this.localeLogs = ResourceBundle.getBundle("localizations/logs", this.locale);
		}
	}

	/**
	 * @see FieldValueValidating#getLocale()
	 */
	@NotNull
	@Override
	public Locale getLocale()
	{
		return this.locale;
	}

	/**
	 * @see FieldValueValidating#isPushy()
	 */
	@NotNull
	@Override
	public Boolean isPushy()
	{
		return this.pushy;
	}

	/**
	 * @see FieldValueValidating#setPushy(Boolean)
	 */
	@Override
	public void setPushy(@NotNull final Boolean pushy)
	{
		Objects.requireNonNull(pushy);

		this.pushy = pushy;
	}

	/**
	 * @see FieldValueValidating#validate(Object)
	 */
	@UnmodifiableView
	@NotNull
	@Override
	public List<@NotNull Violating> validate(@NotNull final Object object)
	{
		Objects.requireNonNull(object);

		LOG.debug(String.format(
			this.localeLogs.getString("00001"),
			object.getClass().getName()
		));

		List<Violating> violations = new ArrayList<>();

		for (Field field : object.getClass().getDeclaredFields())
		{
			violations.addAll(
				this.validateField(object, field)
			);

			if (!this.pushy && violations.size() > 0) {
				break;
			}
		}

		LOG.debug(String.format(
			this.localeLogs.getString("00002"),
			object.getClass().getName()
		));

		return Collections.unmodifiableList(violations);
	}

	/**
	 * Проверяет поле класса на корректность.
	 *
	 * @param object Объект класса в котором поля необходимо проверить на корректность.
	 * @param field Поле класса.
	 * @return Коллекцию нарушений.
	 */
	@NotNull
	private List<@NotNull Violating> validateField(@NotNull final Object object, @NotNull final Field field)
	{
		Objects.requireNonNull(object);
		Objects.requireNonNull(field);

		List<Violating> violations = new ArrayList<>();

		Map<@NotNull String, @NotNull Validating> validators = this.loadValidators();

		for (Annotation constraintField : field.getDeclaredAnnotations())
		{
			Class<? extends Annotation> constraintType = constraintField.annotationType();
			Validating constraintValidator = validators.get(constraintType.getName());

			if (constraintValidator == null) {
				continue;
			}

			try
			{
				LOG.debug(String.format(
					this.localeLogs.getString("00003"),
					field.getName(),
					constraintType.getSimpleName()
				));

				field.setAccessible(true);

				Violating violation = constraintValidator.valid(field, field.get(object));

				field.setAccessible(false);

				if (violation == null)
				{
					LOG.debug(this.localeLogs.getString("00004"));
				}
				else
				{
					LOG.debug(this.localeLogs.getString("00006"));

					violations.add(violation);

					if (!this.pushy) {
						break;
					}
				}
			}
			catch (IllegalAccessException exception)
			{
				exception.printStackTrace();
			}
		}

		return violations;
	}

	/**
	 * Загружает коллекцию проверяющих.
	 *
	 * @return Коллекцию проверяющих.
	 */
	@UnmodifiableView
	@NotNull
	private Map<@NotNull String, @NotNull Validating> loadValidators()
	{
		Map<String, Validating> validators = new LinkedHashMap<>();

		validators.put(NoNull.class.getName(), new NoNullValidator(this.locale));
		validators.put(NoEmpty.class.getName(), new NoEmptyValidator(this.locale));
		validators.put(Size.class.getName(), new SizeValidator(this.locale));
		validators.put(Length.class.getName(), new LengthValidator(this.locale));
		validators.put(Language.class.getName(), new LanguageValidator(this.locale));
		validators.put(Number.class.getName(), new NumberValidator(this.locale));
		validators.put(NumberFloat.class.getName(), new NumberFloatValidator(this.locale));
		validators.put(Pattern.class.getName(), new PatternValidator(this.locale));

		validators.put(Valid.class.getName(), new ValidValidator(this.locale));
		validators.put(Valid.ManyValid.class.getName(), new ValidValidator(this.locale));

		validators.put(Exist.class.getName(), new ExistValidator(this.locale));
		validators.put(Exist.ManyExist.class.getName(), new ExistValidator(this.locale));

		return Collections.unmodifiableMap(validators);
	}
}
