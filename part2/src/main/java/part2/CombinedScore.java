package part2;

public class CombinedScore 
{
    private final long id;
    private final String content;
    
    public CombinedScore(long id, String content) 
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
