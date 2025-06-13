package data.structures.algorithms;

import java.util.Arrays;

/*
Median of two Sorted Arrays of Different Sizes
Last Updated : 15 Dec, 2024
Given two sorted arrays, a[] and b[], the task is to find the median of these sorted arrays.
Assume that the two sorted arrays are merged and then median is selected from the
combined array.

This is an extension of Median of two sorted arrays of
equal size problem. Here we handle arrays of unequal size also.

Examples:

Input: a[] = [-5, 3, 6, 12, 15], b[] = [-12, -10, -6, -3, 4, 10]
Output: 3
Explanation: The merged array is
[-12, -10, -6, -5 , -3, 3, 4, 6, 10, 12, 15].
So the median of the merged array is 3.


Input: a[] = [1, 12, 15, 26, 38], b[] = [2, 13, 17, 30, 45, 60]
Output: The median is 11.
Explanation : The merged array is [1, 2, 12, 13, 15, 17, 26, 30, 38, 45, 60]. So the median of the merged array is 17.


Input: a[] = [], b[] = [2, 4, 5, 6]
Output: The median is 4.5
Explanation: The merged array is [2, 4, 5, 6]. The total number of elements are even, so there are two middle elements. Take the average between the two: (4 + 5) / 2 = 4.5
 */
public class MediunOfSortedArrays {

    public double findTheMedian(int[] num1, int[] num2) {
        int num1Length = num1.length;
        int num2Length = num2.length;
        int medianIndex = (num1Length + num2Length) / 2;
        int[] resultArray = new int[num1Length + num2Length];
        System.arraycopy(num1, 0, resultArray, 0, num1Length);
        System.arraycopy(num2, 0, resultArray, num1Length, num2Length);
        Arrays.sort(resultArray);
        System.out.println(Arrays.toString(resultArray));
        System.out.println(medianIndex);
        System.out.println(resultArray[medianIndex]);
        return medianIndex;
    }

    public double findMedianInSortedArray(int[] largeArray, int[] smallArray) {
        int lLength = largeArray.length;
        int sLength = smallArray.length;

        if (sLength > lLength) {
            return findMedianInSortedArray(smallArray, largeArray);
        }
        System.out.println("largeArray : "+ Arrays.toString(largeArray));
        System.out.println("smallArray : "+ Arrays.toString(smallArray));
        int[] resultArray = new int[lLength + sLength];
        int medianIndex = (lLength + sLength) / 2 + 1;
        System.out.println("medianIndex : " + medianIndex);
        int lIndex = 0, sIndex = 0, index = 0;
        while (lIndex < lLength && sIndex < sLength && index != medianIndex) {
            if (largeArray[lIndex] < smallArray[sIndex]) {
                resultArray[index] = largeArray[lIndex++];
            } else {
                resultArray[index] = smallArray[sIndex++];
            }
            index++;
        }
        while(sIndex < sLength && index != medianIndex) {
            resultArray[index++] = smallArray[sIndex++];
        }
        while(lIndex < lLength && index != medianIndex) {
            resultArray[index++] = largeArray[lIndex++];
        }
        System.out.println(Arrays.toString(resultArray));
        System.out.println(resultArray[medianIndex-1]);
        return 0.0;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums1.length < nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        int i = 0, j = 0, k = 0;
        int medianIndex = (nums1.length-nums2.length)/2 + nums2.length + 1;
        int arr[] = new int[medianIndex];

        System.out.println("medianIndex 1 : " + medianIndex);

        while(i < nums1.length && j < nums2.length && k != medianIndex){
            if(nums1[i] < nums2[j]){
                arr[k] = nums1[i++];
            }else{
                arr[k] = nums2[j++];
            }
            k++;
        }

        while(i < nums1.length && k != medianIndex){
            arr[k++] = nums1[i++];
        }

        while(j < nums2.length && k != medianIndex){
            arr[k++] = nums2[j++];
        }
       double result =  (nums1.length + nums2.length) % 2 != 0 ?
                arr[(nums1.length + nums2.length)/2] :
                (double)(arr[((nums1.length + nums2.length)-1)/2]
                        +
                        arr[((nums1.length + nums2.length)-1)/2 + 1])/2 ;
        System.out.println("result 1 : " + result);
        return result;
    }

    public static void main(String[] args) {
        int[] num1 = {-5, 3, 6, 12, 15};
        int[] num2 = {-12, -10, -6, -3, 4, 10};
        int[] num3 = {1, 12, 15, 26, 38};
        int[] num4 = {2, 13, 17, 30, 45, 60};
        int[] num5 = {};
        int[] num6 = {2, 4, 5, 6};
        new MediunOfSortedArrays().findTheMedian(num1, num2);
        new MediunOfSortedArrays().findMedianInSortedArray(num1, num2);
        new MediunOfSortedArrays().findMedianSortedArrays(num1, num2);
        new MediunOfSortedArrays().findMedianInSortedArray(num3, num4);
        new MediunOfSortedArrays().findMedianSortedArrays(num3, num4);
        new MediunOfSortedArrays().findMedianInSortedArray(num5, num6);
        new MediunOfSortedArrays().findMedianSortedArrays(num5, num6);
    }
}
