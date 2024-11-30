import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'larrysArray' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER_ARRAY A as parameter.
     */

    private static int mergeCount(List<Integer> A, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<Integer> leftArr = new ArrayList<>(A.subList(left, mid + 1));
        List<Integer> rightArr = new ArrayList<>(A.subList(mid + 1, right + 1));
        
        int inversionCount = 0;
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr.get(i) <= rightArr.get(j)) {
                A.set(k++, leftArr.get(i++));
            } else {
                A.set(k++, rightArr.get(j++));
                inversionCount += (n1 - i);
            }
        }
        
        while (i < n1) {
            A.set(k++, leftArr.get(i++));
        }
        
        while (j < n2) {
            A.set(k++, rightArr.get(j++));
        }

        return inversionCount;
    }

    private static int mergeSortAndCount(List<Integer> A, int left, int right) {
        int inversionCount = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            inversionCount += mergeSortAndCount(A, left, mid);
            inversionCount += mergeSortAndCount(A, mid + 1, right);
            inversionCount += mergeCount(A, left, mid, right);
        }

        return inversionCount;
    }

    public static String larrysArray(List<Integer> A) {
        int inversionCount = mergeSortAndCount(A, 0, A.size() - 1);
        return (inversionCount % 2 == 0) ? "YES" : "NO";
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                String result = Result.larrysArray(A);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
