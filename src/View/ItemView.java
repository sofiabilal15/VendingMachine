package View;
import Model.VD_DTO;
import Service.itemService;
import Service.itemServiceImpl;
import Utility.Change;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ItemView {
    public void runMenu() throws IOException {

        itemService vdService = new itemServiceImpl();
        try {
            vdService.readFromFile(); // Read data from file and populate the data store
        } catch (FileNotFoundException fe) {
            System.out.println("There was some issue with the file. Please try again!");
        } catch (IOException e1) {
            System.out.println("There was some issue with the input. Please try again!");
        }

        Scanner scan = new Scanner(System.in);
        char con = 'n';
        do {
            System.out.println("********************************************");
            System.out.println("Welcome to Sofia's Ice-Cream Vending Machine");
            System.out.println("********************************************");

            List<VD_DTO> itemsList = vdService.fetchAllitems();
            for (VD_DTO item : itemsList) {
                System.out.println("Item ID: " + item.getItemId());
                System.out.println("Item Name: " + item.getItemName());
                System.out.println("Item Cost: " + item.getItemCost());
                System.out.println("Item Stock: " + item.getItemStock());
                System.out.println("----------------------");
            }

            System.out.println("1. Vend an item");
            System.out.println("2. Write to a file");
            System.out.println("3. Exit");
            System.out.println("********************************************");
            System.out.println("Enter your option:");
            int option = scan.nextInt();
            System.out.println("********************************************");
            switch (option) {
                case 1:
                    System.out.println("Enter the item Id you want to vend");
                    int vendItemId = scan.nextInt();

                    VD_DTO vendItem = vdService.fetchById(vendItemId);

                    if (vendItem != null && vendItem.getItemStock() > 0) {
                        System.out.println("You have vended the item: " +  vendItem.getItemName());
                        System.out.println("The item costs: "+ vendItem.getItemCost());

                        System.out.println("Enter the amount: ");
                        double Amount = scan.nextDouble();

                        if (Amount >= vendItem.getItemCost()) {
                            double dueAmount = Amount - vendItem.getItemCost();
                            System.out.println("Due Amount: " + dueAmount);

                            BigDecimal changeInPennies = BigDecimal.valueOf(dueAmount * 100); // Convert to pennies
                            Change calculateChange = new Change(changeInPennies);

                            int quarters = calculateChange.getQuarters();
                            int dimes = calculateChange.getDimes();
                            int nickels = calculateChange.getNickels();
                            int pennies = calculateChange.getPennies();

                            System.out.println("Change: ");
                            System.out.println("Quarters: " + quarters);
                            System.out.println("Dimes: " + dimes);
                            System.out.println("Nickels: " + nickels);
                            System.out.println("Pennies: " + pennies);

                            vdService.updateStock(vendItem.getItemId(), vendItem.getItemStock() -1);
                        } else {
                            System.out.println("Insufficient amount. Please insert more money.");
                        }
                    } else {
                        System.out.println("Item not available or out of stock.");
                    }
                    break;

                case 2:
                    System.out.println("Written to a file...");
                    try {
                        vdService.writeToFile();
                    } catch (IOException e) {
                        System.out.println("There was some issue with the input. Please try again!");
                    }
                    break;
                case 3:
                    System.out.println("************************");
                    System.out.println("Thank you for using the vending machine!");
                    System.out.println("************************");
                    System.exit(0);
                    break;

            }

            System.out.println("Do you want to continue?(y/n)");
            con = scan.next().charAt(0);

        } while (con == 'y');
    }
}