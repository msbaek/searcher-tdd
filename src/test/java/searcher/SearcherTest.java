package searcher;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearcherTest {
    @Test
    public void
    should_return_matched_result() {
        searchAndAssertResult(null, null, "");
        searchAndAssertResult(null, "", "");
        searchAndAssertResult("", null, "");
        searchAndAssertResult("", "", "");
        searchAndAssertResult("flex", "flex", "{flex}");
    }

    private void searchAndAssertResult(String statement, String keyword, String expectedResult) {
        String result;
        result = search(statement, keyword);
        assertThat(result, is(expectedResult));
    }

    private String search(String statement, String keyword) {
        return "";
    }
}
