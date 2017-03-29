import java.util.*;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class part1
{
    public static void main(String[] args)
    {
        JSONParser parser = new JSONParser();
        
        try 
        {
        
            //parse json file and read into arraylist of vehicle objects
            JSONObject jsonObject =  (JSONObject) 
                                parser.parse(new FileReader("./vehicles.json"));
            
            JSONObject search = (JSONObject) jsonObject.get("Search");
            JSONArray vehicleArray = (JSONArray) search.get("VehicleList");
            
            List<Vehicle> vehicleList = new ArrayList();
            
            for (Object o : vehicleArray)
            {
                JSONObject JSONVehicle = (JSONObject) o; 
                Vehicle vehicle = new Vehicle();
                
                vehicle.setSpecification((String)JSONVehicle.get("sipp"));
                vehicle.setName( (String)JSONVehicle.get("name") ); 
                vehicle.setPrice( (Double) JSONVehicle.get("price") ); 
                vehicle.setSupplier( (String )JSONVehicle.get("supplier") ); 
                vehicle.setRating((Number)  JSONVehicle.get("rating") );
                
                vehicleList.add(vehicle);
            }
            
            //sort by ascending price order, then iterate over and print            
            Collections.sort(vehicleList, Vehicle.comparatorByPrice);
            for (Vehicle v : vehicleList)
                v.printWithPrice();
            System.out.println();
            
            /*specification has already been set when constructing list, so just 
              iterate over and print */
            for (Vehicle v : vehicleList)
                v.printWithSpec();
            System.out.println();
            
            //store output in new list
            List<Vehicle> highestRatedSupplierByType = new ArrayList();
            for (int t=0; t< 10; t++)
            {
                /*create a sub-list for each type of car, sort by rating, then extract 
                  the first element */
                List<Vehicle> vehicleListOfType = new ArrayList();  
                for (Vehicle v : vehicleList)
                    if (v.getCarType().ordinal() == t)            
                        vehicleListOfType.add(v);
                if (vehicleListOfType.size() > 0)
                {
                    Collections.sort(vehicleListOfType, Vehicle.comparatorByRatingDescending);
                    highestRatedSupplierByType.add(vehicleListOfType.get(0));
                }            
            }
            
            Collections.sort(vehicleList, Vehicle.comparatorByPrice);
            
            for (Vehicle v : highestRatedSupplierByType)
                v.printRatingSupplierCarType();
            System.out.println();
            

            for (Vehicle v : vehicleList)
                v.setScore();
            Collections.sort(vehicleList, Vehicle.comparatorBySumOfScores);
            for (Vehicle v : vehicleList)
                v.printWithScores();

            
            
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
