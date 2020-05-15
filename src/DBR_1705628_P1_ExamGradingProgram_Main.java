/*
 * Raghad Zohair Sadi Yahyha, ID: 1705628, DBR, CPCS203 .
 * Program 1 : ExamGradingProgram .
 * Email: ryahya0010@stu.kau.edu.sa
 */


/**
 *
 * @author Raghad
 */
import java.util.*;
import java.io.*;
public class DBR_1705628_P1_ExamGradingProgram_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // creainte files: 1\To read data from file "input.txt" 2\ To print data from file "input.txt".
        File fileRead = new File("input.txt");
        File fileWrite = new File("print.txt");
        
        // cheke if the file not exists
        if(!fileRead.exists()){
            System.out.println("The file does not exist");
            System.exit(0);
        }
        
        // create Scanner and printwriter object
        Scanner input = new Scanner(fileRead);
        PrintWriter write = new PrintWriter(fileWrite);
        
        read(input,write);
        
        input.close();
        write.close();
    }
    //////////// CREATE METHOD FOR READ THE COMMAND
    public static void read(Scanner input, PrintWriter write){
        /////------------------------ START FIRST WHILE LOOP ----------------------------////
        while (input.hasNext()){
            // To read size of courses and store them fileRead array
            int sizeCourse =input.nextInt();
            String[] course = new String[sizeCourse];
            // size of exam type
            int sizeExam = 6;
            String []exam = new String [sizeExam];
            // Create array for student and read the size for each course
            String[][] student = new String[sizeCourse][] ;
            for(int i =0; i<sizeCourse ;i++){
                student[i] = new String[input.nextInt()];               
            }
            // Create array for marks of student and read the size for each course
            int [][][]marks =new int [sizeCourse][student.length][exam.length];
            for(int i=0; i<sizeCourse;i++){
                marks[i] = new int[student[i].length][exam.length];
            }
            int indexcourse =0;
            /////----------------- START SECOND WHILE LOOP ----------------//////
            while(input.hasNext()){
                String command = input.next();
                if(command.equals("Add_Course")){
                    storeCourses(input,command,course,write);
                }
                else if(command.equals("Add_ExamType")){
                    examType(input,command,exam,write);
                }
                else if(command.equals("Add_Student_For_Course")){
                    storeStudent(input,command,student,sizeCourse,write);  
                }
                else if(command.equals("Add_Marks")){
                    marks(input,marks);
                }
                else if(command.equals("Print_Result")){
                    printResult(input,indexcourse,command,marks,student,course,write);
                    indexcourse++;    
                }
                else if(command.equals("Print_Result_For_All")){
                    printResultAll(command,marks,student,course,write);  
                }
                else{
                    quit(write);
                } 
            }///////// END SECOND WHILE LOOP /////////  
        }///////// END FIRST WHILE LOOP ///////// 
    }
    //////////// CREATE METHOD FOR READ AND PRINT COURSES 
    public static String[] storeCourses(Scanner input,String command,String[]courses,PrintWriter write){
        write.println("COMMAND: "+command.toUpperCase());
        write.println("***Course Record For Exam Grading System***");
        write.println();
        for(int i=0; i<courses.length; i++){
            courses[i]=input.next();
            write.print(" - Course: "+courses[i]+"  ");
        }
        write.println();
        write.println("------------------------------------------------"
                    + "-------------------------------------------------------");
        write.println();
        return courses;
    }
    //////////// CREATE METHOD FOR READ AND PRINT EXAM TYPE
    public static String[] examType(Scanner input,String command,String[]storeExam,PrintWriter write){
        write.println("COMMAND: "+command.toUpperCase());
        write.println("***Exam Type Record For Exam Grading System***");
        write.println();
        for (int i = 0; i < storeExam.length; i++){
            storeExam[i]=input.next();
            write.print(" - Exam Type: "+storeExam[i]+" ");
        }
        write.println();
        write.println("------------------------------------------------"
                    + "-------------------------------------------------------");
        write.println();
        return storeExam;
    }
    //////////// CREATE METHOD FOR READ AND PRINT STUDENT
    public static void storeStudent(Scanner input,String command,String[][]student,int sizeCourse,PrintWriter write){
        write.println("COMMAND: "+command.toUpperCase());
        write.println("***Student Record For Exam Grading System***");
        write.println();
        int indexCourse =input.nextInt();
        for(int i=0; i<sizeCourse;i++){
            for(int j=0;j<student[indexCourse].length;j++){
                student[indexCourse][j] =input.next();
                write.print(" - Name: "+student[indexCourse][j]+" ");
            }
            write.println();
            write.println("------------------------------------------------"
                    + "-------------------------------------------------------");
            write.println();
            return;
        } 
    }
    //////////// CREATE METHOD FOR READ AND STORE MARKS
    public static int[][][] marks(Scanner input,int[][][]mark){
        for(int i=0; i<mark.length;i++){
            for(int j=0; j<mark[i].length; j++){
               for(int k=0; k<mark[i][j].length; k++){
                   mark[i][j][k] = input.nextInt();
                }
            }  
        }
        return mark;
    }
    //////////// CREATE METHOD FOR PRINT IFORMAION FOR EACH COURSE
    public static void printResult(Scanner input,int indexcourse,String command,int [][][]mark,String[][]student,String[]course,PrintWriter write){
        String nameCourse = input.next();
        write.println("COMMAND: "+command.toUpperCase());
        write.println("***Students Winner Record For Exam Grading System***");       
        write.println();
        write.println("-- (Students Total Marks)  Result For Course "+nameCourse+"  --");
        int maxMark =-1;
        String name ;
        String nameTopper ='y';
        for(int i=indexcourse; i<mark.length;){
            for(int j=0; j<mark[i].length;j++){    
                name=student[i][j];
                int totalMarks =0;
                for(int k=0; k<mark[i][j].length;k++){
                    totalMarks += mark[i][j][k];     
                }
                write.println(name+" "+totalMarks+",");
                if(totalMarks>maxMark){
                    maxMark=totalMarks;
                    nameTopper=student[i][j];
                }  
            }
            write.println();
            write.println("Topper in the course is  :  "+nameTopper.toUpperCase()+"  with Marks "+maxMark+" / 100 ");
            write.println();
            write.println("---------------------------------------------------"
                    + "----------------------------------------------------");
            write.println();
            return;
        }  
    }
    //////////// CREATE METHOD FOR PRINT THE TOPPER STUDENT
    public static void printResultAll(String command,int[][][]mark,String [][]student,String[]course,PrintWriter write){
        write.println("COMMAND: "+command.toUpperCase());
        write.println("***High Marks gainer from all courses For Exam Grading System***");
        write.println();
        int maxMark =0;
        // name studentTopper and course
        String nameTopper =null;
        String nameCourse = null;
        for(int i=0;i<mark.length;i++){
            for(int j=0;j<mark[i].length;j++){
                int total =0;
                for(int k=0; k<mark[i][j].length;k++){
                    total+=mark[i][j][k];
                    if(total>maxMark){
                        maxMark =total;
                        nameCourse = course[i];
                        nameTopper=student[i][j];
                    }
                }
            }  
        }
        write.println(nameTopper+" is The Highest marks gainer in all Courses from  :  "+nameCourse
                +"  with Marks "+maxMark+" / 100");
        write.println("----------------------------------------------------"
                + "---------------------------------------------------");
        write.println();
    }
    public static void quit(PrintWriter write){
        write.println();
        write.println("Thank you for using Exam Grading System, Good Bye!");
    }

    
}
//for (int i =; i<cours.lenght;){
    //for(int j =0; j<Quize_Key[i].lenght; j++){
        // Quize_Key[i][j] = input.next;
    //}
    // return;
//}

/*
String nameCourse = input.next();
String nameStudent;
int maxMark =-1;
 String nameTopper =null;
for(int i=indexcourse; i<Quizes.length;){
            for(int j=0; j<Quizes[i].length;j++){    
                name=student[i][j];
                int totalMarks =0;
                for(int k=0; k<Quizes[i][j].length;k++){
                   if(Quizes[i][j][k] == Quize_key[i][j]){
                    totalMarks++;   
                }
                write.println(name+" "+totalMarks+",");
                if(totalMarks>maxMark){
                    maxMark=totalMarks;
                    nameTopper=student[i][j];
                }  
            }
            write.println();
            write.println("Topper in the course is  :  "+nameTopper.toUpperCase()+"  with Marks "+maxMark+" / 100 ");
            write.println();
            write.println("---------------------------------------------------"
                    + "----------------------------------------------------");
            write.println();
            return;
        /*}  

