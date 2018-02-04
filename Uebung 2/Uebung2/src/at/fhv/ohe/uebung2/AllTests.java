package at.fhv.ohe.uebung2;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.fhv.ohe.uebung2_a.RotEncryptionTest;
import at.fhv.ohe.uebung2_b.PostfixCalculatorTest;
import at.fhv.ohe.uebung2_c.IdGeneratorTest;


@RunWith(Suite.class)
@SuiteClasses({ RotEncryptionTest.class, PostfixCalculatorTest.class, IdGeneratorTest.class })
public class AllTests {

}
