package com.github.rmannibucau.git.info;

import org.apache.deltaspike.core.api.config.PropertyFileConfig;

public class GitPropertyFile implements PropertyFileConfig {
    @Override
    public String getPropertyFileName() {
        return "git.properties";
    }
}
