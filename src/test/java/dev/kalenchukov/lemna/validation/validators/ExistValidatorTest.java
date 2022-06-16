/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validator;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Exist;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.supports.existences.GenderExistence;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExistValidatorTest
{
	private static final Validating FIELD_VALUE_VALIDATOR = new Validator();

	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void TestValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			private Integer gender = 1;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());
	}

//	/**
//	 * Проверка с некорректным классом проверки.
//	 */
//	@Test(expected = InvalidExistenceClassException.class)
//	public void TestValidNotCorrectClass()
//	{
//		class Experimental
//		{
//			@Exist(existence = InvalidExistence.class)
//			private String gender = "MALE";
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
			@Exist(existence = GenderExistence.class)
			private String gender = "MALE";
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
			@Exist(existence = GenderExistence.class)
			@Exist(existence = GenderExistence.class)
			private String gender = "MALE";
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
			@Exist(existence = GenderExistence.class)
			private String gender = "ЗНАЧЕНИЕ";
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
			@Exist(existence = GenderExistence.class)
			private String gender = null;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}
}