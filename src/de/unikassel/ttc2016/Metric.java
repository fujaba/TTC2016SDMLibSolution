package de.unikassel.ttc2016;

//needs explicit import
import de.unikassel.ttc2016.model.ClassModel;
import de.unikassel.ttc2016.model.Feature;
import de.unikassel.ttc2016.model.Method;
import de.unikassel.ttc2016.model.util.AttributeSet;
import de.unikassel.ttc2016.model.util.MethodSet;
import de.unikassel.ttc2016.model.Attribute;
import de.unikassel.ttc2016.model.Class;

public class Metric
{
	/**
	 * @param classModel
	 * @return heavy calculated 
	 *         things
	 */
	public double calc2(ClassModel classModel)
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




   /**
    * @param classModel
    * @return heavy calculated 
    *         things
    */
   public double calc(ClassModel classModel)
   {

      if (classModel.getClasses().size() >= 420)
      {
         return 0;
      }

      return classModel.getClasses().size() / 42d;
   }

   public double computeFitness(ClassModel model)
   {
      double result = cohesionRatio(model) - couplingRatio(model);
      
      return result;
   }

   private double cohesionRatio(ClassModel model)
   {
      double sum = 0;
      
      for(Class c : model.getClasses())
      {
         int mc = c.getEncapsulates().indexOf(new MethodSet());
         int ac = c.getEncapsulates().indexOf(new AttributeSet());
         
         if (mc*ac != 0)
         {
            sum += MAI(c,c) / (mc * ac);
         }
         
         if (mc * (mc-1) != 0)
         {
            sum += MMI(c,c) / (mc * (mc-1));
         }
      }
      
      return sum;
   }

   private double MMI(Class c1, Class c2)
   {
      double result = 0;
      
      for (Method m1 : c1.getEncapsulates().instanceOf(new MethodSet()))
      {
         for (Method m2 : c2.getEncapsulates().instanceOf(new MethodSet()))
         {
            if (m1.getFunctionalDependency().contains(m2))
            {
               result++;
            }
         }
      }   
      
      return result;
   }

   private double MAI(Class c1, Class c2)
   {
      double result = 0;
      
      for (Method m : c1.getEncapsulates().instanceOf(new MethodSet()))
      {
         for (Attribute a : c2.getEncapsulates().instanceOf(new AttributeSet()))
         {
            if (m.getDataDependency().contains(a))
            {
               result++;
            }
         }
      }
      
      return result;
   }

   private double couplingRatio(ClassModel model)
   {
      double sum = 0;
      
      for(Class c1 : model.getClasses())
      {
         for(Class c2 : model.getClasses())
         {
            if (c1 == c2)
            {
               continue;
            }
            
            int mc1 = c1.getEncapsulates().indexOf(new MethodSet());
            int mc2 = c2.getEncapsulates().indexOf(new MethodSet());
            int ac2 = c2.getEncapsulates().indexOf(new AttributeSet());

            if (mc1*ac2 != 0)
            {
               sum += MAI(c1,c2) / (mc1 * ac2);
            }

            if (mc1 * (mc2-1) != 0)
            {
               sum += MMI(c1,c2) / (mc1 * (mc2-1));
            }
         }
      }
      
      return sum;
   }
}
