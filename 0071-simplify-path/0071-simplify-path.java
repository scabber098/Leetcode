class Solution {
    public String simplifyPath(String path) {
        String[] parts = path.split("/");
        java.util.Stack<String> stack = new java.util.Stack<>();

        for (String part : parts) {
            if (part.equals("") || part.equals(".")) {
                continue;
            }

            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }

        return "/" + String.join("/", stack);
    }
}