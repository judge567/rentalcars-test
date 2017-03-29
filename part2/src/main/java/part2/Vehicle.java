package part2;
import java.util.Comparator;

public class Vehicle 
{   
    private String name;
    private double price;
    private String supplier;
    private double rating;
    private String sipp;
    
    public enum CarTypeEnum {MINI, ECONOMY, COMPACT, INTERMEDIATE, STANDARD
        , FULLSIZE, PREMIUM, LUXURY, SPECIAL , UNKNOWN}
    public enum DoorsEnum{TWO_DOORS, FOUR_DOORS, FIVE_DOORS, ESTATE, CONVERTIBLE, SUV, PICKUP, PASSENGER_VAN, UNKNOWN}
    public enum TransmissionEnum { MANUAL, AUTOMATIC, UNKNOWN }
    public enum Fuel_ACEnum { PETROL_NO_AC, PETROL_AC, UNKNOWN }    
    
    private CarTypeEnum carType;
    private  DoorsEnum doors;
    private  TransmissionEnum transmission;
    private  Fuel_ACEnum fuel_ac;

    private int score;

    //constructor
    public Vehicle()
    {

    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPrice(Double price)
    {
        this.price = price.doubleValue();
    }

    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }

    public void setRating(Number rating)
    {
        this.rating = rating.doubleValue();
    }

    public void setSpecification(String sipp)
    {
        this.sipp = sipp;
        char[] sippArray = sipp.toCharArray();
        
        switch(sippArray[0])
        {
            case 'M':
                carType = CarTypeEnum.MINI;
                break;
                
            case 'E':
                carType = CarTypeEnum.ECONOMY;
                break;
            
            case 'C':
                carType = CarTypeEnum.COMPACT;
                break;
            
            case 'I':
                carType = CarTypeEnum.INTERMEDIATE;
                break;
                
            case 'S':
                carType = CarTypeEnum.STANDARD;
                break;
                
            case 'F':
                carType = CarTypeEnum.FULLSIZE;
                break;
                
            case 'P':
                carType = CarTypeEnum.PREMIUM;
                break;
                
            case 'L':
                carType = CarTypeEnum.LUXURY;
                break;
                
            case 'X':
                carType = CarTypeEnum.SPECIAL;
                break;
                
            default:
                carType = CarTypeEnum.UNKNOWN;
                break;
        
        }
    
        switch(sippArray[1])
        {
            case 'B':
                doors = DoorsEnum.TWO_DOORS ;
                break;
        
            case 'C':
                doors = DoorsEnum.FOUR_DOORS ;
                break;
            
            case 'D':
                doors = DoorsEnum.FIVE_DOORS ;
                break;
            
            case 'W':
                doors = DoorsEnum.ESTATE ;
                break;
            
            case 'T':
                doors = DoorsEnum.CONVERTIBLE ;
                break;
                
            case 'F':
                doors = DoorsEnum.SUV ;
                break;
                
            case 'P':
                doors = DoorsEnum.PICKUP ;
                break;
                
            case 'V':
                doors = DoorsEnum.PASSENGER_VAN ;
                break;
                
            default:
                doors = DoorsEnum.UNKNOWN;
                break;
        }
    
        switch(sippArray[2])
        {
            case 'M':
                transmission = TransmissionEnum.MANUAL;
                break;
            
            case 'A':
                transmission = TransmissionEnum.AUTOMATIC;
                break;
                
            default:
                transmission = TransmissionEnum.UNKNOWN;
                break;            
        }
    
        switch(sippArray[3])
        {
            case 'N':
                fuel_ac = Fuel_ACEnum.PETROL_NO_AC;
                break;
                
            case 'R':
                fuel_ac = Fuel_ACEnum.PETROL_AC;
                break;
            
            default:
                fuel_ac = Fuel_ACEnum.UNKNOWN;
                break;
        }
    
    }

    public void setScore()
    {
        score = 0;
        if (transmission == TransmissionEnum.MANUAL)
            score += 1;
        else if  (transmission == TransmissionEnum.AUTOMATIC)
            score += 5;
            
        if (fuel_ac == Fuel_ACEnum.PETROL_AC)
            score += 2;
    }
    
    public CarTypeEnum getCarType()
    {
        return carType;    
    }

    public String stringWithPrice()
    {
        return (name + "\t--  £" + this.price);
    }
    
    public void printWithPrice()
    {
        System.out.println(stringWithPrice());
    }
    
    public String stringWithSpec()
    {
        return (name + "\t- " + carType.toString() + "\t- " 
                     + doors.toString() + "\t- " + transmission.toString() 
                     + "\t- " + fuel_ac.toString());
    }
    
    public void printWithSpec()
    {
        System.out.println(stringWithSpec());
    } 
       
    public String stringRatingSupplierCarType()
    {
        return ( name + "\t- " + carType.toString() + "\t- " + supplier + "\t- " 
               + rating);
    }
       
    public void printRatingSupplierCarType()
    {
        System.out.println(stringRatingSupplierCarType());
    }   
    
    public String stringWithScores()
    {
        double sum = score + rating;
        return ( name + "\t- " + score + "\t- " + rating + "\t- " + sum );
    }
        
    public void printWithScores()
    {
        System.out.println(stringWithScores());
    }
       
    public static Comparator<Vehicle> comparatorByPrice = new Comparator<Vehicle>()
    {
        public int compare(Vehicle v1, Vehicle v2)
        {
            return v1.price < v2.price ? -1
                 : v1.price > v2.price ?  1
                 : 0;      
        }
    };
    
    public static Comparator<Vehicle> comparatorByRatingDescending = new Comparator<Vehicle>()
    {
        public int compare(Vehicle v1, Vehicle v2)
        {
            return v1.rating > v2.rating ? -1
                 : v1.rating < v2.rating ?  1
                 : 0;      
        }
    };
    
    public static Comparator<Vehicle> comparatorBySumOfScores = new Comparator<Vehicle>()
    {
        public int compare(Vehicle v1, Vehicle v2)
        {
            return (v1.score + v1.rating) > (v2.score + v2.rating) ? -1
                 : (v1.score + v1.rating) < (v2.score + v2.rating) ?  1
                 : 0 ;
        }
    };
}

