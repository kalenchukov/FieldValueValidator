/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.constraints;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ограничение по целым числам.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Number
{
	/**
	 * Задаёт минимальное значение.
	 *
	 * @return Минимальное значение.
	 */
	long min() default 0L;

	/**
	 * Задаёт максимальное значение.
	 *
	 * @return Максимальное значение.
	 */
	long max() default Long.MAX_VALUE;

	/**
	 * Задаёт сообщение о нарушении.
	 * Переменные: <ul>
	 *     <li>{@code %FIELD%} - название поля класса</li>
	 *     <li>{@code %MIN%} - минимальное значение</li>
	 *     <li>{@code %MAX%} - максимальное значение</li>
	 * </ul>
	 *
	 * @return Сообщение о нарушении.
	 */
	@NotNull
	String message() default "%DEFAULT_MESSAGE%";
}
