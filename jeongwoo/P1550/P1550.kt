class Solution {
    fun threeConsecutiveOdds(arr: IntArray): Boolean {
        if(arr.size < 3) return false
        
        for(i in 0 until (arr.size - 2)) {
            if(arr[i] % 2 == 1
                && arr[i + 1] % 2 == 1
                && arr[i + 2] % 2 == 1) {
                return true
            }
        }

        return false
    }
}
