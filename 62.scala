import scala.collection.mutable;
import scala.collection.mutable.ArrayBuffer;

/**
* @author Dibyo Mukherjee
*/
object Solution {
   def main(args: Array[String]) { 
    val t0 = System.nanoTime();
    var cubes:ArrayBuffer[Long] = computeCubePermutations();        
    val t1 = System.nanoTime();
    println("The smallest cube for which five permutations of its digits are cube is " + cubes(0) );
    println("The cubes are: " + cubes.toString());
    println("Time taken: " + (t1-t0) + "ns" );
    assert(cubes.size == 5);
    assert(cubes(0) == 127035954683l);
   }

   def computeCubePermutations() : ArrayBuffer[Long] = {
   	  var map = mutable.HashMap.empty[String, ArrayBuffer[Long]];
      var i = 0l;
      var found = false;
      while( !found ){
         var cubed = i*i*i; //Cube the number
         var orderedNumber = digitsInAscOrder(cubed); //Return digits in ascending order;
         
         //If the digits are not already present, add it as a new entry to the map
         if (!map.contains(orderedNumber))
         {
         	val b = new ArrayBuffer[Long]();
         	b += cubed;
         	map += (orderedNumber -> b);
         }
         else //If it already exists, add the counter to the value.
         {
         	var cubesBuffer:ArrayBuffer[Long] = map.get(orderedNumber).get;
         	cubesBuffer += cubed;

         	//If the size is 5, we have found the answer
         	if(cubesBuffer.size == 5)
         	{
         		found = true;
         		return cubesBuffer;
         	}
         }
         i +=1; //Increment counter
      }
      return null;
   }  
   
   def digitsInAscOrder(number:BigInt) : String = {
   	return number.toString.map(_.toString.toLong).sorted.toString;
   }

}

