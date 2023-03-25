package org.example.thirdLess.orgStructureParser;

import java.io.File;
import java.io.IOException;

public class OrgStructureParserTest {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/file.csv");
        OrgStructureParser parser = new OrgStructureParserImpl();
        Employee boss = parser.parseStructure(file);
        System.out.println(boss.getName() + " " + boss.getPosition() + " Подчиненные:" + boss.getSubordinate());
        System.out.println(boss.getBoss());
    }
}
