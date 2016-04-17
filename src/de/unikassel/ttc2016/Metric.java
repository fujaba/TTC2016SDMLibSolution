package de.unikassel.ttc2016;

import de.unikassel.ttc2016.model.*;
//needs explicit import
import de.unikassel.ttc2016.model.Class;

public class Metric
{
	/**
	 * @param classModel
	 * @return heavy calculated 
	 *         things
	 */
	public double calc(ClassModel classModel)
	{
		return cRAIndex(classModel);
	}

	public double cRAIndex(ClassModel classModel){
		return calcCohesianRatio(classModel) - calcCouplingRatio(classModel);
	}

	private double calcCohesianRatio(ClassModel classModel) {

		double sum = 0;
		
		for (Class ci : classModel.getClasses()) {
			sum += calcRatio(ci, ci);
		}
		
		return sum;
	}

	private double calcCouplingRatio(ClassModel classModel){
		
		double sum = 0;
		
		for (Class ci : classModel.getClasses()) {
			for (Class cj : classModel.getClasses()){
				if(ci != cj){
					sum += calcRatio(ci, cj);
				}
			}
		}
		
		return sum;
	}
	
	private double calcRatio(Class ci, Class cj){

		double result = 0;
		
		
		//TODO replace these calls and functions with lambda expressions
		int mci = calcM(ci);
		int mcj = calcM(cj);
		int aci = calcA(ci);
		int acj = calcA(cj);
		
		//not sure if there is actually a cross product in the formula
		result = (
				(calcMAI(ci, cj) / Math.abs(mci) * Math.abs(acj)) + 
				(calcMMI(ci, cj) / mci * (mcj - 1))
		);
		
		return result;
	}
	
	private int calcM(Class c) {
		int count = 0;
		for(Feature f : c.getEncapsulates()){
			if (f instanceof Method) count++;
		}
		return count;
	}

	private int calcA(Class c) {
		int count = 0;
		for(Feature f : c.getEncapsulates()){
			if (f instanceof Attribute) count++;
		}
		return count;
	}

	//TODO solve DMM DMA MAI and MMI via join of FeatureSets and resulting size

	private double calcMMI(Class ci, Class cj){

		double sum = 0;

		for (Feature fi : ci.getEncapsulates()) {
			if(fi instanceof Method){
				Method mi = (Method)fi;
				for (Feature fj : cj.getEncapsulates()) {
					if(fj instanceof Method){
						Method mj = (Method)fj;
						sum += calcDMM(mi, mj);
					}
				}
			}
		}

		return sum;
	}

	private double calcMAI(Class ci, Class cj){

		double sum = 0;

		for (Feature fi : ci.getEncapsulates()) {
			if(fi instanceof Method){
				Method mi = (Method)fi;
				for (Feature fj : cj.getEncapsulates()) {
					if(fj instanceof Attribute){
						Attribute aj = (Attribute)fj;
						sum += calcDMA(mi, aj);
					}
				}
			}
		}

		return sum;
	}

	private int calcDMA(Method mi, Attribute aj){
		if(mi.getDataDependency().contains(aj)){
			return 1;
		}else{
			return 0;
		}
	}

	private int calcDMM(Method mi, Method mj){
		//TODO discuss whether this is the right implementation
		if(mi.getFunctionalDependency().contains(mj) ||
				mi.getFunctionalDependencyTransitive().contains(mj)){
			return 1;
		}else{
			return 0;
		}
	}



}
