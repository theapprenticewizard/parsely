package parsing;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpolatorImpl implements Interpolator {
    private final Pattern regex = Pattern.compile("\\{\\{(.+?)\\}\\}");


    /**
     * Interpolate a target string, replacing all instances in between {{}} with a replacement text
     * @param target
     * @param replacement
     * @return
     */
    public String interpolateText(String target, String replacement) {
        Matcher matcher = regex.matcher(target);
        return matcher.replaceAll(replacement);
    }

    /**
     * Interpolate {{}} and replace one by one with a list of Strings.
     * If there are more replacements than groups to interpolate the replacements will be ignored.
     * @param target
     * @param replacements
     * @return
     */
    public String interpolateText(String target, List<String> replacements) {
        Matcher matcher = regex.matcher(target);
        String result = null;

        for (String s :
                replacements) {
            result = matcher.replaceFirst(s);
            matcher.reset(result);
        }

        return result;
    }
}
