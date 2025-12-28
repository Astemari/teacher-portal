package methods;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface Validate {

    public static Boolean admins(String passkey) {
        return "admin".equals(passkey);
    }

    public static Boolean teachers(String name, String passkey) {
        String[] user = UserPreferences.getUserByName(name);
        if (user != null && user.length >= 3) {
            return user[2].equals(passkey);
        }
        return false;
    }
    
    public static Boolean teacherExist(String name) {
        return UserPreferences.userExists(name);
    }
    
    public static Boolean isSectionValiad(String sec) {
        return !UserPreferences.sectionExists(sec);
    }
    
    public static Boolean isNameValid(String name) {
        return !UserPreferences.userExists(name);
    }
    
    public static Boolean isValidEmail(String email)
    {
          String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
          Pattern pattern = Pattern.compile(EMAIL_PATTERN);
          Matcher matcher = pattern.matcher(email);
          
          if(matcher.matches())
          {
              return true;
          }
          else
          {
              return false;
          }
    }
    
}
