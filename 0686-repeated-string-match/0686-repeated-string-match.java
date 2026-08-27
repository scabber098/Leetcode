class Solution {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder temp = new StringBuilder(a);
        int count = 1;

        while (temp.length() < b.length()) {
            temp.append(a);
            count++;
        }

        if (temp.toString().contains(b)) {
            return count;
        }

        temp.append(a);

        if (temp.toString().contains(b)) {
            return count + 1;
        }

        return -1;
    }
}