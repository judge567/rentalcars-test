package part2;

public class PriceOrder 
{
    private final long id;
    private final String content;
    
    public PriceOrder(long id, String content) 
    {
        this.id = id;
        this.content = content;
    }
    
    public long getId()
    {
        return id;
    }
    
    public String getContent()
    {
        return content;
    }

}
