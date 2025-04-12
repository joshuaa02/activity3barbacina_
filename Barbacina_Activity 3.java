import java.util.Scanner;

public class StudentSort {

    static class Student {
        String name;
        double grade;

        Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return name + " - " + grade;
        }
    }

    static void bubbleSort(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students[j].grade > students[j + 1].grade) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    static void selectionSort(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students[j].grade < students[minIndex].grade) {
                    minIndex = j;
                }
            }
            Student temp = students[minIndex];
            students[minIndex] = students[i];
            students[i] = temp;
        }
    }

    
    static void insertionSort(Student[] students) {
        for (int i = 1; i < students.length; i++) {
            Student key = students[i];
            int j = i - 1;
            while (j >= 0 && students[j].grade > key.grade) {
                students[j + 1] = students[j];
                j--;
            }
            students[j + 1] = key;
        }
    }

    static void mergeSort(Student[] students, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(students, left, mid);
            mergeSort(students, mid + 1, right);
            merge(students, left, mid, right);
        }
    }

    static void merge(Student[] students, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] L = new Student[n1];
        Student[] R = new Student[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = students[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = students[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i].grade <= R[j].grade) {
                students[k] = L[i];
                i++;
            } else {
                students[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1)
            students[k++] = L[i++];
        while (j < n2)
            students[k++] = R[j++];
    }

  
    static void quickSort(Student[] students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }

    static int partition(Student[] students, int low, int high) {
        double pivot = students[high].grade;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students[j].grade < pivot) {
                i++;
                Student temp = students[i];
                students[i] = students[j];
                students[j] = temp;
            }
        }
        Student temp = students[i + 1];
        students[i + 1] = students[high];
        students[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine(); 

        Student[] students = new Student[n];

        // Input student data
        for (int i = 0; i < n; i++) {
            System.out.println("Enter student #" + (i + 1) + " name: ");
            String name = scanner.nextLine();
            System.out.println("Enter " + name + "'s grade: ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); 
            students[i] = new Student(name, grade);
        }

        
        System.out.println("Choose sorting algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Quick Sort");
        System.out.print("Enter your choice (1-5): ");
        int choice = scanner.nextInt();

       
        switch (choice) {
            case 1:
                bubbleSort(students);
                break;
            case 2:
                selectionSort(students);
                break;
            case 3:
                insertionSort(students);
                break;
            case 4:
                mergeSort(students, 0, students.length - 1);
                break;
            case 5:
                quickSort(students, 0, students.length - 1);
                break;
            default:
                System.out.println("Invalid choice. Using Bubble Sort by default.");
                bubbleSort(students);
                break;
        }

       
        System.out.println("\nSorted list of students by grade:");
        for (Student s : students) {
            System.out.println(s);
        }

        scanner.close();
    }
}
