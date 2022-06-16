/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Locale;

/**
 * Интерфейс для реализации класса по проверке корректности значений полей класса.
 */
public interface Validating
{
	/**
	 * Устанавливает локализацию.
	 *
	 * @param locale Локализация.
	 */
	void setLocale(@NotNull Locale locale);

	/**
	 * Возвращает локализация.
	 *
	 * @return Локализация.
	 */
	@NotNull
	Locale getLocale();

	/**
	 * Определяет настырность проверки.
	 *
	 * @return {@code True} если проверка настырная, иначе {@code false}.
	 */
	@NotNull
	Boolean isPushy();

	/**
	 * Устанавливает настырность проверки.
	 *
	 * @param pushy Настырность.
	 */
	void setPushy(@NotNull Boolean pushy);

	/**
	 * Проверяет поля класса на корректность.
	 *
	 * @param object Объект класса в котором поля необходимо проверить на корректность.
	 * @return Коллекцию нарушений.
	 */
	@UnmodifiableView
	@NotNull
	List<@NotNull Violating> validate(@NotNull Object object);
}
