package methods;

import java.io.File;
import java.util.List;

public class StorageUpgrader {
    
    /**
     * Checks if an upgrade from CSV to preferences storage is needed
     * @return true if upgrade is needed, false otherwise
     */
    public static boolean isUpgradeNeeded() {
        File csvFile = new File("Admin.csv");
        if (!csvFile.exists()) {
            return false; // No CSV file, nothing to upgrade
        }
        
        // Check if we have any users in preferences
        return UserPreferences.getAllUsers().isEmpty();
    }
    
    /**
     * Performs the upgrade from CSV to preferences storage
     * @return true if upgrade was successful, false otherwise
     */
    public static boolean performUpgrade() {
        try {
            // This will migrate data from CSV to preferences
            UserPreferences.migrateFromCsv();
            
            // Verify the migration was successful
            List<String[]> users = UserPreferences.getAllUsers();
            if (users.isEmpty()) {
                System.err.println("Warning: No users were migrated during upgrade");
                return false;
            }
            
            System.out.println("Successfully migrated " + users.size() + " users to secure storage");
            
            // Optionally, you can backup the old CSV file here
            // File oldFile = new File("Admin.csv");
            // File backupFile = new File("Admin.csv.bak");
            // Files.move(oldFile.toPath(), backupFile.toPath(), 
            //     java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            
            return true;
        } catch (Exception e) {
            System.err.println("Error during storage upgrade: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
