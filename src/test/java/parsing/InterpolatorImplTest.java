package parsing;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InterpolatorImplTest {
    private final InterpolatorImpl interpolator = new InterpolatorImpl();

    @Test
    public void interpolateText() throws Exception {
        assertThat(interpolator.interpolateText("{{foo}}", "bar"), is("bar"));
    }

    @Test
    public void interpolateText_list() {
        List<String> strings = Arrays.asList("spam", "eggs");
        assertThat(interpolator.interpolateText("{{foo}}, {{bar}}", strings).contains("spam"), is(true));
        assertThat(interpolator.interpolateText("{{foo}}, {{bar}}", strings).contains("{"), is(false));
    }

    @Test
    public void interpolatorText_JSONInterpolation() throws IOException {
        List<String> strings = Arrays.asList("spam", "eggs", "ham");

        String line;

        FileReader fr = new FileReader("/home/raymond/IdeaProjects/src/test/java/parsing/test.json");
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();

        while((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }

        fr.close();

        System.out.println(interpolator.interpolateText(sb.toString(), strings));
    }

}