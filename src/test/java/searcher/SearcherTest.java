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
        String result = search(statement, keyword);
        assertThat(result, is(""));
    }

    private String search(String statement, String keyword) {
        return "";
    }
}
