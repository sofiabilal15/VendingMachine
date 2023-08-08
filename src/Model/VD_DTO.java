package Model;

public class VD_DTO {

    private int itemId;
	private String itemName;
	private double itemCost;
	private int itemStock;

    public VD_DTO(int itemId, String itemName, double itemCost, int itemStock) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemStock = itemStock;

    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    public VD_DTO copyitemsData() {
        // incoming VD_DTO object will be copied to the returning VD_DTO  object
        VD_DTO returningVD_DTO = new VD_DTO(this.itemId,this.itemName,this.itemCost,this.itemStock);
        return returningVD_DTO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return "itemID:" + itemId + "\n" +
                "itemName:" + itemName + "\n" +
                "itemCost:" + itemCost + "\n" +
                "itemStock:" + itemStock;
       // return sb.toString();
//        sb.append("item ID: ").append(itemId).append("\n");
//        sb.append("item Name: ").append(itemName).append("\n");
//        sb.append("item Cost: ").append(itemCost).append("\n");
//        sb.append("item Stock: ").append(itemStock).append("\n");

    }
}
