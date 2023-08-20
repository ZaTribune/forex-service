package com.zatribune.forex.job;

import com.zatribune.forex.service.RatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.BackgroundJob;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@Component
public class TradeRateJob implements CommandLineRunner {


    private final RatesService ratesService;

    //for testing
    @Job(name = JobNames.TRADE_RATES_JOB)
    public void everyMinute() throws IOException {
        log.info("Starting job [{}] recurring every minute - {}",JobNames.TRADE_RATES_JOB, Thread.currentThread().getName());
        ratesService.refresh();
        log.info("Job finished!");
    }

    @Override
    public void run(String... args) {

        BackgroundJob.scheduleRecurrently(JobNames.TRADE_RATES_JOB, "0 */1 * * * *",
                this::everyMinute);
    }
}
