/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.supports.validators;

import dev.kalenchukov.lemna.validation.interfaces.Validable;
import org.jetbrains.annotations.NotNull;

public class BrowserValidator implements Validable<String>
{
	@Override
	public boolean valid(@NotNull final String value)
	{
		return value.equals("FIREFOX") || value.equals("CHROME");
	}
}
