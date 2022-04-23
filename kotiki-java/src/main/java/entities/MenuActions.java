package entities;

public class MenuActions {

    public static void showMenu() {
        System.out.println(
                        " -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "     Welcome to the SuperCatMenu V1.0!\n" +
                        " ----------------------------------------\n" +
                        "Add options:\n" +
                        "   1. Add a new cat\n" +
                        "   2. Make friends with the cats\n" +
                        "   3. Add a new owner\n" +
                        "   4. Assign the cat to the owner\n" +
                        "Edit names:\n" +
                        "   5. Edit cat's name\n" +
                        "   6. Edit owner's name\n" +
                        "Remove options:\n" +
                        "   7. Remove a cat\n" +
                        "   8. Finish the cat friendship\n" +
                        "   9. Remove an owner\n" +
                        "   10. Untie the cat from the owner\n" +
                        "Show options:\n" +
                        "   11. Show all cats\n" +
                        "   12. Show all friendships\n" +
                        "   13. Show all owners\n" +
                        "   14. Show all ownerships\n" +
                        "   15. Show all cat's friends\n" +
                        "   16. Show all owner's cats\n" +
                        "   17. Show cat's owner\n" +
                        "Exit:\n" +
                        "   18. Shut Down\n" +
                        " -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
    }

    public static boolean shutdown(){
        System.out.println("Bye, see u soon, m8!");
        return false;
    }
}
