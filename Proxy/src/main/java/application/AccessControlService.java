package application;

import java.util.HashMap;
import java.util.Map;

public class AccessControlService {
  private static Map<String, Integer> permission = new HashMap<>();

  // unsafe for sure
  public static boolean isAllowed(int id, String username) {
    if (permission == null || permission.get(username) == null) {
      return false;
    }
    return permission.get(username) == id ? true : false;
  }

  public static void addPermission(int id, String name) {
    if (permission == null) {
      permission = new HashMap<>();
    }
    permission.put(name, id);
  }
}
