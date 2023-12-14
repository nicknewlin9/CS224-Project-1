import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SortingAlgos
{
   // MERGE SORT IS A STABLE ALGORITHM, MEANING KEEPS ORDER OF ID WHEN SORTING
    public void bubble_sort(List<Employee> employees)
    {
        for(int i = 0; i < employees.size(); i++)
        {
            for(int j = employees.size()-1; j > i; j--)
            {
                if(employees.get(j).getAge() < employees.get(j-1).getAge())
                {
                    swap(employees, j, j-1);
                }
            }
        }
    }
    public void selection_sort(List<Employee> employees)
    {
        for(int i = 0; i < employees.size(); ++i)
        {
            Employee min = employees.get(i);
            int pos = i;
            for(int j = i +1; j < employees.size(); ++j)
            {
                if(employees.get(j).getAge() < min.getAge())
                {
                    min = employees.get(j);
                    pos = j;
                }
            }
            swap(employees, i,pos);
        }
    }
    private void swap(List<Employee> employees, int i, int j)
    {
        Employee temp = employees.get(i);
        employees.set(i, employees.get(j));
        employees.set(j,temp);
    }
    public void insertion_sort(List<Employee> employees)
    {
        for(int i = 0; i < employees.size(); i++)
        {
            for(int j = i; j > 0; j--)
            {
                if(employees.get(j).getAge() > employees.get(j-1).getAge())
                {
                    break;
                }
                else
                {
                    swap(employees, j, j-1);
                }
            }
        }
    }
    public void quick_sort(List<Employee> employees)
    {
        quick_sort(employees, 0, employees.size()-1);
    }
    private void quick_sort(List<Employee> employees, int low, int high)
    {
        if(low >= high)
        {
            return;
        }
        else
        {
            int position = partition(employees,low,high);
            quick_sort(employees, low, position - 1);
            quick_sort(employees, position + 1, high);
        }
    }
    private int partition(List<Employee> employees, int low, int high)
    {
        Employee p = employees.get(low);
        int j = low + 1;
        for(int i = low + 1; i <= high; i++)
        {
            if(employees.get(i).getAge() < p.getAge())
            {
                swap(employees, i, j++);
            }
        }
        j-=1;
        swap(employees,low,j);
        return j;
    }
    public void merge_sort(List<Employee> employees)
    {
        List<Employee> res = merge_sort(employees,0,employees.size()-1);
        for(int i = 0; i < employees.size(); ++i)
        {
            employees.set(i,res.get(i));
        }
    }
    private List<Employee> merge_sort(List<Employee> employees, int low, int high)
    {
        if (low == high)
        {
            return Arrays.asList(employees.get(low));
        }
        int mid = low + (high - low)/2;
        List<Employee> left = merge_sort(employees, low, mid);
        List<Employee> right = merge_sort(employees, mid+1, high);
        return merge_sort_merge(left,right);
    }
    private List<Employee> merge_sort_merge(List<Employee> left, List<Employee> right)
    {
        List<Employee> res = new ArrayList<>();
        for(int i = 0, j = 0; i < left.size() || j < right.size();)
        {
            if(i < left.size() && j < right.size())
            {
                if(left.get(i).getAge() <= right.get(j).getAge())
                {
                    res.add(left.get(i++));
                }
                else
                {
                    res.add(right.get(j++));
                }
            }
            else if(i < left.size())
            {
                res.add(left.get(i++));
            }
            else
            {
                res.add(right.get(j++));
            }
        }
        return res;
    }
}
