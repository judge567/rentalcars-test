package part2;

import java.util.*;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class part2
{
    private final AtomicLong counter = new AtomicLong();

    public List<Vehicle> readFile()
    {
        JSONParser parser = new JSONParser();
        List<Vehicle> vehicleList = new ArrayList();
        
        try 
        {
        
            //parse json file and read into arraylist of vehicle objects
            JSONObject jsonObject =  (JSONObject) 
                                parser.parse(new FileReader("./vehicles.json"));
            
            JSONObject search = (JSONObject) jsonObject.get("Search");
            JSONArray vehicleArray = (JSONArray) search.get("VehicleList");
            
            
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
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return vehicleList;
        }
    }
    
    @RequestMapping("/PriceOrder")
    public PriceOrder priceOrder()
    {
        List<Vehicle> vehicleList = readFile();
        //sort by ascending price order, then iterate over and print            
        Collections.sort(vehicleList, Vehicle.comparatorByPrice);
        String content = "";
        
        for (Vehicle v : vehicleList)
            content = content.concat(v.stringWithPrice() + "\n");
        
        return new PriceOrder(counter.incrementAndGet(), content);        
    }
    
    @RequestMapping("/Spec")
    public Spec spec()
    {
        List<Vehicle> vehicleList = readFile();
        String content = "";
        
        /*specification has already been set when constructing list, so just 
              iterate over and print */
        for (Vehicle v : vehicleList)
            content = content.concat(v.stringWithPrice() + "\n");
            
        return new Spec(counter.incrementAndGet(), content);
    }

    @RequestMapping("/SupplierRating")
    public SupplierRating supplierRating()
    {
        List<Vehicle> vehicleList = readFile();
        String content = "";
        
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
            content = content.concat( v.stringRatingSupplierCarType() + "\n" );
    
        return new SupplierRating(counter.incrementAndGet(), content);
    }

    @RequestMapping("/CombinedScore")
    public CombinedScore combinedScore()
    {
        List<Vehicle> vehicleList = readFile();
        String content = "";
    
        for (Vehicle v : vehicleList)
            v.setScore();
        Collections.sort(vehicleList, Vehicle.comparatorBySumOfScores);
        for (Vehicle v : vehicleList)
            content = content.concat( v.stringWithScores() + "\n");
            
        return new CombinedScore(counter.incrementAndGet(), content);   
        
    }
    
}
