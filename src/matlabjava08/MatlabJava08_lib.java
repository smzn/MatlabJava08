package matlabjava08;

import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import com.mathworks.engine.MatlabExecutionException;
import com.mathworks.engine.MatlabSyntaxException;

public class MatlabJava08_lib {
	private double a[][], b[], Aeq[], beq, lb[], ub[], f[];
	Future<MatlabEngine> eng;
	MatlabEngine ml;

	public MatlabJava08_lib(double[][] a, double[] b, double[] aeq, double beq, double[] lb, double[] ub, double[] f) {
		this.a = a;
		this.b = b;
		Aeq = aeq;
		this.beq = beq;
		this.lb = lb;
		this.ub = ub;
		this.f = f;
		eng = MatlabEngine.startMatlabAsync();
		try {
			//返された Future オブジェクトの get メソッドを使用して、MatlabEngine オブジェクトが返されるのを待ちます。
			ml = eng.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//https://jp.mathworks.com/help/optim/ug/linprog.html
	public double[] getOptimization() {
		double result[] = null;
		try {
			ml.putVariableAsync("a", a);
			ml.putVariableAsync("b", b);
			ml.putVariableAsync("Aeq", Aeq);
			ml.putVariableAsync("beq", beq);
			ml.putVariableAsync("lb", lb);
			ml.putVariableAsync("ub", ub);
			ml.putVariableAsync("f", f);
			ml.eval("a");
			ml.eval("options = optimoptions('linprog','Algorithm','dual-simplex');");
			ml.eval("[x,fval,exitflag,output] = linprog(f,a,b,Aeq,beq,lb,ub,options)");
			
			Future<double[]> futureEval_x = ml.getVariableAsync("x");
			result = futureEval_x.get();
		} catch (MatlabExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatlabSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CancellationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
