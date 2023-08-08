package Service;
import DAO.itemDAO;
import DAO.itemDaoFileCollection;
import Model.VD_DTO;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;

public class itemServiceImpl implements itemService {

    itemDAO vdDao = null;

    public itemServiceImpl()throws FileNotFoundException, IOException {

        vdDao = new itemDaoFileCollection();
    }

    @Override
    public boolean readFromFile() throws IOException {
        return vdDao.readFromFile();
    }

    @Override
    public List<VD_DTO> fetchAllitems() {
        return vdDao.fetchAllitems();
    }

    @Override
    public VD_DTO fetchById(int itemId) {
        return vdDao.fetchById(itemId);
    }

    @Override
    public void updateStock(int itemId, int newStock) {
        vdDao.updateStock(itemId, newStock);
    }

    @Override
    public boolean writeToFile() throws IOException {
        return vdDao.writeToFile();
    }
}
