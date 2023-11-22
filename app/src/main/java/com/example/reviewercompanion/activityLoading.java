package com.example.reviewercompanion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activityLoading extends AppCompatActivity {
    private int progressStatus = 0;
    private final Handler handler = new Handler();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar = findViewById(R.id.progressBar);

        InsertInitialQuestions();
    }

    /* TODO :baguhin to into the length of the question being added and then if done na mag kakaroon ng
        if statement na mag loloading na lang
    */
    @SuppressLint("StaticFieldLeak")
    public void InsertInitialQuestions() {
        runOnUiThread(() -> {
            Toast.makeText(activityLoading.this, "Process On Going...", Toast.LENGTH_SHORT).show();
        });
        DatabaseQuestions myDB = new DatabaseQuestions(this);
        ProgressBar progressBar = findViewById(R.id.progressBar);

            progressBar.setVisibility(View.VISIBLE); // Show the progress bar

            // Use AsyncTask to perform database operations in the background
            new AsyncTask<Void, Integer, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    int totalQuestions = questionTexts.length; // Assuming questionTexts contains your questions
                    for (int i = 0; i < totalQuestions; i++) {
                        // Insert the question into the database here
                        // For illustration purposes, I'm using a sleep to simulate the insertion
                        try {
                            Thread.sleep(100); // Adjust delay time as needed
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Update progress based on the inserted question count
                        publishProgress((i + 1) * 100 / totalQuestions);
                    }
                    return null;
                }
                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    progressBar.setProgress(values[0]); // Update progress bar
                }
                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    // Database insertion completed
                    progressBar.setVisibility(View.GONE); // Hide progress bar
                    Intent intent = new Intent(activityLoading.this, activityHomeScreen.class);
                    startActivity(intent);
                }
            }.execute();
    }

    String[] questionTexts = {
            "D borrowed P50,000.00 from C. The obligation is payable in full after 30 days. In which of the following cases is D justified in making consignation of his payment in court?",
            "Which of the following condonations will not extinguish the debtor’s obligation?",
            "In which of the following independent cases is the payor of the debtor’s debt not subrogated to the rights of the creditor?",
            "S sold his only horse to B for P10, 000.00 cash. The parties agreed that S shall deliver the horse within one week from their agreement. Nothing is mentioned in the agreement on how the horse will be cared for by S before delivery",
            "D owes C the following debts: P3, 000.00 due on August 10; P3, 000.00 due on August 15; P3, 000.00 due on August 20; and P3, 000.00 due on August 25 which is secured by a pledge of D’s ring. If today is August 22, and D pays C P3,000.00 with neither D nor C designating the debt to which payment shall apply, the payment shall be applied:",
            "M executed a promissory note payable to P for P100,000.00. The note, which bears interest at 2% per month, is payable after 60 days. On the date of maturity, P proceeded to M’s place to collect but when M demanded the presentation of the promissory note, P could not present it claiming that it had been lost. M is able and willing to pay the whole amount due including the interest but he is at loss on what to do because P does not have the instrument. On the other hand, if he does not pay the amount due, the interest on the principal will continue to accrue. If you were M, the remedy that you will likely avail yourself of is:",
            "A, B, C and D owe X, Y and Z the sum of P12, 000.00. Based on the foregoing data, which of the following statements is incorrect?",
            "P took a public bus in going to his office. Although P paid fare, the bus conductor did not issue to him a ticket. Along the way, the bust met an accident causing a slight injury to p and other passengers. If P is to recover damages from the bus owner, the source of the bus owner’s liability is:",
            "One of the following obligations is void. Which is it?",
            "Which of the following obligations is a pure obligation and is demandable at once?",
            "On January 1, 2014, D obtained a loan of P100,000.00 from C. The loan is secured by a chattel mortgage on D’s car and is payable on December 31, 2014. On September 26, 2014, the car was taken at gunpoint from D while he was starting its engine at the parking lot of a department store.",
            "A and B are the owners of adjacent poultry and piggery farms. One day, B got sick and failed to visit his farm. When A noticed that B was not again around during the second day, he himself took care of the animals by feedng them and cleaning the pig and poultry pens. A did this for the next three days until B returned. A incurred necessary and useful expenses amounting to P50,000.00 in the process. Under the circumstances, B is obliged to reimburse A for such expenses which the latter incurred by reason of:",
            "D has a savings deposit with XYZ Bank in the amount of P20,000.00 which D may withdraw anytime from the bank. He also has a loan obligation to XYZ bank amounting to P20,000.00 which has become due. D wants to withdraw his savings deposit but XYZ Bank informs D that it has claimed compensation of D’s deposit and his loan obligation",
            "Which of the following is an divisible obligation?",
            "Panotes obtained a loan of P50,000.00 from Pedir who is engaged in the business of financing. The written contract of the parties provides that the loan shall bear interest of 12% per annum and shall be paid in full together with the interest at the end of 12 months at Pedir’s business office. On due date, Panotes proceeded to Pedir’s business office to pay his debt but the place was padlocked and showed no signs that it had been occupied for some time. Panotes is now at a loss on what to do as Pedir did not forward his present address to him. Panotes does not want to have any outstanding obligation at the end of the year and incur further interest. Which course of action will you recommend to Panotes?",
            "D borrowed P100,000.00 from C. The parties agreed at the time the obligation was constituted that should D so desire, he may give his agricultural land to C by way of dacion en pago to pay his loan obligation on due date. The obligation of D to C is:",
            "One of the distinctions between a facultative obligation and an alternative obligation is that in an alternative obligation:",
            "D stole the carabao of C. D was arrested, tried in court and convicted. Aside from being sentenced to a prison term, D was also ordered by the court to return the carabao. However, the carabao died before D could deliver it to C",
            "Under a contract between D and C, D is obliged to deliver 10 bags of detergent soap to C 10 days after the execution of their agreement. On due date, D delivered to C 10 bags of detergent soap which he mixed with chalk",
            "D obtained a loan from C in the amount of P50,000.00. unable to give cash on due date in payment of his loan obligation, D proposed to C that he would be giving instead his diamond ring to settle his debt. C agreed and accepted the ring from D. the new agreement between D and C involved both:",
            "Rubi and Sabularce entered into a contract whereby Rubi would deliver 5 pieces of genuine Rolex wristwatches to Sabularce. Rubi proposed to Sabularce that should Rubi deliver 5 units of fake pieces of Rolex wristwatches by reason of financial difficulties on his part, Sabularce would not sue him for damages on the ground of fraud. Sabularce accepted the proposal. On due date, Rubi delivered 5 pieces of fake Rolex wristwatches. Upon discovery of the fraud, Sabularce sues Rubi for damages. Rubi contends that he cannot held liable for damages because Sabularce waived his right to hold him (Rubi) liable on the ground of fraud if the reason thereof is the financial of Rubi.",
            "B ordered 10 boxes of “X” shirts worth P2,000.00 per box from S paying immediately the total price of P20,000.00. While inspecting the goods after their delivery to his store, B discovered that one box was missing. Upon being informed, S apologized for the error and promised to refund the price of P2,000.00 within three days as there was no more stock of “X” shirts available. In the meantime, B ordered and received from S one box of “Y” shirts which was also worth P2,000.00 promising to pay the same, within three days. On the third day, assuming that S had not yet refunded the price of the undelivered “X” shirts to B, b need not pay S the price of “Y” shirts by claiming:",
            "The following are obligations with a term or period, except:",
            "A, B and C are liable in solidum to X for P12,000.00. X renounced the share of A who accepted it. Later, B becomes insolvent.",
            "Ortiz Realty Company (Ortiz) ordered 6 units of “Borden” typewriters from Villaester Office Machines (Villaester) at the price of P8,000.00 per unit. However, central delivered to Ortiz 6 units of “Remington” typewriters, a superior brand which was priced at P8,500.00 per unit. Villaester informed Ortiz that it will bill the latter for the “Remington” typewriters at P8,000.00 only. Ortiz refused to accept the “Remington” typewriters.",
            "Prime Engineering Review center (PERC) stated in the leaflets it distributed last January that any reviewee who places first in the licensure examinations for engineers this year will receive a cash prize of P150,000.00.",
            "Three of the following statements pertain to natural obligation. Which does not?",
            "D has a grains warehouse in Davao, while c has a grains warehouse in Cebu. D borrowed 10 sacks of rice worth P10,000.00 from C for D’s customer in Cebu. Later, C borrowed 10 sacks of rice which was also worth P10,000.00 from D for C’s customer in Davao. Both the obligations are already due. Transport costs to Davao amount to P1,000.00, while those for Cebu amount to P800.00.",
            "D owes C P20,000.00 due on March 25. C, on the other hand, owes D the following debts: P8,000.00 due on March 1, P3,000.00 due on March 8, P5,000.00 due on March 14, and P2,000.00 due on March 24. On March 18, C assigned his credit right to T without performing D who learned of the assignment on March 20. On March 25, T may collect from D:",
            "D owes C P20,000.00 due on March 15. C, on the other hand, owes D the following debts: P8,000.00 due on March 1, P3,000.00 due on March 8, P5,000.00 due on March 14. On March 12, assigned his right to T with notice to D but with D not giving his consent to the assignment. On March 15, T can collect from D:",
            "On December 1, 2014, Miss Asutilla, a professor of Manila College, engaged the services of Rato Transport to bring her class to Calamba, Laguna in time for the Rizal Day celebration on December 30, 2014 which would start at nine o’clock in the morning. The contract signed by the parties specified that a bus would be in the school premises at six o’clock in the morning and would leave at exactly 6:30 a.m. However, Rato Transport failed to send a bus on the date, time and place agreed upon. As a result, Miss Asutilla and her class failed to attend the celebration. Ms. Asutilla sued Rato Transport for damages on the ground of delay in the performance of its obligation. For its defense, Rato Transport claimed there was no delay because Miss Asutilla never made a demand",
            "Vargas, the owner of an apartment, leased the premises to Rigon. The terms of the lease provide for a monthly rental of P6,000.00 which Rigon must pay at the residence of Vargas about two blocks from the apartment, For the month of December, Rigon went to the residence of Vargas but the latter was not around to receive the payment. Not wanting to go back, Rigon left the payment with Pajares, a neighbor of Vargas, who promised to give the payment to Vargas. However, Pajares spent the amount he received for himself. Based on the forgoing facts, which of the following statements is incorrect?",
            "D owes the following creditors. X, P20,000.00; Y P30,000.00; and Z, P50,000.00. D is insolvent so he offers to assign all his properties (except those excempt from execution) to his creditors in payment of his debts. The creditors accept the offer and are able to sell the debtor’s properties for P70,000.00. Based on the foregoing facts, which of the following statements is incorrect?",
            "On May 31, 2014, D promised to give a specific house and lot to C if C passes the Bar Examination. On September 24, 2014, C took the Bar Examination. The result of the Bar Examination which C took was released on march 26, 2015 and C passed it. C shall be entitled to the house and lot:",
            "A and B are indebted to X and Y for P10,000.00. A and B share in the debt in the ratio of 1:3; while X and Y share in the credit in the ratio of 2:3. How much may X collect from A if the debtors are joint debtors, while the creditors are joint creditors?",
            "A and B are indebted to X and Y for P10,000.00. A and B share in the debt in the ratio of 1:3; while X and Y share in the credit in the ratio of 2:3. How much may X collect from A if there is active solidarity?",
            "A and B are indebted to X and Y for P10,000.00. A and B share in the debt in the ratio of 1:3; while X and Y share in the credit in the ratio of 2:3. How much may X collect from A if there is passive solidarity?",
            "A, B and C are solidary debtors of X, Y and Z. Solidary creditors, in the amount of P2,700.00. X renounces the whole obligation without the consent of Y and Z. The debtors accepted the renunciation",
            "A, B and C are solidary debtors of X in the amount of P3,000.00. X renounces the share of A and A accepts the renunciation. Thereafter, B becomes insolvent. Ultimately:",
            "A, B and C are obliged to deliver a specific horse to X, Y and Z",
            "O was cleaning the glass window of his building when a large piece of broken glass fell down directly hitting the roof of a car which was parked below. C, the owner of the car, was not around. Sensing that he would be made liable for the damage on the car, O immediately went down and cleaned the mess. O, seeing that W had witnessed all that happened, proposed to give W P2,000.00 so that W would not testify in case a court case is filed. W agreed to the proposal and accepted the money. Based on the foregoing facts, which of the following statements is incorrect?",
            "A, B and C are solidary debtors of X and y for P30,000.00, joint creditors",
            "S, a supplier of fresh fish from Lucena City, hired T, the owner of a trucking company, for a fee of P3,000.00 to bring the fish catch of S to the Dampa Market in Parañaque City which ordered the fish for a price of P20,000.00. In so far as S is concerned, his prestation in his contract with T is:",
            "Refer to the preceding number. In the contract between S and T:",
            "D obtained a loan of P200,000.00 from C. Not having  any cash on due date when C visited him to demand payment, D offered to C to accept, at C’s choice, either D’s necklace, diamond ring, or a parcel of land, all of which D showed to C. C accepted the proposal, but requested that he be given one week to decide which item to take. Before the week was over, however, armed men forcibly took the necklace and diamond from D’s house",
            "The following are characteristics of a facultative obligation, except:",
            "D owes C the following debts: P5,000.00 due on January 1; P7,000.00 on January 5; P8,000.00 due on January 10; P10,000.00 sue on January 15; and P5,000.00 due on January 20. By agreement of the parties, D was given the benefit of the period. As of January 17, D has not paid any of the debts. He has P5,000.00 which he wants to remit to C",
            "Refer to the preceding number. Assume that D did not designate the debt to which the payment shall apply. In this case:",
            "Refer to No. 147. Assume the creditor did not also designate the debt to which the payment shall apply. In such a case:",
            "Dacion en pago and payment by cession are special forms of payment. They are similar in which of the following espects?",

    };


}