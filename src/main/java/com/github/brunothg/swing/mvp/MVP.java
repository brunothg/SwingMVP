package com.github.brunothg.swing.mvp;

public final class MVP
{
	public static final String LOGO_ASCII_ART = "\r\n   _____            _                _____         _               __  ____      _______  \r\n  / ____|          (_)              / ____|       (_)             |  \\/  \\ \\    / /  __ \\ \r\n | (___  _ __  _ __ _ _ __   __ _  | (_____      ___ _ __   __ _  | \\  / |\\ \\  / /| |__) |\r\n  \\___ \\| \'_ \\| \'__| | \'_ \\ / _` |  \\___ \\ \\ /\\ / / | \'_ \\ / _` | | |\\/| | \\ \\/ / |  ___/ \r\n  ____) | |_) | |  | | | | | (_| |  ____) \\ V  V /| | | | | (_| | | |  | |  \\  /  | |     \r\n |_____/| .__/|_|  |_|_| |_|\\__, | |_____/ \\_/\\_/ |_|_| |_|\\__, | |_|  |_|   \\/   |_|     \r\n        | |                  __/ |                          __/ |                         \r\n        |_|                 |___/                          |___/                          ";

	public static String getViewNameFromAnnotation(Class<?> type, SwingView annotation)
	{
		String name = annotation.name();
		if (name.equals(SwingView.AUTO_CREATION_NAME))
		{
			name = type.getCanonicalName();
		}

		return name;
	}

	public static String getViewNameFromAnnotation(Class<?> type, SwingPresenter annotation)
	{
		String name = annotation.viewName();
		if (name.equals(SwingPresenter.AUTO_CREATION_NAME))
		{
			name = type.getCanonicalName();
		}

		return name;
	}

}
