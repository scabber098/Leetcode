class StreamChecker {

    class Node {
        Node[] next = new Node[26];
        boolean end;
    }

    Node root = new Node();
    StringBuilder stream = new StringBuilder();

    public StreamChecker(String[] words) {

        for (String word : words) {
            Node curr = root;

            for (int i = word.length() - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';

                if (curr.next[index] == null) {
                    curr.next[index] = new Node();
                }

                curr = curr.next[index];
            }

            curr.end = true;
        }
    }

    public boolean query(char letter) {

        stream.append(letter);

        Node curr = root;
        for (int i = stream.length() - 1; i >= 0; i--) {

            int index = stream.charAt(i) - 'a';

            if (curr.next[index] == null) {
                return false;
            }

            curr = curr.next[index];

            if (curr.end) {
                return true;
            }
        }

        return false;
    }
}