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
        searchAndAssertResult(statement, keyword, expectedResult);

        statement = null;
        keyword = "";
        searchAndAssertResult(statement, keyword, "");

        statement = "";
        keyword = null;
        searchAndAssertResult(statement, keyword, "");

        statement = "";
        keyword = "";
        searchAndAssertResult(statement, keyword, "");
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
