package edu.kit.informatik.junit;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * This is a test suite for JUnit 5 tests. 
 * @author Lucas Alber
 * @version 1.1
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("edu.kit.informatik.junit.suite")
@IncludeClassNamePatterns({"^Test\\d+.*"})
public class AllTests { }
