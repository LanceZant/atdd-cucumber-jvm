package me.lzant.toys.jumble_jvm;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, snippets = SnippetType.CAMELCASE)
//@CucumberOptions(plugin = {"progress"}, snippets = SnippetType.CAMELCASE)
public class RunCukesTest {
}
