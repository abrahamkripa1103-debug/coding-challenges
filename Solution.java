class Solution {
    public String solution(String S) {
        // Since S only contains "plus" and "minus",
        // safe to replace directly.
        return S.replace("plus", "+").replace("minus", "-");
    }
}
