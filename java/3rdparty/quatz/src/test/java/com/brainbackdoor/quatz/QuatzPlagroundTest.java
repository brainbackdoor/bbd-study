package com.brainbackdoor.quatz;

import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

class QuatzPlagroundTest {

    @Test
    void helloJob() throws SchedulerException, InterruptedException {
        JobDetail jobDetail = newJob(HelloJob.class).build();

        Trigger trigger = newTrigger().build();

        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        defaultScheduler.start();
        defaultScheduler.scheduleJob(jobDetail, trigger);
        Thread.sleep(3 * 1000);  // Job이 실행될 수 있는 시간 여유를 준다

        defaultScheduler.shutdown(true);
    }

    @Test
    void helloJob2nd() throws SchedulerException, InterruptedException {
        JobDetail jobDetail = newJob(HelloJob2nd.class).build();

        Trigger trigger = newTrigger().build();

        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        defaultScheduler.start();
        defaultScheduler.scheduleJob(jobDetail, trigger);
        Thread.sleep(3 * 1000);

        defaultScheduler.shutdown(true);
    }

    @Test
    void baseJobTemplateMethod() throws SchedulerException, InterruptedException {
        JobDetail jobDetail = newJob(HelloJob3nd.class).build();

        Trigger trigger = newTrigger().build();

        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        defaultScheduler.start();
        defaultScheduler.scheduleJob(jobDetail, trigger);
        Thread.sleep(3 * 1000);

        defaultScheduler.shutdown(true);
    }

    @Test
    void jobChaining() throws SchedulerException, InterruptedException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("JobName", " Job Chain 1");

        JobDetail jobDetail = newJob(HelloJob4nd.class).usingJobData(jobDataMap).build();
        Trigger trigger = newTrigger().build();

        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        defaultScheduler.start();
        defaultScheduler.scheduleJob(jobDetail, trigger);
        Thread.sleep(3 * 1000);

        defaultScheduler.shutdown(true);
    }

    @Test
    void jobChaingingQueue() throws SchedulerException, InterruptedException {
        // Job 1 구성
        JobDataMap jobDataMap1 = new JobDataMap();
        jobDataMap1.put("JobName", "Job Chain 1");
        JobDetail jobDetail1 = newJob(HelloJob4nd.class).usingJobData(jobDataMap1).build();

        // Job 2 구성
        JobDataMap jobDataMap2 = new JobDataMap();
        jobDataMap2.put("JobName", "Job Chain 2");
        JobDetail jobDetail2 = newJob(HelloJob4nd.class).usingJobData(jobDataMap2).build();

        // Job 3 구성
        JobDataMap jobDataMap3 = new JobDataMap();
        jobDataMap3.put("JobName", "Job Chain 3");
        JobDetail jobDetail3 = newJob(HelloJob4nd.class).usingJobData(jobDataMap3).build();

        // 실행할 모든 Job의 JobDetail를 jobDetail1의 JobDataMap에 담는다.
        List<JobDetail> jobDetailQueue = new LinkedList<>();
        jobDetailQueue.add(jobDetail1);
        jobDetailQueue.add(jobDetail2);
        jobDetailQueue.add(jobDetail3);

        // 주의사항: 아래와 같이 jopDataMap1에 저장하면 반영되지 않는다.
        // jobDataPam1.put("JobDetailQueue", jobDetailQueue);
        // 아래와 같이 jobDetail1에서 getJobDataMap()으로 새로 가져온 JobDataMap에 저장해야 한다.
        jobDetail1.getJobDataMap().put("JobDetailQueue", jobDetailQueue);

        Trigger trigger = newTrigger().build();

        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        defaultScheduler.start();
        defaultScheduler.scheduleJob(jobDetail1, trigger);
        Thread.sleep(3 * 1000);

        defaultScheduler.shutdown(true);
    }
}