/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.constraints;

import dev.kalenchukov.lemna.validation.interfaces.Validable;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * Ограничение по корректности с помощью собственной реализации.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Repeatable(Valid.ManyValid.class)
public @interface Valid
{
	/**
	 * Задаёт класс с собственной реализацией проверки корректности.
	 *
	 * @return Класс с собственной реализацией проверки корректности.
	 */
	@NotNull
	Class<? extends Validable<?>> validator();

	/**
	 * Задаёт сообщение о нарушении.
	 * Переменные: <ul>
	 *     <li>{@code %FIELD%} - название поля класса</li>
	 * </ul>
	 * @return Сообщение о нарушении.
	 */
	@NotNull
	String message() default "%DEFAULT_MESSAGE%";

	/**
	 * Ограничение множественным {@code Valid}.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface ManyValid
	{
		/**
		 * Задаёт множество {@code Valid}.
		 *
		 * @return Массив из {@code Valid}.
		 */
		@NotNull
		Valid @NotNull [] value();
	}
}
