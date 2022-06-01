/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.supports.existences;

import dev.kalenchukov.fieldvaluevalidator.interfaces.Existable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GenderExistence implements Existable<String>
{
	@Override
	public boolean exists(@NotNull String value)
	{
		Objects.requireNonNull(value);

		return value.equals("MALE") || value.equals("FEMALE");
	}
}
