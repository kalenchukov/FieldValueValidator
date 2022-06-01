/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.validators;

import dev.kalenchukov.fieldvaluevalidator.FieldValueValidating;
import dev.kalenchukov.fieldvaluevalidator.FieldValueValidator;
import dev.kalenchukov.fieldvaluevalidator.Violating;
import dev.kalenchukov.fieldvaluevalidator.constraints.Length;
import dev.kalenchukov.fieldvaluevalidator.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LengthValidatorTest
{
	private static final FieldValueValidating FIELD_VALUE_VALIDATOR = new FieldValueValidator();

	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void TestValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Length(min = 3, max = 13)
			private Integer comment = 1;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeCorrect()
	{
		class Experimental
		{
			@Length(min = 3, max = 13)
			private String comment = "значение";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка без символов в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeCorrectZero()
	{
		class Experimental
		{
			@Length(min = 0, max = 13)
			private String comment = "";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с меньшим количеством символов в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeNotCorrectMin()
	{
		class Experimental
		{
			@Length(min = 3, max = 13)
			private String comment = "ВЦ"; // 2 символа
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с большим количеством символов в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeNotCorrectMax()
	{
		class Experimental
		{
			@Length(min = 3, max = 13)
			private String comment = "Мой комментарий к посту"; // 23 символа
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void TestValidStringTypeNull()
	{
		class Experimental
		{
			@Length(min = 3, max = 13)
			private String comment = null;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}
}