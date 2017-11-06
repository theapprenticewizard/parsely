package parsing;

import java.util.List;

public interface Interpolator {
    String interpolateText(String target, List<String> strings);
    String interpolateText(String target, String replacement);
}
