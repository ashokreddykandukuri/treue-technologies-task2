import java.text.SimpleDateFormat;
import java.util.*;

enum Priority {
    HIGH, MEDIUM, LOW
}

class Task {
    private String title;
    private Date dueDate;
    private String category;
    private Priority priority;
    private boolean isCompleted;

    public Task(String title, Date dueDate, String category, Priority priority) {
        this.title = title;
        this.dueDate = dueDate;
        this.category = category;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getCategory() {
        return category;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDueDate = sdf.format(dueDate);
        return "Title: " + title +
               "\nDue Date: " + formattedDueDate +
               "\nCategory: " + category +
               "\nPriority: " + priority +
               "\nStatus: " + (isCompleted ? "Completed" : "Not Completed");
    }
}

public class TaskReminderApp {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Task Reminder Application");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createTask(scanner);
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    markTaskAsCompleted(scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.next();
        System.out.print("Enter due date (yyyy-MM-dd): ");
        String dueDateStr = scanner.next();
        Date dueDate = parseDate(dueDateStr);
        System.out.print("Enter task category: ");
        String category = scanner.next();
        System.out.print("Enter task priority (HIGH, MEDIUM, LOW): ");
        Priority priority = Priority.valueOf(scanner.next().toUpperCase());

        Task task = new Task(title, dueDate, category, priority);
        tasks.add(task);
        System.out.println("Task created successfully.");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("Task " + (i + 1) + ":\n" + tasks.get(i));
                System.out.println("-------------------------");
            }
        }
    }

    private static void markTaskAsCompleted(Scanner scanner) {
        viewTasks();
        System.out.print("Enter the number of the task to mark as completed: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            if (!task.isCompleted()) {
                task.markAsCompleted();
                System.out.println("Task marked as completed.");
            } else {
                System.out.println("Task is already completed.");
            }
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}