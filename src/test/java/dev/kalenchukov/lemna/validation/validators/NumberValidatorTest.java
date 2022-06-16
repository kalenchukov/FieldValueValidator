/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validator;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Number;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NumberValidatorTest
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
			@Number(min = 0, max = 1000)
			private String sum = "13";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Long sum = 10L;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Integer sum = 10;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Short sum = 10;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 120)
			private Byte sum = 10;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 0L;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 0;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 0;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 0;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeCorrect()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 785L;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeCorrect()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 785;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeCorrect()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 785;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeCorrect()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 98;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 1000L;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 1000;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 1000;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 120;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 1164L;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 1164;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 1164;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 127;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void TestValidLongTypeNull()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = null;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}
}