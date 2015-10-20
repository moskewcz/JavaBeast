/*
 * Copyright 2015 Alpenliebe <alpseinstein@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.javabeast.senior;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B11_BehaviorPattern {
    
    public static void main(String[] args){
        String exp = "2+8";
        ICalculator cal = new Plus();
        System.out.println(cal.calculate(exp));
        
        String exp2 = "8+8";  
        AbstractCalculator2 cal2 = new Plus2();
        System.out.println(cal2.calculate(exp2, "\\+")); 
    }
    
}

//Strategy
interface ICalculator {
	public int calculate(String exp);
}

abstract class AbstractCalculator {
	
	public int[] split(String exp,String opt){
		String array[] = exp.split(opt);
		int arrayInt[] = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}
}

class Plus extends AbstractCalculator implements ICalculator {

	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp,"\\+");
		return arrayInt[0]+arrayInt[1];
	}
}

class Minus extends AbstractCalculator implements ICalculator {

	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp,"-");
		return arrayInt[0]-arrayInt[1];
	}

}

//Template Method
abstract class AbstractCalculator2 {
    
    public final int calculate(String exp,String opt){  
        int array[] = split(exp,opt);  
        return calculate(array[0],array[1]);  
    }
    
    abstract public int calculate(int num1,int num2);
    
    public int[] split(String exp,String opt){
            String array[] = exp.split(opt);
            int arrayInt[] = new int[2];
            arrayInt[0] = Integer.parseInt(array[0]);
            arrayInt[1] = Integer.parseInt(array[1]);
            return arrayInt;
    }
}

class Plus2 extends AbstractCalculator2 {

    @Override
    public int calculate(int num1,int num2) {
        return num1 + num2;
    }
}