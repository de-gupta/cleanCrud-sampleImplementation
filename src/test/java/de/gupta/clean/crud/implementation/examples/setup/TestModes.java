package de.gupta.clean.crud.implementation.examples.setup;

import java.util.Locale;

public final class TestModes
{
	public static final String PROPERTY_NAME = "testMode";

	public static Mode current()
	{
		return Mode.from(System.getProperty(PROPERTY_NAME, Mode.MEDIUM.propertyValue));
	}

	private TestModes()
	{
	}

	public enum Mode
	{
		FAST("fast"),
		MEDIUM("medium"),
		FULL("full");

		private final String propertyValue;

		public static Mode from(final String propertyValue)
		{
			return switch (propertyValue.toLowerCase(Locale.ROOT))
			{
				case "fast" -> FAST;
				case "medium" -> MEDIUM;
				case "full" -> FULL;
				default -> throw new IllegalArgumentException(
						"Unsupported test mode '" + propertyValue + "'. Expected one of: fast, medium, full.");
			};
		}

		public boolean includes(final Mode requiredMode)
		{
			return ordinal() >= requiredMode.ordinal();
		}

		Mode(final String propertyValue)
		{
			this.propertyValue = propertyValue;
		}
	}
}