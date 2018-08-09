package lb.edu.aub.cmps253.yzo01.ABO;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TipsTextActivity extends AppCompatActivity {

    @Bind(R.id.tipText) TextView tipTextXml;
    Map<String, String> tipstextMap=new HashMap<String, String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateMap();
        String tipName="default";
        if(getIntent().hasExtra("Tip")) {
            tipName= getIntent().getStringExtra("Tip");
        }

        setContentView(R.layout.activity_tipstext);
        ButterKnife.bind(this);
        String tipText=tipstextMap.get(tipName);
        tipTextXml.setText(tipText);
    }

    public void populateMap(){
        tipstextMap.put("Who can Donate","Basic Eligibility Guidelines\n" +
                "Age: You must be at least 17 years old to donate to the general blood supply. There is no upper age limit for blood donation as long as you are well with no restrictions or limitations to your activities.\n" +
                "\n" +
                "High Blood Pressure: Acceptable as long as your blood pressure is below 180 systolic (first number) and below 100 diastolic (second number) at the time of donation. Medications for high blood pressure do not disqualify you from donating.\n" +
                "\n" +
                "Body Piercing: You must not donate if you have had a tongue, nose, belly button or genital piercing in the past 12 months (donors with pierced ears are eligible).\n" +
                "\n" +
                "Cold and Flu: Wait if you have a fever or a productive cough (bringing up phlegm). Wait if you do not feel well on the day of donation. Wait until you have completed antibiotic treatment for sinus, throat or lung infection.\n" +
                "\n" +
                "Diabetes: Acceptable as long as it is well controlled, whether medication is taken or not.\n" +
                "\n" +
                "Diet: A meal is recommended at least four hours prior to donation. Drink plenty of fluids.\n" +
                "\n" +
                "MSM: Men who have had sex with other men, at any time since 1977 (the beginning of the AIDS epidemic in the United States) are currently deferred as blood donors. This is because MSM are, as a group, at increased risk for HIV, hepatitis B and certain other infections that can be transmitted by transfusion.\n" +
                "\n" +
                "Tattoos: One-year deferral.\n" +
                "\n" +
                "Travel: Please refer to Centers for Disease Control and Prevention. If you need further assistance please call UCSF Blood Center (415) 353-1809.\n" +
                "\n" +
                "Weight: You must weigh at least 110 pounds to be eligible for blood donation for your own safety. Blood volume is in proportion to body weight. Donors who weigh less than 110 pounds may not tolerate the removal of the required volume of blood as well as those who weigh more than 110 pounds. There is no upper weight limit as long as your weight is not higher than the weight limit of the donor bed or lounge you are using. You can discuss any upper weight limitations of beds and lounges with your local health historian.\n" +
                "\n" +
                "Other criteria that will be assessed at the time of donation such as (list is not all inclusive):\n" +
                "\n" +
                "Hemoglobin, Travel, Cancer, Medications, Hepatitis, and HIV Risk:\n" +
                "\n" +
                "Intravenous drug abusers: HIV, HBV, HCV and HTLV\n" +
                "Transplant patients: animal tissue or organs\n" +
                "People who have recently traveled to or lived abroad in certain countries may be excluded because they are at risk for transmitting agents such as malaria or variant Creutzfeldt-Jakob Disease (vCJD).\n" +
                " \n" +
                "\n" +
                "Reviewed by health care specialists at UCSF Medical Center.");
        tipstextMap.put("Before You Donate","Before You Donate\nMaintain a healthy iron level in your diet by eating iron rich foods, such as red meat, fish, poultry, beans, spinach, iron-fortified cereals and raisins.\n" +
                "Get a good night's sleep.\n" +
                "Drink an extra 16 oz. of water or nonalcoholic fluids before the donation.\n" +
                "Eat a healthy meal before your donation. Avoid fatty foods, such as hamburgers, fries or ice cream before donating. (Fatty foods can affect the tests we do on your blood. If there is too much fat in your blood, your donation cannot be tested for infectious diseases and the blood will not be used for transfusion.)\n" +
                "If you are a platelet donor, remember that your system must be free of aspirin for two days prior to donation.\n" +
                "Remember to bring your donor card, driver's license or two other forms of ID.");
        tipstextMap.put("During Donation","During Donation\nWear clothing with sleeves that can be raised above the elbow.\n" +
                "Let the person taking your blood know if you have a preferred arm and show them any good veins that have been used successfully in the past to draw blood.\n" +
                "Relax, listen to music, talk to other donors or read during the donation process.\n" +
                "Take the time to enjoy a snack and a drink in the refreshments area immediately after donating.");
        tipstextMap.put("After Donation","After Donation\nDrink an extra four (8 ounce) glasses of liquids and avoid alcohol over the next 24 hours.\n" +
                "Remove the wrap bandage (if you had one put on your arm) within the next hour.\n" +
                "Keep the strip bandage on for the next several hours.\n" +
                "To avoid a skin rash, clean the area around the strip bandage with soap and water.\n" +
                "Do not do any heavy lifting or vigorous exercise for the rest of the day.\n" +
                "If the needle site starts to bleed, apply pressure to it and raise your arm straight up for about 5-10 minutes or until bleeding stops.\n" +
                "If you experience dizziness or lightheadedness after donation, stop what you are doing and sit down or lie down until you feel better. Avoid performing any activity where fainting may lead to injury for at least 24 hours.\n" +
                "Call us at 1-866-236-3276 to report any additional health information that you forgot to tell us, if you have any problems or if you needed medical care after giving blood.");
        tipstextMap.put("Health Status Before Donation","Allergy, Stuffy Nose, Itchy Eyes, Dry Cough\n" +
                "\n" +
                "Acceptable as long as you feel well, have no fever, and have no problems breathing through your mouth.\n" +
                "Cold, Flu\n" +
                "\n" +
                "Wait if you have a fever or a productive cough (bringing up phlegm)\n" +
                "Wait if you do not feel well on the day of donation.\n" +
                "Wait until you have completed antibiotic treatment for sinus, throat or lung infection.\n" +
                "Donation Intervals\n" +
                "\n" +
                "Wait at least 8 weeks between whole blood (standard) donations.\n" +
                "Wait at least 7 days between platelet (pheresis) donations.\n" +
                "Wait at least 16 weeks between Power Red (automated) donations.\n" +
                "Weight/Height\n" +
                "\n" +
                "You must weigh at least 110 lbs to be eligible for blood donation for your own safety. Students who donate at high school drives and donors 18 years of age or younger must also meet additional height and weight requirements for whole blood donation (applies to girls shorter than 5'6\" and boys shorter than 5').\n" +
                "\n" +
                "Blood volume is determined by body weight and height. Individuals with low blood volumes may not tolerate the removal of the required volume of blood given with whole blood donation. There is no upper weight limit as long as your weight is not higher than the weight limit of the donor bed/lounge you are using. You can discuss any upper weight limitations of beds and lounges with your local health historian.\n" +
                "\n" +
                "Unable to Give Blood?\n" +
                "You can help people facing emergencies by making a financial donation to support the Red Cross’s greatest needs. Your gift enables the Red Cross to ensure an ongoing blood supply, provide humanitarian support to families in need and prepare communities by teaching lifesaving skills. Make a financial gift today.");
        tipstextMap.put("Donation Myths","Blood Myths\n" +
                "There are many common excuses for not giving blood, and also many misconceptions about eligibility requirements. Here are a few myths dispelled:\n" +
                "I'm nervous about giving blood.\n" +
                "Most first-time donors are! However, after donating most also wonder why they were ever nervous. Once you see how simple it is and know the value of what you have given, you will probably want to give again.\n" +
                "Giving blood is inconvenient.\n" +
                "From the time you sign in, the whole donation process takes under an hour. Your actual donation time will be less than ten minutes. With donor centers located throughout the Southern Louisiana and Mississippi Gulf Coast area, and blood drives held nearly every day of the year, our highly-trained staff works hard to make the process as convenient as possible for you.\n" +
                "I might get a disease from donating blood.\n" +
                "All of our equipment is brand new, state-of-the-art, disposable and used only one time. The only thing you can get from donating blood is the good feeling knowing you've helped others.\n" +
                "They don't really need my type.\n" +
                "Every type is the right type. All types, including common ones like O-positive and A-positive, are needed by patients all the time. The only wrong type is the type we do not have.\n" +
                "I'm too young or too old.\n" +
                "You can donate blood as long as you are at least 16 years old. And you are never too old as long as you are healthy. There is absolutely no upper age limit on donating blood.\n" +
                "Other people must be giving enough blood.\n" +
                "Actually, less than 5% of the eligible center actually donates blood. Yet the demand for blood and blood components is constant. The needs of patients can only be met by people like you.\n" +
                "I heard that I can’t donate blood because…\n" +
                "…I have diabetes.\n" +
                "As long as your diabetes is under control and stable, you can donate!\n" +
                "…I just got my ears pierced.\n" +
                "If your ears were pierced with a sterile, single-use kit, there is no wait!\n" +
                "…I have a heart condition.\n" +
                "As long as you are not on any heart medication (other than aspirin) and there is no underlying damage, you can donate!\n" +
                "…I am taking medications for mitral valve prolapse.\n" +
                "You may donate, as long as your doctor has not put any restrictions on your activity.\n" +
                "…I take medication for high blood pressure.\n" +
                "You may donate as long as your blood pressure is controlled and stable.\n" +
                "…I had a stroke.\n" +
                "As long as it has been a year since your stroke, are not on any medications for it and you have no activity restrictions, you can donate!\n" +
                "…I just got a tattoo.\n" +
                "As long as your tattoo was done at a licensed shop in Louisiana, Texas, Alabama or Mississippi, you only have to wait one month!");
        tipstextMap.put("Donations Stories","\n" +
                "Thank You Donor\n" +
                "by Laura \n" +
                "I wish I could tell you my donor how grateful I am for your selfless act. We may be coworkers or schoolmates or just two in the same community. You may have wondered what became of your donation once you left the donation site. For this lucky recipient it gave me the strength to go back to work and school after fusion surgery in my cervical spine. Because you took 20 minutes out of your day I am well again. Thank you for sharing such a vital part of you.\n"+
        "Life is in the Blood\n" +
                "by James \n" +
                "At the doctor's office we were trying to figure out why I landed on the flood out for a few minutes earlier this morning. An hour later I was told that I had lost half of my blood. This was amazing because it didn't feel like I was shutting down.\n" +
                "\n" +
                "Outside, waiting for Deb to pick me up, I wondered what was going to happen at the hospital. No ER, just straight to a room on St. Joe's 3rd floor. \n" +
                "\n" +
                "It seems that an appointment was made for a duodenal bleeding ulcer. UGH. There were mild pains in the gut, but I thought I just needed to eat better. \n" +
                "\n" +
                "Lying down, my nurse asked me to get up. I remember two things, standing, then waking up with doctors and nurses yelling at each other over me back in bed. Fallen to the floor, my nurse and wife had called for help! Deb was in a slight shock to see the room fill up so fast.\n" +
                "\n" +
                "Where is his blood?! It should be here already! I'm O+. For some reason it was difficult to get three units. \n" +
                "\n" +
                "There it was. Drip. Drip. Drip. What a strange sensation. All had quieted down but my thoughts were pondering my life. Had I truly been bleeding so badly? My life was slowly dripping back to health. The life, my life, is in this blood. I hardly thought I slowly dying just minutes ago. \n" +
                "\n" +
                "It was a great thing to donate blood over the years, however now it was this man's turn to be on the receiving end. \n" +
                "\n" +
                "Tomorrow they patch up everything and I'll start popping a pill to keep the leak closed. I'm truly blessed for all those involved in the blood business.\n"+
        "One life spared while bringing another life into the world\n" +
                "by Christina \n" +
                "This story happened 2 1/2 months earlier than it was suppose to, I went into premature labor with my son on March 18, 2015.... I had 4 other kids at home getting ready for school. I called my husband who was on an audit for the U.S Coast Guard. He sped home to rush me to the hospital. We dropped half the kids off at school and the other half at a friends house... I got to the hospital and started to bleed uncontrollably, I said goodbye to my husband as they Literally RAN me to the Operating room. I thought that was it, there was no way that much blood can come out of one person and survive...... thanks to donated blood my WHOLE life was saved.... apparently my entire body is filled with new blood, DONATED blood. My family didn't loose a mother, sister, daughter, and wife. 3 1/2 hours later I was out of surgery and thanking doctors left and right... What a scary experience but it was one that has made me realize the importance of donating blood. Blood donation saved my kids from being motherless and my husband from being a widower, not to mention saved my life! I am so thankful for blood donors, I owe my life to them.");
        tipstextMap.put("Frequently Asked Questions","1. What happens to my donated blood?\n" +
                " Each unit of blood collected will be examined for 5 transfusion-transmissible infectious diseases, namely: HIV, Malaria, Syphillis, Hepatitis B, and Hepatitis C before it is transfused to patients.\n" +
                " \n" +
                "2. Is it safe to give blood?\n" +
                " Yes. The Red Cross ensures that donating blood is a safe opportunity to give the gift of life. Each needle used in the procedure is sterile and is disposed after a single use. It is important that all blood donors are in good health, well-rested, and have eaten prior to donation.\n" +
                " \n" +
                "3. When can we donate blood?\n" +
                " A healthy person may donate blood every three months.\n" +
                " \n" +
                "4. Where can I donate blood?\n" +
                " You may come and visit PRC's National Blood Center, Regional Blood Centers, or any of its Blood Services Facilities, nationwide.\n" +
                " \n" +
                "5. Can a person who has a tattoo donate blood?\n" +
                " As long as the tattooing procedure was done aseptically (in a sterile manner), he/ she may donate blood one year after the procedure. This is the same with ear piercing, acupuncture, and other procedures involving needles.\n" +
                " \n" +
                "6. Are the health history questions necessary every time I donate?\n" +
                " To ensure the safest possible blood supply, all donors must undergo the necessary screening every donation. The World Health Organization and the Department of Health require all blood centers to conform to this practice.\n" +
                " \n" +
                "7. What does the term \"donor deferral\" mean?\n" +
                " Individuals disqualified from donating blood are known as \"deferred\" donors. A prospective donor may be deferred at any point during the collection and testing process. Whether or not a person is deferred, temporarily or permanently, will depend on the specific reason for disqualification (i.e. a person may be deferred temporarily because of anemia, a condition that is usually reversible). If a person is to be deferred, his or her mobile is entered into a list of deferred donors maintained by the blood center, often known as the \"deferral registry.\" If a deferred donor attempts to give blood before the end of the deferral period, the donor will not be accepted for donation. Once the reason for the deferral no longer exists and the temporary deferral period has lapsed, the donor may return to the blood bank and be re-entered into the system.\n" +
                " \n" +
                "8. If I was deferred once before, am I still ineligible to donate?\n" +
                " If your deferral is of a permanent nature, you will be informed. Otherwise, the deferral time depends upon the reason for deferral. Prior to each donation, you will be given a mini-physical and medical interview. At that time, it will be determined if you are eligible to donate blood on that particular day.\n" +
                " \n" +
                "9. What are some of the reasons for permanent deferral?\n" +
                " - Hepatitis B or C infection.\n" +
                "- HIV infection.\n" +
                "- Having sexual contact with a person infected with HIV\n" +
                "- Having multiple sex partners/ patronizing sex workers\n" +
                "- Serious chronic illness (heart and lung diseases)\n" +
                " \n" +
                "10. Can a person who just had his/ her tooth extracted donate blood?\n" +
                " He/ She will be temporarily deferred for a year.\n" +
                " \n" +
                "11. If I just received a flu shot, can I donate blood?\n" +
                " Yes. There is no waiting period to donate after receiving a flu shot.\n" +
                " \n" +
                "12. If I have a cold flu, can I donate blood?\n" +
                " In order to donate, blood centers require that you should be generally in good health (symptom-free); thus, it is important that you are feeling well.\n" +
                " \n" +
                "13. Can I still donate if I have high blood pressure?\n" +
                " Yes, if your blood pressure is under control and within the limits set in the donation guidelines.\n" +
                " \n" +
                "14. What if I'm taking aspirin or medication prescribed by my doctor?\n" +
                " Aspirin and Ibuprofen will not affect a whole blood donation. Apheresis platelet donors, however, must not take aspirin or aspirin products 36 hours prior to the donation. Many other medications are acceptable; but it is recommended that you call the blood center ahead of time to inquire about the type of medication you are taking.\n" +
                " \n" +
                "15. What if I have Anemia?\n" +
                " You cannot give blood if you have anemia. However, this can often be a temporary condition. Your hemoglobin level will be tested before you donate, in order to make sure that it is within an acceptable range.\n" +
                " \n" +
                "16. How long does it take to donate blood?\n" +
                " The whole process of donating blood will only take an average of 25 minutes.\n" +
                " \n" +
                "17. Will I put on weight after blood donation?\n" +
                " No. All you put on is the feeling of satisfaction because you have helped someone.\n" +
                " \n" +
                "18. What other types of tests are done on the blood?\n" +
                " Your blood is tested to determine your blood type—classified as A, B, AB, and O—and your Rh factor. The Rh factor refers to the presence or absence of a specific antigen, a substance capable of stimulating an immune response, in the blood; so, you are either Rh positive or Rh negative, meaning you either carry the antigen or you don't. This information is important to know, because your blood type and Rh factor must be compatible with the blood type and Rh factor of the person receiving your blood.\n" +
                " \n" +
                "19. What is the most common blood type?\n" +
                " The approximate distribution of blood types in the Philippines center is as follows (though distribution may be different for specific racial and ethnic groups):\n" +
                " \n" +
                "O Rh-positive --- 44-46 percent\n" +
                "A Rh-positive --- 22-23 percent\n" +
                "B Rh-positive --- 24-25 percent\n" +
                "AB Rh-positive --- 4-6 percent\n" +
                "Rh-negative group --- Less than 1 percent\n" +
                " \n" +
                "20. What fees are associated with blood?\n" +
                " While the donated blood is free, there are significant costs associated with the collection, testing, labeling, preparation of components, and storage of blood. In addition to these, charges are also incurred through recruitment and education of donors, as well as quality assurance. As a result, processing fees are charged to recover these costs. Blood processing fees collected are in conformance with the stipulated allowable fees as mandated by the Department of Health.\n" +
                " \n" +
                "21. What can you do if you aren't eligible to donate?\n" +
                " While a given individual may be unable to donate, he or she may be able to recruit a suitable donor. PRC Blood Banks are always in need of volunteers to assist during blood donations, or to organize mobile blood drives. In addition, monetary donations through the Blood Samaritan Project of the Red Cross are always welcome, to help ensure that blood banks can continue providing safe blood to those in need, most especially to indigent patients.\n" +
                " \n" +
                "22. How can I host a mobile blood donation activity at work, school, church or community?\n" +
                "Kindly refer to the Blood Services Facility near you. Contact the blood center in order to learn more about the requirements.");

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return Utils.onOptionsItemSelected(this,getApplicationContext(),item.getItemId()) || super.onOptionsItemSelected(item);
    }
}
