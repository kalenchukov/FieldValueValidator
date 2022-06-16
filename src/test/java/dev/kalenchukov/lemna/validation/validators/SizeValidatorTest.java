/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validator;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Size;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SizeValidatorTest
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
			@Size(min = 2, max = 4)
			private String days = "1, 2, 3";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());
	}

	/**
	 * Проверка с корректным значением в поле типа массив.
	 */
	@Test
	public void TestValidArrayTypeCorrect()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1, 2, 3};
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Collection}.
	 */
	@Test
	public void TestValidCollectionTypeCorrect()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1, 2, 3));
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Map}.
	 */
	@Test
	public void TestValidMapTypeCorrect()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Map<Integer, Integer> days = new HashMap<>(Map.ofEntries(
				Map.entry(0, 1),
				Map.entry(1, 2),
				Map.entry(2, 3)
			));
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка без элементов в поле типа массив.
	 */
	@Test
	public void TestValidArrayTypeCorrectZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private Integer[] days = {};
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка без элементов в поле типа {@code Collection}.
	 */
	@Test
	public void TestValidCollectionTypeCorrectZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private List<Integer> days = new ArrayList<>();
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка без элементов в поле типа {@code Map}.
	 */
	@Test
	public void TestValidMapTypeCorrectZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private Map<Integer, Integer> days = new HashMap<>();
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа массив.
	 */
	@Test
	public void TestValidArrayTypeNotCorrectMin()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1}; // 1 элемент
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа {@code Collection}.
	 */
	@Test
	public void TestValidCollectionTypeNotCorrectMin()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1)); // 1 элемент
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа {@code Map}.
	 */
	@Test
	public void TestValidMapTypeNotCorrectMin()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Map<Integer, Integer> days = new HashMap<>(Map.ofEntries(
				Map.entry(0, 1)
			)); // 1 элемент
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с большим количеством элементов в поле типа массив.
	 */
	@Test
	public void TestValidArrayTypeNotCorrectMax()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1, 2, 3, 4, 5}; // 5 элементов
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с большим количеством элементов в поле типа {@code Collection}.
	 */
	@Test
	public void TestValidCollectionTypeNotCorrectMax()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1, 2, 3, 4, 5)); // 5 элементов
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с большим количеством элементов в поле типа {@code Map}.
	 */
	@Test
	public void TestValidMapTypeNotCorrectMax()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Map<Integer, Integer> days = new HashMap<>(Map.ofEntries(
				Map.entry(0, 1),
				Map.entry(1, 2),
				Map.entry(2, 3),
				Map.entry(3, 4),
				Map.entry(4, 5)
			)); // 5 элементов
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void TestValidArrayTypeNull()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = null;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}
}