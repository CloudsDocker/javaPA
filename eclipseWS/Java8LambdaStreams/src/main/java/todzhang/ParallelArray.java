package todzhang;

import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class ParallelArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] list=new int[15];
		Arrays.parallelSetAll(list, i->i);
//		System.out.println(list);
//		List theList=Arrays.asList(list);
//		Arrays.asList(list).stream().forEach(System.out::println);
//		Arrays.asList(list).stream().forEach(i->System.out.println("["+i+"]"));
//		System.out.println(Arrays.asList(list).stream().toString());
		Arrays.stream(list).boxed().forEach(System.out::println);
//		for (int j : list) {
//			System.out.println(j);
//		}
	}

}
