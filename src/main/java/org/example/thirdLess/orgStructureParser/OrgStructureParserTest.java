package org.example.thirdLess.orgStructureParser;

import java.io.File;
import java.io.IOException;

public class OrgStructureParserTest {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/file.txt");
        OrgStructureParser parser = new OrgStructureParserImpl();
        Employee employee = parser.parseStructure(file);
        System.out.println(employee.getName() + " " + employee.getPosition() + " Подчиненные:" + employee.getSubordinate());
    }
}
