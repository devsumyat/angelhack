package mm.com.sumyat.nurse.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * This class is used to change your application locale and persist this change for the next time
 * that your app is going to be used.
 * <p/>
 * You can also change the locale of your application on the fly by using the setLocale method.
 * <p/>
 * Created by gunhansancar on 07/10/15.
 */
public class LocaleHelper {

	private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
	private static final String SELECTED_COUNTRY = "Locale.helper.Selected.Country";

	public static Context onAttach(Context context) {
		String lang = getPersistedLanguage(context, Locale.getDefault().getLanguage());
		String country = getPersistedCountry(context, Locale.getDefault().getCountry());
		return setLocale(context, lang, country);
	}

	public static Context onAttach(Context context, String defaultLanguage, String defaultCountry) {
		String lang = getPersistedLanguage(context, defaultLanguage);
		String country = getPersistedCountry(context, defaultCountry);
		return setLocale(context, lang, country);
	}

	public static String getLanguage(Context context) {
		return getPersistedLanguage(context, Locale.getDefault().getLanguage());
	}

	public static String getCountry(Context context) {
		return getPersistedCountry(context, Locale.getDefault().getCountry());
	}

	public static Context setLocale(Context context, String language, String country) {
		persist(context, language, country);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return updateResources(context, language, country);
		}

		return updateResourcesLegacy(context, language, country);
	}

	private static String getPersistedLanguage(Context context, String defaultLanguage) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
	}

	private static String getPersistedCountry(Context context, String defaultLanguage) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(SELECTED_COUNTRY, defaultLanguage);
	}


	private static void persist(Context context, String language, String country) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();

		editor.putString(SELECTED_LANGUAGE, language);
		editor.putString(SELECTED_COUNTRY, country);
		editor.apply();
	}

	@TargetApi(Build.VERSION_CODES.N)
	private static Context updateResources(Context context, String language, String country) {
		Locale locale = new Locale(language, country);
		Locale.setDefault(locale);

		Configuration configuration = context.getResources().getConfiguration();
		configuration.setLocale(locale);
		configuration.setLayoutDirection(locale);

		return context.createConfigurationContext(configuration);
	}

	@SuppressWarnings("deprecation")
	private static Context updateResourcesLegacy(Context context, String language, String country) {
		Locale locale = new Locale(language, country);
		Locale.setDefault(locale);

		Resources resources = context.getResources();

		Configuration configuration = resources.getConfiguration();
		configuration.locale = locale;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			configuration.setLayoutDirection(locale);
		}

		resources.updateConfiguration(configuration, resources.getDisplayMetrics());

		return context;
	}
}