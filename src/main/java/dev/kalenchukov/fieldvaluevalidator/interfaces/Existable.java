/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс для реализации собственного класса для проверки существования.
 *
 * @param <T> Объект типа поля класса.
 */
public interface Existable<T>
{
	/**
	 * Проверяет существование значения.
	 *
	 * @param value Значение поля класса.
	 * @return {@code True} если значение существует, иначе {@code false}.
	 */
	boolean exists(@NotNull T value);
}
