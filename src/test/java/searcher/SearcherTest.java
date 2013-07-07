package searcher;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearcherTest {
    @Test
    public void
    should_return_matched_result() {
        String statement = null;
        String keyword = null;
        String result;
        String expectedResult = "";
        result = search(statement, keyword);
        assertThat(result, is(expectedResult));

        statement = null;
        keyword = "";
        result = search(statement, keyword);
        assertThat(result, is(""));

        statement = "";
        keyword = null;
        result = search(statement, keyword);
        assertThat(result, is(""));

        statement = "";
        keyword = "";
        result = search(statement, keyword);
        assertThat(result, is(""));
    }

    private String search(String statement, String keyword) {
        return "";
    }
}
