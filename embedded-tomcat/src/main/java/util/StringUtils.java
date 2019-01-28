package util;

public class StringUtils {

    public static String firstCharacterUpper(String url) {
        return url.substring(0, 1).toUpperCase() + url.substring(1);
    }

    public static String convertUrl(String url) {
        String[] urlToken = url.split("/");
        if (url.contains("/")) {
            StringBuilder urlBuilder = new StringBuilder();
            for (String className : urlToken) {
                urlBuilder.append(StringUtils.firstCharacterUpper(className));
            }
            url = urlBuilder.toString();
        } else {
            url = StringUtils.firstCharacterUpper(url);
        }
        return url;
    }
}
