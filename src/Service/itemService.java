package Service;
import Model.VD_DTO;
import java.util.List;
import java.io.IOException;
public interface itemService {
    boolean readFromFile() throws IOException;
    List <VD_DTO> fetchAllitems();
    VD_DTO fetchById(int itemId);
    void updateStock (int itemId, int newStock);
    boolean writeToFile() throws IOException;

}
