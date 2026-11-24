package model;

import assessments.AssessmentFeed;

public abstract class Student {
    protected String name;
    protected String cid;
    protected String type;
    protected double IPTProgMark;
    protected double AIPTFuncMark;
    protected boolean IPTExplainedSuccess;
    protected double AIPTVerificationMark;

    public Student(String n, String c) {
        assert n != null;
        assert c != null;
        this.name = n;
        this.cid = c;
        this.AIPTFuncMark = AssessmentFeed.getResult(cid, AssessmentFeed.AI_PROJECT, AssessmentFeed.FUNCTIONALITY_MARK);
        this.IPTProgMark = AssessmentFeed.getResult(cid, AssessmentFeed.PROGRAMMING_TEST, AssessmentFeed.PROGRAM_MARK);
        this.AIPTVerificationMark = AssessmentFeed.getResult(cid, AssessmentFeed.AI_PROJECT, AssessmentFeed.VERIFICATION_MARK);

        if (AssessmentFeed.getResult(cid, AssessmentFeed.PROGRAMMING_TEST, AssessmentFeed.EXPLAINED_SUCCESSFULLY) == 0) {
            this.IPTExplainedSuccess = false;
        } else {
            this.IPTExplainedSuccess = true;
        }

    }

    public double getAIPTFuncMark() {
        return AIPTFuncMark;
    }

    public double getIPTProgMark() {
        return IPTProgMark;
    }

    public double getAIPTVerificationMark() {
        return AIPTVerificationMark;
    }

    public boolean isIPTExplainedSuccess() {
        return IPTExplainedSuccess;
    }

    public String getName() {
        return name;
    }

    public String getCid() {
        return cid;
    }

    public String getType() {
        return type;
    }
}
