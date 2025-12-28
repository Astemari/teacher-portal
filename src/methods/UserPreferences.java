package methods;

import java.util.prefs.Preferences;
import java.util.ArrayList;
import java.util.List;

public class UserPreferences {
    private static final String NODE_NAME = "com/teacherportal/users";
    private static final String USER_COUNT_KEY = "userCount";
    private static final String USER_PREFIX = "user_";
    private static final String ID_SUFFIX = "_id";
    private static final String NAME_SUFFIX = "_name";
    private static final String PASS_SUFFIX = "_pass";
    private static final String EMAIL_SUFFIX = "_email";
    private static final String SEC_SUFFIX = "_sec";
    
    private static Preferences getPrefs() {
        return Preferences.userRoot().node(NODE_NAME);
    }
    
    public static void saveUser(int id, String name, String password, String email, String sec) {
        Preferences prefs = getPrefs();
        String userKey = USER_PREFIX + id;
        
        prefs.putInt(userKey + ID_SUFFIX, id);
        prefs.put(userKey + NAME_SUFFIX, name);
        prefs.put(userKey + PASS_SUFFIX, password);
        prefs.put(userKey + EMAIL_SUFFIX, email);
        prefs.put(userKey + SEC_SUFFIX, sec);
        
        // Update user count if this is a new user
        int userCount = prefs.getInt(USER_COUNT_KEY, 0);
        if (id > userCount) {
            prefs.putInt(USER_COUNT_KEY, id);
        }
    }
    
    public static String[] getUserByName(String name) {
        Preferences prefs = getPrefs();
        int userCount = prefs.getInt(USER_COUNT_KEY, 0);
        
        for (int i = 1; i <= userCount; i++) {
            String userKey = USER_PREFIX + i;
            String storedName = prefs.get(userKey + NAME_SUFFIX, null);
            if (storedName != null && storedName.equals(name)) {
                return new String[]{
                    String.valueOf(prefs.getInt(userKey + ID_SUFFIX, 0)),
                    storedName,
                    prefs.get(userKey + PASS_SUFFIX, ""),
                    prefs.get(userKey + EMAIL_SUFFIX, ""),
                    prefs.get(userKey + SEC_SUFFIX, "")
                };
            }
        }
        return null;
    }
    
    public static List<String[]> getAllUsers() {
        List<String[]> users = new ArrayList<>();
        Preferences prefs = getPrefs();
        int userCount = prefs.getInt(USER_COUNT_KEY, 0);
        
        for (int i = 1; i <= userCount; i++) {
            String userKey = USER_PREFIX + i;
            if (prefs.get(userKey + NAME_SUFFIX, null) != null) {
                users.add(new String[]{
                    String.valueOf(prefs.getInt(userKey + ID_SUFFIX, 0)),
                    prefs.get(userKey + NAME_SUFFIX, ""),
                    prefs.get(userKey + PASS_SUFFIX, ""),
                    prefs.get(userKey + EMAIL_SUFFIX, ""),
                    prefs.get(userKey + SEC_SUFFIX, "")
                });
            }
        }
        return users;
    }
    
    public static boolean updateUser(int id, String name, String password, String email, String sec) {
        Preferences prefs = getPrefs();
        String userKey = USER_PREFIX + id;
        
        if (prefs.get(userKey + NAME_SUFFIX, null) == null) {
            return false; // User doesn't exist
        }
        
        saveUser(id, name, password, email, sec);
        return true;
    }
    
    public static boolean userExists(String name) {
        return getUserByName(name) != null;
    }
    
    public static boolean sectionExists(String sec) {
        for (String[] user : getAllUsers()) {
            if (user[4].equals(sec)) {
                return true;
            }
        }
        return false;
    }
    
    public static void migrateFromCsv() {
        // This method will be called once to migrate data from CSV to preferences
        try {
            java.io.File csvFile = new java.io.File("Admin.csv");
            if (!csvFile.exists()) {
                return; // No CSV file to migrate
            }
            
            java.util.Scanner scanner = new java.util.Scanner(csvFile);
            // Skip header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    String pass = fields[2];
                    String email = fields[3];
                    String sec = fields[4];
                    saveUser(id, name, pass, email, sec);
                }
            }
            scanner.close();
            
            // Optionally, you can backup the old CSV file instead of deleting it
            // Files.move(csvFile.toPath(), new File("Admin.csv.bak").toPath(), 
            //     StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
