public class Hello {
    public static void main(String[] args)
    {
        String AppName = "Hello";
        String dbFile = "./db/Database.accdb";
        Database db = new Database(dbFile);
        Window window = new Window(AppName, 300, 200);
        System.out.println(AppName);
        window.login(db);
    }
}
