package DAO;
import java.io.File;
import Model.VD_DTO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class itemDaoFileCollection implements itemDAO {

    List<VD_DTO> itemsFileDataStore = new ArrayList<VD_DTO>();
    @Override
    public boolean readFromFile() throws IOException {
        try{
            // 1.
            File myFile = new File("C:\\Wiley Edge Java\\Vending_Machine\\src\\itemDB.txt");

            // 2.
            FileReader fr = new FileReader(myFile);
            // 3.
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while ((line = br.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(line, ":");

                st.nextToken();

                String tId = st.nextToken().trim();
                int itemId = Integer.parseInt(tId);

                line = br.readLine();
                st = new StringTokenizer(line, ":");
                st.nextToken();
                String itemName = st.nextToken().trim();


                line = br.readLine();
                st = new StringTokenizer(line, ":");
                st.nextToken();
                String Icost = st.nextToken().trim();
                double itemCost = Double.parseDouble(Icost);


                line = br.readLine();
                st = new StringTokenizer(line, ":");
                st.nextToken();
                String Istock = st.nextToken().trim();
                int itemStock = Integer.parseInt(Istock);


                VD_DTO item = new VD_DTO(itemId, itemName, itemCost, itemStock);
                itemsFileDataStore.add(item);
            }
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
        System.out.println(itemsFileDataStore.size());
        return true;
    }

    @Override
    public List<VD_DTO> fetchAllitems() {
        List<VD_DTO> returnitemsFileDataStore = new ArrayList<VD_DTO>(itemsFileDataStore);
        return returnitemsFileDataStore;
    }

    @Override
    public VD_DTO fetchById(int itemId) {
        VD_DTO returnVD_DTO = null;
        for(int i=0;i<itemsFileDataStore.size();i++) {
            if(itemsFileDataStore.get(i).getItemId() == itemId) {
                returnVD_DTO = itemsFileDataStore.get(i).copyitemsData();
            }
        }
        return returnVD_DTO;
    }

    @Override
    public void updateStock(int itemId, int newStock) {
        for (VD_DTO item : itemsFileDataStore) {
            if (item.getItemId() == itemId) {
                // Update the itemStock with the new value
                item.setItemStock(newStock);

                // Reduce the itemStock by 1 if the item is vended
                if (newStock == 0) {
                    item.setItemStock(item.getItemStock() - 1);
                }
                break;
            }
        }

    }

    @Override
    public boolean writeToFile() throws IOException {

        try {

            System.out.println("In Writing to file =====");
            File myFile = new File("C:\\Wiley Edge Java\\Vending_Machine\\src\\itemDB.txt");
            FileWriter fw = new FileWriter(myFile);
            System.out.println(itemsFileDataStore);
            for (VD_DTO eachItem : itemsFileDataStore) {
                fw.write((eachItem.toString() + "\n").toCharArray());
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return true;
    }
}

