using System.CodeDom.Compiler;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Diagnostics.CodeAnalysis;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Runtime.Serialization;
using System.Text.RegularExpressions;
using System.Text;
using System;

class Result
{

    /*
     * Complete the 'larrysArray' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER_ARRAY A as parameter.
     */

    private static int mergeCount(List<int> A, int left, int mid, int right)
    {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        List<int> leftArr = A.GetRange(left, mid - left + 1);
        List<int> rightArr = A.GetRange(mid + 1, right - mid);
                
        int inversionCount = 0;
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2)
        {
            if (leftArr[i] <= rightArr[j])
            {
                A[k++] = leftArr[i++];
            }
            else
            {
                A[k++] = rightArr[j++];
                inversionCount += (n1 - i);
            }
        }
        
        while (i < n1)
        {
            A[k++] = leftArr[i++];
        }
        
        while (j < n2)
        {
            A[k++] = rightArr[j++];
        }        

        return inversionCount;
    }
    
    private static int mergeSortAndCount(List<int> A, int left, int right)
    {
        int inversionCount = 0;
        
        if (left < right)
        {
            int mid = left + (right - left) / 2;
            
            inversionCount += mergeSortAndCount(A, left, mid);
            inversionCount += mergeSortAndCount(A, mid + 1, right);
            inversionCount += mergeCount(A, left, mid, right);
        }
        
        return inversionCount;
    }
    
    public static string larrysArray(List<int> A)
    {
        int inversionCount = mergeSortAndCount(A, 0, A.Count - 1);
        return (inversionCount % 2 == 0) ? "YES" : "NO";
    }
}


class Solution
{
    public static void Main(string[] args)
    {
        TextWriter textWriter = new StreamWriter(@System.Environment.GetEnvironmentVariable("OUTPUT_PATH"), true);

        int t = Convert.ToInt32(Console.ReadLine().Trim());

        for (int tItr = 0; tItr < t; tItr++)
        {
            int n = Convert.ToInt32(Console.ReadLine().Trim());

            List<int> A = Console.ReadLine().TrimEnd().Split(' ').ToList().Select(ATemp => Convert.ToInt32(ATemp)).ToList();

            string result = Result.larrysArray(A);

            textWriter.WriteLine(result);
        }

        textWriter.Flush();
        textWriter.Close();
    }
}
