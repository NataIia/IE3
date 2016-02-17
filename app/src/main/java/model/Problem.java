package model;

/**
 * Created by surfing on 2/16/2016.
 */
public class Problem {
    private String id;
    private String problemId;
    private String problemTitle;
    private String problemDescription;
    private String problemSolutionNotes;
    private Boolean problemSolved;


    public Problem() {}

    public Problem(String id, String problemId, String problemTitle, String problemDescription) {
        this.id = id;
        this.problemId = problemId;
        this.problemTitle = problemTitle;
        this.problemDescription = problemDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getProblemSolutionNotes() {
        return problemSolutionNotes;
    }

    public void setProblemSolutionNotes(String problemSolutionNotes) {
        this.problemSolutionNotes = problemSolutionNotes;
    }

    public Boolean isProblemSolved() {
        return problemSolved;
    }

    public void setProblemSolved(Boolean problemSolved) {
        this.problemSolved = problemSolved;
    }
}
