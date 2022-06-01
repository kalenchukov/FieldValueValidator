/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.supports.existences;

import dev.kalenchukov.fieldvaluevalidator.interfaces.Existable;
import org.jetbrains.annotations.NotNull;

public class InvalidExistence implements Existable<String>
{
	@Override
	public boolean exists(@NotNull final String value)
	{
		return false;
	}
}
