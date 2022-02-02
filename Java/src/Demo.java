import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        //==============================================================1
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("David",23));
        empList.add(new Employee("Alice",18));
        empList.add(new Employee("Bob",17));
        empList.add(new Employee("Amy",18));
        empList.add(new Employee("Curry",18));
        empList.add(new Employee("Amy",20));

        List<Employee> sortedEmpList = new ArrayList<>(empList);
        sortedEmpList.sort((x,y)->{
            if((x.name).equals(y.name)){
                return x.age-y.age;
            }
            return y.name.compareTo(x.name);
        });
        System.out.println(empList.toString());
        //==============================================================2
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<List> integerStream = partition(intList);
        System.out.println(integerStream);
        //==============================================================3
        char[] charArray = new char[]{'h','e','l','l','o'};
        System.out.println(combineChars(charArray));
    }

    public static List<List> partition(List<Integer> intList){
        return intList.stream().collect(()->new ArrayList<>(),
                (list,x)-> {list.add(new ArrayList());if(x%2!=0){list.get(0).add(x);}else{list.get(1).add(x);}list.removeIf(a-> a.isEmpty());},
                (l1,l2)->l1.addAll(l2));
    }

    public static String combineChars(char[] arr){
        return Arrays.asList(arr).stream().collect(()->new StringBuilder(),
                (builder,x)->builder.append(x),
                (b1,b2)->b1.append(b2)).toString();
    }
}
