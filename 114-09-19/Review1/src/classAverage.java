import java.util.Scanner;

public class classAverage {
    public static void main(String[]args){
        Scanner input = new Scanner((System.in));

        int total = 0;//總分
        int grade_counter = 0;//成績計數器

        System.out.println("輸入完成ctrl-z離開");

        while(input.hasNext()) {

            int grade = input.nextInt();//讀單筆成績
            total += grade;//累加總分
            grade_counter++;//成績計數器加一
        }
        if(grade_counter != 0){
            double average = (double)total/grade_counter;
            System.out.printf("總分:%d\n",total);
            System.out.printf("成績筆數:%d\n",grade_counter);
            System.out.printf("平均數:%f\n",average);
        }
        else{
            System.out.println("沒有成績輸入");
        }
    }
}
