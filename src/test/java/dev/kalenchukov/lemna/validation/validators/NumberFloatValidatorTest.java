/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validator;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.NumberFloat;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NumberFloatValidatorTest
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
			@NumberFloat(min = 0.0, max = 1000.1000)
			private String sum = "13.13";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeCorrectLessMin()
	{
		class Experimental
		{
			@NumberFloat(min = 100, max = 1000.1000)
			private Double sum = 10.10;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeCorrectLessMin()
	{
		class Experimental
		{
			@NumberFloat(min = 100, max = 1000.1000)
			private Float sum = 10.10F;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeCorrectMin()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 0.0;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeCorrectMin()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 0.0F;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeCorrect()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 785.785;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeCorrect()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 785.785F;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeCorrectMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 1000.1000;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeCorrectMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 1000.1000F;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeMoreMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 1164.1164;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeMoreMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 1164.1164F;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void TestValidDoubleTypeNull()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = null;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}
}