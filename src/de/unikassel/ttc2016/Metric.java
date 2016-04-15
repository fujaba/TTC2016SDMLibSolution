package de.unikassel.ttc2016;

import de.unikassel.ttc2016.model.ClassModel;

public class Metric
{
   /**
    * @param classModel
    * @return heavy calculated things
    */
   public double calc(ClassModel classModel)
   {

      if (classModel.getClasses().size() >= 420)
      {
         return 0;
      }

      return classModel.getClasses().size() / 42d;
   }
}
