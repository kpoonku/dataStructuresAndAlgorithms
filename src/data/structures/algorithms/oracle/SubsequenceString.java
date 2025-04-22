package data.structures.algorithms.oracle;

public class SubsequenceString {
    public static boolean isSubsequence(String sub, String main) {
        int j = 0;
        for (int i = 0; i < main.length(); i++) {
            if (sub.charAt(j) == main.charAt(i)) {
                j++;
            }
        }
        return j == sub.length();
    }

    public static void main(String[] args) {
        System.out.println("Is Sequence: " + isSubsequence("abc", "ahbgdc"));
    }
}
