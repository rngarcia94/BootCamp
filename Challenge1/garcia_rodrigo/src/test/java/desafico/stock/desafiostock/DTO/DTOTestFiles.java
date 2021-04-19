package desafico.stock.desafiostock.DTO;

import java.io.File;

public class DTOTestFiles {

    public static File createFile(String fileName) {
        return new File("src/main/resources/" + fileName);
    }

}
