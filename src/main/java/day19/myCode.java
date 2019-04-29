package day19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 0 > 1
// 1 > 2
// 2 > 0
// 0 > 1> 2 > 0
// 0 > 1
//0 > 2
//1 >2
// 0>1 >2
class myCode {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeSize = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>(nodeSize);
        for (int i = 0; i < nodeSize; i++) {
            list.add(new LinkedList<>());
        }
        String temp;
        while ((temp = br.readLine()) != null) {
            int[] nums = Arrays.stream(temp.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            list.get(nums[0]).add(nums[1]);
        }

        boolean[] visited = new boolean[nodeSize];
        boolean[] stack = new boolean[nodeSize];
        boolean isCycle = false;
        for (int i = 0; i < nodeSize; i++) {
            if (isCycleCheck(i, visited, stack, list)) {
                isCycle = true;
                break;
            }
        }
        System.out.println(isCycle);


    }


    private static boolean isCycleCheck(int i, boolean[] visited,
                                        boolean[] stack, List<List<Integer>> list) {
        if (stack[i]) {
            return true;
        }
        if (visited[i]) {
            return false;
        }
        visited[i] = true;
        stack[i] = true;
        List<Integer> children = list.get(i);
        for (Integer c : children) {
            if (isCycleCheck(c, visited, stack, list)) {
                return true;
            }
        }
        stack[i] = false;

        return false;
    }
}
