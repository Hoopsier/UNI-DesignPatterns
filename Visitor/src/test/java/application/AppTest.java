package application;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for the visitor pattern implementation.
 */
public class AppTest 
    extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * accept() must dispatch Files to visitFile().
     */
    public void testFileAcceptsVisitor()
    {
        RecordingVisitor visitor = new RecordingVisitor();
        new File("notes", ".txt", 10).accept(visitor);

        assertEquals(1, visitor.visited.size());
        assertEquals("file:notes", visitor.visited.get(0));
    }

    /**
     * accept() must dispatch Directories to visitDirectory().
     */
    public void testDirectoryAcceptsVisitor()
    {
        RecordingVisitor visitor = new RecordingVisitor();
        new Directory("home").accept(visitor);

        assertEquals(1, visitor.visited.size());
        assertEquals("directory:home", visitor.visited.get(0));
    }

    /**
     * The search visitor must recurse through the tree using accept().
     */
    public void testSearchVisitorFindsNestedDirectory()
    {
        FileSystemElement etc = new Directory("etc");
        FileSystemElement[] inHome = { new File("Hoopsy", ".me", 1000) };
        FileSystemElement[] inRoot = { new Directory("home", inHome), etc };
        Directory root = new Directory("/", inRoot);

        SearchVisitor visitor = new SearchVisitor("etc");
        root.accept(visitor);

        assertEquals(1, visitor.getResults().size());
        assertSame(etc, visitor.getResults().get(0));
    }

    /**
     * A file matching by both name and extension must only be returned once.
     */
    public void testSearchVisitorDoesNotReturnDuplicateMatches()
    {
        FileSystemElement[] contents = { new File("notes", "notes", 10) };
        Directory root = new Directory("/", contents);

        SearchVisitor visitor = new SearchVisitor("notes");
        root.accept(visitor);

        assertEquals(1, visitor.getResults().size());
    }

    /**
     * The size visitor must sum the sizes of files at every depth.
     */
    public void testSizeCalculatorVisitorSumsNestedFiles()
    {
        FileSystemElement[] inHome = { new File("Hoopsy", ".me", 1000) };
        FileSystemElement[] inRoot = {
            new Directory("home", inHome),
            new File("readme", ".md", 250)
        };
        Directory root = new Directory("/", inRoot);

        SizeCalculatorVisitor visitor = new SizeCalculatorVisitor();
        root.accept(visitor);

        assertEquals(1250, visitor.getTotalSize());
    }

    private static class RecordingVisitor implements FileSystemVisitor
    {
        private List<String> visited = new ArrayList<>();

        public void visitFile( File element )
        {
            visited.add( "file:" + element.getName() );
        }

        public void visitDirectory( Directory element )
        {
            visited.add( "directory:" + element.getName() );
        }
    }
}
