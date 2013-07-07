package searcher;

import org.apache.commons.lang3.StringUtils;

public class Searcher {
    private String statement;

    public Searcher(String statement) {
        this.statement = statement;
    }

    public String search(String keyword) {
        if (notMatched(keyword) ||
                hasInvalidLeadingCharacter(keyword) ||
                hasInvalidTrailingCharacter(keyword))
            return "";

        return getHighlightedString(keyword);
    }

    private String getHighlightedString(String keyword) {
        int index = statement.indexOf(keyword);
        return String.format("%s{%s}%s",
                getLeadingString(index),
                keyword,
                getTrailingString(keyword, index));
    }

    private boolean hasInvalidTrailingCharacter(String keyword) {
        int index = statement.indexOf(keyword);
        if (index + keyword.length() < statement.length()) {
            String ch = "" + statement.charAt(index + keyword.length());
            if (StringUtils.isAlphanumeric(ch) || ch.equals("_"))
                return true;
        }
        return false;
    }

    private boolean hasInvalidLeadingCharacter(String keyword) {
        int index = statement.indexOf(keyword);
        if (index > 0) {
            String ch = "" + statement.charAt(index - 1);
            if (StringUtils.isAlphanumeric(ch) || ch.equals("_"))
                return true;
        }
        return false;
    }

    private String getTrailingString(String keyword, int index) {
        return index + keyword.length() < statement.length() ?
                statement.substring(index + keyword.length()) : "";
    }

    private String getLeadingString(int index) {
        return statement.substring(0, index);
    }

    private boolean notMatched(String keyword) {
        return StringUtils.isEmpty(statement) || StringUtils.isEmpty(keyword) || statement.indexOf(keyword) == -1;
    }
}