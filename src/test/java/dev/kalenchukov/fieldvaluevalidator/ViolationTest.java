/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ViolationTest
{
	/**
	 * Проверка одинаковых объектов.
	 */
	@Test
	public void testEquals1()
	{
		Map<String, String> params = new HashMap<>();
		params.put("%FIELD%", "id");

		Violating violation1 = new Violation("id", "Сообщение", params);
		Violating violation2 = new Violation("id", "Сообщение", params);

		assertTrue(violation1.equals(violation2));
	}

	/**
	 * Проверка разных объектов.
	 */
	@Test
	public void testEquals2()
	{
		Map<String, String> params = new HashMap<>();
		params.put("%FIELD%", "id");

		Violating violation1 = new Violation("id", "Сообщение", params);
		Violating violation2 = new Violation("name", "Сообщение", params);

		assertFalse(violation1.equals(violation2));
	}
}