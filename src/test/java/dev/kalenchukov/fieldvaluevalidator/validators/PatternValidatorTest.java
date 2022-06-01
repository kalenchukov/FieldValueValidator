/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.validators;

import dev.kalenchukov.fieldvaluevalidator.FieldValueValidating;
import dev.kalenchukov.fieldvaluevalidator.FieldValueValidator;
import dev.kalenchukov.fieldvaluevalidator.Violating;
import dev.kalenchukov.fieldvaluevalidator.constraints.Pattern;
import dev.kalenchukov.fieldvaluevalidator.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PatternValidatorTest
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
			@Pattern(regexp = "[0-9A-F]+")
			private Integer hexadecimal = 12345;
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
			@Pattern(regexp = "[0-9A-F]+")
			private String hexadecimal = "08A6D9";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Character}.
	 */
	@Test
	public void TestValidCharacterTypeCorrect()
	{
		class Experimental
		{
			@Pattern(regexp = "[0-9A-F]")
			private Character hexadecimal = 'C';
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeNotCorrect()
	{
		class Experimental
		{
			@Pattern(regexp = "[0-9A-F]+")
			private String hexadecimal = "08A6DZ";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с некорректным значением в поле типа {@code Character}.
	 */
	@Test
	public void TestValidCharacterTypeNotCorrect()
	{
		class Experimental
		{
			@Pattern(regexp = "[0-9A-F]")
			private Character hexadecimal = 'Z';
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
			@Pattern(regexp = "[0-9A-F]+")
			private String hexadecimal = null;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}
}