package app;
import java.util.ArrayList;

/**
 *  This class provides methods that perform operations on a list of generic objects.
 *  The Objects are assumed to have an appropriate implementation of the equals method.
 */
public class ArrayListServices <T> {

   /**
    * This method takes an ArrayList as a parameter and returns a new ArrayList that 
    * does not contain any duplicate data. For example, if this list was passed in: 
    * [A, B, D, A, A, E, B], the method would return a list containing: [A, B, D, E]. 
    * The ordering of the data does not matter. 
    * Assume that the parameter is not null, but it may be an empty ArrayList, in which case 
    * your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the 
    * same as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> deDuplicate(ArrayList<T> inputList){
      //Write your comments on how you implemented the method.
      /**
      Firstly, I will create a new ArrayList to store all the deDuplicate element.
      Second, I will use loop to check if the element is not duplicated and add it to the new ArrayList that I have created.
      **/
      
      //TODO: Implement this method.
      ArrayList<T> newDeduplicate = new ArrayList<T>();
      for (T element : inputList){
         if(!newDeduplicate.contains(element)){
            newDeduplicate.add(element);
         }
      }
      /* 
      for (int i = 0; i < inputList.size(); i++){
         for(int j = i+1; i < inputList.size(); j++){
            if (inputList.get(i).equals(inputList.get(j))){
               newDeduplicate.add(inputList.remove(i));
            }
         }
      } */
      return newDeduplicate; 
   }

   /**
    * This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the intersection of the data in the ArrayLists passed in. The intersection 
    * contains the elements that occur in both lists.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [B, E]. The ordering of the data does not matter. Note that 
    * the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList, in which 
    * case your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same as 
    * the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetIntersection(ArrayList<T> listA, ArrayList<T> listB){
      //Write your comments on how you implemented the method.
      /**
      First, I will create a new ArrayList to store all the intersection elements of two initial ArrayList.
      Second, I will run the nested loop through both listA and listB to check which elements are the same and add it to the new ArrayList. 
      Last, we need to use deDuplicate method to remove all duplicated element in the list before returning it.
      **/
      
      //TODO: Implement this method.
      ArrayList<T> newIntersection = new ArrayList<T>();
      
      for(T element : listA){
         if(listB.contains(element)){
            newIntersection.add(element);
         }
      }

      newIntersection = deDuplicate(newIntersection);

      return newIntersection;
   }

   /**
    *  This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the set difference of the data in the ArrayLists passed in. The set difference 
    * contains the elements that occur only in one or the other list, but not in both.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [A, C]. The ordering of the data does not matter. Note 
    * that the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList. In the 
    * case where one list is empty, your method returns a new ArrayList that contains all of the 
    * elements on the other list- with no duplicates. In the case where both lists are empty, your 
    * method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same 
    * as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetDifference(ArrayList<T> listA, ArrayList<T> listB){
      //Write your comments on how you implemented the method.
      /**
      First, I will create a new ArrayList to store all the intersection elements of two initial ArrayList.
      Second, I will run the loop through both listA and listB to check which elements are the different and add it to the new ArrayList.
      Last, we need to use deDuplicate method to remove all duplicated element in the list before returning it.
      **/
      
      //TODO: Implement this method.
      ArrayList<T> newSetdifference = new ArrayList<>();
      
      for (T element : listA){
         if(!listB.contains(element)){
            newSetdifference.add(element);
         }
      }

      for (T element : listB){
         if(!listA.contains(element)){
            newSetdifference.add(element);
         }
      }
      newSetdifference = deDuplicate(newSetdifference);
      return newSetdifference;
   }

}