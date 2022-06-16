/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validator;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.NoNull;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NoNullValidatorTest
{
	private static final Validating FIELD_VALUE_VALIDATOR = new Validator();

	/**
	 * Проверка с корректным значением.
	 */
	@Test
	public void TestValidCorrect()
	{
		class Experimental
		{
			@NoNull
			private Long id = 1L;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void TestValidNotCorrect()
	{
		class Experimental
		{
			@NoNull
			private Long id = null;
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}
}