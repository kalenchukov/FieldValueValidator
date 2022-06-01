/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.validators;

import dev.kalenchukov.fieldvaluevalidator.FieldValueValidating;
import dev.kalenchukov.fieldvaluevalidator.FieldValueValidator;
import dev.kalenchukov.fieldvaluevalidator.Violating;
import dev.kalenchukov.fieldvaluevalidator.constraints.Valid;
import dev.kalenchukov.fieldvaluevalidator.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.fieldvaluevalidator.supports.validators.BrowserValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ValidValidatorTest
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
			@Valid(validator = BrowserValidator.class)
			private Integer browser = 1;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());
	}

//	/**
//	 * Проверка с некорректным классом проверки.
//	 */
//	@Test(expected = InvalidValidationClassException.class)
//	public void TestValidNotCorrectClass()
//	{
//		class Experimental
//		{
//			@Valid(validator = InvalidValidator.class)
//			private String browser = "FIREFOX";
//		}
//
//		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());
//	}

	/**
	 * Проверка с корректным значением поля.
	 */
	@Test
	public void TestValidCorrect()
	{
		class Experimental
		{
			@Valid(validator = BrowserValidator.class)
			private String browser = "FIREFOX";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с множественной проверкой и корректным значением поля.
	 */
	@Test
	public void TestValidCorrectMany()
	{
		class Experimental
		{
			@Valid(validator = BrowserValidator.class)
			@Valid(validator = BrowserValidator.class)
			private String browser = "FIREFOX";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением поля.
	 */
	@Test
	public void TestValidNotCorrect()
	{
		class Experimental
		{
			@Valid(validator = BrowserValidator.class)
			private String browser = "ЗНАЧЕНИЕ";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void TestValidNull()
	{
		class Experimental
		{
			@Valid(validator = BrowserValidator.class)
			private String browser = null;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}
}