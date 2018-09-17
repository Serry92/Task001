package android.serry.task001.utilities;

import java.util.Locale;

public class Constants {
    public static final String URL_BASE = "http://souq.hardtask.co/app/app.asmx/";
    public static final String GET_CATEGORIES = URL_BASE + "GetCategories?categoryId=0&countryId=1";

    public boolean isAppInEnglish() {
        String deviceLanguage = Locale.getDefault().getDisplayLanguage();
        return deviceLanguage.equals("English");
    }
}
