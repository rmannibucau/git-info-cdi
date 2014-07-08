package com.github.rmannibucau.git.info;

import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.deltaspike.core.impl.config.BaseConfigSource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

import static org.apache.openejb.loader.JarLocation.jarLocation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class GitPropertyFileTest {
    @Deployment
    public static Archive<?> jar() {
        return ShrinkWrap.create(WebArchive.class, "git-info.war")
                .addClass(GitPropertyFile.class)
                .addAsResource("git.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(
                    jarLocation(ConfigProperty.class),
                    jarLocation(BaseConfigSource.class)
                );
    }

    @Inject
    @ConfigProperty(name = "git.remote.origin.url")
    private String gitUrl;

    @Inject
    @ConfigProperty(name = "git.commit.id")
    private String gitCommitId;

    @Inject
    @ConfigProperty(name = "git.branch")
    private String gitBranch;

    @Test
    public void checkValues() {
        assertEquals("master", gitBranch);
        assertEquals("https://github.com/rmannibucau/git-info-cdi.git", gitUrl);
        assertNotNull(gitCommitId);
    }
}
