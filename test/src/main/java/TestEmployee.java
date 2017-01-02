import java.util.HashSet;

/**
 * Created by todzhang on 2017/1/2.
 */
public class TestEmployee {
    public static void main(String[] args){
        Employee emp1=new Employee(23);
        Employee emp2=new Employee(24);
        Employee emp3=new Employee(25);
        Employee emp4=new Employee(26);
        Employee emp5=new Employee(27);

        HashSet<Employee>  hs=new HashSet<Employee>();
        hs.add(emp1);
        hs.add(emp2);
        hs.add(emp2);
        hs.add(emp3);
        hs.add(emp4);
        hs.add(emp5);

        System.out.println("Hashset size --->>>"+hs.size());
        System.out.println("hs.contains (new Emp(25))--->>>"+hs.contains(new Employee(25)));
        System.out.println("hs.remove (new Emp(23))--->>>"+hs.remove(new Employee(23)));
        System.out.println("Now Hashset size --->>>"+hs.size());
    }
}
