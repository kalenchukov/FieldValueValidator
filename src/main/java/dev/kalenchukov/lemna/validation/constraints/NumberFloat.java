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
 * Ограничение по дробным числам.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NumberFloat
{
	/**
	 * Задаёт минимальное значение.
	 *
	 * @return Минимальное значение.
	 */
	double min() default 0.0;

	/**
	 * Задаёт максимальное значение.
	 *
	 * @return Максимальное значение.
	 */
	double max() default Double.MAX_VALUE;

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
