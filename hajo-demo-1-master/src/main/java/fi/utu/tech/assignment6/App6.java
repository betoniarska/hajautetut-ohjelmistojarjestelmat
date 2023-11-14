package fi.utu.tech.assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;

public class App6{
    public static void main(String[] args) {
        // Generoidaan kasa esimerkkitehtäväpalautuksia

        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.UNFAIR);





        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        // Luodaan uusi arviointitehtävä

        List<GradingTask> apu = TaskAllocator.allocate(ungradedSubmissions, 10);

        List<Submission> gradedSubmissions = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // luodaan niin monta säiettä kun listassa apu on GradingTask olioita

        for (int i = 0; i < apu.size(); i++){

            Runnable worker = new GradingTask(apu.get(i).getLista());

            executor.execute(worker);



        }
        executor.shutdown();
        while (!executor.isTerminated()) {
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
