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
 * Ограничение по количеству символов.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Length
{
	/**
	 * Задаёт минимальное количество символов.
	 *
	 * @return Минимальное количество символов.
	 */
	int min() default 0;

	/**
	 * Задаёт максимальное количество символов.
	 *
	 * @return Максимальное количество символов.
	 */
	int max();

	/**
	 * Задаёт сообщение о нарушении.
	 * Переменные: <ul>
	 *     <li>{@code %FIELD%} - название поля класса</li>
	 *     <li>{@code %MIN%} - минимальное количество символов</li>
	 *     <li>{@code %MAX%} - максимальное количество символов</li>
	 * </ul>
	 *
	 * @return Сообщение о нарушении.
	 */
	@NotNull
	String message() default "%DEFAULT_MESSAGE%";
}
