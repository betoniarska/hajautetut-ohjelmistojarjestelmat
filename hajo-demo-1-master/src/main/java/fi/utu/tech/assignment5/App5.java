package fi.utu.tech.assignment5;

import java.util.ArrayList;
import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;


public class App5 {
    public static void main( String[] args )
    {

        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        // Luodaan uusi arviointitehtävä

        List<GradingTask> apu = TaskAllocator.allocate(ungradedSubmissions, 8);

        List<Submission> gradedSubmissions = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // luodaan niin monta säiettä kun listassa apu on GradingTask olioita

        for (int i = 0; i < apu.size(); i++){

            Thread thread = new Thread(apu.get(i));
            threads.add(thread);

            thread.start();

        }

        // odotetaan kaikki säikeet ja lopetetaan samaan aikaan

        for (int i = 0; i < threads.size(); i++){
            try{
                threads.get(i).join();
            } catch(Exception e){

            }
        }

        // lisätään arvostellut gradeSubmission:iin

        for (int j = 0; j < apu.size(); j++){
            for (int i = 0; i < apu.get(j).getLista().size(); i++){

                gradedSubmissions.add(apu.get(j).getLista().get(i));
            }
        }

        // Tulostetaan arvioidut palautukset
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradedSubmissions) {
            System.out.println(gs);
        }

        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);

    }
}
