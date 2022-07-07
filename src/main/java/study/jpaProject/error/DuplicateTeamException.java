package study.jpaProject.error;

public class DuplicateTeamException extends RuntimeException{
    public DuplicateTeamException(String message){ super(message); }
    public DuplicateTeamException(String message, Throwable cause) { super(message, cause); }
}
