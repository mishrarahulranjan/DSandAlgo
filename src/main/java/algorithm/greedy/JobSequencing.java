package algorithm.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobSequencing {

        static class Job {
            String id;
            int deadline;
            int profit;

            public Job(String id, int deadline, int profit) {
                this.id = id;
                this.deadline = deadline;
                this.profit = profit;
            }

            @Override
            public String toString() {
                return "Job{" +
                        "id=" + id +
                        ", deadline=" + deadline +
                        ", profit=" + profit +
                        '}';
            }
        }

       static  List<Job> printJobScheduling(List<Job> jobList, int time){

            int n = jobList.size();
            Collections.sort(jobList, (a, b) -> b.profit - a.profit);

            List<Job> resultList = new ArrayList<>(time);

            boolean[] placed = new boolean[time];

            for (Job job: jobList) {
                for (int j= Math.min(time - 1, job.deadline - 1);j >= 0; j--) {
                    if(!placed[j]){
                        resultList.add(job);
                        placed[j]= true;
                        break;
                    }
                }
            }

            return resultList;
       }

       public static void main(String [] args){

            List<Job> arr = new ArrayList<Job>();
            arr.add(new Job("a", 2, 45));
            arr.add(new Job("b", 2, 40));
            arr.add(new Job("c", 1, 50));
            arr.add(new Job("d", 4, 10));
            arr.add(new Job("e", 3, 30));

            System.out.println("Following is maximum profit sequence of jobs");
           List<Job> jobList = printJobScheduling(arr,3);
            jobList.stream().forEach(System.out::println);
       }


}
