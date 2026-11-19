package application;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     * A book can be added to and removed from a recommendation.
     */
    public void testRemoveBook()
    {
        Recommendation recommendation = new Recommendation( "Test audience" );
        Book book = new Book( "Title", "Author", "Genre", 2000 );

        recommendation.addBook( book );
        assertTrue( recommendation.getBooks().contains( book ) );

        assertTrue( recommendation.removeBook( book ) );
        assertFalse( recommendation.getBooks().contains( book ) );

        // Removing the same book twice returns false.
        assertFalse( recommendation.removeBook( book ) );
    }
}
