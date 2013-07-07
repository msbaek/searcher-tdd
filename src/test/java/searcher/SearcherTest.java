package searcher;

import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isEmpty;
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
        result = search(statement, keyword);
        assertThat(result, is(expectedResult));
    }

    private String search(String statement, String keyword) {
        if (notMatched(statement, keyword))
            return "";
        int index = statement.indexOf(keyword);
        if (index > 0) {
            String ch = "" + statement.charAt(index - 1);
            if (isAlphanumeric(ch) || ch.equals("_"))
                return "";
        }
        if (index + keyword.length() < statement.length()) {
            String ch = "" + statement.charAt(index + keyword.length());
            if (isAlphanumeric(ch) || ch.equals("_"))
                return "";
        }
        return String.format("%s{%s}%s", statement.substring(0, index),
                statement.substring(index, index + keyword.length()),
                index + keyword.length() < statement.length() ?
                        statement.substring(index + keyword.length()) : "");
    }

    private boolean notMatched(String statement, String keyword) {
        return isEmpty(statement) || isEmpty(keyword) || statement.indexOf(keyword) == -1;
    }
}
