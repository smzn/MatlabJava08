package matlabjava08;

import java.util.Arrays;

public class MatlabJava08_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a[][] = { { 1, 1 }, { 1, 0.25 }, { 1, -1 }, { -0.25, -1 }, { -1, -1 }, { -1, 1 } };
		double b[] = { 2, 1, 2, 1, -1, 2 };
		double Aeq[] = { 1,  0.25 };
		double beq = 0.5;
		double lb[] = { -1, -0.5 };
		double ub[] = { 1.5, 1.25 };
		double f[] = { -1, -0.33 };
		MatlabJava08_lib mlib = new MatlabJava08_lib(a, b, Aeq, beq, lb, ub, f);
		System.out.println("x = " +Arrays.toString(mlib.getOptimization()));
	}
}
