package ua.org.gostroy.communityJavaProject.core_spring_data.audit;

import org.springframework.data.domain.AuditorAware;

/**
 * Created by Panov Sergey on 10/3/2014.
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return "SYSTEM";
    }
}
