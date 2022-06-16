/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.constraints;

import dev.kalenchukov.lemna.validation.interfaces.Existable;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * Ограничение по существованию с помощью собственной реализации.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Repeatable(Exist.ManyExist.class)
public @interface Exist
{
	/**
	 * Задаёт класс с собственной реализацией проверки существования.
	 *
	 * @return Класс с собственной реализацией проверки существования.
	 */
	@NotNull
	Class<? extends Existable<?>> existence();

	/**
	 * Задаёт сообщение о нарушении.
	 * Переменные: <ul>
	 *     <li>{@code %FIELD%} - название поля класса</li>
	 * </ul>
	 *
	 * @return Сообщение о нарушении.
	 */
	@NotNull
	String message() default "%DEFAULT_MESSAGE%";

	/**
	 * Ограничение множественным {@code Exist}.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface ManyExist
	{
		/**
		 * Задаёт множество {@code Exist}.
		 *
		 * @return Массив из {@code Exist}.
		 */
		@NotNull
		Exist @NotNull [] value();
	}
}
