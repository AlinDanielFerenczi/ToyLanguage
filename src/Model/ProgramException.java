package Model;

public class ProgramException extends Exception
{
    public ProgramException() {}

    public ProgramException(String message) {
        System.out.println(message);
    }
}
