package com;

import com.repository.AgentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    @Scheduled(fixedRate = 300000) // 5 min heartbeat
    public static void heartbeat() {
        AgentRepository agentRepo = AgentRepository.get();

        agentRepo.deleteInactiveAgents();
    }
}
