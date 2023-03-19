package org.example.thirdLess.orgStructureParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OrgStructureParserImpl implements OrgStructureParser {
    private final List<Employee> employers = new ArrayList<>();
    @Override
    public Employee parseStructure(File csvFile) {

        Employee boss = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), StandardCharsets.UTF_8));
            reader.readLine();
            String line;
            Employee employee;

            while ((line = reader.readLine()) != null) {
                String[] info = line.split(";");
                employee = new Employee();
                employee.setId(Long.valueOf(info[0]));
                if (!info[1].equals("")) {
                    employee.setBossId(Long.parseLong(info[1]));
                }
                else {
                boss = employee;}
                employee.setName(info[2]);
                employee.setPosition(info[3]);
                employers.add(employee);
               for (Employee e : employers) {
                    if (employee.getBossId() != null && employee.getBossId().equals(e.getId())) {
                        employee.setBoss(e);
                        e.getSubordinate().add(employee);
                    }
                }
            }
            reader.close();
            return boss;
        } catch (FileNotFoundException fnfe) {
            System.out.println("Не найден файл");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return boss;
    }
}
