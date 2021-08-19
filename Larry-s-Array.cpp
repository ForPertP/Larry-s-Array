string larrysArray(vector<int> A)
{
    int count = 0;
    
    for (int i = 0; i < A.size()-1; ++i)
    {
        for (int j = i+1; j < A.size(); ++j)
        {
            if (A[i] > A[j])
            {
                count++;
            }
        }
    }
    
    return (count % 2 == 0) ? "YES" : "NO";
}

