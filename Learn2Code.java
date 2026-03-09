package com.learn2code;

/**
 * PROJECT: Learn 2 Code (HTML Console Edition)
 */

import java.util.*;

// ==========================================
// MODELS & DATA STRUCTURES
// ==========================================

enum Difficulty {
    EASY, MEDIUM, HARD
}

// [CO2] - ADT (Abstract Data Type) Implementation using classes
class TestCase {
    String input;
    String output;

    public TestCase(String input, String output) {
        this.input = input;
        this.output = output;
    }
}

class Problem {
    String id;
    String title;
    Difficulty difficulty; // [CO1] - Analyzing input characteristics (Difficulty)
    String description;
    String inputFormat;
    String outputFormat;
    String constraints;
    List<String> requiredKeywords;
    List<TestCase> sampleTestCases;
    boolean solved;
    String category;

    public Problem(String id, String title, Difficulty difficulty, String category, String description,
            String inputFormat, String outputFormat, String constraints, List<String> requiredKeywords) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.category = category;
        this.description = description;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
        this.constraints = constraints;
        this.requiredKeywords = requiredKeywords;
        this.sampleTestCases = new ArrayList<>(); // [CO2, CO4] - Dynamic Array (ArrayList) implementation from Java
                                                  // Collections
        this.solved = false;
    }
}

class Module {
    String id;
    String title;
    String duration;
    Difficulty level;
    String content;
    Problem linkedProblem;
    boolean completed;

    public Module(String id, String title, String duration, Difficulty level, String content, Problem linkedProblem) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.level = level;
        this.content = content;
        this.linkedProblem = linkedProblem;
        this.completed = false;
    }
}

class Course {
    String id;
    String title;
    String category;
    List<Module> modules;

    public Course(String id, String title, String category) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.modules = new ArrayList<>(); // [CO2, CO4] - Using Java Collections List (ArrayList) for ADT implementation
    }

    public void addModule(Module m) {
        this.modules.add(m);
    }
}

class User {
    String username;
    String password;
    UserStats stats;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.stats = new UserStats();
    }
}

class UserStats {
    int solvedCount;
    int modulesCompleted;
    int easySolved, mediumSolved, hardSolved;
    int streak;

    public void updateStats(Difficulty difficulty) {
        solvedCount++;
        switch (difficulty) {
            case EASY:
                easySolved++;
                break;
            case MEDIUM:
                mediumSolved++;
                break;
            case HARD:
                hardSolved++;
                break;
        }
    }
}

// ==========================================
// DATABASE (MOCK)
// ==========================================

class Database {
    // [CO2, CO4] - Java Collections: List and Map (Hash-based structure)
    public static List<Course> courses = new ArrayList<>();
    public static Map<String, User> users = new HashMap<>(); // [CO4] - Mapping for fast lookup (Hash Table
                                                             // implementation)

    // [CO3] - Queue implementation for modeling real-world workflows (Recent
    // Activities)
    public static Queue<String> recentActivities = new LinkedList<>();
    private static final int MAX_ACTIVITIES = 10;

    public static void addActivity(String activity) {
        if (recentActivities.size() >= MAX_ACTIVITIES) {
            recentActivities.poll(); // [CO2, CO3] - Dequeue operation (First-In, First-Out)
        }
        recentActivities.offer(activity); // [CO2, CO3] - Enqueue operation
    }

    static {
        initializeData();
        users.put("admin", new User("admin", "admin"));
        users.put("prem", new User("prem", "pass123"));
    }

    private static void initializeData() {
        // --- CONSOLIDATED COURSE: HTML MASTERY ---
        Course htmlCourse = new Course("html-mastery", "HTML Mastery", "Web Development");

        // Module 1: Structure
        Problem p1 = new Problem("html-p1", "HTML Structure", Difficulty.EASY, "Fundamentals",
                "Task: Create a complete HTML5 skeleton.\nRequirements:\n1. Must start with the <!DOCTYPE html> declaration.\n2. Must have <html>, <head>, and <body> tags.\n3. All tags must be properly opened and closed.\nPro-tip: This is the foundation of every web page!",
                "None (Keyboard Input)", "Valid HTML5 structure (Markup Check)",
                "Must include exactly: <!DOCTYPE html>, <html>, <head>, <body>",
                Arrays.asList("<!DOCTYPE html>", "<html>", "<head>", "<body>"));
        htmlCourse.addModule(new Module("html-m1", "HTML Basics: Structure & Doctype", "10 min", Difficulty.EASY,
                "HTML stands for HyperText Markup Language. It is the standard markup language for documents designed to be displayed in a web browser.\nEvery HTML5 document should start with a <!DOCTYPE html> declaration to tell the browser to use the latest HTML standards.\nExample structure:\n<!DOCTYPE html>\n<html>\n<head><title>Title</title></head>\n<body>Content</body>\n</html>",
                p1));

        // Module 2: Attributes
        Problem p7 = new Problem("html-p7", "HTML Attributes", Difficulty.EASY, "Attributes",
                "Task: Create a functional hyperlink.\nRequirements:\n1. Use the <a> tag.\n2. Add an 'href' attribute pointing to any URL (e.g., 'https://google.com').\n3. Add a 'title' attribute to provide a tooltip description.",
                "None (Keyboard Input)", "An <a> tag with href and title attributes",
                "Attributes must be inside the opening tag: <a attr=\"value\">",
                Arrays.asList("<a", "href=", "title="));
        htmlCourse.addModule(new Module("html-m2", "Elements and Attributes", "15 min", Difficulty.EASY,
                "Attributes provide additional information about elements. They are always specified in the start tag and usually come in name/value pairs like name=\"value\".\nThe href attribute of <a> specifies the URL of the page the link goes to.\nThe src attribute of <img> is used to embed an image path.",
                p7));

        // Module 3: Headings
        Problem p2 = new Problem("html-p2", "Headings & Paragraphs", Difficulty.EASY, "Text",
                "Task: Define a heading and a paragraph.\nRequirements:\n1. Create a top-level heading using <h1>.\n2. Add a followed-up paragraph using <p>.\nTip: Use <h1> only once per page for SEO best practices!",
                "None (Keyboard Input)", "<h1> and <p> tags", "Case-insensitive tag check",
                Arrays.asList("<h1>", "<p>"));
        htmlCourse.addModule(new Module("html-m3", "Headings and Paragraphs", "10 min", Difficulty.EASY,
                "HTML provides six levels of headings: <h1> being the most important and <h6> the least.\nParagraphs are defined with the <p> tag and are block-level elements that always start on a new line.",
                p2));

        // Module 4: Formatting
        Problem p8 = new Problem("html-p8", "Text Emphasis", Difficulty.EASY, "Formatting",
                "Task: Apply semantic emphasis to text.\nRequirements:\n1. Use the <strong> tag for important text.\n2. Use the <em> tag for emphasized (italicized) text.\nNote: Avoid using <b> and <i> as they are non-semantic.",
                "None (Keyboard Input)", "<strong> and <em> tags", "Semantic markup only",
                Arrays.asList("<strong>", "<em>"));
        htmlCourse.addModule(new Module("html-m4", "Text Formatting", "15 min", Difficulty.EASY,
                "HTML contains several elements for defining text with a special meaning.\n<strong> defines important text, while <em> defines emphasized text. Both are semantic elements that improve accessibility.",
                p8));

        // Module 5: Lists
        Problem p3 = new Problem("html-p3", "Lists", Difficulty.EASY, "Structure",
                "Task: Create both types of lists.\nRequirements:\n1. Create an Unordered List (bullets) using <ul>.\n2. Create an Ordered List (numbers) using <ol>.\n3. Both lists must contain at least one List Item (<li>).",
                "None (Keyboard Input)", "ul, ol, and li tags", "Proper nesting required",
                Arrays.asList("<ul>", "<li>", "<ol>"));
        htmlCourse.addModule(new Module("html-m5", "Lists: Ordered and Unordered", "15 min", Difficulty.EASY,
                "Use <ul> for bulleted (unordered) lists and <ol> for numbered (ordered) lists.\nIndividual list items are marked with the <li> tag.",
                p3));

        // Module 6: Links
        Problem p4 = new Problem("html-p4", "Links & Navigation", Difficulty.EASY, "Navigation",
                "Task: Build a navigation menu.\nRequirements:\n1. Wrap everything in a <nav> element.\n2. Create three separate links using <a> tags.\nTip: Most websites use a <nav> section in their header!",
                "None (Keyboard Input)", "nav tag containing three <a> tags", "Multiple <a> tags required",
                Arrays.asList("<nav>", "<a", "<a", "<a"));
        htmlCourse.addModule(new Module("html-m6", "Links and Navigation", "12 min", Difficulty.EASY,
                "The <a> tag defines a hyperlink. The 'href' attribute is the most important as it indicates the destination.\nExample: <a href=\"https://example.com\">Visit Web</a>",
                p4));

        // Module 7: Images
        Problem p9 = new Problem("html-p9", "Images", Difficulty.EASY, "Media",
                "Task: Embed an image correctly.\nRequirements:\n1. Use the <img> tag.\n2. Provide the 'src' attribute for the image source.\n3. Provide the 'alt' attribute for accessibility (it's mandatory!).",
                "None (Keyboard Input)", "<img> with src and alt attributes", "Self-closing tag with attributes",
                Arrays.asList("<img", "src=", "alt="));
        htmlCourse.addModule(new Module("html-m7", "HTML Images", "15 min", Difficulty.EASY,
                "Images are embedded using the <img> tag, which is an empty element (self-closing).\nThe 'src' attribute points to the image file, and 'alt' provides a text description for screen readers.",
                p9));

        // Module 8: Tables
        Problem p5 = new Problem("html-p5", "Data Tables", Difficulty.MEDIUM, "Data",
                "Task: Create a structured data table.\nRequirements:\n1. Start with a <table> tag.\n2. Create a row using <tr>.\n3. Define headers using <th>.\n4. Add data using <td>.\nConstraint: Ensure proper hierarchy: table > tr > th/td.",
                "None (Keyboard Input)", "Full table structure (Markup Check)",
                "Must include table, tr, th, and td", Arrays.asList("<table>", "<tr>", "<th>", "<td>"));
        htmlCourse.addModule(new Module("html-m10", "HTML Tables", "20 min", Difficulty.MEDIUM,
                "An HTML table consists of one <table> element and one or more <tr> (rows), <th> (headers), and <td> (data) elements.\nTables are used for displaying structured data.",
                p5));

        // Module 9: Forms
        Problem p6 = new Problem("html-p6", "Forms", Difficulty.MEDIUM, "Interaction",
                "Task: Create a user input form.\nRequirements:\n1. Use a <form> container.\n2. Add an input field with type=\"email\".\n3. Add a clickable <button>.\nTip: Emails are validated automatically by the browser with this type!",
                "None (Keyboard Input)", "<form> with email input and button", "type='email' is mandatory",
                Arrays.asList("<form", "type=\"email\"", "<button"));
        htmlCourse.addModule(new Module("html-m11", "HTML Forms and Inputs", "30 min", Difficulty.MEDIUM,
                "The <form> element is used to create a section for user input.\nThe <input> element is the most vital, allowing for text, password, and email types.",
                p6));

        courses.add(htmlCourse);
    }
}

// ==========================================
// MAIN APPLICATION
// ==========================================

public class Learn2Code {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        // [CO3] - Real-world workflow: User Authentication and Navigation flow.
        // The JVM call stack manages the navigation (returning from methods is like
        // popping from a stack).
        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showAuthMenu() {
        clearScreen();
        System.out.println("========================================");
        System.out.println("   LEARN 2 CODE - WEB ACCESS PORTAL    ");
        System.out.println("========================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("========================================");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                login();
                break;
            case "2":
                register();
                break;
            case "3":
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = Database.users.get(username); // [CO2, CO4] - Search operation in a Hash-based collection (O(1)
                                                  // average time complexity)
        if (user != null && user.password.equals(password)) {
            currentUser = user;
            Database.addActivity("User '" + username + "' logged in."); // Update Queue workflow
            System.out.println("Login successful! Welcome, " + username);
            pause();
        } else {
            System.out.println("Invalid credentials.");
            pause();
        }
    }

    private static void register() {
        System.out.print("Choose Username: ");
        String username = scanner.nextLine();
        if (Database.users.containsKey(username)) {
            System.out.println("Username already exists.");
            pause();
            return;
        }
        System.out.print("Choose Password: ");
        String password = scanner.nextLine();

        Database.users.put(username, new User(username, password));
        System.out.println("Registration successful!");
        pause();
    }

    private static void showMainMenu() {
        clearScreen();
        System.out.println("========================================");
        System.out.println("   DASHBOARD | Welcome, " + currentUser.username);
        System.out.println("========================================");

        // [CO3] - Displaying Queue status (Traversing/Displaying a linear workflow)
        if (!Database.recentActivities.isEmpty()) {
            System.out.println("Recent Activities:");
            for (String activity : Database.recentActivities) {
                System.out.println(" - " + activity);
            }
            System.out.println("----------------------------------------");
        }

        System.out.println("1. Learning Hub (Courses)");
        System.out.println("2. Coding Practice (Problems)");
        System.out.println("3. Global Leaderboard");
        System.out.println("4. My Progress");
        System.out.println("5. Code Playground");
        System.out.println("6. Logout");
        System.out.println("========================================");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                showLearningHub();
                break;
            case "2":
                showPracticeHub();
                break;
            case "3":
                showLeaderboard();
                break;
            case "4":
                showStats();
                break;
            case "5":
                showPlayground();
                break;
            case "6":
                currentUser = null;
                break;
            default:
                System.out.println("Invalid option.");
                pause();
        }
    }

    private static void showLearningHub() {
        while (true) {
            clearScreen();
            System.out.println("--- LEARNING HUB ---");
            for (int i = 0; i < Database.courses.size(); i++) {
                System.out.println(
                        (i + 1) + ". " + Database.courses.get(i).title + " (" + Database.courses.get(i).category + ")");
            }
            System.out.println((Database.courses.size() + 1) + ". Back");
            System.out.print("Select a course: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == Database.courses.size() + 1)
                    return;
                if (choice > 0 && choice <= Database.courses.size()) {
                    showCourse(Database.courses.get(choice - 1));
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
                pause();
            }
        }
    }

    private static void showCourse(Course course) {
        while (true) {
            clearScreen();
            System.out.println("--- COURSE: " + course.title + " ---");
            for (int i = 0; i < course.modules.size(); i++) {
                Module m = course.modules.get(i);
                String status = m.completed ? "[COMPLETED]" : "[ ]";
                System.out.println((i + 1) + ". " + status + " " + m.title + " (" + m.duration + ")");
            }
            System.out.println((course.modules.size() + 1) + ". Back");
            System.out.print("Select a module: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == course.modules.size() + 1)
                    return;
                if (choice > 0 && choice <= course.modules.size()) {
                    viewModule(course.modules.get(choice - 1));
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
                pause();
            }
        }
    }

    private static void viewModule(Module module) {
        clearScreen();
        System.out.println("MODULE: " + module.title);
        System.out.println("Level: " + module.level + " | Duration: " + module.duration);
        System.out.println("----------------------------------------");
        System.out.println(module.content);
        System.out.println("----------------------------------------");
        System.out.println("1. Solve Linked Problem: " + module.linkedProblem.title);
        System.out.println("2. Mark Module as Completed");
        System.out.println("3. Back");

        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            solveProblem(module.linkedProblem);
            if (module.linkedProblem.solved)
                module.completed = true;
        } else if (choice.equals("2")) {
            module.completed = true;
            currentUser.stats.modulesCompleted++;
            System.out.println("Module marked as completed!");
            pause();
        }
    }

    private static void showPracticeHub() {
        // Collect all problems from all courses
        List<Problem> allProblems = new ArrayList<>();
        for (Course c : Database.courses) {
            for (Module m : c.modules) {
                allProblems.add(m.linkedProblem);
            }
        }

        while (true) {
            clearScreen();
            System.out.println("--- CODING PRACTICE ---");
            for (int i = 0; i < allProblems.size(); i++) {
                Problem p = allProblems.get(i);
                String status = p.solved ? "[SOLVED]" : "[ ]";
                System.out.println((i + 1) + ". " + status + " " + p.title + " (" + p.difficulty + ")");
            }
            System.out.println((allProblems.size() + 1) + ". Back");
            System.out.print("Select a problem: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == allProblems.size() + 1)
                    return;
                if (choice > 0 && choice <= allProblems.size()) {
                    solveProblem(allProblems.get(choice - 1));
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
                pause();
            }
        }
    }

    private static void solveProblem(Problem p) {
        clearScreen();
        System.out.println("PROBLEM: " + p.title + " (" + p.difficulty + ")");
        System.out.println("----------------------------------------");
        System.out.println(p.description);
        System.out.println("Input: " + p.inputFormat);
        System.out.println("Output: " + p.outputFormat);
        System.out.println("Constraints: " + p.constraints);
        System.out.println("----------------------------------------");
        System.out.println("Type your code (Type 'SUBMIT' on a new line):");

        StringBuilder code = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().equalsIgnoreCase("SUBMIT"))
                break;
            code.append(line).append("\n");
        }

        if (code.length() > 0) {
            System.out.println("Accepting Submission... COMPILING...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            boolean allKeywordsPresent = true;
            List<String> missingKeywords = new ArrayList<>();
            String submittedCode = code.toString().toLowerCase();

            for (String keyword : p.requiredKeywords) {
                if (!submittedCode.contains(keyword.toLowerCase())) {
                    allKeywordsPresent = false;
                    missingKeywords.add(keyword);
                }
            }

            if (allKeywordsPresent) {
                System.out.println("STATUS: ACCEPTED");
                p.solved = true;
                Database.addActivity("Problem '" + p.title + "' solved."); // Update Queue workflow
                currentUser.stats.updateStats(p.difficulty);
            } else {
                System.out.println("STATUS: WRONG ANSWER");
                System.out.println("Missing requirements:");
                for (String missing : missingKeywords) {
                    System.out.println(" - Must include: " + missing);
                }
            }
            pause();
        }
    }

    private static void showLeaderboard() {
        clearScreen();
        System.out.println("--- GLOBAL LEADERBOARD ---");
        List<User> sortedUsers = new ArrayList<>(Database.users.values());
        // [CO3] - Prioritized processing: This scenario is often handled using Heaps or
        // Priority Queues
        // to maintain an ordered list of elements based on specific criteria
        // (solvedCount).
        // [CO1] - Classical Sorting Algorithm usage (Collections.sort uses
        // Merge/TimSort)
        // Analyzing efficiency of sorting algorithmic solutions
        sortedUsers.sort((a, b) -> b.stats.solvedCount - a.stats.solvedCount);

        for (int i = 0; i < sortedUsers.size(); i++) {
            User u = sortedUsers.get(i);
            System.out.println((i + 1) + ". " + u.username + " - " + u.stats.solvedCount + " solved");
        }
        System.out.println("----------------------------------------");
        pause();
    }

    private static void showStats() {
        clearScreen();
        System.out.println("--- MY PROGRESS ---");
        System.out.println("Modules Completed: " + currentUser.stats.modulesCompleted);
        System.out.println("Total Problems Solved: " + currentUser.stats.solvedCount);
        System.out.println("  - Easy: " + currentUser.stats.easySolved);
        System.out.println("  - Medium: " + currentUser.stats.mediumSolved);
        System.out.println("  - Hard: " + currentUser.stats.hardSolved);
        System.out.println("Current Streak: " + currentUser.stats.streak + " days");
        pause();
    }

    private static void showPlayground() {
        clearScreen();
        System.out.println("--- CODE PLAYGROUND (Standalone) ---");
        System.out.println("Write and test your HTML markup here.");
        System.out.println("Type 'EXIT' on a new line to go back.");
        System.out.println("----------------------------------------");
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().equalsIgnoreCase("EXIT"))
                break;
        }
    }

    private static void pause() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
