package searcher;

import org.apache.commons.lang3.StringUtils;

public class Searcher {
    public Searcher() {
    }

    String search(String statement, String keyword) {
        if (notMatched(statement, keyword) ||
                hasInvalidLeadingCharacter(statement, keyword) ||
                hasInvalidTrailingCharacter(statement, keyword))
            return "";

        return getHighlightedString(statement, keyword);
    }

    String getHighlightedString(String statement, String keyword) {
        int index = statement.indexOf(keyword);
        return String.format("%s{%s}%s",
                getLeadingString(statement, index),
                keyword,
                getTrailingString(statement, keyword, index));
    }

    boolean hasInvalidTrailingCharacter(String statement, String keyword) {
        int index = statement.indexOf(keyword);
        if (index + keyword.length() < statement.length()) {
            String ch = "" + statement.charAt(index + keyword.length());
            if (StringUtils.isAlphanumeric(ch) || ch.equals("_"))
                return true;
        }
        return false;
    }

    boolean hasInvalidLeadingCharacter(String statement, String keyword) {
        int index = statement.indexOf(keyword);
        if (index > 0) {
            String ch = "" + statement.charAt(index - 1);
            if (StringUtils.isAlphanumeric(ch) || ch.equals("_"))
                return true;
        }
        return false;
    }

    String getTrailingString(String statement, String keyword, int index) {
        return index + keyword.length() < statement.length() ?
                statement.substring(index + keyword.length()) : "";
    }

    String getLeadingString(String statement, int index) {
        return statement.substring(0, index);
    }

    boolean notMatched(String statement, String keyword) {
        return StringUtils.isEmpty(statement) || StringUtils.isEmpty(keyword) || statement.indexOf(keyword) == -1;
    }
}