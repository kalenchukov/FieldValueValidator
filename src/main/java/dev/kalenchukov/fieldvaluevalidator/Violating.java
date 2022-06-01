/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.fieldvaluevalidator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Интерфейс для реализации класса нарушения.
 */
public interface Violating
{
	/**
	 * Возвращает название поля класса.
	 *
	 * @return Название поля класса.
	 */
	@NotNull
	String getField();

	/**
	 * Задаёт сообщение о нарушении.
	 *
	 * @return Сообщение о нарушении.
	 */
	@NotNull
	String getMessage();

	/**
	 * Возвращает параметры нарушения.
	 * <ul>
	 * 		<li><b>key</b> - название.</li>
	 * 		<li><b>value</b> - значение.</li>
	 * </ul>
	 * @return Коллекцию параметров нарушения.
	 */
	@UnmodifiableView
	@NotNull
	Map<@NotNull String, @NotNull String> getParams();
}
