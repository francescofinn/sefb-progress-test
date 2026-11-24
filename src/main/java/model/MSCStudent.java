package model;

import assessments.AssessmentFeed;

public class MSCStudent extends Student {
    private double veevaDesingMark;
    private double veevaExplanationMark;

    public MSCStudent(String n, String c) {
        super(n, c);
        this.type = "MSc";
        this.veevaDesingMark = AssessmentFeed.getResult(cid, AssessmentFeed.DESIGN_VIVA, AssessmentFeed.DESIGN_MARK);
        this.veevaExplanationMark = AssessmentFeed.getResult(cid, AssessmentFeed.DESIGN_VIVA, AssessmentFeed.EXPLANATION_MARK);
    }

    public double getVeevaDesingMark() {
        return veevaDesingMark;
    }

    public double getVeevaExplanationMark() {
        return veevaExplanationMark;
    }
}
