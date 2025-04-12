
import java.util.Scanner;

class Student {
    String name;
    double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + " - Grade: " + grade;
    }
}

public class StudentSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        Student[] students = new Student[count];

        for (int i = 0; i < count; i++) {
            System.out.println("Enter name of student " + (i + 1) + ":");
            String name = scanner.nextLine();
            System.out.println("Enter grade of student " + (i + 1) + ":");
            double grade = scanner.nextDouble();
            scanner.nextLine(); 
            students[i] = new Student(name, grade);
        }

        System.out.println("\nSort by:");
        System.out.println("1. Grade");
        System.out.println("2. Name");
        int sortField = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Sort order:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int sortOrder = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nChoose a sorting algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Quick Sort");
        int algoChoice = scanner.nextInt();

        switch (algoChoice) {
            case 1 -> bubbleSort(students, sortField, sortOrder);
            case 2 -> selectionSort(students, sortField, sortOrder);
            case 3 -> insertionSort(students, sortField, sortOrder);
            case 4 -> mergeSort(students, 0, students.length - 1, sortField, sortOrder);
            case 5 -> quickSort(students, 0, students.length - 1, sortField, sortOrder);
            default -> System.out.println("Invalid option!");
        }

        System.out.println("\nSorted list:");
        for (Student s : students) {
            System.out.println(s);
        }

        scanner.close();
    }

    private static int compare(Student a, Student b, int field, int order) {
        int result;
        if (field == 1) { 
            result = Double.compare(a.grade, b.grade);
        } else { 
            result = a.name.compareToIgnoreCase(b.name);
        }
        return order == 1 ? result : -result;
    }

    private static void bubbleSort(Student[] arr, int field, int order) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compare(arr[j], arr[j + 1], field, order) > 0) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void selectionSort(Student[] arr, int field, int order) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (compare(arr[j], arr[minIdx], field, order) < 0) {
                    minIdx = j;
                }
            }
            Student temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    private static void insertionSort(Student[] arr, int field, int order) {
        for (int i = 1; i < arr.length; i++) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && compare(arr[j], key, field, order) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void mergeSort(Student[] arr, int left, int right, int field, int order) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, field, order);
            mergeSort(arr, mid + 1, right, field, order);
            merge(arr, left, mid, right, field, order);
        }
    }

    private static void merge(Student[] arr, int left, int mid, int right, int field, int order) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] L = new Student[n1];
        Student[] R = new Student[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (compare(L[i], R[j], field, order) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private static void quickSort(Student[] arr, int low, int high, int field, int order) {
        if (low < high) {
            int pi = partition(arr, low, high, field, order);
            quickSort(arr, low, pi - 1, field, order);
            quickSort(arr, pi + 1, high, field, order);
        }
    }

    private static int partition(Student[] arr, int low, int high, int field, int order) {
        Student pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot, field, order) <= 0) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
