import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestInsertionSort
{
    public static void main(String[] args)
    {
        String[] companies = new String[]{"a","b","c","e"};
        for(String string : companies)
        {
            String inputFilePath = "company_" + string + ".csv";
            List<Employee> employees = new ArrayList<>();
            try
            {
                Scanner scanner = new Scanner(new File(inputFilePath));
                String header = scanner.nextLine();

                while(scanner.hasNext())
                {
                    String[] employeeData = scanner.nextLine().split(",");
                    employees.add(new Employee(Integer.parseInt(employeeData[0]),employeeData[1]
                            ,employeeData[2],Integer.parseInt(employeeData[3])));
                }

                scanner.close();
                SortingAlgos algos = new SortingAlgos();
                long start = System.nanoTime();
                algos.insertion_sort(employees);
                long end = System.nanoTime();
                System.out.println("CPU runtime: " + (double) (end-start) / 1_000_000 + " ms.");

                String filename = "company_" + string + "_insertion.csv";
                String outputDir = "./outputs/";
                File dir = new File(outputDir);
                if (!dir.exists())
                {
                    dir.mkdirs();
                }
                String outputPath = outputDir + "company_" + string;
                writeToFile(outputPath,filename,employees);
            }
            catch(FileNotFoundException FNFException)
            {
                System.out.println(FNFException);
            }
        }
    }
    public static void writeToFile(String outputPath, String filename, List<Employee> employees)
    {
        File dir = new File(outputPath);
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        try(FileWriter writer = new FileWriter(outputPath + "/" + filename))
        {
            String title = "id, first_name, last_name, age\n";
            writer.write(title);
            for(Employee employee : employees)
            {
                String line = employee.getId() + "," + employee.getFirst_Name()
                        + "," + employee.getLast_Name() + "," + employee.getAge() + "\n";
                writer.write(line);
            }
        }
        catch(IOException IOE)
        {
            System.out.println(IOE);
        }
    }
}
