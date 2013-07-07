package searcher;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearcherTest {
    private final Searcher searcher = new Searcher();

    @Test
    public void
    should_return_matched_result() {
        searchAndAssertResult(null, null, "");
        searchAndAssertResult(null, "", "");
        searchAndAssertResult("", null, "");
        searchAndAssertResult("", "", "");
        searchAndAssertResult("flex", "flex", "{flex}");
        searchAndAssertResult("there're keyword", "flex", "");
        searchAndAssertResult("reflex", "flex", "");
        searchAndAssertResult("r1flex", "flex", "");
        searchAndAssertResult("r_flex", "flex", "");
        searchAndAssertResult("r?flex", "flex", "r?{flex}");
        searchAndAssertResult("flexer", "flex", "");
        searchAndAssertResult("flex1r", "flex", "");
        searchAndAssertResult("flex_r", "flex", "");
        searchAndAssertResult("flex?r", "flex", "{flex}?r");
        searchAndAssertResult("(flex)", "flex",  "({flex})");
        searchAndAssertResult("no, flexible", "flex", "");
        searchAndAssertResult("no a flex", "flex", "no a {flex}");
    }

    private void searchAndAssertResult(String statement, String keyword, String expectedResult) {
        String result;
        result = searcher.search(statement, keyword);
        assertThat(result, is(expectedResult));
    }
}
