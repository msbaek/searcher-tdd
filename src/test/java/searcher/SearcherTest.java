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
        if (hasInvalidLeadingCharacter(statement, keyword))
            return "";
        if (hasInvalidTrailingCharacter(statement, keyword))
            return "";
        int index2 = statement.indexOf(keyword);
        return String.format("%s{%s}%s",
                getLeadingString(statement, index2),
                keyword,
                getTrailingString(statement, keyword, index2));
    }

    private boolean hasInvalidTrailingCharacter(String statement, String keyword) {
        int index1 = statement.indexOf(keyword);
        if (index1 + keyword.length() < statement.length()) {
            String ch = "" + statement.charAt(index1 + keyword.length());
            if (isAlphanumeric(ch) || ch.equals("_"))
                return true;
        }
        return false;
    }

    private boolean hasInvalidLeadingCharacter(String statement, String keyword) {
        int index = statement.indexOf(keyword);
        if (index > 0) {
            String ch = "" + statement.charAt(index - 1);
            if (isAlphanumeric(ch) || ch.equals("_"))
                return true;
        }
        return false;
    }

    private String getTrailingString(String statement, String keyword, int index) {
        return index + keyword.length() < statement.length() ?
                statement.substring(index + keyword.length()) : "";
    }

    private String getLeadingString(String statement, int index) {
        return statement.substring(0, index);
    }

    private boolean notMatched(String statement, String keyword) {
        return isEmpty(statement) || isEmpty(keyword) || statement.indexOf(keyword) == -1;
    }
}
