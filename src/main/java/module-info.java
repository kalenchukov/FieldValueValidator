/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

module dev.kalenchukov.fieldvaluevalidator
{
	requires org.jetbrains.annotations;
	requires log4j;
	requires dev.kalenchukov.stringformat;

	exports dev.kalenchukov.fieldvaluevalidator;
	exports dev.kalenchukov.fieldvaluevalidator.constraints;
	exports dev.kalenchukov.fieldvaluevalidator.interfaces;
	exports dev.kalenchukov.fieldvaluevalidator.exceptions;
}