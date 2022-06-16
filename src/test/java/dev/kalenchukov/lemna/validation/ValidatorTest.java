/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation;

import dev.kalenchukov.lemna.validation.constraints.Language;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ValidatorTest
{
	private static final Validating FIELD_VALUE_VALIDATOR = new Validator();

	/**
	 * Проверка настырности на примере ограничения {@link Language}.
	 */
	@Test
	public void TestValidatePushyTrue()
	{
		class Experimental
		{
			@Language
			private String language1 = "value";

			@Language
			private String language2 = "ru-RU";

			@Language
			private String language3 = "VALUE";
		}

		FIELD_VALUE_VALIDATOR.setPushy(true);
		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(2, violation.size());
	}

	/**
	 * Проверка не настырности на примере ограничения {@link Language}.
	 */
	@Test
	public void TestValidatePushyFalse()
	{
		class Experimental
		{
			@Language
			private String language1 = "ru-RU";

			@Language
			private String language2 = "value";

			@Language
			private String language3 = "VALUE";
		}

		FIELD_VALUE_VALIDATOR.setPushy(false);
		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка собственного сообщения о нарушении.
	 */
	@Test
	public void TestValidateMessage()
	{
		class Experimental
		{
			@Language(message = "Некорректный формат языка в поле класса '%FIELD%'")
			private String language1 = "value";
		}

		List<Violating> violation = FIELD_VALUE_VALIDATOR.validate(new Experimental());

		assertEquals("Некорректный формат языка в поле класса '%FIELD%'", violation.get(0).getMessage());
	}
}