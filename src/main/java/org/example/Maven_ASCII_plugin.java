package org.example;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Mojo(name = "ASCII-plugin", defaultPhase = LifecyclePhase.COMPILE)
public class Maven_ASCII_plugin extends AbstractMojo {

    @Parameter(property = "choice")
    String choice;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        int rand = getRandomNumber(0, 3);

        if(choice == null) {
            List<String> art = new ArrayList<>();
            art.add("computer.txt");
            art.add("dog.txt");
            art.add("monkey.txt");

            try {
                getLog().info("\n" + IOUtils.toString(getFileAsIOStream(art.get(rand)), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            switch (choice) {
                case "computer" -> {
                    try {
                        getLog().info("\n" + IOUtils.toString(getFileAsIOStream("computer.txt"), StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "dog" -> {
                    try {
                        getLog().info("\n" + IOUtils.toString(getFileAsIOStream("dog.txt"), StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "monkey" -> {
                    try {
                        getLog().info("\n" + IOUtils.toString(getFileAsIOStream("monkey.txt"), StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> throw new IllegalArgumentException("There is no ASCII art with the name: " + choice);
            }
        }
    }

    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
