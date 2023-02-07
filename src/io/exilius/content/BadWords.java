package io.exilius.content;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.openhft.hashing.LongHashFunction;

/**
 * Originally created by Pim De Witte.
 * <p>
 * Performance drastically improved by over an order of magnitude by Thomas G. P. Nappo (Jire).
 * Garbage production has been eliminated as well.
 */
public final class BadWords {

    private static final String[] emptyComboWords = new String[]{};
    private static Long2ObjectMap<String[]> words = new Long2ObjectOpenHashMap<>();
    private static int largestWordLength = 0;

    public static void flag(String word, String... ignoreComboWords) {
        if (word.length() > largestWordLength) {
            largestWordLength = word.length();
        }
        words.put(LongHashFunction.xx().hashChars(word), ignoreComboWords);
    }

    public static void loadBadWords() {
        for (String blacklisted : blacklist) {
            flag(blacklisted, emptyComboWords);
        }
        flag("scape",
                "runescape",
                "landscape",
                "machinescape",
                "fashionscape",
                "07scape",
                "2007scape",
                "osrscape",
                "osrsscape",
                "moparscape",
                "didyscape",
                "scapeing"
        );
    }

    private static final String[] blacklist = {
            /* Bad words */
            "nigger",
            "faggot",
            "fag",
            "bitch",
            "cunt",
            "whore",
            "asshole",
            "sluts",
            "slut",

            /* Advertisement basics */
            "www.",
            "wwwdot",
            ".com",
            ".org",
            ".net",
            ".io",
            ".ps",
            ".tk",
            "dotcom",
            "dotorg",
            "dotnet",
            "dottk",

            /* Individual server names */
            "kronos",
            "atlas",
            "osscape",
            "alora",
            "elkoy",
            "osrune",
            "guthixp",
            "dawntained",
            "locopk",
            "imagineps",
            "nearreal",
            "pkhonor",
            "dreamsc",
            "manicps",
            "imagineps",
            "draganoth",
            "alosps",
            "rsps2",
            "lostisle",
            "necrotic",
            "redrune",
            "deathwish",
            "pkowned",
            "osbase",
            "beastpk",
            "roatpk",
            "rsgenesis",
            "trinityps",
            "boxrune",
            "runique",
            "furiousp",
            "novus",
            "ikov",
            "joinmy",
            "atarax",
            "nardahp",
            "illerai",
            "letspk",
            "ratedpixel",
            "cloudnine",
            "viceos",
            "deprivedr",
            "exoria",
            "simplicityp",
            "cruxp",
            "ospkz",
            "scapewar",
            "amberp",
            "diviner",
            "osunity",
            "amulius",
            "zenyteps",
            "zenyteosrs",
            "xeros"
    };

    private static final char[][] leetspeakToNormal = {
            {'1', 'i'},
            {'!', 'i'},
            {'3', 'e'},
            {'4', 'a'},
            {'@', 'a'},
            {'5', 's'},
            {'7', 't'},
            {'0', 'o'},
            {'9', 'g'},

            /* Additional leetspeak support that Jire added. */
            {'6', 'g'},
            {'$', 's'},
            {'&', 'a'},
            {'(', 'c'},
            {')', 'd'},
            {'+', 't'}
    };

    private static final ThreadLocal<StringBuilder> sb = ThreadLocal.withInitial(StringBuilder::new); // make this regular if you don't need thread safety.

    /**
     * Iterates over a String input and checks whether a cuss word was found in a list, then checks if the word should be ignored (e.g. bass contains the word *ss).
     */
    public static boolean containsBadWord(String input) {
        if (input == null) {
            return false;
        }

        StringBuilder sb = BadWords.sb.get();
        sb.setLength(0);

        removeLeetspeak:
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                for (char[] conversion : leetspeakToNormal) {
                    if (c == conversion[0]) {
                        sb.append(conversion[1]);
                        continue removeLeetspeak;
                    }
                }
            }
        }

        // iterate over each letter in the word
        for (int start = 0; start < sb.length(); start++) {
            // from each letter, keep going to find bad words until either the end of the sentence is reached, or the max word length is reached.
            for (int offset = 1; offset < (sb.length() + 1 - start) && offset < largestWordLength; offset++) {
                long hash = LongHashFunction.xx().hashChars(sb, start, offset);
                if (words.containsKey(hash)) {
                    // for example, if you want to say the word bass, that should be possible.
                    String[] ignoreCheck = words.get(hash);
                    boolean ignore = false;
                    for (int s = 0; s < ignoreCheck.length; s++) {
                        if (indexOf(sb, ignoreCheck[s]) >= 0) {
                            ignore = true;
                            break;
                        }
                    }
                    if (!ignore) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static int indexOf(CharSequence source, CharSequence target) {
        int sourceCount = source.length();
        int targetCount = target.length();
        int sourceOffset = 0;
        int targetOffset = 0;

        if (0 >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (targetCount == 0) {
            return 0;
        }

        char first = target.charAt(targetOffset);
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset; i <= max; i++) {
            /* Look for first character. */
            if (source.charAt(i) != first) {
                while (++i <= max && source.charAt(i) != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source.charAt(j)
                        == target.charAt(k); j++, k++)
                    ;

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }

}
