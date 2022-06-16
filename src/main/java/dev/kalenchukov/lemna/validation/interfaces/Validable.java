/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс для реализации собственного класса для проверки корректности.
 *
 * @param <T> Объект типа поля класса.
 */
public interface Validable<T>
{
	/**
	 * Проверяет корректность значения.
	 *
	 * @param value Значение поля класса.
	 * @return {@code True} если значение корректно, иначе {@code false}.
	 */
	boolean valid(@NotNull T value);
}
