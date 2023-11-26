package com.example.reviewercompanion;

public class DatabaseVariableCategory {

    // create a for loop method

    public final String[] Category = {
            "AFAR",
            "Auditing",
            "FAR",
            "MS",
            "Law",
            "Tax"
    };


    public final String[][] questions = {
            VariableAFAR.Questions(),
            VariableAuditing.Questions(),
            VariableFAR.Questions(),
            VariableMS.Questions(),
            VariablesLaw.Questions(),
            VariableTax.Questions(),
    };

    public final String[][] ChoiceA = {
            VariableAFAR.ChoiceA(),
            VariableAuditing.ChoiceA(),
            VariableFAR.ChoiceA(),
            VariableMS.ChoiceA(),
            VariablesLaw.ChoiceA(),
            VariableTax.ChoiceA()
    };
    public final String[][] ChoiceB = {
            VariableAFAR.ChoiceB(),
            VariableAuditing.ChoiceB(),
            VariableFAR.ChoiceB(),
            VariableMS.ChoiceB(),
            VariablesLaw.ChoiceB(),
            VariableTax.ChoiceB()
    };
    public final String[][] ChoiceC = {
            VariableAFAR.ChoiceC(),
            VariableAuditing.ChoiceC(),
            VariableFAR.ChoiceC(),
            VariableMS.ChoiceC(),
            VariablesLaw.ChoiceC(),
            VariableTax.ChoiceC()
    };
    public final String[][] ChoiceD = {
            VariableAFAR.ChoiceD(),
            VariableAuditing.ChoiceD(),
            VariableFAR.ChoiceD(),
            VariableMS.ChoiceD(),
            VariablesLaw.ChoiceD(),
            VariableTax.ChoiceD()
    };
    public final String[][] CorrectAns = {
            VariableAFAR.Answers(),
            VariableAuditing.Answers(),
            VariableFAR.Answers(),
            VariableMS.Answers(),
            VariablesLaw.Answers(),
            VariableTax.Answers()
    };
//
//    // Access questions by category index
//    String[] afarQuestions = questions[0];
//    String[] msQuestions = questions[1];
//    String[] farQuestions = questions[2];
//    String[] auditingQuestions = questions[3];
//    String[] lawQuestions = questions[4];
//    String[] taxQuestions = questions[5];
}
