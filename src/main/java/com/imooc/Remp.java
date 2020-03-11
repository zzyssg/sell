package com.imooc;

public class Remp {
    //    public int findKthLargest(int[] nums, int k){
//        quickSelect(nums, 0, nums.length  - 1, k);
//        return nums[k - 1];
//    }
    public int findKthLargest(int[] nums, int k) {
        quickSelect(nums, 0, nums.length - 1, k);
        return nums[k - 1];
    }

    private void quickSelect(int[] nums, int left, int rihgt, int k) {
        if (left == rihgt) {
            return;
        }
        int pivot = nums[left];
        int i = left;
        int j = rihgt + 1;
        while (true) {
            while (i < rihgt && nums[++i] > pivot) {
            }
            while ( j > left && nums[--j] < pivot ) {

            }
            if (i >= j) {
                break;

            }
            swap(nums, i, j);
        }
        swap(nums, left, j);
        if (k == j + 1) {
            return;
        } else if (k < j + 1) {
            quickSelect(nums, left, j - 1, k);
        } else {
            quickSelect(nums, j, rihgt, k);
        }

    }

    private void  swap(int[] nums, int i, int j) {
        int num = nums[i];
        nums[i] = nums[j];
        nums[j] = num;
    }

    public static void main(String[] args) {
        Remp remp = new Remp();
        remp.findKthLargest(new int[]{2,1,3,6,4,4},3);
    }
//
//    private void quickSelect(int[] nums, int left, int right, int k) {
//        if (left == right)
//            return;
//        
//        int pivot = nums[left];
//        int i = left;
//        int j = right + 1;
//        while (true) {
//            while (nums[++i] > pivot && i < right) { }
//            while (nums[--j] < pivot && j > left) { }
//            if (i >= j)
//                break;
//            
//            swap(nums, i, j);
//        }
//        swap(nums, left, j);
//
//        if (k == j + 1)
//            return;
//        else if (k < j + 1)
//            quickSelect(nums, left, j  - 1, k);
//        else
//            quickSelect(nums, j  + 1, right, k);
//    }
//
//    private void swap(int[] nums, int i, int j){
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }
}
