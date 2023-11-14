package fi.utu.tech.common;

import java.util.ArrayList;
import java.util.List;

/**
 * You need to modify this file
 */


public class TaskAllocator {

    /**
     * Allocator that creates list of two (2) GradingTask objects with each having half of the given submissions
     * @param submissions The submissions to be allocated
     * @return The two GradingTask objects in a list, each having half of the submissions
     */
    public static List<GradingTask> sloppyAllocator(List<Submission> submissions) {

        List<GradingTask> tasks = new ArrayList<>();

        List<Submission> list1 = new ArrayList<>();
        List<Submission> list2 = new ArrayList<>();

        for (int i = 0; i < submissions.size(); i++){
            if (i % 2 == 0){
                list1.add(submissions.get(i));
            } else {
                list2.add(submissions.get(i));
            }
        }

        GradingTask gradingTask1 = new GradingTask(list1);
        GradingTask gradingTask2 = new GradingTask(list2);
        tasks.add(gradingTask1);
        tasks.add(gradingTask2);

        return tasks;
    }


    
    /**
     * Allocate List of ungraded submissions to tasks
     * @param submissions List of submissions to be graded
     * @param taskCount Amount of tasks to be generated out of the given submissions
     * @return List of GradingTasks allocated with some amount of submissions (depends on the implementation)
     */
    public static List<GradingTask> allocate(List<Submission> submissions, int taskCount) {

        List<List<Submission>> apulista = new ArrayList<>();

        List<GradingTask> tasks = new ArrayList<>();


        for (int i = 0; i < taskCount; i++){
            apulista.add(new ArrayList<Submission>()); // ?
        }

        for (int i = 0; i < submissions.size(); i++){
            int indeksi = i % taskCount;
            apulista.get(indeksi).add(submissions.get(i));
        }
        for (int i = 0; i < taskCount; i++){
            GradingTask gradingTask = new GradingTask(apulista.get(i));
            tasks.add(gradingTask);
        }

        return tasks;
    }
}
