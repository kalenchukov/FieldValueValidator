/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

module dev.kalenchukov.lemna.validation
{
	requires org.jetbrains.annotations;
	requires log4j;
	requires dev.kalenchukov.stringformat;

	exports dev.kalenchukov.lemna.validation;
	exports dev.kalenchukov.lemna.validation.constraints;
	exports dev.kalenchukov.lemna.validation.interfaces;
	exports dev.kalenchukov.lemna.validation.exceptions;
}